/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.lidai.study.service.serviceImpl;

import com.lidai.study.Entity.User;
import com.lidai.study.dao.UserMapper;
import com.lidai.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @author lidai
 * @date 2018/10/18 15:23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServcieImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    //redis中键值为user1
    @Cacheable("user1")
    public User getUserById(String id){
        User user = userMapper.getUserById(id);
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        return user;
    }

    public String getSessionId(HttpSession session){
        UUID uuid=(UUID)session.getAttribute("uid");
        if(null==uuid){
            uuid=UUID.randomUUID();
            session.setAttribute("uid",uuid);
        }
        return session.getId();
    }
}

