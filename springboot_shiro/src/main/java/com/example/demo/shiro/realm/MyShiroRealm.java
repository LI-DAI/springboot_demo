/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.shiro.realm;

import com.example.demo.entity.Menu;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.service.MenuService;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import com.example.demo.token.JwtToken;
import com.example.demo.utils.JwtHelper;
import io.jsonwebtoken.Jwt;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lidai
 * @date 2018/10/30 16:54
 */
public class MyShiroRealm extends AuthorizingRealm{

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    /**
     * 授权
     * @param principalCollection 身份集合
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        User user= (User) principalCollection.getPrimaryPrincipal();
        List<Role> roles=roleService.getRolesByUserId(user.getUserId());
        List<Menu> menus=menuService.getMenusByUserId(user.getUserId());
        simpleAuthorizationInfo.addRoles(roles.stream().map(Role::getRoleName).collect(Collectors.toSet()));
        simpleAuthorizationInfo.addStringPermissions(menus.stream().map(Menu::getPerms).collect(Collectors.toSet()));
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     * @param authenticationToken token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String username= (String) authenticationToken.getPrincipal();
        User user =userService.getUserByUsername(username);
        if(user == null){
            throw new UnknownAccountException("用户名或密码错误");
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo(
                user,user.getPassword(), ByteSource.Util.bytes(user.getSalt()),getName()
        );
        Session session=SecurityUtils.getSubject().getSession();
        session.setAttribute("user",user);
        return simpleAuthenticationInfo;
    }

    /**
     * 清理缓存权限
     */
    public void clearCachedAuthorizationInfo() {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }
}

