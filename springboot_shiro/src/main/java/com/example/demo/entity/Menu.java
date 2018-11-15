/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.entity;

import com.example.demo.enums.MenuType;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author lidai
 * @date 2018/10/30 15:45
 */
@Data
@ToString
public class Menu {

    private String menuId;
    private String menuName;
    private String parentId;
    private String url;
    private String perms;
    private MenuType menuType;
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;
    private List<Menu> children;
}

