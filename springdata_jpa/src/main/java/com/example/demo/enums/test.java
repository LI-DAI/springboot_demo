/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.enums;

/**
 * @author lidai
 * @date 2018/10/25 15:21
 */
public enum test {
    RED("RED",1),
    GREEN("绿色",2),
    BLUE("蓝色",3);

    private String name;
    private Integer value;

    test(String name, Integer value) {
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
