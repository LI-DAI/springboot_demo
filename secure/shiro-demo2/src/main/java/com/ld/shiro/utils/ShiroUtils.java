package com.ld.shiro.utils;

import com.ld.shiro.entity.vo.LoginUser;
import org.apache.shiro.SecurityUtils;

/**
 * @Author ld
 * @Date 2020/5/5 17:06
 */
public class ShiroUtils {

    public static LoginUser getUserInfo() {
        return (LoginUser) SecurityUtils.getSubject().getPrincipal();
    }

    public static String getUsername() {
        return getUserInfo().getUsername();
    }

    public static String getUserId() {
        return getUserInfo().getUserId();
    }

}
