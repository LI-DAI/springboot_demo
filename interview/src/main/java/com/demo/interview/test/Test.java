/*
 * Copyright (C), 2013-2019, 天津大海云科技有限公司
 */
package com.demo.interview.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lidai
 * @date 2019年07月31日 17:51
 */
public class Test {

    public static void main(String[] args) {
//        HashMap<String, Object> map = new HashMap<>(16);
//        map.put("test1", "test1");
//
//        System.out.println(17 & 15);
//        System.out.println(17 % 16);

//        Test test = new Test();
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> test.eat()).start();
//        }

//        String[] str = {"1","3","2"};
//        List<String> list = new ArrayList<>(16);
//        Arrays.stream(str).forEach(list::add);
//        list.remove(1);
//        System.out.println(list);

        String s1 = new String("lidai");
        String s2 = new String("lidai");

        System.out.println(s1.equals(s2));
        System.out.println(s1==s2);

    }


    public void eat() {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            System.out.println("eating....");
            System.out.println(1 / 0);
        } catch (Exception e) {
            System.out.println("exception has occur..");
        }

//        lock.notify();

    }

}

