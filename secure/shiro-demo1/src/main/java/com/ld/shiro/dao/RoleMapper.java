/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.ld.shiro.dao;

import com.ld.shiro.entity.manage.Role;
import com.ld.shiro.entity.manage.RoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lidai
 * @date 2018/10/31 10:44
 */
@Mapper
public interface RoleMapper {

    List<Role> getRolesByUserId(String userId);

    List<Role> getAllRoles(@Param("roleName")String roleName,@Param("remark")String remark);

    int batchInsertRoleMenu(List<RoleMenu> roleMenus);
}

