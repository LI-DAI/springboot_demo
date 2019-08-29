/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.shiro.filter;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Result;
import com.example.demo.shiro.token.ValidateToken;
import com.example.demo.shiro.utils.JwtHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lidai
 * @date 2018/12/25 15:07
 */
@Component
@Slf4j
public class CustomAuthenticationFilter extends AuthenticatingFilter {

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String TOKEN = "Token";

    private ValidateToken validateToken;

    public CustomAuthenticationFilter() {
        this.validateToken = new ValidateToken();
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        String username;
        String password;
        if (servletRequest.getContentType().equals(MediaType.APPLICATION_JSON_VALUE) || servletRequest.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE)) {
            //非表单登录
            try (BufferedReader reader = servletRequest.getReader()) {
                String body = reader.lines().reduce(String::concat).orElseThrow(IllegalArgumentException::new);
                JSONObject object = JSONObject.parseObject(body);
                username = object.getString(USERNAME);
                password = object.getString(PASSWORD);
                if (StringUtils.hasText(username) && StringUtils.hasText(password)) {
                    return createToken(username, password, servletRequest, servletResponse);
                }
            }
        } else {
            //表单登录
            username = servletRequest.getParameter(USERNAME);
            password = servletRequest.getParameter(PASSWORD);
            if (StringUtils.hasText(username) && StringUtils.hasText(password)) {
                return createToken(username, password, servletRequest, servletResponse);
            }
        }
        throw new IllegalArgumentException("请求Content-type错误");
    }

    /**
     * 未通过处理
     *
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (isLoginRequest(request, response)) {
            if (!request.getMethod().equalsIgnoreCase(POST_METHOD)) {
                setResponse(Result.build().fail("Method is not Allowed:" + request.getMethod()), response);
                return false;
            }
            return executeLogin(servletRequest, servletResponse);
        }
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        setResponse(Result.build().unauthenticated(), servletResponse);
        return false;
    }

    /**
     * 是否允许通过
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        if (isLoginRequest(request, response)) {
            return false;
        }
        String jwtToken = httpServletRequest.getHeader(TOKEN);
        if (!StringUtils.hasText(jwtToken)) {
            jwtToken = httpServletRequest.getParameter(TOKEN);
            if (!StringUtils.hasText(jwtToken)) {
                return false;
            }
        }
        if (!validateToken.validateToken(jwtToken, request)) {
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            setResponse(Result.build().invalidToken(), response);
            return false;
        }
        return true;
    }

    /**
     * 登录成功执行方法
     *
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        String jwtToken = JwtHelper.createJWT((String) token.getPrincipal());
        Map<String, Object> map = new HashMap<>();
        map.put("Token", jwtToken);
        log.info("Token:{}", jwtToken);
        Result result = Result.build().success("登录成功", map);
        setResponse(result, response);
        return false;
    }

    /**
     * 登录失败执行方法
     *
     * @param token
     * @param e
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        Result result = Result.build().fail("登录失败，请检查用户名或密码是否正确");
        setResponse(result, response);
        return false;
    }

    private void setResponse(Result result, ServletResponse response) {
        //解决中文乱码
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter writer = response.getWriter()) {
            writer.write(JSONObject.toJSONString(result));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

}

