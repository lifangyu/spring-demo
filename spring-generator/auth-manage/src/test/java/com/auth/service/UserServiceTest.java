/**  
 * @(#)UserServiceTest.java	  2016年7月13日 下午3:31:40
 *
 * Copyright 2016 Lify, Inc. All rights reserved.
 * Lify PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.auth.service;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.auth.controller.service.UserService;

/**
 * userService 测试类
 * @author lifangyu
 * @version V1.0
 */
public class UserServiceTest extends BaseServiceTest {

    /**
     * // UserService userService = (UserService)SpringContextUtil.getBean("userService");
     */
    @Autowired
    private UserService userService;
    
    
    @Test
    public void serviceTest(){

        logger.info(ReflectionToStringBuilder.toString(userService.findUserByUserName("admin"),ToStringStyle.SHORT_PREFIX_STYLE));
    }

}
