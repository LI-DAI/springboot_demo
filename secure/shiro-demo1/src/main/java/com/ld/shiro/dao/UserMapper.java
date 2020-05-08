/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.ld.shiro.dao;

import com.ld.shiro.entity.manage.User;
import com.ld.shiro.entity.manage.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lidai
 * @date 2018/10/30 16:58
 */
@Mapper
public interface UserMapper {
    User getUserByUsername(String username);

    User getUserById(String userId);

    int insertUser(User user);

    List<User> getAllUser(@Param("username")String username,@Param("email")String email,@Param("phoneNumber")String phoneNumber);

    int updateUser(User user);

    int deleteUserByIds(List<String> ids);

    int insertUserRoles(List<UserRole> list);

    int deleteUserRolesByUserIds(String[] ids);

    int batchInsert(List<User> users);
}

