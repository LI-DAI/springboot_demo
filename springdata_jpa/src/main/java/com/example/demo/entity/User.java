/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.entity;

import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

/**
 * @author lidai
 * @date 2018/10/23 13:53
 */
@Entity//告诉JPA这是一个实体类
//@Data
@Table(name = "t_user")//和@entity配合使用映射表结构。可以省略，如果省略，则默认为表名小写
@ToString
public class User {

    @Id//标注主键
    @GeneratedValue
    private String userId;

    private String username;

    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "t_user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private Set<Role> roleSet;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }
}

