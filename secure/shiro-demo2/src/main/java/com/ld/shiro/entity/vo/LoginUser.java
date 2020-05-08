package com.ld.shiro.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author ld
 * @Date 2020/5/6 15:10
 */
@Data
public class LoginUser implements Serializable {

    private String userId;

    private String username;

    private String email;

    private String phoneNumber;

    private String roles;
}
