/*
 * Copyright (C), 2013-2019, 天津大海云科技有限公司
 */
package com.demo.interview.string;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author lidai
 * @date 2019/08/26 15:34
 */
public class Test4 {

    public static void main(String[] args) {
        String s1 = "hello";
        String s2= "world";
        String s3= "helloworld";
        String s4=s1+s2;
        System.out.println(s4==s3);

        AtomicLong al = new AtomicLong();
        al.addAndGet(1);
    }

}

