/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lidai
 * @date 2018/11/16 13:37
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public User getByUsername(@PathVariable String username){
        return userService.findByUsername(username);
    }
}

