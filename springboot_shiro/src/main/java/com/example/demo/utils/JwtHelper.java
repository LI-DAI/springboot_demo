/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lidai
 * @date 2018/11/2 16:21
 */
public class JwtHelper {

    private static final String ISSUER="user";//签发者

    private static final String SECRET="secretTest";//密钥

    private static final Long TTLMillis=12*60*60*1000L;//过期时间12小时

    /**
     * 创建jwt
     * @param username
     * @return
     */
    public static String createJWT(String username){
        Map<String,Object> claims=new HashMap<>();
        claims.put("username",username);
        Long currentTimeMillis=System.currentTimeMillis();
        JwtBuilder builder= Jwts.builder()
                .setClaims(claims)
                .setIssuer(ISSUER)
                .signWith(SignatureAlgorithm.HS512,SECRET.getBytes())
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis+TTLMillis));
        return builder.compact();
    }

    /**
     * 解析jwt
     * @param jwt
     * @return
     */
    public static Claims parseJWT(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }

}

