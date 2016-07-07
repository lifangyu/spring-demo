/**  
 * @(#)LoginServiceImpl.java	  2016年7月6日 上午10:50:45
 *
 * Copyright 2016 Lify, Inc. All rights reserved.
 * Lify PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.auth.controller.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.controller.service.LoginService;
import com.auth.dao.AuthMenuMapper;
import com.auth.model.AuthMenu;

/**
 * 登录服务
 * @author lifangyu
 * @version V1.0
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthMenuMapper authMenuMapper;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.auth.controller.service.LoginService#getMenusByUserIdPid(java.lang.Long)
     */
    @Override
    public List<AuthMenu> getMenusByUserIdPid(Long userId, Long parentId) {
        return authMenuMapper.getMenusByUserIdPid(userId, parentId);
    }

}
