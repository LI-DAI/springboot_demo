/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.lidai.study.service;

import com.lidai.study.Entity.User;
import com.lidai.study.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

/**
 * @author lidai
 * @date 2018/10/10 9:54
 */

public interface UserService {

    User getUserById(String id);

    List<User> getAll();
}

