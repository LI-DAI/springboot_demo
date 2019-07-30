/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.service;

import com.example.demo.entity.Result;
import com.example.demo.entity.UserRole;
import com.example.demo.shiro.ShiroUtils;
import com.example.demo.dao.UserMapper;
import com.example.demo.entity.User;
import com.example.demo.utils.ExcelUtils;
import com.example.demo.utils.PasswordHelper;
import com.example.demo.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lidai
 * @date 2018/10/30 16:58
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUserByUsername(String username){
        return  userMapper.getUserByUsername(username);
    }

    public User getUserById(String userId){
        return userMapper.getUserById(userId);
    }

    /**
     * 新增user
     * @param user
     * @return
     */
    public int insertUser(User user){
        User u=userMapper.getUserByUsername(user.getUsername());
        if(u!=null){
            throw new RuntimeException("用户名已存在");
        }
        user.setUserId(UUIDUtils.getUUID());
        user.randomSalt();
//        user.setCreateBy(ShiroUtils.getUser().getUsername());
        PasswordHelper.encryptPassword(user);
        return userMapper.insertUser(user);
    }

    /**
     * 获取所有user,可根据条件查询
     * @param username
     * @param email
     * @param phoneNumber
     * @return
     */
    public List<User> getAllUser(String username,String email,String phoneNumber){
        return userMapper.getAllUser(username,email,phoneNumber);
    }

    /**
     * 修改user
     * @param user
     * @return
     */
    public int updateUser(User user){
        User u=userMapper.getUserByUsername(user.getUsername());
        if(u!=null){
            throw new RuntimeException("该用户名已存在");
        }
        user.setUpdateBy(ShiroUtils.getUser().getUsername());
        return userMapper.updateUser(user);
    }

    /**
     * 根据id批量删除user
     * @param ids user唯一标识集合
     * @return
     */
    public int deleteUserByIds(List<String> ids){
        return userMapper.deleteUserByIds(ids);
    }

    /**
     * 批量添加UserRole
     * @param userRoles UserRole对象集合
     * @return
     */
    public int insertUserRoles(List<UserRole> userRoles){
        return userMapper.insertUserRoles(userRoles);
    }

    /**
     * 批量删除userRole，批量删除user时需要同时删除多个userRole
     * @param ids userId集合
     * @return
     */
    public int deleteUserRolesByUserIds(String[] ids){
        return userMapper.deleteUserRolesByUserIds(ids);
    }

    public void exportExcel(){
        List<User> users=getAllUser(null,null,null);
        ExcelUtils<User> excelUtils=new ExcelUtils<>(User.class);
        try {
            excelUtils.exportExcel(users,"user");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

