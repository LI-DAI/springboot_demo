/*
 * Copyright (C), 2013-2019, 天津大海云科技有限公司
 */
package com.example.demo.regex;

/**
 * @author lidai
 * @date 2019/6/6 14:52
 */
public class RegexDemo_1 {

    public static void main(String[] args) {
        test_1();
        test_2();
        test_3();
        test_4();
        test_5();
        System.out.println("-----------------------分割线---------------------------");
        test_6();
        test_7();
        test_8();
        test_9();
        test_10();
        System.out.println("-----------------------分割线---------------------------");
        test_11();
    }


    /**
     * 类似 [abc] 匹配单个字符，为a,b或者c
     * 类似 [a-z] 匹配单个字符，a-z,且必须小写,匹配大写为[A-Z] 匹配数字为 [0-9],均为单个字符匹配
     */
    public static void test_1(){
        String str = "acd";
        String regex = "[abc][a-z]";
        System.out.println(str.matches(regex));
    }


    /**
     * 类似 [^abc] 匹配单个字符，不为a,b或者c, ^代表亦或，即相当于‘非’得意思
     * 类似 [a-g[x-z]] 匹配单个字符，a-g或x-z 即取两者并集得任意字符即可
     */
    public static void test_2(){
        String str = "1sc";
        String regex = "[^abc][^0-9][a-g[x-z]]";
        System.out.println(str.matches(regex));
    }


    /**
     * 类似 [a-z&&[^bcd]] 匹配单个字符，取[a-z]和[^bcd]交集
     */
    public static void test_3(){
        String str = "b";
        String regex = "[a-z&&[^bcd]]";
        System.out.println(str.matches(regex));
    }


    /**
     * . 匹配单个字符，可匹配任意字符
     */
    public static void test_4(){
        String str = "b";
        String regex = ".";
        System.out.println(str.matches(regex));
    }

    /**
     * \d 匹配单个字符，任意一个数字，相当于[0-9]
     */
    public static void test_5(){
        String str = "9";
        String regex = "\\d";
        System.out.println(str.matches(regex));
    }

    /**
     * \D 匹配单个字符，任意非数字即可，相当于[^0-9]
     */
    public static void test_6(){
        String str = "_";
        String regex = "\\D";
        System.out.println(str.matches(regex));
    }

    /**
     * \s 匹配空白字符 换行符空白符等 相当于[ \t\n\x0B\f\r]
     */
    public static void test_7(){
        String str = "\n";
        String regex = "\\s";
        System.out.println(str.matches(regex));
    }

    /**
     * \S 匹配非空白字符 ，相当于[^\s]
     */
    public static void test_8(){
        String str = "\n";
        String regex = "\\S";
        System.out.println(str.matches(regex));
    }

    /**
     * \w 匹配所有字母，相当于[a-zA-Z]
     */
    public static void test_9(){
        String str = "d";
        String regex = "\\w";
        System.out.println(str.matches(regex));
    }

    /**
     * \W 匹配非字母 相当于[^\w]或者[^a-zA-Z],匹配单个字符
     */
    public static void test_10(){
        String str = " ";
        String regex = "\\W";
        System.out.println(str.matches(regex));
    }

    public static void test_11(){
        String str = "@";
        String regex = "@";
        System.out.println(str.matches(regex));
    }


}

