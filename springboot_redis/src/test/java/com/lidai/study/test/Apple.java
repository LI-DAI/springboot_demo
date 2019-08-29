/*
 * Copyright (C), 2013-2019, 天津大海云科技有限公司
 */
package com.lidai.study.test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author lidai
 * @date 2019/3/4 16:34
 */
public class Apple extends Fruit {

    public void eat(){
        Map<String,String> map = new HashMap<>();
        map.put("test","value");
        map.put("test2","value2");
        map.put("test3","value3");
        for(int i=0;i<10000000;i++){
            map.put("test"+i,"value"+i);
        }
        Long startTime1 = LocalDateTime.now().toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        //第一种遍历
        Iterator<Map.Entry<String,String>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String,String> entry =iterator.next();
            String key  = entry.getKey();
            String value = map.get(key);
            System.out.println(key+"---"+value);
        }
        Long endTime1 = LocalDateTime.now().toInstant(ZoneOffset.ofHours(8)).toEpochMilli();


        Long startTime2 = LocalDateTime.now().toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        //第二种遍历
        map.forEach((key,value)-> System.out.println(key+"---"+value));
        Long endTime2 = LocalDateTime.now().toInstant(ZoneOffset.ofHours(8)).toEpochMilli();


        Long startTime3 = LocalDateTime.now().toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        //第三种遍历
        for(String key:map.keySet()){
            System.out.println(key+"--"+map.get(key));
        }
        Long endTime3 = LocalDateTime.now().toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        System.out.println("=============================================="+(endTime1-startTime1));
        System.out.println("=============================================="+(endTime2-startTime2));
        System.out.println("=============================================="+(endTime3-startTime3));
        System.out.println("苹果好吃");
    }

    public static void main(String[] args) {
        Apple apple = new Apple();
        apple.eat();
    }
}

