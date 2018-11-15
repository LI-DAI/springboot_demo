/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * @author lidai
 * @date 2018/10/25 16:50
 */
@Entity
@Table(name= "t_dashboard")
@Data
public class Dashboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Dashboard 名称
     */

    private String dashboardName;
    /**
     * Dashboard 描述
     */
    private String dashboardDesc;
    /**
     * Dashboard 配置
     */
    private String dashboardConfig;
    /**
     * 是否是主页
     */
    private boolean isHomepage;

    private String userId;
    private String isShared;
    /**
     * 控件编组数据  注意：该属性在保存控件时提交
     */
    private String groupWidget;
    /**
     * X轴对齐线  注意：该属性在保存控件时提交
     */
    private String xLine;
    /**
     * Y轴对齐线  注意：该属性在保存控件时提交
     */
    private String yLine;
    /**
     * 当前 Dashboard 中包含的控件
     */
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "dashboardId")
    private List<Widget> widgets;
    /**
     * 标签
     */
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "dashboardId")
    private Set<Tags> tags;
    /**
     * 缩略图，base64编码
     */
    private String thumbnail;
}

