package com.ld.shiro.entity.common;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author ld
 * @Date 2020/3/5 10:56
 */
@Data
public class PageData<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private long total;

    private List<T> rows;

    public PageData() {
    }

    public PageData(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }


}