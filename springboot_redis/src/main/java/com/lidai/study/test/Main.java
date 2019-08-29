/*
 * Copyright (C), 2013-2019, 天津大海云科技有限公司
 */
package com.lidai.study.test;


/**
 * @author lidai
 * @date 2019/4/23 11:48
 */
public class Main {
    public static void main(String[] args) throws Exception {

        Class<Person> clazz = Person.class;
        Person person = clazz.newInstance();

    }
}

