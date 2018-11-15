/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.lidai.study.scheduler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lidai
 * @date 2018/10/10 14:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SchedulerTaskTest {

    @Autowired
    private SchedulerTask schedulerTask;

    @Autowired
    private SchedulerTask2 schedulerTask2;

    @Test
    public void test(){
        schedulerTask.process();
        schedulerTask2.reportCurrentTime();
    }
}

