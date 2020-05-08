/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.ld.shiro.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ld.shiro.dao.UserMapper;
import com.ld.shiro.entity.manage.User;
import com.ld.shiro.entity.manage.UserRole;
import com.ld.shiro.service.UserService;
import com.ld.shiro.utils.PasswordHelper;
import com.ld.shiro.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lidai
 * @date 2018/10/30 16:58
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public User getUserById(String userId) {
        return userMapper.getUserById(userId);
    }

    /**
     * 新增user
     *
     * @param user
     * @return
     */
    @Override
    public int insertUser(User user) {
        User u = userMapper.getUserByUsername(user.getUsername());
        if (u != null) {
            throw new RuntimeException("用户名已存在");
        }
        user.setUserId(IdUtil.simpleUUID());
        user.randomSalt();
        PasswordHelper.encryptPassword(user);
        return userMapper.insertUser(user);
    }

    /**
     * 获取所有user,可根据条件查询
     *
     * @param username
     * @param email
     * @param phoneNumber
     * @return
     */
    @Override
    public List<User> getAllUser(String username, String email, String phoneNumber) {
        return userMapper.getAllUser(username, email, phoneNumber);
    }

    /**
     * 修改user
     *
     * @param user
     * @return
     */
    @Override
    public int updateUser(User user) {
        User u = userMapper.getUserByUsername(user.getUsername());
        if (u != null) {
            throw new RuntimeException("该用户名已存在");
        }
        user.setUpdateBy(ShiroUtils.getUsername());
        return userMapper.updateUser(user);
    }

    /**
     * 根据id批量删除user
     *
     * @param ids user唯一标识集合
     * @return
     */
    @Override
    public int deleteUserByIds(List<String> ids) {
        return userMapper.deleteUserByIds(ids);
    }

    /**
     * 批量添加UserRole
     *
     * @param userRoles UserRole对象集合
     * @return
     */
    @Override
    public int insertUserRoles(List<UserRole> userRoles) {
        return userMapper.insertUserRoles(userRoles);
    }

    /**
     * 批量删除userRole，批量删除user时需要同时删除多个userRole
     *
     * @param ids userId集合
     * @return
     */
    @Override
    public int deleteUserRolesByUserIds(String[] ids) {
        return userMapper.deleteUserRolesByUserIds(ids);
    }

}

