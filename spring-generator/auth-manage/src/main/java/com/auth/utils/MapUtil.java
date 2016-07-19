/**  
 * @(#)Test.java	  2016年7月19日 下午3:29:14
 *
 * Copyright 2016 Lify, Inc. All rights reserved.
 * Lify PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.auth.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 接口返回前端map工具类
 * @author lifangyu
 * @version V1.0
 */
public class MapUtil {

    /**
     * @param return_code 返回状态的码 0:成功，10001：系统异常，其他的状态码可以根据情况来设计
     * @param result_message 提示语言
     * @param result_info 传递的对象，可以为null，对象
     * @param 设定文件o
     * @return Map 返回类型
     * @Title: getAppMap
     * @Description: TODO(返回APP接口使用使用的返回数据)
     */
    public static Map<String, Object> getAppMap(Integer result_code, String result_message, Object result_info) {
        Map<String, Object> map = new HashMap<>();
        map.put("result_code", result_code);
        map.put("result_message", result_message);
        map.put("result_info", result_info);
        return map;
    }
}
