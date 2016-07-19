package com.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.auth.model.AuthMenu;

/**
 * auth_menu 表dao层接口
 * @author lifangyu
 * @version V1.0
 */
public interface AuthMenuMapper {

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(AuthMenu record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(AuthMenu record);

    /**
     * 根据主键查询记录
     */
    AuthMenu selectByPrimaryKey(Long id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(AuthMenu record);

    /**
     * 根据主键更新记录
     */
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