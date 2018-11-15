/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo;

import com.example.demo.enums.test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lidai
 * @date 2018/10/25 15:17
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestEnum {

    @Test
    public void test1(){

        for (test t:test.values()) {
            System.out.println(t.getName()+":"+t.getValue());
        }
        System.out.println(test.RED.getName().equals("RED"));
        System.out.println(test.RED.getName()=="RED");
    }

}

