/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.ld.shiro.entity.manage;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author lidai
 * @date 2018/10/30 15:19
 */
@Data
@ToString
public class User implements Serializable {
    private String userId;
    private String username;
    private String password;
    private String salt;
    private String email;
    private String phoneNumber;
    private Integer sex;
    private String deleteFlag;
    private String createBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private String updateBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

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

