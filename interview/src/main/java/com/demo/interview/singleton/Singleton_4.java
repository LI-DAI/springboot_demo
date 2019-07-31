/*
 * Copyright (C), 2013-2019, 天津大海云科技有限公司
 */
package com.demo.interview.singleton;

/**
 * @author lidai
 * @date 2019/6/18 15:57
 * <p>
 * 静态内部类不会随着外部类的初始化而初始化，它需要单独去加载和初始化
 */
public class Singleton_4 {

    private Singleton_4() {
    }

    private static class Inner {
        private static final Singleton_4 INSTANCE = new Singleton_4();
    }

    public static Singleton_4 getInstance() {
        return Inner.INSTANCE;
    }

}

