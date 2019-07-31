/*
 * Copyright (C), 2013-2019, 天津大海云科技有限公司
 */
package com.demo.interview.string;

/**
 * @author lidai
 * @date 2019/6/18 14:51
 */
public class Test_1 {


    /**
     * 本题考查i++ 和 ++i
     * ++在前，则运算之前就相加
     * ++载后则运算之后才相加
     */
    public static void main(String[] args) {
        int i = 1;
        i = i++;
        int j = i++;
        int k = i + ++i * i++;
        System.out.println("i = " + i);
        System.out.println("j = " + j);
        System.out.println("k = " + k);
    }

}

