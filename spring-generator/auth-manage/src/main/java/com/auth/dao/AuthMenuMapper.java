package com.auth.dao;

import com.auth.model.AuthMenu;

public interface AuthMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AuthMenu record);

    int insertSelective(AuthMenu record);

    AuthMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AuthMenu record);

    int updateByPrimaryKey(AuthMenu record);
}