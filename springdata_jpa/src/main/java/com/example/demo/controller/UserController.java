/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @author lidai
 * @date 2018/10/23 14:18
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> findAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public Optional<User> findById(@PathVariable String id) {
        Optional<User> user = userService.findById(id);
        return user;
    }

}

