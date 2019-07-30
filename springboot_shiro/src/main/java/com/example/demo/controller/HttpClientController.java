package com.example.demo.controller;

import com.example.demo.client.HttpClientService;
import com.example.demo.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther lidai
 * @create 2019/1/11 19:22
 */
@RestController
@RequestMapping("/client")
public class HttpClientController {

    @Autowired
    private HttpClientService httpClientService;

    @GetMapping("/doGet")
    public Result doGetWithParams(@RequestParam("url")String url){
        try {
            Map<String,Object> param = new HashMap<>();
            param.put("userId","654321");
            return Result.build().success(httpClientService.doGet(url,param));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.build().fail("fail");
    }

    @GetMapping("/doPost")
    public Result doPostWithParams(@RequestParam("url")String url,@RequestParam("jsonParam")String jsonParam){
        try {
            return Result.build().success(httpClientService.doPost(url,jsonParam));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.build().fail("fail");
    }
}
