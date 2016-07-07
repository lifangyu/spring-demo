/***********************************************************************

 * 作者：疯狂秀才 QQ：1055818239
 * 讨论群：112044258、32994605、36534121、56271061
 * 创建日期：2011/1/17

 * 广告：本人承接各类大中小型管理系统的软件的设计与开发，有需要的朋友联系我啦~~~~

 **********************************************************/

window.onload = function(){
    $('#loading-mask').fadeOut();
}

var onlyOpenTitle="欢迎使用";//不允许关闭的标签的标题

$(function(){
    InitLeftMenu();
    tabClose();
    tabCloseEven();

    /* 选择TAB时刷新内容
     $('#tabs').tabs({
     onSelect: function (title) {
     var currTab = $('#tabs').tabs('getTab', title);
     var iframe = $(currTab.panel('options').content);

     var src = iframe.attr('src');
     if(src)
     $('#tabs').tabs('update', { tab: currTab, options: { content: createFrame(src)} });

     }
     });
     */
})

//初始化左侧
function InitLeftMenu() {
    $("#nav2").accordion({animate:false,fit:true,border:false});
    var ontreenodeclick = function(node){
        if(node.url){
            addTab(node.text,node.url,node.icon)
        }
    }

    $.getJSON('js/tree_data1.json',function(data){
        $.each(data,function(i,n){
            $('#nav2').accordion('add', {
                title: n.text,
                content: '<ul id="tree'+ n.id+'"></ul>',
                border:false,
                iconCls: 'icon ' + n.icon
            });

            $('#tree'+ n.id).tree({
                data: n.children,
                onClick:ontreenodeclick
            });

        });

        $('#nav2').accordion('select',0);
    });


}

function addTab(subtitle,url,icon){
    if(!$('#tabs').tabs('exists',subtitle)){
        $('#tabs').tabs('add',{
            title:subtitle,
            content:createFrame(url),
            closable:true,
            icon:icon
        });
    }else{
        $('#tabs').tabs('select',subtitle);
        // 刷新
        closeTab('refresh',subtitle);
    }
    tabClose();
}

function createFrame(url)
{
    var s = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
    return s;
}

function tabClose()
{
    /*双击关闭TAB选项卡*/
    $(".tabs-inner").dblclick(function(){
        var subtitle = $(this).children(".tabs-closable").text();
        $('#tabs').tabs('close',subtitle);
    })
    /*为选项卡绑定右键*/
    $(".tabs-inner").bind('contextmenu',function(e){
        $('#mm').menu('show', {
            left: e.pageX,
            top: e.pageY
        });

        var subtitle =$(this).children(".tabs-closable").text();

        $('#mm').data("currtab",subtitle);
        $('#tabs').tabs('select',subtitle);
        return false;
    });
}


//绑定右键菜单事件
function tabCloseEven() {

    $('#mm').menu({
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


//弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
function msgShow(title, msgString, msgType) {
    $.messager.alert(title, msgString, msgType);
}
