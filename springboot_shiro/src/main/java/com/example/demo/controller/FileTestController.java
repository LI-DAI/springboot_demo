/*
 * Copyright (C), 2013-2019, 天津大海云科技有限公司
 */
package com.example.demo.controller;

import com.example.demo.entity.Result;
import com.example.demo.service.FileTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author lidai
 * @date 2019/4/30 15:26
 */
@RestController
@RequestMapping("/file")
public class FileTestController {

    @Autowired
    private FileTestService fileTestService;

    @PostMapping("/")
    public Result writeFile(@RequestParam("file") MultipartFile file, HttpServletResponse response){
        fileTestService.writeFile(response,file);
        return Result.build().success("success");
    }
}

