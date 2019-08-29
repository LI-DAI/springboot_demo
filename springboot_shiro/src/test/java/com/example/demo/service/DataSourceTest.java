/*
 * Copyright (C), 2013-2019, 天津大海云科技有限公司
 */
package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author lidai
 * @date 2019/6/26 14:29
 * @since
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class DataSourceTest {

    @Autowired
    DataSource dataSource;

    @Test
    public void test() throws SQLException {
        Connection conn = dataSource.getConnection();
        System.out.println(conn);
    }
}

