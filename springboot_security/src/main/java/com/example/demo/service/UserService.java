/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lidai
 * @date 2018/11/16 11:56
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    /**
     * 通过用户名获取user
     * @param username
     * @return {@link User} user中已包含Role
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=this.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("user does not exists");
        }
        return user;
    }
}

