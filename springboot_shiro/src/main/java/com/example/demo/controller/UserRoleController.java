/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.controller;

import com.example.demo.entity.Result;
import com.example.demo.entity.UserRole;
import com.example.demo.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lidai
 * @date 2018/11/1 10:35
 */
@RestController
@RequestMapping("/user_role")
public class UserRoleController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    @RequiresPermissions("userRole:add")
    public Result insertUserRoles(@RequestBody List<UserRole> userRoles){
        Assert.notEmpty(userRoles,"userRole不能为空");
        return Result.build().success("添加成功",userService.insertUserRoles(userRoles));
    }

    @DeleteMapping("/delete")
    @RequiresPermissions("userRole:delete")
    public Result deleteUserRolesByUserIds(@RequestParam("ids") String[] ids){
        Assert.notNull(ids,"ids不能为空");
        return Result.build().success("删除成功",userService.deleteUserRolesByUserIds(ids));
    }
}

