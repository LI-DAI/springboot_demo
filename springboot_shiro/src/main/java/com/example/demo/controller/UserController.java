/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.controller;

import com.example.demo.entity.Result;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/**
 * @author lidai
 * @date 2018/10/31 14:18
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    @Cacheable(value = {"user"}, key = "#userId")
    public User getUserById(@PathVariable String userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/add")
    @RequiresPermissions("user:create")
    public int insertUser(@RequestBody User user) {
        return userService.insertUser(user);
    }

    @GetMapping("/all")
    @RequiresPermissions("user:get")
    public PageInfo<User> getAllUser(@RequestParam(value = "pageNum ",defaultValue = "1")int  pageNum,
                                     @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
                                     @RequestParam("username") String username,
                                     @RequestParam("email") String email,
                                     @RequestParam("phoneNumber") String phoneNumber) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userService.getAllUser(username, email, phoneNumber);
        PageInfo<User> pageInfo = new PageInfo(users);
        return pageInfo;
    }

    @PutMapping("/update")
    @RequiresPermissions("user:update")
    public Result updateUser(@RequestBody User user) {
        Assert.notNull(user.getUserId(), "userId不能为空");
        return Result.build().success("修改成功", userService.updateUser(user));
    }

    @PutMapping("/delete")
    @RequiresPermissions("user:delete")
    public Result deleteUserByIds(@RequestParam("ids") List<String> ids) {
        Assert.notEmpty(ids, "id不能为空");
        return Result.build().success("删除成功", userService.deleteUserByIds(ids));
    }


    @PostMapping("/download")
    public void download(HttpServletResponse response) {
        File file = new File("E:/pdf/test.png");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment; filename=" + file.getName());
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
             BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream())) {
            int len;
            byte[] bytes = new byte[1024 * 10];
            while ((len = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
                bos.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ;
    }

}

