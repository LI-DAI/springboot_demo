/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.lidai.study.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author lidai
 * @date 2018/10/9 16:06
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport{

    @Bean
    public KeyGenerator keyGenerator(){
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                StringBuilder sb = new StringBuilder();
                sb.append(o.getClass().getName());
                sb.append(method.getName());
                for(Object object:objects){
                    sb.append(object.toString());
                }
                return sb.toString();
            }
        };
    }


    /**
     * 2.0之后此方法不可用
     * @param factory
     * @return
     */
//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory factory){
//        RedisCacheManager rcm= RedisCacheManager.create(factory);
//        //设置缓存过期时
//        //rcm.setDefaultExpiration(60);//秒
//        return rcm;
//    }

    /**
     * RedisCacheConfiguration设置配置缓存的方法都是有返回值的，需要重新赋值给config对象
     * 比如设置缓存时间的方法entryTtl,如不设置则不启作用
     * @param factory
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory){
        //生成一个默认配置,通过config对象可对缓存进行自定义设置
        RedisCacheConfiguration config=RedisCacheConfiguration.defaultCacheConfig();
        config=config.entryTtl(Duration.ofMinutes(1))//设置缓存默认过期时间,1分钟
                .disableCachingNullValues();//不缓存空值

        //设置初始化的缓存空间
        Set<String> cacheNames=new HashSet<>();
        cacheNames.add("my-redis-cache1");
        cacheNames.add("my-redis-cache2");

        //对不同的缓存空间进行不同的设置
        Map<String,RedisCacheConfiguration> configMap=new HashMap<>();
        configMap.put("my-redis-cache1",config);
        configMap.put("my-redis-cache2",config.entryTtl(Duration.ofSeconds(60)));

        RedisCacheManager cacheManager=RedisCacheManager.builder(factory)
                .initialCacheNames(cacheNames)//注意调用顺序,先调用缓存名,再调用缓存配置
                .withInitialCacheConfigurations(configMap)
                .build();

        return cacheManager;
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
}

