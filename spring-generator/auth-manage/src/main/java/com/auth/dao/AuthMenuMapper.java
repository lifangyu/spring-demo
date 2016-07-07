package com.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.auth.model.AuthMenu;

public interface AuthMenuMapper {

    int deleteByPrimaryKey(Long id);

    int insert(AuthMenu record);

    int insertSelective(AuthMenu record);

    AuthMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AuthMenu record);

    int updateByPrimaryKey(AuthMenu record);


    /**
     * 通过用户Id和父节点查询其菜单
     *
     * @author lifangyu
     * @param userId
     * @param parentId
     * @return
     * 		List<AuthMenu>
     */
    List<AuthMenu> getMenusByUserIdPid(Long userId, @Param(value = "pid") Long parentId);
}