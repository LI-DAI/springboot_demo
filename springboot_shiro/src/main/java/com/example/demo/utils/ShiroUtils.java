/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.utils;

import com.example.demo.entity.User;
import org.apache.shiro.SecurityUtils;

/**
 * @author lidai
 * @date 2018/12/24 16:49
 */
public class ShiroUtils {

    public static User getUser() {
        return (User) SecurityUtils.getSubject().getPrincipal();
    }

//    public static void clearCachedAuthorizationInfo() {
//        RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
//        CustomShiroRealm realm = (CustomShiroRealm) rsm.getRealms().iterator().next();
//        realm.clearCachedAuthorizationInfo();
//    }

}

