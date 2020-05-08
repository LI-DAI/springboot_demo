package com.ld.shiro.controller;

import com.ld.shiro.entity.common.R;
import com.ld.shiro.service.UserService;
import com.ld.shiro.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ld
 * @Date 2020/4/28 12:17
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public R<String> test() {
        return R.data("hello ...");
    }

    @GetMapping("/no")
    public R<String> test2() {
        return R.data("权限测试 ...");
    }

    @GetMapping("/get")
    public R<Object> getUser() {
        return R.data(ShiroUtils.getUserInfo());
    }
}
