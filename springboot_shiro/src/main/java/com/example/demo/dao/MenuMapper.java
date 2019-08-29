/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.dao;

import com.example.demo.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lidai
 * @date 2018/10/31 11:03
 */
@Mapper
public interface MenuMapper {

    /**
     * 获取用户权限
     *
     * @author lidai
     * @date 2019/7/26 10:15
     * @param userId
     * @return java.util.List<com.example.demo.entity.Menu>
     */
    List<Menu> getMenusByUserId(String userId);

    List<Menu> getMenusByRoleId(String roleId);

    List<Menu> getAllMenus();
}

