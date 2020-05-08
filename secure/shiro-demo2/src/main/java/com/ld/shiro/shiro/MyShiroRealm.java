package com.ld.shiro.shiro;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.ld.shiro.entity.vo.LoginUser;
import com.ld.shiro.utils.JwtUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author ld
 * @Date 2020/4/27 14:03
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private PermissionService permissionService;

    @Override
    public Class getAuthenticationTokenClass() {
        return JwtToken.class;
    }

    /**
     * 授权
     *
     * @param principalCollection 身份集合
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        LoginUser user = (LoginUser) principalCollection.getPrimaryPrincipal();
        String userId = user.getUserId();
        List<Map<String, Object>> roles = permissionService.getRolesByUserId(userId);
        if (roles != null && roles.size() > 0) {
            simpleAuthorizationInfo.addRoles(roles.stream().map(map -> String.valueOf(map.get("roleName"))).collect(Collectors.toSet()));
        }
        List<Map<String, Object>> menus = permissionService.getMenusByUserId(userId);
        if (menus != null && menus.size() > 0) {
            simpleAuthorizationInfo.addStringPermissions(menus.stream().map(map -> String.valueOf(map.get("perms"))).collect(Collectors.toSet()));
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     *
     * @param authenticationToken token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken token = (JwtToken) authenticationToken;
        String jwtToken = token.getToken();
        Map<String, Object> map = JwtUtil.parseJwtToken(jwtToken);
        LoginUser loginUser = BeanUtil.mapToBean(map, LoginUser.class, CopyOptions.create());
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(loginUser, token.getCredentials(), getName());
        return simpleAuthenticationInfo;
    }

}
