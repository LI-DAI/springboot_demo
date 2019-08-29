/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.shiro.realm;


import com.example.demo.entity.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lidai
 * @date 2018/12/24 15:37
 */
public class CustomShiroRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(CustomShiroRealm.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public Class getAuthenticationTokenClass() {
        return super.getAuthenticationTokenClass();
    }

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        User user = userService.getUserByUsername(upToken.getUsername());
        if (null == user) {
            throw new UnknownAccountException("用户名或密码错误");
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                user,
                user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()),
                getName());

        return simpleAuthenticationInfo;
    }
}

