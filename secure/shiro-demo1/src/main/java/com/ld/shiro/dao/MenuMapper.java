/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.ld.shiro.dao;


import com.ld.shiro.entity.manage.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lidai
 * @date 2018/10/31 11:03
 */
@Mapper
public interface MenuMapper {

    List<Menu> getMenusByUserId(String userId);

    List<Menu> getMenusByRoleId(String roleId);

    List<Menu> getAllMenus();
}

