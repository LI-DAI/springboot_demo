/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.ld.shiro.service.impl;

import com.ld.shiro.dao.RoleMapper;
import com.ld.shiro.entity.manage.Role;
import com.ld.shiro.entity.manage.RoleMenu;
import com.ld.shiro.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lidai
 * @date 2018/10/31 10:50
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> getRolesByUserId(String userId) {
        return roleMapper.getRolesByUserId(userId);
    }
    @Override
    public List<Role> getAllRoles(String roleName, String remark) {
        return roleMapper.getAllRoles(roleName, remark);
    }
    @Override
    public int batchInsertRoleMenu(List<RoleMenu> roleMenus) {
        return roleMapper.batchInsertRoleMenu(roleMenus);
    }
}

