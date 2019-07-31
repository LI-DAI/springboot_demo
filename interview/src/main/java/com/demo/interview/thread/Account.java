package com.demo.interview.thread;

import java.util.concurrent.TimeUnit;

/**
 * @auhtor lidai
 * @date
 *
 * 读写操作必须全都加锁，否则可能会造成脏读问题
 */
public class Account {

    String name;
    Double balance;

    public synchronized void set(String name, Double balance) {
        this.name = name;

        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(2));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.balance = balance;
    }

    public /*synchronized*/ Double get(String name) {
        return this.balance;
    }


    public static void main(String[] args) {
        Account a = new Account();

        new Thread(()->a.set("小明",1000d)).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(a.get("小明"));

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(a.get("小明"));
    }
}
