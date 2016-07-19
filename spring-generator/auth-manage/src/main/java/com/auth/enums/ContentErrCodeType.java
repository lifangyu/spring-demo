/**  
 * @(#)ContentErrCodeType.java	  2016年7月19日 下午3:31:07
 *
 * Copyright 2016 Lify, Inc. All rights reserved.
 * Lify PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.auth.enums;

/**
 * 请求错误代码定义
 * @author lifangyu
 * @version V1.0
 */
public enum ContentErrCodeType {
    /**
     * 各方法自定义正整数区间 2-99(包含2和99) 的含义.
     * 这里面不要用, Each method uses 2-99(Contains 2 and  99) to define its meaning,Don't use 1-99 here.
     */
    REQUEST_SUCCESS("0", "请求数据成功"),

    REQUEST_FAIL("1", "请求数据失败"),

    NOT_LOGIN("100", "请登录"),

    REQUEST_PARAM_ERROE("1001", "请求参数出错"),

    DB_UNKNOWN_COLUMN_ERROR("1054", "有未知的字段");

    private String code;
    private String name;

    private ContentErrCodeType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return Integer.valueOf(code);
    }

    public String getName() {
        return name;
    }
}
