/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.shiro;

import com.example.demo.entity.User;
import com.example.demo.shiro.realm.MyShiroRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;

/**
 * @author lidai
 * @date 2018/10/31 17:08
 */
public class ShiroUtils {

    public static User getUser(){
        User user= (User) SecurityUtils.getSubject().getPrincipal();
        return user;
    }

    public static void clearCachedAuthorizationInfo() {
        RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        MyShiroRealm realm = (MyShiroRealm) rsm.getRealms().iterator().next();
        realm.clearCachedAuthorizationInfo();
    }
}

