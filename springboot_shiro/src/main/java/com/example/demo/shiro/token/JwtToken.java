/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.shiro.token;

import org.apache.shiro.authc.HostAuthenticationToken;

/**
 * @author lidai
 * @date 2018/12/28 11:57
 */
public class JwtToken implements HostAuthenticationToken {

    private String token;
    private String host;

    public JwtToken() {
    }

    public JwtToken(String token, String host) {
        this.token = token;
        this.host = host;
    }

    @Override
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return Boolean.TRUE;
    }
}

