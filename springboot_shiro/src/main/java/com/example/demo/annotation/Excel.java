/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lidai
 * @date 2018/11/7 14:37
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Excel {

    /**
     * 字段名
     */
    String name() default "";

}

