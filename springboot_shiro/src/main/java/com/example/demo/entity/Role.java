/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.entity;

import com.example.demo.enums.DeleteStatus;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author lidai
 * @date 2018/10/30 15:39
 */
@Data
@ToString
public class Role {
    private String roleId;
    private String roleName;
    private String remark;
    private DeleteStatus deleteFlag;
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;
    private List<Menu> menus;
}

