package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.example.demo.dao")
@EnableCaching
public class SpringbootShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootShiroApplication.class, args);
    }
}
