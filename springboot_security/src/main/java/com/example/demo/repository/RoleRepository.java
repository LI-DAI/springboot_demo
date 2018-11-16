/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.repository;

import com.example.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @author lidai
 * @date 2018/11/16 11:46
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,String>,
        JpaSpecificationExecutor<Role>,
        Serializable {

}

