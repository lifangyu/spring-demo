/**  
 * @(#)EasyuiTreeNode.java	  2016年7月5日 下午3:10:01
 *
 * Copyright 2016 Lify, Inc. All rights reserved.
 * Lify PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.spring.common.utils.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 菜单
 * @author lifangyu
 * @version V1.0
 */
public class EasyuiTreeNode {

    private static final long serialVersionUID = 1L;

    /**
     * 当前node的id
     */
    private String id;
    /**
     * 当前树节点的名称
     */
    private String text;
    /**
     * 前面的小图标样式
     */
    private String iconCls;
    /**
     * 是否勾选状态
     */
    private Boolean checked = false;
    /**
     * 其他参数
     */
    private Map<String, String> attributes;
    /**
     * 子节点
     */
    private List<EasyuiTreeNode> children = new ArrayList<EasyuiTreeNode>();
    /**
     * 是否展开(open,closed)，默认为展开状态
     */
    private String state = "open";
    /**
     * 父节点id
     */
    private String pid;

    /**
     * sort 左侧树排序标示位
     */
    private Integer sort;

    public EasyuiTreeNode(String id, String pid, String text, String iconCls) {
        this.id = id;
        this.pid = pid;
        this.text = text;
        this.iconCls = iconCls;
    }

    public EasyuiTreeNode(String id, String pid, String text, String state, String iconCls) {
        this.id = id;
        this.pid = pid;
        this.text = text;
        this.state = state;
        this.iconCls = iconCls;
    }

    public EasyuiTreeNode(String id, String pid, String text, String iconCls, Integer sort) {
        this.id = id;
        this.pid = pid;
        this.text = text;
        this.iconCls = iconCls;
        this.sort = sort;
    }

    public EasyuiTreeNode(String id, String pid, String text, String state, String iconCls, Integer sort) {
        this.id = id;
        this.pid = pid;
        this.text = text;
        this.state = state;
        this.iconCls = iconCls;
        this.sort = sort;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public List<EasyuiTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<EasyuiTreeNode> children) {
        this.children = children;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public void add(EasyuiTreeNode node) {
        if ("".equals(node.getPid())) {
            this.children.add(node);
        } else if (node.getPid() != null && node.getPid().equals(this.id)) {
            this.children.add(node);
        } else {
            for (EasyuiTreeNode tmp_node : children) {
                tmp_node.add(node);
            }
        }
    }
}
