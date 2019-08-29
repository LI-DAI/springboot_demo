/*
 * Copyright (C), 2013-2019, 天津大海云科技有限公司
 */
package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * @author lidai
 * @date 2019/4/30 15:27
 */
@Service
public class FileTestService {

    public void writeFile(HttpServletResponse response, MultipartFile file){
        String filename = file.getOriginalFilename();
        String path = "E:/test";
        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path+File.separator+filename));
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream())){
            int len;
            byte[] buffer = new byte[1024*10];
            while ((len = bis.read(buffer,0,buffer.length))!=-1){
                bos.write(buffer,0,len);
                bos.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

