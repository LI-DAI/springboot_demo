/*
 * Copyright (C), 2013-2019, 天津大海云科技有限公司
 */
package com.demo.interview.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author lidai
 * @date 2019/08/27 14:47
 * <p>
 * volatile 保持线程之间的可见性，禁止指令重排序
 * <p>
 * cpu处理running的时候回将值存到自己的缓冲区中去，主线程更改了running的值之后，对第一个线程并不透明，
 * 这时并没有从主cpu中刷新值（非常忙得时候不会刷新，如果在while 中sleep一段时间就可能会刷新）
 * 而volatile得意思就是当这个值修改之后，就会通知线程做出修改缓冲区值
 */
public class VolatileTest {

    volatile boolean running = true;

    void m() {
        System.out.println("start");
        while (running) {
            /*try {
                TimeUnit.MICROSECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
        System.out.println("end");
    }

    public static void main(String[] args) {
        VolatileTest v = new VolatileTest();
        new Thread(v::m).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        v.running = false;
    }
}

