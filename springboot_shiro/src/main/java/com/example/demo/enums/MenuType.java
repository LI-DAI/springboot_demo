/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.enums;

/**
 * @author lidai
 * @date 2018/10/30 15:43
 */
public enum MenuType {

    M("M","目录"),
    C("C","菜单"),
    F("F","按钮");
    private String name;
    private String value;

    MenuType(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
