/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.lidai.study.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author lidai
 * @date 2018/10/18 14:40
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AspectConfig {
/**
 *
 * 1. 注解@EnableAspectJAutoProxy开启代理;
 *
 * 2. proxyTargetClass默认为false, 表示使用jdk动态代理织入增强;
 *
 * 3. proxyTargetClass设置为true，表示使用cglib动态代理技术织入增强;
 *
 * 4. 如果属性proxyTargetClass设置为false，但是目标类没有声明接口，
 *    spring aop还是会使用cglib动态代理，也就是说非接口的类要生成代理都用Cglib。
 */

}

