<% if Request("id")=1 then %>

[
	{id:10, pId:1, name: "10父节点1异步加载模式 下简单的勾选操作" , url:"asyncData/getNodes.asp?id=1", target:"_blank"},
	{id:11, pId:1, name: "11子节点11异步加载模式 下简单的勾选操作" , url:"asyncData/getNodes.asp?id=1", target:"_blank"},
	{id:12, pId:11, name: "12子节点12异步加载模式 下简单的勾选操作" , url:"asyncData/getNodes.asp?id=1", target:"_blank"},
	{id:13, pId:12, name: "13子节点13异步加载模式 下简单的勾选操作"},
	{id:14, pId:13, name: "14子节点14异步加载模式 下简单的勾选操作", url:"asyncData/getNodes.asp?id=1", target:"_blank"},
	{id:15, pId:14, name: "15子节点15异步加载模式 下简单的勾选操作", url:"asyncData/getNodes.asp?id=11", target:"_blank"},
	{id:16, pId:14, name: "16子节点16这个演示式实现了 异步加载模式 下简单的勾选操作", url:"asyncData/getNodes.asp?id=1", target:"_blank"}
]
<% elseif Request("id")=2 then %>

[
	{id:20, pId:2, name: "2父节20这个演示式实现了 异步加载模式 下简单的勾选操作"},
	{id:21, pId:20, name: "2子节点21这个演示式实现了 异步加载模式 下简单的勾选操作"},
	{id:22, pId:21, name: "2子节点2这个演示式实现了 异步加载模式 下简单的勾选操作"},
	{id:23, pId:22, name: "2子节点2这个演示式实现了 异步加载模式 下简单的勾选操作1"},
	{id:24, pId:22, name: "2子2这个演示式实现了 异步加载模式 下简单的勾选操作节点2"},
	{id:25, pId:21, name: "2子2这个演示式实现了 异步加载模式 下简单的勾选操作节点1"},
	{id:26, pId:21, name: "2子节2这个演示式实现了 异步加载模式 下简单的勾选操作点2"}
]

<% End If %>