/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.controller;

import com.example.demo.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * @author lidai
 * @date 2018/10/30 18:00
 */
//@Controller
//public class LoginController {
//
//    @GetMapping("/login")
//    public String login(){
//        return "/login";
//    }

//    @PostMapping("/login")
//    public String login(@RequestBody User user){
//        Assert.notNull(user.getUsername(),"username不能为空");
//        Assert.notNull(user.getPassword(),"password不能为空");
//        UsernamePasswordToken upToken=new UsernamePasswordToken(user.getUsername(),user.getPassword());
//
//        Subject subject= SecurityUtils.getSubject();
//        if(subject==null){
//            throw new RuntimeException("登陆异常");
//        }
//        try{
//            subject.login(upToken);
//        }catch (Exception e){
//            e.printStackTrace();
//            return "login";
//        }
//        return "index";
//    }


//}

