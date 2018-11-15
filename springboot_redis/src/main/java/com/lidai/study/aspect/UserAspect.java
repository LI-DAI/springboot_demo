/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.lidai.study.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author lidai
 * @date 2018/10/18 14:28
 */
@Aspect
@Component
public class UserAspect {
    @Pointcut("execution(* com.lidai.study.service.serviceImpl.UserServcieImpl.*(..))")
    public void getUserById(){}

    @Before("getUserById()")
    public void before(){
        System.out.println("即将开会，准备一下");
    }

    @After("getUserById()")
    public void after(){
        System.out.println("会议结束，收拾椅子");
    }
}

