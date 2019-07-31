/*
 * Copyright (C), 2013-2019, 天津大海云科技有限公司
 */
package com.demo.interview.singleton;

/**
 * @author lidai
 * @date 2019/6/18 15:12
 * <p>
 * 饿汉式单例
 */
public class Singleton_2 {

    private Singleton_2() {
    }

    public static final Singleton_2 INSTANCE = new Singleton_2();
}

