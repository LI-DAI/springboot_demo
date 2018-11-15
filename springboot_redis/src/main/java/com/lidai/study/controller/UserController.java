/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.lidai.study.controller;

import com.lidai.study.Entity.User;
import com.lidai.study.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lidai
 * @date 2018/11/15 15:47
 */
@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id){
        System.out.println("进入controller");
        return userService.getUserById(id);
    }
}

