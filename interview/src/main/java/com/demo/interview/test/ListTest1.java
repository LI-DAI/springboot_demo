/*
 * Copyright (C), 2013-2019, 天津大海云科技有限公司
 */
package com.demo.interview.test;

import java.io.InputStream;

/**
 * @author lidai
 * @date 2019/08/26 15:44
 */
public class ListTest1 {

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        Integer one = 128;
        Integer two = 128;
        System.out.println(one);
        System.out.println(two);
        System.out.println(one == two);//false

        Integer three = 100;
        Integer four = 100;
        System.out.println(three == four);//true
    }

}

