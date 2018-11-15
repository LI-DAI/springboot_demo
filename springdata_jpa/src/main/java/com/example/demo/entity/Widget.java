/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author lidai
 * @date 2018/10/25 16:51
 */
@Entity
@Table(name = "t_dashboard_widget")
@Data
public class Widget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dashboardId;

    private String optionConfig;
}

