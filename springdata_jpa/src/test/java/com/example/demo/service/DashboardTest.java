/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.service;

import com.example.demo.entity.Dashboard;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author lidai
 * @date 2018/10/25 16:58
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class DashboardTest {

    @Autowired
    private DashboardService dashboardService;

    @Test
    public void testFindAll(){
        List<Dashboard> dashboards = dashboardService.getAllDashboards();
        log.info("集合为{}",dashboards);
    }

    @Test
    public void testGetDashboardById(){
//        Dashboard dashboard=dashboardService.getDashboardById(1l,"111","test");
//        log.info("id为{},dashboard为{}",1,dashboard);

        String time=LocalDateTime.now().plusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        log.info("明天当前时间为:{}",time);//明天当前时间为:2018-10-27T14:47:57.085
    }


}

