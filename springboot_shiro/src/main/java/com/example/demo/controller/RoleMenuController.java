/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.controller;

import com.example.demo.entity.Result;
import com.example.demo.entity.RoleMenu;
import com.example.demo.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lidai
 * @date 2018/11/16 16:34
 */
@RestController
@RequestMapping("/role_menu")
public class RoleMenuController {

    @Autowired
    private RoleService roleService;

    @RequiresRoles("admin")
    @PostMapping("/")
    public Result batchInsertRoleMenu(@RequestBody List<RoleMenu> roleMenus){
        return Result.build().success("新增成功",roleService.batchInsertRoleMenu(roleMenus));
    }

}

