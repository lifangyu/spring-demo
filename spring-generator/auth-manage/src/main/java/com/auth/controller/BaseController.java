package com.auth.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth.enums.ContentErrCodeType;
import com.auth.exception.CommonException;
import com.auth.utils.MapUtil;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

/** Controller 基础类
 * @author lifangyu
 * @version V1.0
 */
public class BaseController<T extends BaseController<T>> {


    protected Logger logger = null;

    public BaseController() {
        /*Class<T> subclass = ((Class<T>) ((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]);
        logger = LoggerFactory.getLogger(subclass);*/
        logger = LoggerFactory.getLogger(this.getClass());
    }

    @ResponseBody
    @ExceptionHandler
    public JSON exception(HttpServletRequest request, Exception e) {
        
        // 添加自己的异常处理逻辑，如日志记录
        logger.error("", e);

        Map<String, Object> map = null;
        
        int errorCode = 0;
        
        // 根据不同的异常类型进行不同处理
        
        if (e instanceof BadSqlGrammarException) {
            BadSqlGrammarException bException = (BadSqlGrammarException) e;
            if (bException.getCause() instanceof MySQLSyntaxErrorException) {
                MySQLSyntaxErrorException mysqlEx = (MySQLSyntaxErrorException) bException.getCause();
                errorCode = mysqlEx.getErrorCode();
            }
            map = MapUtil.getAppMap(errorCode, ContentErrCodeType.DB_UNKNOWN_COLUMN_ERROR.getName(), e.getMessage());
        }
        
        
        if (e instanceof MySQLIntegrityConstraintViolationException) {
            MySQLIntegrityConstraintViolationException mysqlEx = (MySQLIntegrityConstraintViolationException) e;
            map = MapUtil.getAppMap(mysqlEx.getErrorCode(), "数据库查询出错", mysqlEx.getMessage());
            
        }
        
        if (e instanceof IllegalArgumentException) {
            IllegalArgumentException argument = (IllegalArgumentException) e;
            map = MapUtil.getAppMap(errorCode, ContentErrCodeType.DB_UNKNOWN_COLUMN_ERROR.getName(), argument.getMessage());
        }
        
        if (e instanceof NullPointerException) {
            NullPointerException nnullEx = (NullPointerException) e;
            map = MapUtil.getAppMap(Integer.valueOf(errorCode), nnullEx.getMessage(), "数据不可以为空");
        }
        
        if (e instanceof CommonException) {
            CommonException ifangEx = (CommonException) e;
            map = MapUtil.getAppMap(Integer.valueOf(ifangEx.getCode()), ifangEx.getMessage(), e.getMessage());
        }
        
        if (map == null) {
            map = MapUtil.getAppMap(10000, "出现末捕获的异常错误！", e.getMessage());
        }

        return new JSONObject(map);
    }

    @ResponseBody
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Object missingParamterHandler(HttpServletRequest request, HttpServletResponse response, final MissingServletRequestParameterException e) {
        
        logger.error("", e);
        
        String pathInfo = request.getRequestURI();
        
        logger.info("请求的路径为：{}", pathInfo);
        
        String paramName = e.getParameterName();
        
        logger.info("请求的参数名为：{}", paramName);
        
        
        String paramType = e.getParameterType();
        
        logger.info("请求的参数类型为：{}", paramType);
        
        // 参数验证 可通过配置文件配置
        String message = "";// PropertiesUtils.getString(paramName);
        if (StringUtils.isBlank(message)) {
            message = "必填参数不能为空";// PropertiesUtils.getString("paramNotNull");
        }
        
        String[] messages = message.split("&");
        
        logger.info("提示信息为：{}", message);
        
        
        Map<String, Object> map = MapUtil.getAppMap(Integer.valueOf(messages[0]), messages[1], e.getMessage());
        
        return new JSONObject(map);

    }

}
