/*
 * Copyright (C), 2013-2019, 天津大海云科技有限公司
 */
package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lidai
 * @date 2019年07月29日 10:39
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ATest {

    @Autowired
    private AMapper aMapper;

    @Test
    public void test_1() {
        A a = new A();
        a.setId(1L);
        a.setName("test");
        a.setDescription("test");
        a.setMoney(1000d);
        aMapper.insertSelective(a);
    }

}

