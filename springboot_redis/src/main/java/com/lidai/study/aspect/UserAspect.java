/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.lidai.study.aspect;

import com.lidai.study.Entity.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author lidai
 * @date 2018/10/18 14:28
 */
@Aspect
@Component
public class UserAspect {
//    @Pointcut("execution(* com.lidai.study.service.serviceImpl.UserServcieImpl.*(..))")
//    public void getUserById(){}
//
//    @Before("getUserById()")
//    public void before(){
//        System.out.println("即将开始拦截处理");
//    }
//
//    @After("getUserById()")
//    public void after(){
//        System.out.println("拦截处理结束");
//    }

    @Around("execution(* com.lidai.study.service.serviceImpl.UserServiceImpl.*(..))")
    public Object userAspectTest(ProceedingJoinPoint point) throws Throwable {
        Long startTime=new Date().getTime();
        System.out.println("即将开始拦截处理，拦截时间为："+startTime);
        Object object =point.proceed();
        System.out.println("拦截处理结束，耗时时间为："+(new Date().getTime()-startTime));
        return object;
    }
}

