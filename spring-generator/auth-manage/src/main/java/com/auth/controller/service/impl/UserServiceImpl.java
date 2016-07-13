/**  
 * @(#)UserServiceImpl.java	  2016年7月11日 下午3:03:06
 *
 * Copyright 2016 Lify, Inc. All rights reserved.
 * Lify PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.auth.controller.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.controller.service.UserService;
import com.auth.dao.AuthUserMapper;
import com.auth.model.AuthUser;

/**
 * 用户服务
 * @author lifangyu
 * @version V1.0
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AuthUserMapper authUserMapper;

    /* (non-Javadoc)
     * @see com.auth.controller.service.UserService#findUserByUserName(java.lang.String)
     */
    @Override
    public AuthUser findUserByUserName(String userName) {
        return authUserMapper.selectByUserName(userName);
    }

}
