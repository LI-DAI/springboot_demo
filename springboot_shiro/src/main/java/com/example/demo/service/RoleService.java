/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.service;

import com.example.demo.dao.RoleMapper;
import com.example.demo.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lidai
 * @date 2018/10/31 10:50
 */
@Service
@Transactional
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    public List<Role> getRolesByUserId(String userId){
        return  roleMapper.getRolesByUserId(userId);
    }

    public List<Role> getAllRoles(String roleName,String remark){
        return roleMapper.getAllRoles(roleName,remark);
    }
}

