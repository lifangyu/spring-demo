package com.auth.dao;

import com.auth.model.AuthUser;

/**
 * 
 * auth_user 表对应的dao层接口
 * @author lifangyu
 * @version V1.0
 */
public interface AuthUserMapper {
    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(AuthUser record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(AuthUser record);

    /**
     * 根据主键查询记录
     */
    AuthUser selectByPrimaryKey(Long id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(AuthUser record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(AuthUser record);

    /**
     * 用户名查询用户对象
     *
     * @author lifangyu
     * @param userName
     * @return
     * 		AuthUser
     */
    AuthUser selectByUserName(String userName);
}