/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.handler;

import com.example.demo.entity.Result;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author lidai
 * @date 2018/11/15 11:40
 * 全局异常处理
 */
@RestControllerAdvice
public class DefaultExceptionHandler {

    /**
     * 无权限异常
     * @param e 异常信息
     * @return
     */
    @ExceptionHandler(value= AuthorizationException.class)
    public Result AuthorizationExceptionHandler(Exception e){
        return Result.build().fail("无权限访问："+e.getMessage());
    }
}

