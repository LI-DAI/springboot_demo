/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.utils;

import java.util.UUID;

/**
 * @author lidai
 * @date 2018/10/31 15:25
 */
public class UUIDUtils {

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}

