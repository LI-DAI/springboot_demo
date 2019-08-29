/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

/**
 * @author lidai
 * @date 2018/10/23 14:14
 */
public interface UserRepository extends JpaRepository<User,String>,
        JpaSpecificationExecutor<User>,
        Serializable{

    User findByUsername(String username);
}

