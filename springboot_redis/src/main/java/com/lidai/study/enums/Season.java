/*
 * Copyright (C), 2013-2019, 天津大海云科技有限公司
 */
package com.lidai.study.enums;

/**
 * @author lidai
 * @date 2019/5/6 16:49
 */
public enum Season {

    Y("Y",1),
    N("N",0);

    private String name;
    private int value;



    Season(String name, int value) {
        this.name  =name ;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
