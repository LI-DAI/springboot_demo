package com.ld.shiro.service;

import com.ld.shiro.entity.manage.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author ld
 * @Date 2020/4/18 16:29
 */
public interface OnlineUserService {

    /**
     * 判断是否已存在登陆用户
     *
     * @param username
     * @param token
     */
    void checkOnlineUser(String username, String token);

    /**
     * 保存登陆用户
     *
     * @param user
     * @param token
     * @param request
     */
    void saveOnlineUser(User user, String token, HttpServletRequest request);
}
