/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

/**
 * @author lidai
 * @date 2018/11/16 11:26
 */
//@Data     //用@Data就报错，用get set就没事，我也是很郁闷
@Table(name = "t_user")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private String userId;
    private String username;
    private String email;
    private String phoneNumber;
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch= FetchType.EAGER)
    @JoinTable(name = "t_user_role",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "role_id"))
    private Set<Role> roleSet;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities=new ArrayList<>();
        roleSet.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName())));
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

