/*
 * Copyright (C), 2013-2019, 天津大海云科技有限公司
 */
package com.lidai.study.thread.test1;

/**
 * @author lidai
 * @date 2019/5/5 13:54
 * <p>
 * 静态内部类模式单例，推荐使用
 */
public class Single2 {

    private Single2() {
    }

    private static class instance {
        private static Single2 sin = new Single2();
    }

    public static Single2 getInstance() {
        return instance.sin;
    }
}

