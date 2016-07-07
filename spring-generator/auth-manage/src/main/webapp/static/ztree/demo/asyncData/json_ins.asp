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
dim rs,nsrsbh,yszhm,cphm,xkzbh,cllx,cpxh,hzdw,clzt,xkzpicurl,hgzpicurl,clpicurl,tjrq,czrq,i
'Set Rs = Easp.Db.Sel("Select * From cljbxx")
'Easp.Println Easp.Str.ToString(Rs)
'Easp.Db.Close Rs
Easp.Var("nsrsbh") = "3504250037456267"
for i=0 to 35

Easp.Print Easp.Db.Ins("cljbxx", "nsrsbh:{nsrsbh},yszhm:{Easp.Newid},cphm:{Easp.Newid},xkzbh:{Easp.Newid}")
next

'Easp.Db.Ins("cljbxx", "nsrsbh:{nsrsbh},yszhm:{Easp.Newid},yszhm:{Easp.Newid},xkzbh:{Easp.Newid}")
%>