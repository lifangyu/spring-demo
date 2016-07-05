<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>

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
<link rel="stylesheet" type="text/css" href="${ctx}/static//jquery-easyui-1.4.5/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static//jquery-easyui-1.4.5/themes/default/easyui.css" />
<script type="text/javascript" src="${ctx}/static/jquery-easyui-1.4.5/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/static/jquery-easyui-1.4.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/static/jquery-easyui-1.4.5/locale/easyui-lang-zh_CN.js"></script>

<!-- <script type="text/javascript" >
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
</script> -->

