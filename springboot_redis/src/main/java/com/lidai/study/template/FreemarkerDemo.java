/*
 * Copyright (C), 2013-2019, 天津大海云科技有限公司
 */
package com.lidai.study.template;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lidai
 * @date 2019/4/23 11:06
 */
public class FreemarkerDemo {

//    public static final String TEMPLATE_PATH="E:\\TestProject\\springboot_study\\springboot_redis\\src\\main\\java\\com\\lidai\\study\\template";
//    private static final String CLASS_PATH = "E:\\TestProject\\springboot_study\\springboot_redis\\src\\main\\java\\com\\lidai\\study\\test";

    public static final String TEMPLATE_PATH = "src/main/java/resource/template";
    private static final String CLASS_PATH = "src/main/java/com/lidai/study/test";

    public static void main(String[] args) {
        // step1 创建freeMarker配置实例
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_22);
        Writer out = null;
        try {
            // step2 获取模版路径
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
            // step3 创建数据模型
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("classPath", "com.lidai.study.test");
            dataMap.put("className", "Role");
            dataMap.put("Id", "Id");
            dataMap.put("userName", "userName");
            dataMap.put("password", "password");
            // step4 加载模版文件
            Template template = configuration.getTemplate("test.ftl");
            // step5 生成数据
            File docFile = new File(CLASS_PATH + "\\" + "User.java");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            // step6 输出文件
            template.process(dataMap, out);
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^User.java 文件创建成功 !");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.flush();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}

