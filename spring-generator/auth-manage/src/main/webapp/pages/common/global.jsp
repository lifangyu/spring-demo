<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- jstl 标签的引入 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 			prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" 			prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" 	prefix="fn"%>

<%
String path = request.getContextPath();
String port = request.getServerPort()==80?"":":"+request.getServerPort();
String basePath = request.getScheme()+"://"+request.getServerName()+port+path+"/";
%>
<c:set value="<%=basePath %>" var="ctx"/>
<c:set var="moneyexp" value="#,##0.00#"></c:set>
<c:set var="moneyexpInt" value="#"></c:set>

<!-- js 插件的引入  -->
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.11.3.min.js"></script>
<%-- <link href="${ctx }css/common.css" type="text/css" rel="stylesheet"> --%>

</head>
</html>



