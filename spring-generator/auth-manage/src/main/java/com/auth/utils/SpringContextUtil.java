/**  
 * @(#)SpringContextUtils.java	  2016年7月13日 下午1:53:22
 *
 * Copyright 2016 Lify, Inc. All rights reserved.
 * Lify PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.auth.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获取spring ApplicationContext上下文 beanFactory中注入的bean实例通用工具类
 * @author lifangyu
 * @version V1.0
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static Logger logger = LoggerFactory.getLogger(SpringContextUtil.class);

    /**
     * Spring应用上下文环境
     */
    private static ApplicationContext applicationContext;

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     */
    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        SpringContextUtil.applicationContext = context;
    }


    /**
     * 获取String的ApplicationContext对象
     *
     * @author lifangyu
     * @return
     *      ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 
     * 获取spring容器中的对象
     *<p>获得注入的bean 需要在注解的时候指定名称[@Service("beanname")，对应获取 (接口Service) SpringContextUtil.getBean("beanname")],否则可能会出异常NoSuchBeanDefinitionException<p>
     * @author lifangyu
     * @param name
     * @return
     *      T
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanName) {
        return (T) applicationContext.getBean(beanName);
    }

    /**
     * 如果applicationContext包含一个与所给名称匹配的bean定义，则返回true
     *
     * @author lifangyu
     * @param beanName
     * @return
     *      boolean
     */
    public static boolean containsBean(String beanName) {
        return applicationContext.containsBean(beanName);
    }

    /**
     * 
     * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。
     * 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）  
     *
     * @author lifangyu
     * @param beanName
     * @return
     * @throws NoSuchBeanDefinitionException
     *      boolean
     */
    public static boolean isSingleton(String beanName) throws NoSuchBeanDefinitionException {
        return applicationContext.isSingleton(beanName);
    }

    /**
     * 
      Class 注册对象的类型
     *
     * @author lifangyu
     * @param beanName
     * @return
     * @throws NoSuchBeanDefinitionException
     *      Class
     */
    @SuppressWarnings("rawtypes")
    public static Class getType(String beanName) throws NoSuchBeanDefinitionException {
        return applicationContext.getType(beanName);
    }

    /**
     * 如果给定的bean名字在bean定义中有别名，则返回这些别名  
     *
     * @author lifangyu
     * @param beanName
     * @return
     * @throws NoSuchBeanDefinitionException
     *      String[]
     */
    public static String[] getAliases(String beanName) throws NoSuchBeanDefinitionException {
        return applicationContext.getAliases(beanName);
    }

}
