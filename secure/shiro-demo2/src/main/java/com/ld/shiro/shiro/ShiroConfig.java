/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.ld.shiro.shiro;

import com.ld.shiro.entity.SecurityProperties;
import com.ld.shiro.shiro.filter.CustomPermissionFilter;
import com.ld.shiro.shiro.filter.JwtTokenFilter;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lidai
 * @date 2018/10/30 16:41
 */
@Configuration
public class ShiroConfig {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private SecurityProperties properties;

    @Autowired
    private JdbcTemplate jdbcTemplate;


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
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filters = new LinkedHashMap<>();
        //添加自定义过滤器
        filters.put("jtf", new JwtTokenFilter(properties, redisTemplate));
        filters.put("cpf", new CustomPermissionFilter());
        shiroFilterFactoryBean.setFilters(filters);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(loadFilterChainDefinitionMap());
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        //调试关闭权限缓存
        myShiroRealm.setAuthorizationCachingEnabled(false);
        myShiroRealm.setCredentialsMatcher(simpleCredentialsMatcher());
        return myShiroRealm;
    }

    @Bean
    public SimpleCredentialsMatcher simpleCredentialsMatcher() {
        return new SimpleCredentialsMatcher();
    }

    /**
     * 加载权限配置
     *
     * @return
     */
    private Map<String, String> loadFilterChainDefinitionMap() {
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        List<Map<String, Object>> ms = getAllMenus();
        List<String> anonUrls = properties.getUrl();
        for (String anon : anonUrls) {
            filterChainDefinitionMap.put(anon, "anon");
        }
        ms.stream().filter(m -> m.get("url") != null && !m.get("url").equals("#") && m.get("perms") != null)
                .filter(m -> !anonUrls.contains(m.get("url")))
                .forEach(m -> filterChainDefinitionMap.put(String.valueOf(m.get("url")), "jtf,cpf[" + m.get("perms") + "]"));
        filterChainDefinitionMap.put("/**", "jtf");
        return filterChainDefinitionMap;
    }

    /**
     * 获取所有权限菜单
     *
     * @return
     */
    public List<Map<String, Object>> getAllMenus() {
        return jdbcTemplate.queryForList("SELECT * FROM sys_menu ORDER BY menu_id ASC");
    }


}

