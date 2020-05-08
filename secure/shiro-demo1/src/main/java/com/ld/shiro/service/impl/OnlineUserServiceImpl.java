package com.ld.shiro.service.impl;

import com.alibaba.fastjson.JSON;
import com.ld.shiro.entity.manage.User;
import com.ld.shiro.entity.vo.OnlineUser;
import com.ld.shiro.service.OnlineUserService;
import com.ld.shiro.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Author ld
 * @Date 2020/4/18 16:29
 */
@Service
@Slf4j
public class OnlineUserServiceImpl implements OnlineUserService {

    @Value("${security.custom.token-validity-in-hours:2}")
    private Integer expire;

    @Value("${security.custom.online-key:online-token}")
    private String onlineKey;

    private final String pattern = "*";

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 判断线上用户
     *
     * @param username
     * @param token
     */
    @Override
    public void checkOnlineUser(String username, String token) {
        Set<String> keys = redisTemplate.keys(pattern);
        Set<String> onlineUserKeys = keys.stream().filter(key -> key.startsWith(onlineKey)).collect(Collectors.toSet());
        for (String userKey : onlineUserKeys) {
            if (token.equals(userKey)) {
                //token完全相同为当前登陆token,下一个
                continue;
            }
            String onlineUserStr = redisTemplate.opsForValue().get(userKey);
            OnlineUser onlineUser = JSON.parseObject(onlineUserStr, OnlineUser.class);
            if (username.equals(onlineUser.getUsername())) {
                //username与当前登陆用户相同，则直接踢出（删除）
                redisTemplate.delete(userKey);
            }
        }
    }

    /**
     * 保存线上用户
     *
     * @param user
     * @param token
     */
    @Override
    public void saveOnlineUser(User user, String token, HttpServletRequest request) {
        OnlineUser onlineUser = OnlineUser.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .loginTime(LocalDateTime.now())
                .ip(StringUtils.getIp(request))
                .build();
        redisTemplate.opsForValue().set(token, JSON.toJSONString(onlineUser), expire, TimeUnit.HOURS);
    }
}
