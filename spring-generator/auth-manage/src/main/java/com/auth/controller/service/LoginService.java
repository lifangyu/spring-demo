/**  
 * @(#)LoginService.java	  2016年7月6日 上午10:50:15
 *
 * Copyright 2016 Lify, Inc. All rights reserved.
 * Lify PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.auth.controller.service;

import java.util.List;

import com.auth.model.AuthMenu;

/**
 * 登录服务接口
 * @author lifangyu
 * @version V1.0
 */
public interface LoginService {


    /**
     * 通过父节点查询其菜单
     *
     * @author lifangyu
     * @param parentId
     * @return
     *      List<AuthMenu>
     */
    List<AuthMenu> getMenusByUserIdPid(Long userId, Long parentId);

}
