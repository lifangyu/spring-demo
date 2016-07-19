package com.auth.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜单表Auth_Menu
 * @author lifangyu
 * @version V1.0
 */
public class AuthMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单url
     */
    private String menuUrl;

    /**
     * 菜单图标url
     */
    private String iconUrl;

    /**
     * 上级菜单ID (如果是根菜单则为 0)
     */
    private Long parentId;

    /**
     * 是否文件夹[1是，0不是]
     */
    private Integer folder;

    /**
     * 状态 [1有效,0无效]
     */
    private Integer status;

    /**
     * 排序序列
     */
    private Integer sort;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 最后一次更新时间
     */
    private Date updateDate;

    /**
     * @return  主键
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id   主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return  菜单名称
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * @param menuName   菜单名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    /**
     * @return  菜单url
     */
    public String getMenuUrl() {
        return menuUrl;
    }

    /**
     * @param menuUrl   菜单url
     */
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    /**
     * @return  菜单图标url
     */
    public String getIconUrl() {
        return iconUrl;
    }

    /**
     * @param iconUrl   菜单图标url
     */
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl == null ? null : iconUrl.trim();
    }

    /**
     * @return  上级菜单ID (如果是根菜单则为 0)
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * @param parentId   上级菜单ID (如果是根菜单则为 0)
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * @return  是否文件夹[1是，0不是]
     */
    public Integer getFolder() {
        return folder;
    }

    /**
     * @param folder   是否文件夹[1是，0不是]
     */
    public void setFolder(Integer folder) {
        this.folder = folder;
    }

    /**
     * @return  状态 [1有效,0无效]
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status   状态 [1有效,0无效]
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return  排序序列
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * @param sort   排序序列
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * @return  创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate   创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return  最后一次更新时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate   最后一次更新时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

}