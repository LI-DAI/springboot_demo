/*
 * Copyright (C), 2013-2019, 天津大海云科技有限公司
 */
package com.lidai.study.thread.test2;

import java.util.LinkedList;

/**
 * @author lidai
 * @date 2019/5/5 14:05
 * <p>
 * 生产者消费者模型
 */
public class ProdAndCons {
    private static LinkedList<String> list = new LinkedList();
    private static int count = 0;

    private synchronized void put() {
        while (list.size() == 100) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count++;
        System.out.println("开始生产");
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list.add("生产者--" + count);
        this.notifyAll();
    }

    private synchronized void cons() {
        while (list.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("开始消费");
        count--;
        list.removeFirst();
        this.notifyAll();
    }

    public static void main(String[] args) {
        ProdAndCons pc = new ProdAndCons();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> pc.put()).start();
        }

//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        for (int i = 0; i < 100; i++) {
            new Thread(() -> pc.cons()).start();
        }
    }
}

