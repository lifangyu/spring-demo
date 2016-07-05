<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/pages/common/global.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限管理系统</title>
</head>
<body class="easyui-layout" >
<!--头部begin-->
<div region="north" border="true" split="false" style="height:15%;overflow:hidden;" href="<c:url value='/pages/head.jsp' />"></div>
<!--头部end-->

<!-- 左侧菜单begin  -->
<div region="west" title="系统菜单" split="true" style="width: 12%;">
 	<div style="margin: 10px 0;">
			<ul class="easyui-tree" id="treeView"></ul>
	</div>
</div>
<!-- 左侧菜单end  -->

<!--中间内容begin-->
<div region="center">
	<div id="main-panle" class="easyui-tabs" fit="true" border="false">
		<div title="首页" id="tabs" href="<c:url value='/pages/welcome.jsp' />"></div>
	</div>
</div>
<!--中间内容end-->

<!-- 底部begin-->
<div region="south" border="false" split="false" style="height:4%;overflow:hidden;" href="<c:url value='/pages/foot.jsp'/>"></div>
 <!-- 底部end-->
 
</body>
</html>
<script type="text/javascript">
<!-- 注： 加载菜单独立成一个js文件不好用 -->
var _treeView = $('#treeView');//菜单栏
var _centerTabs = $('#tabs');//选项卡栏
$(function(){
	//加载左侧菜单树
	_treeView.tree({
		 checkbox: false, 			 
		 url:'${ctx}login/treeView?pid=0',
		 onBeforeExpand:function(node){
			 _treeView.tree("options").url="${ctx}login/treeView?pid="+node.id;
		 },
		 onClick:function(node){
			 var click_obj = $('#treeView').tree('getSelected');
			 if(click_obj.state == 'open' && click_obj.attributes == null){
				 _treeView.tree('collapse',node.target);
			 }else if(click_obj.state == 'closed' && click_obj.attributes == null){
				 _treeView.tree('expand',node.target);
			 }else{
				    var _tit = node.text;
					var _contant = node.attributes["url"];
				    var _tabCls = node.iconCls;
					addNewTab(_tit, _contant, _tabCls, node.id);
			 }
		 }
	}); 
	//给菜单添加点击事件
	$('.easyui-tree a').click(function() {
	    var _tit = $(this).html(),
	    _contant = $(this).attr("href"),
	    _tabCls = $(this).attr("tabCls");
	    addNewTab(_tit, _contant, _tabCls, $(this).attr("id"));
	    return false;
	});
	
	//加载选项卡
	_centerTabs.tabs({
		fit : true,
		border : false,
		onContextMenu : function(e, title) {
			e.preventDefault();
			tabsMenu.menu('show', {
				left : e.pageX,
				top : e.pageY
			}).data('tabTitle', title);
		}
	});	
	
	//添加选项卡
	function addNewTab(title, href, tabCls, nodeId){
		 //选项卡索引
		 var index;
		 //选择存在的选项卡
		 $(_centerTabs.tabs('tabs')).each(function(i){
			 if(this[0].id == 'tree'+nodeId){
				 index = _centerTabs.tabs('getTabIndex',this);
				 _centerTabs.tabs('select', index);
				 return false;
			 }
		 });
		 
		 //不存在，则创建选项卡
		if(!_centerTabs.tabs('exists',index)){
			var content = 'no load page!';
			if(Boolean(href)){
				content = '<iframe scrolling="no" frameborder="0"  src="'+ href + '" style="width:100%;height:99%;"></iframe>';
			}
			_centerTabs.tabs({
				scrollIncrement : 100
			}).tabs('add', {
				id : 'tree'+nodeId,
				title : title,
				content : content,
				closable : true,
				iconCls : tabCls
			});
		}
	 }
});
</script>