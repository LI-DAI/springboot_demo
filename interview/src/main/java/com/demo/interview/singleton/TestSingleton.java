/*
 * Copyright (C), 2013-2019, 天津大海云科技有限公司
 */
package com.demo.interview.singleton;

/**
 * @author lidai
 * @date 2019/6/18 15:36
 */
public class TestSingleton {

    public static void main(String[] args) {
//        Singleton_3 singleton_3  = Singleton_3.INSTATNCE;
//        System.out.println(singleton_3);


        for(int i=0;i<3;i++){
            new Thread(()-> System.out.println(Singleton_1.getInstance().toString())).start();
        }
    }
}

