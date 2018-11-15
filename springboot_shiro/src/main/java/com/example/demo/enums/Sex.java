/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.enums;

/**
 * @author lidai
 * @date 2018/10/30 15:21
 */
public enum Sex {
    MALE("MALE",1),
    FEMALE("FEMALE",2);

    private String name;
    private Integer value;

    Sex(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
