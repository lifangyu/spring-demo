/**  
 * @(#)SystemLogAspect.java	  2016年7月9日 下午6:17:34
 *
 * Copyright 2016 Lify, Inc. All rights reserved.
 * Lify PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.auth.aspect;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auth.annotation.SystemLogAnnotation;
import com.auth.enums.SystemLogTypeEnum;
import com.auth.model.AuthUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 系统日志的切面类
 * @author lifangyu
 * @version V1.0
 */
@Aspect
@Component
public class SystemLogAspect {

    private static Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

    /*
     * 请求时的request，可以获得请求时的入参
     */
    @Autowired
    private HttpServletRequest request;

    /*
     * com.yfjr.包下 有有使用注解@SystemLogAnnotation 的会执行此切点
     */
    public static final String LOG_POINTCUT = "within(com.auth..*) && @annotation(com.auth.annotation.SystemLogAnnotation)";

    /**
     * 服务层的切入点
     */
    @Pointcut(LOG_POINTCUT)
    public void logPointcut() {
    }

    /*
     * 后置通知，当目标方法执行成功后执行该方法体
     * 
     * @annotation(systemLogAnnotation) 注解的入参
     */
    @AfterReturning(value = "SystemLogAspect.logPointcut() && @annotation(systemLogAnnotation) ", returning = "returnValue")
    public void invokeTargetSuccess(JoinPoint joinPoint, SystemLogAnnotation systemLogAnnotation, Object returnValue) {
        try {
            this.handleSystemLog(joinPoint, systemLogAnnotation, returnValue);
        } catch (Exception e) {
            logger.error("SystemLogAspect.invokeTargetSuccess 日志的处理异常", e);
        }
    }

    /*
     * 异常通知，当目标方法出现异常时，执行该方法体
     * 
     * @annotation(systemLogAnnotation) 注解的入参
     */
    @AfterThrowing(value = "SystemLogAspect.logPointcut() && @annotation(systemLogAnnotation)", throwing = "exception")
    public void invokeTargetException(JoinPoint joinPoint, SystemLogAnnotation systemLogAnnotation,
            Exception exception) {
        try {
            this.handleSystemLog(joinPoint, systemLogAnnotation, exception);
        } catch (Exception e) {
            logger.error("SystemLogAspect.invokeTargetException 日志的处理异常", e);
        }
    }

    /**
     * 日志处理方法
     * <p>
     * 可以输出到系统系统/数据库/日志文件
     * </p>
     *
     * @author lifangyu
     * @param joinPoint
     * @param systemLogAnnotation
     * @param returnValue
     *            void
     * @throws JsonProcessingException 
     */
    private void handleSystemLog(JoinPoint joinPoint, SystemLogAnnotation systemLogAnnotation, Object object)
            throws JsonProcessingException {

        // 目标方法的参数
        Object[] params = joinPoint.getArgs();
        // 目标方法
        String method = joinPoint.getSignature().toString();
        // 目标方法返回值
        Object returnValue = object;
        // 注解的值
        String message = systemLogAnnotation.message();
        // 日志类型
        SystemLogTypeEnum logType = systemLogAnnotation.systemLogTypeEnum();

        // 序列化后的入参字符串
        String jsonParams = "";
        // 系列化后的返回值字符串
        String returnJson = "";

        String userName = "";

        AuthUser user = (AuthUser) request.getSession().getAttribute("user");

        if (user != null) {
            userName = user.getUserName();
        }

        // 参数是否序列化
        if (systemLogAnnotation.SerialParam() && params != null) {
            jsonParams = new ObjectMapper().writeValueAsString(params);
        } else {
            if (params != null) {
                for (Object param : params) {
                    jsonParams += jsonParams.equals("") ? "[" + param.toString() : "|" + param.toString();
                }
                jsonParams += "]";
            }
        }

        // 异常的处理
        if (object instanceof Exception) {
            // 异常信息
            returnJson = getStackTrace((Exception) object);
        } else {
            if (systemLogAnnotation.SerialReturnValue()) {
                if (returnValue instanceof List<?>) {
                    returnJson = new ObjectMapper().writeValueAsString(returnValue);
                } else {
                    returnJson = new ObjectMapper().writeValueAsString(returnValue);
                }
            } else {
                returnJson = returnValue != null ? returnValue.toString() : "null";
            }
        }
        // 日志的处理
        logger.info("{}-{},执行的方法:{},入参:{},执行的结果:{},操作人:{}", logType.toString(), message, method, jsonParams, returnJson, userName);
    }

    /**
     * 获取异常的堆栈信息
     * 
     * @author xiaobowen
     * @param throwable
     * @return
     */
    private static String getStackTrace(Throwable throwable) {
        StringBuilder sb = new StringBuilder();
        sb.append(throwable.toString()).append(System.getProperty("line.separator")).append("    ");
        for (StackTraceElement ste : throwable.getStackTrace()) {
            sb.append(ste.toString()).append(System.getProperty("line.separator")).append("    ");
        }
        return sb.toString();
    }
}
