/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.ld.shiro.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author lidai
 * @date 2018/11/2 17:12
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public JwtToken(){}

    public JwtToken(String token){
        this.token=token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

}

