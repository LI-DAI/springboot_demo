/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
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
 * @date 2018/11/7 13:52
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class UserServcieTest {
    @Autowired
    private UserService userService;

    @Test
    public void test1(){
        userService.exportExcel();
    }
}

