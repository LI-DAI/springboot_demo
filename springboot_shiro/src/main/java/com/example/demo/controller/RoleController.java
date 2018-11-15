/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.controller;

import com.example.demo.entity.Result;
import com.example.demo.entity.Role;
import com.example.demo.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lidai
 * @date 2018/11/1 15:02
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/all")
    @RequiresPermissions("role:get")
    public Result getAllRoles(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                              @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
                              @RequestParam("roleName")String roleName,
                              @RequestParam("remark")String remark){
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<Role> pageInfo=new PageInfo<>(roleService.getAllRoles(roleName,remark));
        return Result.build().success("查询成功",pageInfo);
    }
}

