/**  
 * @(#)ZTreeNode.java	  2016年7月5日 下午3:10:01
 *
 * Copyright 2016 Lify, Inc. All rights reserved.
 * Lify PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.spring.common.utils.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 菜单 zTree 实体bean
 * @author lifangyu
 * @version V1.0
 */
public class ZTreeNode implements Serializable {

    private static final long serialVersionUID = -3749360259027337279L;
    /**
     * 当前node的id
     */
    private String id;
    /**
     * 当前树节点的父节点
     */
    private String pId;
    /**
     * 当前树节点的名称
     */
    private String name;
    /**
     * 前面的小图标样式
     */
    private String icon;
    /**
     * 是否展开，默认为展开状态
     */
    private String open = "true";
    
    /**
     * 菜单执行路径
     */
    private String file;
    /**
     * 子节点
     */
    private List<ZTreeNode> children = new ArrayList<ZTreeNode>();
    /**
     * 其他参数
     */
    private Map<String, String> attributes;

    /**
     * sort 左侧树排序标示位
     */
    private Integer sort;
    
    public ZTreeNode() {
    }

    public ZTreeNode(String id, String pId, String name, String icon) {
        this.id = id;
        this.pId = pId;
        this.name = name;
        this.icon = icon;
    }

    public ZTreeNode(String id, String pId, String name, String open, String icon) {
        this.id = id;
        this.pId = pId;
        this.name = name;
        this.open = open;
        this.icon = icon;
    }

    public ZTreeNode(String id, String pId, String name, String icon, Integer sort) {
        this.id = id;
        this.pId = pId;
        this.name = name;
        this.icon = icon;
        this.sort = sort;
    }

    public ZTreeNode(String id, String pId, String name, String open, String icon, Integer sort) {
        this.id = id;
        this.pId = pId;
        this.name = name;
        this.open = open;
        this.icon = icon;
        this.sort = sort;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getPid() {
        return open;
    }

    public void setPId(String pId) {
        this.pId = pId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public List<ZTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<ZTreeNode> children) {
        this.children = children;
    }


    /**
     * @return the pId
     */
    public String getpId() {
        return pId;
    }

    /**
     * @param pId the pId to set
     */
    public void setpId(String pId) {
        this.pId = pId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the open
     */
    public String getOpen() {
        return open;
    }

    /**
     * @param open the open to set
     */
    public void setOpen(String open) {
        this.open = open;
    }

    /**
     * @return the url
     */
    public String getFile() {
        return file;
    }

    /**
     * @param url the url to set
     */
    public void setFile(String file) {
        this.file = file;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void add(ZTreeNode node) {
        if ("".equals(node.getPid())) {
            this.children.add(node);
        } else if (node.getPid() != null && node.getPid().equals(this.id)) {
            this.children.add(node);
        } else {
            for (ZTreeNode tmp_node : children) {
                tmp_node.add(node);
            }
        }
    }
}
