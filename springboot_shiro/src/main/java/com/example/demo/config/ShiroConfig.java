/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.config;

import com.example.demo.shiro.filter.CustomAuthenticationFilter;
import com.example.demo.shiro.realm.CustomShiroRealm;
import com.example.demo.shiro.realm.TokenValidateRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lidai
 * @date 2018/12/24 14:59
 */
@Configuration
public class ShiroConfig {

    public static final String LOGIN_URL = "/login";

    /**
     * 管理bean生命周期
     *
     * @return
     */
    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setLoginUrl(LOGIN_URL);
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filter = new LinkedHashMap<>();
        filter.put("customFilter", new CustomAuthenticationFilter());
        shiroFilterFactoryBean.setFilters(filter);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/logout", "logout");
        //放开拦截
        filterChainDefinitionMap.put("/pipeline/start", "anon");
        filterChainDefinitionMap.put("/function/startTest", "anon");
        filterChainDefinitionMap.put("/app/write", "anon");
        filterChainDefinitionMap.put("/app/upload", "anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/**", "customFilter");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealms(Arrays.asList(customShiroRealm(), tokenValidateRealm()));
        return securityManager;
    }

    @Bean
    public CustomShiroRealm customShiroRealm() {
        CustomShiroRealm shiroRealm = new CustomShiroRealm();
        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return shiroRealm;
    }

    /**
     * 此处realm登录必须开启缓存，登录时会对这个参数进行判断，如果为false,则认证不通过
     *
     * @return
     */
    @Bean
    public TokenValidateRealm tokenValidateRealm() {
        TokenValidateRealm tokenValidateRealm = new TokenValidateRealm();
        tokenValidateRealm.setAuthenticationCachingEnabled(true);
//        tokenValidateRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return tokenValidateRealm;
    }

    /**
     * 凭证匹配，加密算法
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    /**
     * 开启shiro 注解支持
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 开启shiro授权注解，若上面Bean未生效则使用此Bean
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

}

