/*
 * Copyright (C), 2013-2019, 天津大海云科技有限公司
 */
package com.lidai.study.thread.test1;

/**
 * @author lidai
 * @date 2019/5/5 13:50
 *
 * 正常加锁懒汉式单例
 */
public class Single {

    private static Single signle = null;

    private Single() {
    }

    public synchronized static Single getInstance() {
        if (signle == null) {
            signle = new Single();
        }
        return signle;
    }
}

