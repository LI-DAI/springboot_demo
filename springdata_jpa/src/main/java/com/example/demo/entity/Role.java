/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

/**
 * @author lidai
 * @date 2018/10/29 14:15
 */
@Entity
@Table(name = "t_role")
@Data
@ToString
public class Role {

    @Id
    @GeneratedValue
    private String roleId;

    private String roleName;

    private String remark;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "roleSet")
    private Set<User> userSet;

}

