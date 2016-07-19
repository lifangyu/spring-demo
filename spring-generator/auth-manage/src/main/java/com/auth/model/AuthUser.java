package com.auth.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * auth_user 表实体类
 * @author lifangyu
 * @version V1.0
 */
public class AuthUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户真实姓名
     */
    private String realName;

    /**
     * 加密后的密码
     */
    private String password;

    /**
     * 密码加密的盐
     */
    private String salt;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * QQ
     */
    private String qq;

    /**
     * 是否有效[1有效,0无效]
     */
    private Integer status;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 最后一次更新时间
     */
    private Date updateDate;

    /**
     * @return 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 
	 *            主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName 
	 *            用户名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * @return 用户真实姓名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * @param realName 
	 *            用户真实姓名
     */
    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    /**
     * @return 加密后的密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password 
	 *            加密后的密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * @return 密码加密的盐
     */
    public String getSalt() {
        return salt;
    }

    /**
     * @param salt 
	 *            密码加密的盐
     */
    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    /**
     * @return 手机
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile 
	 *            手机
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * @return 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email 
	 *            邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * @return QQ
     */
    public String getQq() {
        return qq;
    }

    /**
     * @param qq 
	 *            QQ
     */
    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    /**
     * @return 是否有效[1有效,0无效]
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            是否有效[1有效,0无效]
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return 部门ID
     */
    public Long getDeptId() {
        return deptId;
    }

    /**
     * @param deptId 
	 *            部门ID
     */
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    /**
     * @return 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate 
	 *            创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return 最后一次更新时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate 
	 *            最后一次更新时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}