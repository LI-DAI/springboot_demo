package com.ld.shiro.controller;

import cn.hutool.core.bean.BeanUtil;
import com.google.common.collect.ImmutableMap;
import com.ld.shiro.entity.SecurityProperties;
import com.ld.shiro.entity.common.CommonConstant;
import com.ld.shiro.entity.common.R;
import com.ld.shiro.entity.manage.User;
import com.ld.shiro.service.OnlineUserService;
import com.ld.shiro.service.UserService;
import com.ld.shiro.utils.JwtUtil;
import com.ld.shiro.utils.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author ld
 * @Date 2020/4/27 12:02
 */
@RequestMapping("/auth")
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private OnlineUserService onlineUserService;

    @Autowired
    private SecurityProperties properties;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("/login")
    public R<Object> login(@RequestBody User user, HttpServletRequest request) {
        String username = user.getUsername();
        String password = user.getPassword();
        User u = userService.getUserByUsername(username);
        if (PasswordHelper.matchPassword(u, password)) {
            //密码正确
            Map<String, Object> map = BeanUtil.beanToMap(u);
            String token = JwtUtil.generateJwtToken(map, properties.getTokenValidityInHours() * 60 * 60 * 1000L);
            String onlineToken = properties.getOnlineKey() + token;
            //保存上线用户
            onlineUserService.saveOnlineUser(u, onlineToken, request);
            //验证线上用户
            onlineUserService.checkOnlineUser(username, onlineToken);
            Map<String, Object> res = ImmutableMap.of("user", u, "Token", onlineToken);
            return R.data(res, "登陆成功");
        }
        return R.fail("密码错误");


    }

    @GetMapping("/logout")
    public R<Object> logout(HttpServletRequest request) {
        String onlineToken = request.getHeader(CommonConstant.AUTHENTICATION);
        redisTemplate.delete(onlineToken);
        return R.data("注销成功");
    }
}
