/*
 * Copyright (C), 2013-2019, 天津大海云科技有限公司
 */
package com.example.demo.regex;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lidai
 * @date 2019/6/6 15:23
 */
@SuppressWarnings("all")
public class RegexDemo_2 {

    public static void main(String[] args) {
        test_1();
        test_2();
        test_3();
        test_4();
        test_5();
        test_6();
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

    /**
     * 为了可以让规则得结果被宠用，正则引入了组，可以将规则封装成一个组用()表示，组得出现都由编号，默认从1开始
     * 想要以有得组可以使用 \n（n是组得编号）获取
     */
    public static void test_4(){
        String str = "fsdjxxxuiroyyjflsuerzzjkhkjh";
        String regex = "(.)\\1+";
        System.out.println(Arrays.toString(str.split(regex)));
    }

    /**
     * 将叠词替换为单个字符 zzzz->z
     * $n(n为组的编号) 获取组的结果
     */
    public static void test_5(){
        String str = "fsdjxxxxxxxxxxxxuiroyyyyyyyyyyjflsuerzzzzzzzzzzzzzjkhkjh";
        String regex = "(.)\\1+";
        String newStr = str.replaceAll(regex,"$1");
        System.out.println(newStr);
    }

    /**
     * 去除符合规则的字符串
     */
    public static void test_6(){
        String str = "fsdjxxxxxxxxxxxxuiroyyyyyyyyyyjflsuerzzzzzzzzzzzzzjkhkjh";
        String regex = "(.)\\1+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher  = pattern.matcher(str);
        while(matcher.find()){
            System.out.println(matcher.group());
        }
    }




}

