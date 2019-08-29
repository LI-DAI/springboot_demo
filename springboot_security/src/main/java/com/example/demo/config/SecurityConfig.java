/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.config;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lidai
 * @date 2018/11/16 15:08
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserService userService;

    /**
     * 设置密码加密和比较
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes());
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.equals(DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes()));
            }
        });
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/amdin/**").hasRole("ROLE_ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(/*new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        httpServletResponse.getWriter().write("登录成功");
                    }
                }*/
                        (httpServletRequest, httpServletResponse, authentication) -> {
                            httpServletResponse.setContentType("application/json;charset=utf-8");
                            httpServletResponse.getWriter().write("登录成功");
                        }
                )
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        httpServletResponse.getWriter().write("登录失败");
                    }
                })
                .loginProcessingUrl("/login")
                .usernameParameter("username").passwordParameter("password").permitAll()
                .and().logout().permitAll()
                .and().csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
}

