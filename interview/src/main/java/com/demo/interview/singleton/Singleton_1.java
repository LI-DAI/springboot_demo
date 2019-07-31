/*
 * Copyright (C), 2013-2019, 天津大海云科技有限公司
 */
package com.demo.interview.singleton;

/**
 * @author lidai
 * @date 2019/6/18 15:09
 * <p>
 * 懒汉式单例
 */
public class Singleton_1 {

    private static Singleton_1 instance;

    private Singleton_1() {
    }

    public static synchronized Singleton_1 getInstance() {
        if (null == instance) {
            instance = new Singleton_1();
        }
        return instance;
    }
}

