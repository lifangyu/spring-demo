/**  
 * @(#)ShiroRealm.java	  2016年7月9日 下午10:00:16
 *
 * Copyright 2016 Lify, Inc. All rights reserved.
 * Lify PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.auth.shiro;

import java.io.Serializable;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.auth.annotation.SystemLogAnnotation;
import com.auth.controller.service.UserService;
import com.auth.controller.service.impl.UserServiceImpl;
import com.auth.enums.SystemLogTypeEnum;
import com.auth.model.AuthUser;
import com.auth.utils.SpringContextUtil;
import com.spring.common.utils.arithmetic.Digests;
import com.spring.common.utils.arithmetic.DigestsEncodes;

/**
 * shiro 认证
 * @author lifangyu
 * @version V1.0
 */
@Component
public class ShiroRealm extends AuthorizingRealm {

    private static Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    private static final int INTERATIONS = 1024;

    private static final int SALT_SIZE = 8;

    private static final String ALGORITHM = "SHA-1";

    /*
     * 不能使用注解，原因：在创建队形的时候spring还没有实例化service,导致NullException;
     * 在使用的时候通过SpringContextUtil.getBean(beanName) 实例
     */
    private UserService userService;

    /**
     * 给ShiroRealm提供编码信息，用于密码密码比对
     */ 
    public ShiroRealm() {
        super();
        // logger.info("ShiroRealm init start...");
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(ALGORITHM);
        matcher.setHashIterations(INTERATIONS);
        setCredentialsMatcher(matcher);
    }

    /**
     * 授权方法
     * 
     */
    @Override
    @SystemLogAnnotation(message = "权限授权", systemLogTypeEnum = SystemLogTypeEnum.SHIRO, SerialParam = true, SerialReturnValue = true)
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
            throws AuthenticationException, UnauthorizedException {
        // TODO 通过用户名获得用户的所有资源，并把资源存入info中
        String userName = (String) getAvailablePrincipal(principals);
        ShiroUser shiroUser = (ShiroUser) principals.fromRealm(getName()).iterator().next();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        /*
         * List<UserRole> userRoles = userRoleService.find(shiroUser.getId());
         * shiroUser.getUser().setUserRoles(userRoles);
         * 
         * if (!userRoles.isEmpty()) { SimpleAuthorizationInfo info = new
         * SimpleAuthorizationInfo(); for (UserRole userRole : userRoles) { //
         * 基于Permission的权限信息
         * info.addStringPermissions(userRole.getRole().getPermissionList()); }
         * return info; } else { return null; }
         */
        return info;
    }

    /**
     * 认证方法
     */
    @Override
    @SystemLogAnnotation(message = "权限认证", systemLogTypeEnum = SystemLogTypeEnum.SHIRO, SerialParam = true, SerialReturnValue = true)
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
            throws AuthenticationException, UnauthorizedException,DisabledAccountException {
        // token中储存着输入的用户名和密码
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        // 获得用户名与密码
        String username = token.getUsername();
        String password = String.valueOf(token.getPassword());
        // 与数据库中用户名和密码进行比对。比对成功则返回info，比对失败则抛出对应信息的异常
        if (userService == null) {
            userService = (UserServiceImpl) SpringContextUtil.getBean("userService");
        }
        AuthUser user = userService.findUserByUserName(token.getUsername());
        if (user != null) {
            if (user.getStatus() == 0) {// 无效用户
                throw new DisabledAccountException();
            }
            byte[] salt = DigestsEncodes.decodeHex(user.getSalt());
            ShiroUser shiroUser = new ShiroUser(user.getId(), user.getUserName(), user);
            return new SimpleAuthenticationInfo(shiroUser, user.getPassword(), ByteSource.Util.bytes(salt), getName());
        } else {
            throw new AuthenticationException("userName:" + username + ";password:" + password + "不存在对应的用戶");
        }
    }

    public static class HashPassword {
        public String salt;
        public String password;
    }

    public HashPassword encrypt(String plainText) {
        HashPassword result = new HashPassword();
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        result.salt = DigestsEncodes.encodeHex(salt);

        byte[] hashPassword = Digests.sha1(plainText.getBytes(), salt, INTERATIONS);
        result.password = DigestsEncodes.encodeHex(hashPassword);
        return result;
    }

    /**
     * 更新用户授权信息缓存.
     */
    public void clearCachedAuthorizationInfo(String principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清除所有用户授权信息缓存.
     */
    public void clearAllCachedAuthorizationInfo() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }

    /**
     * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
     */
    public static class ShiroUser implements Serializable {

        private static final long serialVersionUID = -1748602382963711884L;
        private Long id;
        private String loginName;
        private AuthUser user;

        public ShiroUser() {

        }

        /**  
         * 构造函数
         * @param id
         * @param loginName
         * @param email
         * @param createTime
         * @param status  
         */
        public ShiroUser(Long id, String loginName, AuthUser user) {
            this.id = id;
            this.loginName = loginName;
            this.user = user;
        }

        /**  
         * 返回 id 的值   
         * @return id  
         */
        public Long getId() {
            return id;
        }

        /**  
         * 返回 loginName 的值   
         * @return loginName  
         */
        public String getLoginName() {
            return loginName;
        }

        /**  
         * 返回 user 的值   
         * @return user  
         */
        public AuthUser getUser() {
            return user;
        }

        /**
         * 本函数输出将作为默认的<shiro:principal/>输出.
         */
        @Override
        public String toString() {
            return loginName;
        }
    }

}
