/*
 * Copyright (C), 2013-2019, 天津大海云科技有限公司
 */
package com.example.demo.regex;

/**
 * @author lidai
 * @date 2019/6/6 15:23
 */
public class RegexDemo_2 {

    public static void main(String[] args) {
        test_1();
        test_2();
        test_3();
    }

    /**
     * ? 匹配0次或一次
     */
    public static void test_1() {
        String str = "a3";
        String regex = "[a-z]\\d?";
        System.out.println(str.matches(regex));
    }

    /**
     * * 匹配任意次 可以为0次
     * + 匹配一次或多次
     */
    public static void test_2() {
        String str = "a3333333333@";
        String regex = "[a-z]\\d*@";
        System.out.println(str.matches(regex));
    }

    /**
     * {n}   匹配n个
     * {n,}  匹配至少n次
     * {n,m} 匹配n-m次
     */
    public static void test_3() {
        String str = "234";
        String regex = "\\d{3}";
        System.out.println(str.matches(regex));
    }


}

