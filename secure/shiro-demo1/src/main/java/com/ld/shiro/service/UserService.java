package com.ld.shiro.service;

import com.ld.shiro.entity.manage.User;
import com.ld.shiro.entity.manage.UserRole;

import java.util.List;

/**
 * @Author ld
 * @Date 2020/4/27 13:42
 */
public interface UserService {

    User getUserByUsername(String username);

    User getUserById(String userId);

    int insertUser(User user);

    List<User> getAllUser(String username, String email, String phoneNumber);

    int updateUser(User user);

    int deleteUserByIds(List<String> ids);

    int insertUserRoles(List<UserRole> userRoles);

    int deleteUserRolesByUserIds(String[] ids);
}
