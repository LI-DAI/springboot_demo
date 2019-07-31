/*
 * Copyright (C), 2013-2019, 天津大海云科技有限公司
 */
package com.demo.interview.load_sort;

/**
 * @author lidai
 * @date 2019/6/18 16:14
 */
public class Father {

    private int i = test();

    private static int j = method();

    static {
        System.out.println(1);
    }

    Father() {
        System.out.println(2);
    }

    {
        System.out.println(3);
    }

    public int test() {
        System.out.println(4);
        return 1;
    }

    public static int method() {
        System.out.println(5);
        return 1;
    }

}

