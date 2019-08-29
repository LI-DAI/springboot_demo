/*
 * Copyright (C), 2013-2019, 天津大海云科技有限公司
 */
package com.demo.interview.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author lidai
 * @date 2019/08/27 17:20
 * <p>
 * 这里如果使用volatile则可能出现问题，因为volatile只保证了可见性，不保证i++得原子性
 * 而只使用synchronized则可以得，说明synchronized既保证了可见性又保证了原子性
 */
public class VolatileTest2 {

    volatile int count;

    /*synchronized*/ void m() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public static void main(String[] args) {
        VolatileTest2 v = new VolatileTest2();

        List<Thread> list = new ArrayList<>(15);
        for (int i = 0; i < 10; i++) {
            list.add(new Thread(() -> v.m()));
        }

        list.forEach(thread -> thread.start());

//        list.forEach(t -> {
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });

        try {
            TimeUnit.MICROSECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(v.count);
    }
}

