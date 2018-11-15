/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.lidai.study.dao;

import com.lidai.study.Entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lidai
 * @date 2018/10/10 9:52
 */
@Mapper
public interface UserMapper {

    User getUserById(String id);
}

