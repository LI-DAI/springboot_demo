/*
 * Copyright (C), 2013-2019, 天津大海云科技有限公司
 */
package com.demo.interview.singleton;

import java.io.IOException;
import java.util.Properties;

/**
 * @author lidai
 * @date 2019/6/18 15:32
 * <p>
 * 饿汉单例，加载其他资源
 */
public class Singleton_3 {

    public static final Singleton_3 INSTANCE;
    private String info;

    static {
        Properties prop = new Properties();
        try {
            prop.load(Singleton_3.class.getClassLoader().getResourceAsStream("single.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        INSTANCE = new Singleton_3(prop.getProperty("info"));
    }

    private Singleton_3(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Singleton_3{" +
                "info='" + info + '\'' +
                '}';
    }
}

