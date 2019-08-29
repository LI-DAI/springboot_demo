/*
 * Copyright (C), 2013-2019, 天津大海云科技有限公司
 */
package com.example.demo.config;

import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author lidai
 * @date 2019/6/26 14:49
 * @since
 */
@Configuration
public class MyBatisConfig {

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> {
            //开启驼峰命名
            configuration.setMapUnderscoreToCamelCase(true);
            //关闭二级缓存
            configuration.setCacheEnabled(false);
            //返回值为null显示
            configuration.setCallSettersOnNulls(true);
            //显示日志在控制台
            configuration.setLogImpl(StdOutImpl.class);
        };

    }

    @Bean
    public KeyGenerator keyGenerator() {
        return (obj, method, args) -> method.getName() + Arrays.asList(args).toString().replace("[","").replace("]","");
    }

}

