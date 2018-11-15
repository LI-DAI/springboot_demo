/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.service;

import com.example.demo.dao.MenuMapper;
import com.example.demo.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lidai
 * @date 2018/10/31 11:30
 */
@Service
@Transactional
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    public List<Menu> getMenusByUserId(String userId){
        return menuMapper.getMenusByUserId(userId);
    }

    /**
     * 获取所有菜单权限
     * @return
     */
    public List<Menu> getAllMenus(){
        List<Menu> menus= menuMapper.getAllMenus();
        return getTree(menus,"0");
    }

    /**
     * 获取单个角色下的菜单权限
     * @param roleId
     * @return
     */
    public List<Menu> getMenusByRoleId(String roleId){
        return  getTree(menuMapper.getMenusByRoleId(roleId),"0");
    }

    /**
     * 输出菜单的时候以目录树的形状输出
     * @param menus 所有菜单
     * @param parentId 父id
     * @return
     */
    public List<Menu> getTree(List<Menu> menus,String parentId){
        List<Menu> result = new ArrayList<>();
        menus.forEach(menu -> {
            if(menu.getParentId().equals(parentId)){
                menu.setChildren(getTree(menus,menu.getMenuId()));
                result.add(menu);
            }
        });
        return result;
    }
}

