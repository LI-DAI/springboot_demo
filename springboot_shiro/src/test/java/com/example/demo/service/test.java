/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.utils.JwtHelper;
import com.github.pagehelper.PageHelper;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lidai
 * @date 2018/10/30 15:31
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class test {

    @Test
    public void test1(){
        User user=new User();
        user.randomSalt();
        System.out.println(user.getSalt());
    }

    /**
     * java 8 flatMap测试
     * 相当于将集合中所有元素取出，转为Stream，在对Stream进行操作
     */
    @Test
    public void test2(){
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        Stream<Integer> outputStream = inputStream.flatMap(childList -> childList.stream()).filter(x->x.equals(3));
        List<Integer> list =outputStream.collect(Collectors.toList());
        System.out.println(list.toString());
    }

    @Test
    public void test3(){
        String token = JwtHelper.createJWT("admin");
        System.out.println(token);

    }

    @Test
    public void test4(){
        String token="eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJ1c2VyIiwiZXhwIjoxNTQxMTkyOTM3LCJpYXQiOjE1NDExNDk3MzcsInVzZXJuYW1lIjoiYWRtaW4ifQ.Sjcq8j1eHeRRlOdgeCb5M-GBiHsk1Ou_ahOkgXlV-0FUsJThCG6zS1KdYe1k_cX5_WGkD4RTCnMaENRZqk8bXw";
        String username =(String) JwtHelper.parseJWT(token).get("username");
        System.out.println(username);
    }


}

