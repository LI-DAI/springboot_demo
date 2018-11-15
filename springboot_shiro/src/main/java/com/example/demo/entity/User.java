/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.entity;

import com.example.demo.annotation.Excel;
import com.example.demo.enums.DeleteStatus;
import com.example.demo.enums.Sex;
import lombok.Data;
import lombok.ToString;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author lidai
 * @date 2018/10/30 15:19
 */
@Data
@ToString
public class User implements Serializable {
    @Excel(name = "用户id")
    private String userId;
    @Excel(name= "用户名")
    private String username;
    private String password;
    private String salt;
    @Excel(name = "邮箱")
    private String email;
    @Excel(name = "电话号")
    private String phoneNumber;
    private Sex sex;
    private DeleteStatus deleteFlag;
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;
    private List<Role> roles;

    /**
     * 生成随机盐
     */
    public void randomSalt() {
        SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
        String hex = secureRandom.nextBytes(6).toHex();
        setSalt(hex);
    }
}

