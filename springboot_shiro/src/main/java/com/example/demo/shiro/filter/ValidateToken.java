/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.shiro.filter;

import com.example.demo.token.JwtToken;
import com.example.demo.utils.JwtHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author lidai
 * @date 2018/11/16 17:00
 */
public class ValidateToken {

    public boolean validateToken(String token){
        AuthenticationToken jwtToken=createToken(token);
        try{
            SecurityUtils.getSubject().login(jwtToken);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }
    }

    public AuthenticationToken createToken(String token){
        return new JwtToken(token);
    }
}

