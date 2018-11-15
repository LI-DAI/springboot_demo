/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.service;

import com.example.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author lidai
 * @date 2018/10/25 16:07
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class UserServcieTest {

    @Autowired
    private UserService userService;

//    @Test
//    public void testSave(){
//        User user=new User();
//        user.setUserId("2");
//        user.setUsername("test");
//        user.setPassword("test");
//        userService.insertUser(user);
//    }

    @Test
    public void testFindAll(){
        List<User> users = userService.getAllUser();
        log.info("集合为{}",users);
    }

    @Test
    public void testFind(){
        User user=userService.findByUsername("lisi");
    }

}

