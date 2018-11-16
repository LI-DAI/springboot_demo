/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

/**
 * @author lidai
 * @date 2018/11/16 11:32
 */
@Data
@Table(name = "t_role")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
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

