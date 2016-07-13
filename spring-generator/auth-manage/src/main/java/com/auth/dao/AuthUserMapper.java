package com.auth.dao;

import com.auth.model.AuthUser;

public interface AuthUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AuthUser record);

    int insertSelective(AuthUser record);

    AuthUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AuthUser record);

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