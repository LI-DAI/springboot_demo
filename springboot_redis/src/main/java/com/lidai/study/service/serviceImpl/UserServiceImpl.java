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
import java.util.List;
import java.util.UUID;

/**
 * @author lidai
 * @date 2018/10/18 15:23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    //redis中键值为user1
//    @Cacheable("user1")   //测试拦截需要关闭缓存
    @Override
    public User getUserById(String id){
        User user = userMapper.getUserById(id);
        System.out.println("-----------------无缓存，走数据库--------------------"+user.toString());
        return user;
    }

    @Override
    public List<User> getAll() {
        return null;
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

