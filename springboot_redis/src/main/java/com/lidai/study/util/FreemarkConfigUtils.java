/*
 * Copyright (C), 2013-2019, 天津大海云科技有限公司
 */
package com.lidai.study.util;


import freemarker.template.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

/**
 * @author lidai
 * @date 2019/4/23 11:54
 */
public class FreemarkConfigUtils {
    private static final String PATH = "E:\\TestProject\\springboot_study\\springboot_redis\\src\\main\\java\\com\\lidai\\study\\template";
    public static final int TYPE_HTTP = 1;
    public static final int TYPE_HTTPS = 2;
    public static final int TYPE_WEBSERVICE = 3;
    public static final int TYPE_DEMO = 4;
    public static final int TYPE_API_DOCUMENT = 5;

    private static Configuration configuration;

    public static synchronized Configuration getInstance() {
        if (null == configuration) {
            configuration = new Configuration(Configuration.VERSION_2_3_0);
            try {
                configuration.setDirectoryForTemplateLoading(new File(PATH));
            } catch (IOException e) {
                e.printStackTrace();
            }
            configuration.setEncoding(Locale.CHINA, "UTF-8");
        }
        return configuration;
    }
}

