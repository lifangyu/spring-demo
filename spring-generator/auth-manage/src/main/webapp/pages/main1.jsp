<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>jQuery.EasyUI- 1.4.3 后台经典框架使用DEMO</title>
    <link href="<%=request.getContextPath() %>/static/css/default.css" rel="stylesheet" type="text/css" />

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/easyui/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/easyui/themes/icon.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/zTreeStyle/zTreeStyle.css" />
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/ztree/js/jquery.ztree.all-3.5.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/easyui/menuStandardData.js"></script>
<%--     <script type="text/javascript" src="<%=request.getContextPath() %>/static/js/leftMenu.js"></script> --%>


<script type="text/javascript">
$(function(){
    // 右键菜单
    tabClose();
    tabCloseEven();

})
function tabClose()
{
    /*双击关闭TAB选项卡*/
    $(".tabs-inner").dblclick(function(){
        var subtitle = $(this).children(".tabs-closable").text();
        $('#tabs').tabs('close',subtitle);
    })
    /*为选项卡绑定右键*/
    $(".tabs-inner").bind('contextmenu',function(e){
        $('#right-hand_menu').menu('show', {
            left: e.pageX,
            top: e.pageY
        });

        var subtitle =$(this).children(".tabs-closable").text();

        $('#right-hand_menu').data("currtab",subtitle);
        $('#tabs').tabs('select',subtitle);
        return false;
    });
}


//绑定右键菜单事件
function tabCloseEven() {

    $('#right-hand_menu').menu({
        onClick: function (item) {
            closeTab(item.id);
        }
    });
    return false;
}
function closeTab(action,title)
{
    var alltabs = $('#tabs').tabs('tabs');
    var currentTab =$('#tabs').tabs('getSelected');
    var allTabtitle = [];
    $.each(alltabs,function(i,n){
        allTabtitle.push($(n).panel('options').title);
    })


    switch (action) {
        case "refresh":

            if(title){
                var _currentTab =  $('#tabs').tabs('getTab',title);
            }else{
                _currentTab = currentTab;
            }

            var iframe = $(_currentTab.panel('options').content);
            var src = iframe.attr('src');
            $('#tabs').tabs('update', {
                tab: _currentTab,
                options: {
                    content: createFrame(src)
                }
            })
            break;
        case "close":
            var currtab_title = currentTab.panel('options').title;
            $('#tabs').tabs('close', currtab_title);
            break;
        case "closeall":
            $.each(allTabtitle, function (i, n) {
                if (n != onlyOpenTitle){
                    $('#tabs').tabs('close', n);
                }
            });
            break;
        case "closeother":
            var currtab_title = currentTab.panel('options').title;
            $.each(allTabtitle, function (i, n) {
                if (n != currtab_title && n != onlyOpenTitle)
                {
                    $('#tabs').tabs('close', n);
                }
            });
            break;
        case "closeright":
            var tabIndex = $('#tabs').tabs('getTabIndex', currentTab);

            if (tabIndex == alltabs.length - 1){
                alert('亲，后边没有啦 ^@^!!');
                return false;
            }
            $.each(allTabtitle, function (i, n) {
                if (i > tabIndex) {
                    if (n != onlyOpenTitle){
                        $('#tabs').tabs('close', n);
                    }
                }
            });

            break;
        case "closeleft":
            var tabIndex = $('#tabs').tabs('getTabIndex', currentTab);
            if (tabIndex == 1) {
                alert('亲，前边那个上头有人，咱惹不起哦。 ^@^!!');
                return false;
            }
            $.each(allTabtitle, function (i, n) {
                if (i < tabIndex) {
                    if (n != onlyOpenTitle){
                        $('#tabs').tabs('close', n);
                    }
                }
            });

            break;
        case "exit":
            $('#closeMenu').menu('hide');
            break;
    }
}


//-->
</script>

    <script type="text/javascript">
		function zTreeOnClick(event, treeId, treeNode) {
			//alert(treeNode.tId + ", " + treeNode.name);
			if(treeNode.file){
				addTabs(treeNode.name,treeNode.file);
			}
			
		};
		var setting = {
			/*async: {
				enable: true,
				url:"js/standardData.json",
				autoParam:["id", "name=n", "level=lv"],
				otherParam:{"otherParam":"zTreeAsyncTest"},
				dataFilter: filter
			},*/
			callback: {
				onClick: zTreeOnClick
			}
		};

		function filter(treeId, parentNode, childNodes) {
			if (!childNodes) return null;
			for (var i=0, l=childNodes.length; i<l; i++) {
				childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
			}
			return childNodes;
		}

		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo1"), setting, zNodes);
		});

		function addTabs(subtitle,url){
			if(!$('#tabs').tabs('exists',subtitle)){
				$('#tabs').tabs('add',{
					title:subtitle,
					content:'<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;padding-left:5px"></iframe>',
					closable:true
				});
			}else{
				$('#tabs').tabs('select',subtitle);
				// 刷新
				closeTab('refresh',subtitle);
			}
			tabClose();
		}		
		//-->
    </script>
<script type="text/javascript">
	var setting = {
		view: {
			dblClickExpand: false,
			showLine: true,
			selectedMulti: false
		},
		data: {
			simpleData: {
				enable:true,
				idKey: "id",
				pIdKey: "pId",
				rootPId: ""
			}
		},
		callback: {
			onClick: zTreeOnClick
		}
	};
	
	$(document).ready(function(){
		$.fn.zTree.init($("#treeDemo2"), setting, zNodes1);
	});


//-->
</script>
</head>
<body class="easyui-layout" style="overflow-y: hidden"  fit="true"   scroll="no">
<noscript>
<div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
    <img src="../static/images/noscript.gif" alt='抱歉，请开启脚本支持！' />
</div></noscript>
	<!-- head 部 -->
    <div region="north" split="false" border="false" style="overflow: hidden; height: 30px;
        background: url(../static/images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
        <span style="float:right; padding-right:20px;" class="head">欢迎 　XXX　　<a href="#" class="easyui-linkbutton" onClick="load2()">修改密码</a> <a href="#" class="easyui-linkbutton" onClick="load2()">安全退出</a></span>
        <span style="padding-left:10px; font-size: 16px; "><img src="../static/images/blocks.gif" width="20" height="20" align="absmiddle" /> jQuery.EasyUI- 1.4.4 + zTree3.5.19 完美结合 应用实例</span>
    </div>
    
    <!-- foot 底部 -->
    <div region="south" split="false" style="height: 30px; background: #D2E0F2; ">
        <div class="footer">lify版权所有</div>
    </div>
    
    <!-- 左侧菜单 -->
    <div region="west" split="true" title="导航菜单" style="width:180px;" id="west">
		<div class="easyui-accordion" style="width: 200px; height: 300px;" fit=true border=false>
			<div id="oa_menu" title="oa系统菜单">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
        	<div id="nav1" title="jQ.EasyUI示例">
				<ul id="treeDemo1" class="ztree"></ul>
			</div>
			<div id="nav2" title="zTree 示例" fit=true border=false>
				<ul id="treeDemo2" class="ztree"></ul>
			</div>
        </div>
    </div>
    
    <!-- 欢迎主页 -->
    <div id="mainPanle" region="center" style="background:#eee;overflow-y:hidden">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false">
			<div title="首页" style="padding:20px;overflow:hidden;color:red;" >
				<h1 style="font-size:24px;">* Edit By CYY</h1>
                <h1 style="font-size:24px;">* jQuery.EasyUI- 1.4.4 + zTree3.5.19 完美结合 应用实例 </h1>
                <h1 style="font-size:24px;">* </h1>
				<h1 style="font-size:24px;">* 2016-06-06</h1>
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

</body>
</html>