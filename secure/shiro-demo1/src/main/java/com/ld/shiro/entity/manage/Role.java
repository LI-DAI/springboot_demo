/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.ld.shiro.entity.manage;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
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
    private String deleteFlag;
    private String createBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private String updateBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    private List<Menu> menus;
}

