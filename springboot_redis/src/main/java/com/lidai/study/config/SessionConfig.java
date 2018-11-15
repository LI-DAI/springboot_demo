/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.lidai.study.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author lidai
 * @date 2018/10/10 10:20
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60*1440)
public class SessionConfig {
//    maxInactiveIntervalInSeconds: 设置Session失效时间，使用Redis Session之后，原Boot的server.session.timeout属性不再生效

}

