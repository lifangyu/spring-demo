<!--#include file="../../../../easyasp/easp.asp" -->
<%
'=========================
'EasyASP测试用例功能类文件
'Author:coldstone
'Update time: 2014-02-05
'=========================

'设置Easp数据源：
' 设置默认的数据源

Easp.Db.SetConnection "default", 1, "mldm.mdb", ""
' 或者
'Easp.Db.SetConn "ACCESS", "_sample.mdb", ""



'cljbxx   id,nsrsbh,yszhm,cphm,xkzbh,cllx,cpxh,hzdw,clzt,xkzpicurl,hgzpicurl,clpicurl,tjrq,czrq
dim rs
Set Rs = Easp.Db.Sel("Select top 20 nsrsbh,yszhm,cphm,xkzbh From cljbxx")
Easp.Str.EncodeJsonUnicode = True
Easp.Print Easp.Str.ToString(Rs)
Easp.Db.Close Rs

%>