<!--#include file="../../../../easyasp/easp.asp"-->
<%
'设置Easp数据源：
' 设置默认的数据源

Easp.Db.SetConnection "default", 1, "mldm.mdb", ""
dim rs, json , Devices, a, j
Set Rs = Easp.Db.Sel("Select top 20 nsrsbh as id,yszhm as name,cphm,xkzbh From cljbxx")
Easp.Str.EncodeJsonUnicode = True
'Easp.Print Easp.Str.ToString(Rs)
'Devices = Easp.Str.ToString(Rs)
Devices = rs.getrows()
Easp.Println Easp.Str.ToString(Devices)

a = Array("a","b","c")
Set j = Easp.Json.NewArray
j.SetArray(a)		'变数组为json'
Easp.Println j.Length
Easp.Println j.Get(1)
Easp.Println j.ToString
Easp.Println ""
Set j = Nothing

Easp.Db.Close Rs
 %>
</body>
</html>
