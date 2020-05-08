package com.ld.shiro.entity.common;

import java.io.Serializable;

/**
 * @Author ld
 * @Date 2020/3/5 10:56
 */
public interface IResultCode extends Serializable {

    /**
     * 消息
     *
     * @return String
     */
    String getMessage();

    /**
     * 状态码
     *
     * @return int
     */
    int getCode();
}
