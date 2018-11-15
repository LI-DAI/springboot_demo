/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.repository;

import com.example.demo.entity.Dashboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

/**
 * @author lidai
 * @date 2018/10/25 16:53
 */
public interface DashboardRepository extends JpaRepository<Dashboard,String>,
        JpaSpecificationExecutor<Dashboard>,
        Serializable {
    Dashboard findByIdAndUserIdAndDashboardName(Long id,String userId,String dashboardName);
}

