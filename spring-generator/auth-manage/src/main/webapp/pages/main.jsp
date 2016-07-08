<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head id="Head1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>管理系统</title>
    <%@include file="common/global.jsp"%>
    <script type="text/javascript" src="${ctx }/static/easyui/menuStandardData.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/leftMenu.js"></script>

</head>
<body class="easyui-layout" style="overflow-y: hidden"  fit="true"   scroll="no">
<noscript>
<div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
    <img src="../static/images/noscript.gif" alt='抱歉，请开启脚本支持！' />
</div></noscript>
	<!-- head 部 -->
    <div region="north" split="false" border="false" style="overflow: hidden; height: 30px;
        background: url(static/images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
        <span style="float:right; padding-right:20px;" class="head">欢迎 admin <a href="#" class="easyui-linkbutton" onClick="load2()">修改密码</a> <a href="#" class="easyui-linkbutton" onClick="load2()">安全退出</a></span>
        <span style="padding-left:10px; font-size: 16px; "><img src="static/images/blocks.gif" width="20" height="20" align="absmiddle" /> jQuery.EasyUI- 1.4.4 + zTree3.5.19 完美结合 应用实例</span>
    </div>
    
    <!-- 左侧菜单 -->
    <div region="west" split="true" title="导航菜单" style="width:180px;" id="west">
		<div class="easyui-accordion" style="width: 200px; height: 300px;" fit=true border=false>
			<div title="oa系统菜单">
				<ul id="oa_menu" class="ztree"></ul>
			</div>
        	<div title="jQ.EasyUI示例">
				<ul id="treeDemo1" class="ztree"></ul>
			</div>
			<div title="zTree 示例" fit=true border=false>
				<ul id="treeDemo2" class="ztree"></ul>
			</div>
        </div>
    </div>
    
    <!-- 欢迎主页 -->
    <div id="mainPanle" region="center" style="background:#eee;overflow-y:hidden">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false">
			<div title="首页" style="padding:20px;overflow:hidden;color:red;" >
                <h1 style="font-size:24px;">&nbsp;</h1>
                <h1 style="font-size:24px;">&nbsp;</h1>
				<h1 style="font-size:24px;">* Edit By lify</h1>
                <h1 style="font-size:24px;">* jQuery.EasyUI- 1.4.4 + zTree3.5.19 完美结合 应用实例 </h1>
                <h1 style="font-size:24px;">* </h1>
				<h1 style="font-size:24px;">* 2016-06-06</h1>
				<h1 style="font-size:24px;">&nbsp;</h1>
			</div>
		</div>
    </div>
	<!-- tab 上动态菜单 -->
	<div id="right-hand_menu" class="easyui-menu" style="width:150px;">
		<div id="tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="close">关闭</div>
		<div id="closeall">全部关闭</div>
		<div id="closeother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="closeright">当前页右侧全部关闭</div>
		<div id="closeleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="exit">退出</div>
	</div>
    <!-- foot 底部 -->
    <div region="south" split="false" style="height: 30px; background: #D2E0F2; ">
        <div class="footer">Copyright © 2016-2018, lify All Rights Reserve.</div>
    </div>
</body>
</html>