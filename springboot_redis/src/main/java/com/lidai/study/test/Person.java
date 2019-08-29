/*
 * Copyright (C), 2013-2019, 天津大海云科技有限公司
 */
package com.lidai.study.test;

/**
 * @author lidai
 * @date 2019/5/23 16:47
 */
public class Person {

    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private String show(String test) {
        return "反射测试：" + test;
    }
}

