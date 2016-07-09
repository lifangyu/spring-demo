/**  
 * @(#)MenuService.java	  2016年7月9日 下午6:01:17
 *
 * Copyright 2016 Lify, Inc. All rights reserved.
 * Lify PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.auth.controller.service;

import java.util.List;

import com.spring.common.utils.entity.ZTreeNode;

/**
 * 菜单接口
 * @author lifangyu
 * @version V1.0
 */
public interface MenuService {

    /**
     * 获取用户下有权限的菜单集合
     *
     * @author lifangyu
     * @param userId
     * @return
     * 		List<ZTreeNode>
     */
    List<ZTreeNode> getMenuNodeList(Long userId);

}
