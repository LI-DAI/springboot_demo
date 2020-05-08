/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.ld.shiro.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lidai
 * @date 2018/11/2 16:21
 */
public class JwtUtil {

    /**
     * 签发者
     */
    private static final String ISSUER = "user";

    /**
     * 密钥
     */
    private static final String SECRET = "secret";

    /**
     * 过期时间3小时
     */
    private static final Long EXPIRITION = 3 * 60 * 60 * 1000L;

    /**
     * 创建jwt
     *
     * @param username
     * @return
     */
    public static String generateJwtToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        Long currentTimeMillis = System.currentTimeMillis();
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setIssuer(ISSUER)
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .setIssuedAt(new Date())
                .setExpiration(new Date(currentTimeMillis + EXPIRITION));
        return builder.compact();
    }


    /**
     * 创建jwt
     *
     * @param map
     * @return
     */
    public static String generateJwtToken(Map<String, Object> map) {
        return generateJwtToken(map, EXPIRITION);
    }

    public static String generateJwtToken(Map<String, Object> map, Long expire) {
        String token = Jwts
                .builder()
                .setSubject(ISSUER)
                .setIssuer(ISSUER)
                .setClaims(map)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();
        return token;
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     */
    public static Map<String, Object> parseJwtToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    /**
     * 是否过期
     *
     * @param token
     * @return
     */
    public static boolean isExpiration(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        return claims.getExpiration().before(new Date());
    }

    static int[] test = {-100, -200, -300};

    public static void main(String[] args) {
        System.out.println(Arrays.toString(test));
    }

}

