/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.shiro.filter;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Result;
import com.example.demo.enums.ResultStatus;
import com.example.demo.utils.JwtHelper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lidai
 * @date 2018/11/8 17:35
 */
public class JwtFilter extends AuthenticatingFilter {

    private static final String AUTHORIZATION = "Authorization";

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        return super.executeLogin(request, response);
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        String username = null;
        String password = null;
        if (servletRequest.getContentType().contains(MediaType.APPLICATION_JSON_VALUE)) {
            //post登陆
            try (BufferedReader reader = servletRequest.getReader()) {
                //获取请求体
                String jsonBody = reader.lines().reduce(String::concat).orElseThrow(IllegalArgumentException::new);
                JSONObject jsonObject = JSONObject.parseObject(jsonBody);
                username = jsonObject.getString("username");
                password = jsonObject.getString("password");
                if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
                    return createToken(username, password, servletRequest, servletResponse);
                }
            }
        } else {
            //表单登陆
            username = servletRequest.getParameter("username");
            password = servletRequest.getParameter("password");
            if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
                return createToken(username, password, servletRequest, servletResponse);
            }
        }
        throw new IllegalStateException("请求Content-Type错误");
    }

    /**
     * response返回false 防止filterChain继续执行
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        String jwtToken = JwtHelper.createJWT((String)token.getPrincipal());
        Map<String,Object> map=new HashMap<>();
        map.put("token",jwtToken);
        setResponse(Result.build().success("登陆成功",map),response);
        return false;
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        setResponse(Result.build().fail("登陆失败"),response);
        return super.onLoginFailure(token, e, request, response);
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
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        String token = httpServletRequest.getHeader(AUTHORIZATION);

        if (StringUtils.isEmpty(token)) {
            return false;
        } else {
            try {
                JwtHelper.parseJWT(token);
            } catch (Exception e) {
                throw new AuthenticationException("Token验证失败", e);
            }
        }
        if (isLoginRequest(request, response)) {
            //进入onAccessDenied方法执行登陆操作
            return false;
        }
        return true;
    }

    /**
     * 不允许通过之后处理方法
     *
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        if (isLoginRequest(servletRequest, servletResponse)) {
            if(!WebUtils.toHttp(servletRequest).getMethod().equalsIgnoreCase("post")){
                throw new RuntimeException("你不用POST请求我就不给你过...");
            }
            return executeLogin(servletRequest, servletResponse);
        }
        Map<String,Object> map=new HashMap<>();
        map.put("result",ResultStatus.UNAUTHENTICATED.message());
        setResponse(Result.build().unauthenticated(),servletResponse);
        return false;
    }

    private void setResponse(Result result , ServletResponse response){
        //解决中文乱码
        response.setContentType("application/json;charset=UTF-8");
        try {
            response.getWriter().write(JSONObject.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
            return ;
        }
    }
}

