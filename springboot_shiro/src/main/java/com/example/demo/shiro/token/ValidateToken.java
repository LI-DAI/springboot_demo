/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.shiro.token;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletRequest;

/**
 * @author lidai
 * @date 2018/12/28 13:37
 */
public class ValidateToken {

    public boolean validateToken(String token, ServletRequest request) {
        AuthenticationToken authenticationToken = this.createToken(token, request);
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.login(authenticationToken);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public AuthenticationToken createToken(String token, ServletRequest request) {
        return new JwtToken(token, request.getRemoteHost());
    }
}

