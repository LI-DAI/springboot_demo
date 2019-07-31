/*
 * Copyright (C), 2013-2019, 天津大海云科技有限公司
 */
package com.demo.interview.load_sort;

/**
 * @author lidai
 * @date 2019/6/18 16:15
 */
public class Son extends Father {

    private int i = test();

    private static int j = method();

    static {
        System.out.println(6);
    }

    Son() {
        System.out.println(7);
    }

    {
        System.out.println(8);
    }

    public int test() {
        System.out.println(9);
        return 1;
    }

    public static int method() {
        System.out.println(10);
        return 1;
    }

    public static void main(String[] args) {
        Son son1 = new Son();
        System.out.println();
        Son son2 = new Son();//静态资源只加载一次

//        5
//        1
//        10
//        6
//        9
//        3
//        2
//        9
//        8
//        7

//        9
//        3
//        2
//        9
//        8
//        7

/**
 * 首先加载静态资源，先加载父类静态资源，再加载子类静态资源 ，父类子类中同时存在method静态方法,但是静态方法是不能重载的。所以父类加载method,访问的是自己的method方法
 * 加载完成静态资源后，开始加载父类非静态变量，父类非静态代码块，最后才加载父类构造器（构造器一定是最后调用）
 * 加载完成父类的资源，开始加载子类的普通方法，加载顺序和父类
 * 最后，静态资源只加载一次
 */
    }
}

