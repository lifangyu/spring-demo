<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<!-- jstl 标签的引入 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 			prefix="c"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" 			prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" 	prefix="fn"  %>

<%
String path = request.getContextPath();
String port = request.getServerPort()==80?"":":"+request.getServerPort();
String basePath = request.getScheme()+"://"+request.getServerName()+port+path+"/";
%>
<c:set value="<%=basePath %>" var="ctx"/>
<c:set var="moneyexp" value="#,##0.00#"></c:set>
<c:set var="moneyexpInt" value="#"></c:set>

<!-- js 插件的引入  -->
<link href="<%=request.getContextPath() %>/static/css/default.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}static/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/zTreeStyle/zTreeStyle.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/static/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/ztree/js/jquery.ztree.all-3.5.min.js"></script>

<%-- <script type="text/javascript" >
	//全局的ajax访问，处理ajax清求时sesion超时
	$.ajaxSetup({
		contentType:"application/x-www-form-urlencoded;charset=utf-8",
		complete:function(XMLHttpRequest,textStatus){
			//通过XMLHttpRequest取得响应头，sessionstatus，
			var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus"); 
			if(sessionstatus=="timeout"){
			//如果超时就处理 ，指定要跳转的页面
				window.location.reload("/lend-app-report/");
			}
			
			if(XMLHttpRequest.status==555){
				var location = XMLHttpRequest.getResponseHeader("Location");
				self.parent.window.location=location;
			}
		}
	});
</script> --%>

