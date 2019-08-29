/*
 * Copyright (C), 2013-2019, 天津大海云科技有限公司
 */
package com.lidai.study.test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lidai
 * @date 2019/4/15 14:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

    public static void main(String[] args) throws InterruptedException {
        //获取当前线程名
//        System.out.println(Thread.currentThread().getName());
//        for (int i = 0; i < 10; i++) {
//            new Thread("线程" + i) {
//                @Override
//                public void run() {
//                    System.out.println("Thread: " + getName() + "running");
//                }
//            }.start();
//        }
        MyThread myThread = new MyThread();
        myThread.start();
        myThread.join(1);
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "主线程第" + i + "次执行");
        }
    }

}

class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(getName() + "子线程第" + i + "次执行");
        }
    }
}