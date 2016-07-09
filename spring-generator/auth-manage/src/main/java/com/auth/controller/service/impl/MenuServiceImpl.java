/**  
 * @(#)MenuServiceImpl.java	  2016年7月9日 下午6:01:45
 *
 * Copyright 2016 Lify, Inc. All rights reserved.
 * Lify PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.auth.controller.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.annotation.SystemLogAnnotation;
import com.auth.controller.service.LoginService;
import com.auth.controller.service.MenuService;
import com.auth.enums.SystemLogTypeEnum;
import com.auth.model.AuthMenu;
import com.spring.common.utils.entity.ZTreeNode;

/**
 * 菜单服务类
 * @author lifangyu
 * @version V1.0
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private LoginService loginService;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.auth.controller.service.MenuService#getMenuNodeList(java.lang.Long)
     */
    @Override
    @SystemLogAnnotation(message = "系统菜单", systemLogTypeEnum = SystemLogTypeEnum.MENU, SerialParam = false, SerialReturnValue = true)
    public List<ZTreeNode> getMenuNodeList(Long userId) {
        // 根菜单 parenId=0
        List<AuthMenu> menus = loginService.getMenusByUserIdPid(userId, 0L);
        List<ZTreeNode> nodeList = new ArrayList<>();// 菜单跟目录集合
        for (Iterator<AuthMenu> iterator = menus.iterator(); iterator.hasNext();) {
            AuthMenu authMenu = (AuthMenu) iterator.next();
            // 一级菜单对象( 一级菜单展开，二级三级等菜单不展开)
            ZTreeNode node = new ZTreeNode(String.valueOf(authMenu.getId()), String.valueOf(authMenu.getParentId()),
                    authMenu.getMenuName(), authMenu.getFolder() == 1 ? "true" : "false", authMenu.getIconUrl(),
                    authMenu.getSort());
            if (authMenu.getFolder() == 1) {
                List<AuthMenu> menuChileds = loginService.getMenusByUserIdPid(userId, authMenu.getId());
                List<ZTreeNode> nodeChileList = new ArrayList<>();
                for (Iterator<AuthMenu> iteratorsec = menuChileds.iterator(); iteratorsec.hasNext();) {
                    AuthMenu authMenuChiled = (AuthMenu) iteratorsec.next();
                    // 一级菜单下的子菜单对象(二级菜单)
                    ZTreeNode sec = new ZTreeNode(String.valueOf(authMenuChiled.getId()),
                            String.valueOf(authMenuChiled.getParentId()), authMenuChiled.getMenuName(), "false",
                            authMenuChiled.getIconUrl(), authMenuChiled.getSort());
                    if (authMenuChiled.getFolder() == 1) {
                        // 二级菜单下的子菜单(三级菜单)
                        sec.setChildren(this.getMenuNodeListNext(userId, authMenuChiled));
                    } else {
                        sec.setFile(authMenuChiled.getMenuUrl());
                    }
                    nodeChileList.add(sec);
                }
                node.setChildren(nodeChileList);
            } else {
                node.setFile(authMenu.getMenuUrl());
            }
            nodeList.add(node);
        }

        return nodeList;
    }

    /**
     * 级联菜单 递归处理
     *
     * @author lifangyu
     * @param nodeList 
     * @param userId
     * @param authMenuChiled
     * @return
     *      List<ZTreeNode>
     */
    private List<ZTreeNode> getMenuNodeListNext(long userId, AuthMenu authMenuChiled) {
        List<ZTreeNode> nodeList = new ArrayList<>();
        List<AuthMenu> menuChileds = loginService.getMenusByUserIdPid(userId, authMenuChiled.getId());
        for (Iterator<AuthMenu> iterators = menuChileds.iterator(); iterators.hasNext();) {
            AuthMenu authMenuThird = (AuthMenu) iterators.next();
            // 三级菜单对象
            ZTreeNode third = new ZTreeNode(String.valueOf(authMenuThird.getId()),
                    String.valueOf(authMenuThird.getParentId()), authMenuThird.getMenuName(), "false",
                    authMenuThird.getIconUrl(), authMenuThird.getSort());
            if (authMenuThird.getFolder() == 1) {
                // 三级菜单对象下的子菜单(以此递归)
                third.setChildren(this.getMenuNodeListNext(userId, authMenuThird));
            } else {
                third.setFile(authMenuThird.getMenuUrl());
            }
            nodeList.add(third);
        }
        return nodeList;
    }

}
