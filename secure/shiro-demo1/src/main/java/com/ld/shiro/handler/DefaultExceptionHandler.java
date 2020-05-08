/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.ld.shiro.handler;

import com.ld.shiro.entity.common.R;
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
     * 异常处理
     *
     * @param e 异常信息
     * @return
     */
    @ExceptionHandler(value = RuntimeException.class)
    public R<Object> AuthorizationExceptionHandler(Exception e) {
        return R.fail(e.getMessage());
    }
}

