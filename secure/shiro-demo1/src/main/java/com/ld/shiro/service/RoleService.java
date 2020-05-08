package com.ld.shiro.service;

import com.ld.shiro.entity.manage.Role;
import com.ld.shiro.entity.manage.RoleMenu;

import java.util.List;

/**
 * @Author ld
 * @Date 2020/4/27 13:42
 */
public interface RoleService {

    List<Role> getRolesByUserId(String userId);

    List<Role> getAllRoles(String roleName, String remark);

    int batchInsertRoleMenu(List<RoleMenu> roleMenus);
}
