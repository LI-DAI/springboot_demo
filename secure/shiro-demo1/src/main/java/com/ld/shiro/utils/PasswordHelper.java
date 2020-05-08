/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.ld.shiro.utils;

import com.ld.shiro.entity.manage.User;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @author lidai
 * @date 2018/10/30 17:27
 */
public class PasswordHelper {

    private static final String algorithmName = "md5";
    private static final int hashIterations = 2;

    public static void encryptPassword(User user) {
        String newPassword = encryptPassword(user.getSalt(), user.getPassword());
        user.setPassword(newPassword);
    }

    public static String encryptPassword(String salt, String password) {
        return new SimpleHash(
                algorithmName, password, ByteSource.Util.bytes(salt), hashIterations
        ).toHex();
    }

    public static boolean matchPassword(User user, String password) {
        if (encryptPassword(user.getSalt(), password).equals(user.getPassword())) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        user.randomSalt();
        encryptPassword(user);
        System.out.println(user);
    }
}

