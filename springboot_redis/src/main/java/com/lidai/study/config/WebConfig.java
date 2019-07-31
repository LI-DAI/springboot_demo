package com.lidai.study.config;

import com.lidai.study.aspect.CustomerFilter;
import com.lidai.study.aspect.LoggerInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @auther lidai
 * @create 2019/7/4 18:55
 */
@Configuration
public class WebConfig implements WebMvcConfigurer{

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggerInterceptor()).addPathPatterns("/*").excludePathPatterns("/.js");
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(customerFilter());
        filterRegistrationBean.addUrlPatterns("/**");
        return filterRegistrationBean;
    }

    @Bean
    public CustomerFilter customerFilter(){
        return new CustomerFilter();
    }

}
