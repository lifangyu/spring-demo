/**  
 * @(#)SystemLogAnnotation.java	  2016年7月9日 下午6:11:22
 *
 * Copyright 2016 Lify, Inc. All rights reserved.
 * Lify PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.auth.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.auth.enums.SystemLogTypeEnum;

/**
 * 自定义注解—系统日志记录注解
 * @author lifangyu
 * @version V1.0
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface SystemLogAnnotation {
    /**
     * 接口信息描述
     *
     * @author lifangyu
     * @return
     *      String
     */
    String message() default "";

    /**
     * 系统类型[详见SystemLogType]
     *
     * @author lifangyu
     * @return
     *      SystemLogType
     */
    SystemLogTypeEnum systemLogTypeEnum() default SystemLogTypeEnum.OTHER;

    /**
     * 是否序列化参数 [如果入参时 Object[Map/List/javaBean/]为 true或不填,是number/String/boolean 为false]
     *
     * @author lifangyu
     * @return
     *      boolean
     */
    boolean SerialParam() default true;

    /**
     * 是否序列化返回值 [如果返回值是Object[Map/List/javaBean/建议为 true或不填,是number/String/boolean 为false]
     *
     * @author lifangyu
     * @return
     *      boolean
     */
    boolean SerialReturnValue() default true;

}
