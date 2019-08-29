/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.shiro.realm;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.shiro.token.JwtToken;
import com.example.demo.shiro.utils.JwtHelper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lidai
 * @date 2018/12/28 14:49
 * <p>
 * 此realm为使用token时对其进行登录认证
 */
public class TokenValidateRealm extends AuthenticatingRealm {

    @Autowired
    private UserService userService;

    /**
     * 调用{@code doGetAuthenticationInfo(AuthenticationToken)}之前会shiro会调用{@code supper.supports(AuthenticationToken)}
     * 来判断该realm是否支持对应类型的AuthenticationToken,如果相匹配则会走此realm
     *
     * @return
     */
    @Override
    public Class getAuthenticationTokenClass() {
        return JwtToken.class;
    }

    /**
     * 使用JwtToken登录的时候并未使用MD5加密，接下来会走到{@link SimpleCredentialsMatcher#doCredentialsMatch(AuthenticationToken, AuthenticationInfo)}进行证书比对
     * 也就是此处传入的{@code Boolean.TRUE}与{@link JwtToken}中获取的证书相比较
     * 之所以不加密是因为无法获取用户加密前的密码，一旦经{@link HashedCredentialsMatcher}进行证书比对则报错
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) token;
        String jwt = (String) jwtToken.getPrincipal();
        String username;
        try {
            username = (String) JwtHelper.parseJWT(jwt).get(JwtHelper.USERNAME);
        } catch (Exception e) {
            throw new AuthenticationException("无效得token");
        }

        User user = userService.getUserByUsername(username);
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                user,
                Boolean.TRUE,
                getName());
        return simpleAuthenticationInfo;
    }

}

