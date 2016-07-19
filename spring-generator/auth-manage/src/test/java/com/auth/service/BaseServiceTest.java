/**  
 * @(#)BaseServiceTest.java	  2016年7月13日 下午3:27:23
 *
 * Copyright 2016 Lify, Inc. All rights reserved.
 * Lify PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.auth.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * service 单元测试基类
 * @author lifangyu
 * @version V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml", "classpath:spring/spring-mvc.xml" })
public class BaseServiceTest {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before
    public void init() {
        logger.info("=========junit service test 开始=========");
    }

    @After
    public void destroy() {
        logger.info("=========junit service test 结束=========");
    }

    @Test
    public void serviceTest() {
        logger.info("===========BaseServiceTest===============");
    }

}
