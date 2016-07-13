/**  
 * @(#)UserService.java	  2016年7月11日 下午3:02:23
 *
 * Copyright 2016 Lify, Inc. All rights reserved.
 * Lify PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.auth.controller.service;

import com.auth.model.AuthUser;

/**
 * 用户服务接口
 * @author lifangyu
 * @version V1.0
 */
public interface UserService {

    /**
     * 登录名称查询用户
     *
     * @author lifangyu
     * @param username
     * @return
     * 		AuthUser
     */
    AuthUser findUserByUserName(String username);

}
