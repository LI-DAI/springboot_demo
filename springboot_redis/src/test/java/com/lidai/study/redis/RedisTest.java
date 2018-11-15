/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.lidai.study.redis;

import com.lidai.study.Entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @author lidai
 * @date 2018/10/9 17:14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test1(){
        stringRedisTemplate.opsForValue().set("aaa","111");
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }

    @Test
    public void test2() throws InterruptedException {
        User user=new User("111","lidai","123456");
        ValueOperations<String,User> operations=redisTemplate.opsForValue();
        operations.set("com.test",user);
        operations.set("com.test.fff",user,1, TimeUnit.SECONDS);//设置过期时间1秒
        Thread.sleep(1000);
        boolean exists=redisTemplate.hasKey("com.test.fff");
        if(exists)
            System.out.println("exists is true");
        else
            System.out.println("exists is false");

    }
}

