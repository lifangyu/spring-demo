<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/static/jquery-easyui-1.4.5/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/jquery-easyui-1.4.5/themes/default/easyui.css" />
<script type="text/javascript" src="${ctx}/static/jquery-easyui-1.4.5/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/static/jquery-easyui-1.4.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/static/jquery-easyui-1.4.5/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="${ctx}/static/zTree_v3.5.16/css/demo.css" type="text/css">
<link rel="stylesheet" href="${ctx}/static/zTree_v3.5.16/css/zTreeStyle/zTreeStyle.css" type="text/css">
<%-- <script type="text/javascript" src="${ctx}/static/zTree_v3.5.16/js/jquery-1.4.4.min.js"></script> --%>
<script type="text/javascript" src="${ctx}/static/zTree_v3.5.16/js/jquery.ztree.core-3.5.js"></script>
</head>
</html>



