/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.service;

import com.example.demo.entity.Dashboard;
import com.example.demo.repository.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lidai
 * @date 2018/10/25 16:54
 */
@Service
public class DashboardService {

    @Autowired
    private DashboardRepository dashboardRepository;

    public List<Dashboard> getAllDashboards(){
        return dashboardRepository.findAll();
    }

    public Dashboard getDashboardById(Long id,String userId,String dashboardName){
        return dashboardRepository.findByIdAndUserIdAndDashboardName(id,userId,dashboardName);
    }
}

