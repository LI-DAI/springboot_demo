package com.ld.shiro.service;

import com.ld.shiro.entity.manage.Menu;

import java.util.List;

/**
 * @Author ld
 * @Date 2020/4/27 13:41
 */
public interface MenuService {

    List<Menu> getMenusByUserId(String userId);

    List<Menu> getAllMenus();

    List<Menu> getMenusByRoleId(String roleId);
}
