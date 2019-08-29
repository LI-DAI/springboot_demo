/*
 * Copyright (C), 2013-2019, 天津大海云科技有限公司
 */
package com.demo.interview.string;

import java.util.Arrays;

/**
 * @author lidai
 * @date 2019/6/18 16:48
 */
public class Test_3 {
    /**
     * 首先 要明白对象和常量的存储情况
     *
     * 基础类型 i=1 存在栈内存中，str=hello 存在常量池中，在栈中指向了此常量池地址，num=200，arr,data 均存在堆内存中，也在栈内存中存储了堆内存地址
     *
     * 实参为基本数据类型的时候传递的是数据值，实参为引用数据类型的时候传递的是地址值
     *
     * String以及包装类对象不可变，对String对象的拼接将会产生新的对象
     *
     */
    public static void main(String[] args) {
        int i = 1;
        String str = "hello";
        Integer num = 200;
        int[] arr = {1, 2, 3, 4, 5};
        MyData data = new MyData();

        change(i, str, num, arr, data);

        System.out.println("i = " + i);
        System.out.println("str = " + str);
        System.out.println("num = " + num);
        System.out.println("arr = " + Arrays.toString(arr));
        System.out.println("data.a = " + data.a);

    }

    public static void change(int j, String str, Integer num, int[] arr, MyData data) {
        j += 1;
        str += "world";
        num += 1;
        arr[0] += 1;
        data.a += 1;
    }

//    i = 1
//    str = hello
//    num = 100
//    arr = [2, 2, 3, 4, 5]
//    data.a = 11
}

class MyData {
    int a = 10;
}

