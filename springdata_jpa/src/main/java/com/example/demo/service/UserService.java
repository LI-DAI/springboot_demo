/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author lidai
 * @date 2018/10/23 14:18
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public void insertUser(User user) {
        userRepository.save(user);
    }

    /**
     * 根据id获取user
     *
     * @param id
     * @return
     */
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    /**
     * 根据用户名获取user
     *
     * @param username
     * @return
     */
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}

