package com.ld.shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.ld.shiro.dao")
@SpringBootApplication
public class ShiroDemo1Application {

    public static void main(String[] args) {
        SpringApplication.run(ShiroDemo1Application.class, args);
    }

}
