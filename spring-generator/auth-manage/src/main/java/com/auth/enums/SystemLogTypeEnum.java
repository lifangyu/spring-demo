/**  
 * @(#)SystemLogTypeEnum.java	  2016年7月9日 下午6:13:43
 *
 * Copyright 2016 Lify, Inc. All rights reserved.
 * Lify PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.auth.enums;

/**
 * 系统日志类型-模块枚举
 * @author lifangyu
 * @version V1.0
 */
public enum SystemLogTypeEnum {

    LOGIN("LOGIN", "登录"),

    MENU("MENU", "菜单"),

    SHIRO("SHIRO", "认证"),

    OTHER("OTHER", "其它");

    public String moduleCode;

    public String moduleName;

    /**
     * @param moduleCode
     * @param moduleName
     */
    private SystemLogTypeEnum(String moduleCode, String moduleName) {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
    }


    /**
     * @return the moduleCode
     */
    public String getModuleCode() {
        return moduleCode;
    }


    /**
     * @param moduleCode the moduleCode to set
     */
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }


    /**
     * @return the moduleName
     */
    public String getModuleName() {
        return moduleName;
    }


    /**
     * @param moduleName the moduleName to set
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }


    public String toString() {
        return "SystemLogTypeEnum[" + moduleCode + ":" + moduleName + "]";
    }
}
