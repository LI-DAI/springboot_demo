/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.jsonTest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author lidai
 * @date 2018/10/30 9:54
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class test {

    //json字符串--简单型
    private static final String  JSON_OBJ_STR = "{\"userId\":\"111\",\"username\":\"root\",\"password\":\"123456\"}";
    //json字符串--数组类型
    private static final String  JSON_ARRAY_STR = "[{\"userId\":\"111\",\"username\":\"root1\",\"password\":\"123456\"},{\"userId\":\"222\",\"username\":\"root2\",\"password\":\"654321\"}]";
    //json字符串--复杂类型
    private static final String  COMPLEX_JSON_STR = "{\"userId\":\"111\",\"username\":\"test\",\"password\":\"123456\",\"roleSet\":[{\"roleId\":\"gggg\",\"roleName\":\"common\",\"remark\":\"common\"},{\"roleId\":\"hfdkah\",\"roleName\":\"admin\",\"remark\":\"admin\"}]}";

    /**
     * Json字符串转为JsonObject
     */
    @Test
    public void JsonStringToJsonObject(){
        JSONObject jsonObject=JSONObject.parseObject(JSON_OBJ_STR);
        log.info("userId：{},username：{}，password：{}",jsonObject.getString("userId"),jsonObject.get("username"),jsonObject.get("password"));
    }

    /**
     * Json字符串转为JsonArray
     */
    @Test
    public void JsonStringToJsonArray(){
        JSONArray jsonArray=JSONObject.parseArray(JSON_ARRAY_STR);
        for(Object obj:jsonArray){
            JSONObject jsonObject=(JSONObject)obj;
            log.info("userId：{},username：{}，password：{}",jsonObject.getString("userId"),jsonObject.getString("username"),jsonObject.getString("password"));
        }
    }

    /**
     * Json字符串转为JsonObject,复杂类型
     */
    @Test
    public void JsonStringToJsonComplex(){
        JSONObject jsonObject=JSONObject.parseObject(COMPLEX_JSON_STR);
        log.info("userId：{}",jsonObject.getString("userId"));
        log.info("username：{}",jsonObject.getString("username"));
        log.info("password：{}",jsonObject.getString("password"));
        JSONArray roleSet=jsonObject.getJSONArray("roleSet");
        for(Object obj:roleSet){
            JSONObject object=(JSONObject)obj;
            log.info("roleId：{},roleName:{},remark:{}",object.getString("roleId"),object.getString("roleName"),object.getString("remark"));
        }
    }

    /**
     * Json字符串转为JavaBean
     */
    @Test
    public void JsonStringToJavaBean(){
        User user=JSONObject.parseObject(COMPLEX_JSON_STR,User.class);
        String jsonStr = JSONObject.toJSONString(user);
        System.out.println(jsonStr);
        log.info("user:{}",user.toString());
    }

}

