$(function(){
    // 右键菜单
    tabClose();
    tabCloseEven();
    $.fn.zTree.init($("#treeDemo1"), setting1, zNodes);
    $.fn.zTree.init($("#treeDemo2"), setting2, zNodes1);
    var oa_menu_nodes = '';
    $.ajax({
        url: "treeView",    //请求的url地址
        dataType: "json",   //返回格式为json
        async: false, //请求是否异步，默认为异步(true,false:同步)，这也是ajax重要特性
        type: "POST",   //请求方式
/*      beforeSend: function() {//请求前的处理},*/
        success: function(data) {//请求成功时处理
        	oa_menu_nodes = data;
        },
/*      complete: function() {//请求完成的处理},*/
        error: function() {//请求出错处理
        	alert('error');
        }
    });
    $.fn.zTree.init($("#oa_menu"), setting1, oa_menu_nodes);
});

function tabClose(){
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
};

//绑定右键菜单事件
function tabCloseEven() {
    $('#right-hand_menu').menu({
        onClick: function (item) {
            closeTab(item.id);
        }
    });
    return false;
};

function closeTab(action,title){
    var alltabs = $('#tabs').tabs('tabs');
    var currentTab =$('#tabs').tabs('getSelected');
    var allTabtitle = [];
    $.each(alltabs,function(i,n){
        allTabtitle.push($(n).panel('options').title);
    });
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
};

function createFrame(url){
    var s = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
    return s;
}
function zTreeOnClick(event, treeId, treeNode) {
//	alert(treeNode.tId + ", " + treeNode.name);
	if(treeNode.file){
		addTabs(treeNode.name,treeNode.file);
	}
	
};

function filter(treeId, parentNode, childNodes) {
	if (!childNodes) return null;
	for (var i=0, l=childNodes.length; i<l; i++) {
		childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
	}
	return childNodes;
}


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
};

/**
 * 菜单数据的加载
 */
var setting1 = {
	/*async: {
		enable: true,
		url:"../easyui/standardData.json",
		autoParam:["id", "name=n", "level=lv"],
		otherParam:{"otherParam":"zTreeAsyncTest"},
		dataFilter: filter
	},*/
	callback: {
		onClick: zTreeOnClick
	}
};

var setting2 = {
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
	


