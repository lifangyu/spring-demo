<%@ page language="java" pageEncoding="UTF-8"%>
<%-- <%@include file="/pages/common/global.jsp"%> --%>
<%
	String path = request.getContextPath();
	String port = request.getServerPort() == 80 ? "" : ":" + request.getServerPort();
	String basePath = request.getScheme() + "://" + request.getServerName() + port + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"><!-- PUBLIC 后内容必须存在，否则页面不正常 -->
<HTML>
<HEAD>
	<STYLE type=text/css>
	BODY {
		FONT-SIZE: 12px;
		COLOR: #ffffff;
		FONT-FAMILY: 宋体
	}
	
	TD {
		FONT-SIZE: 12px;
		COLOR: #ffffff;
		FONT-FAMILY: 宋体
	}
	</STYLE>
	
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/easyui/jquery.min.js"></script>

</HEAD>
<BODY>
	<DIV id=UpdatePanel>
		<DIV id=div1
			style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>
		<DIV id=div2
			style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>
		<SCRIPT language=JavaScript>
			/* 动态效果处理 ，不可动*/
			var speed = 20;
			var temp = new Array();
			var clipright = document.body.clientWidth / 2, clipleft = 0
			for (i = 1; i <= 2; i++) {
				temp[i] = eval("document.all.div" + i + ".style");
				temp[i].width = document.body.clientWidth / 2;
				temp[i].height = document.body.clientHeight;
				temp[i].left = (i - 1) * parseInt(temp[i].width);
			}
			function openit() {
				clipright -= speed;
				temp[1].clip = "rect(0 " + clipright + " auto 0)";
				clipleft += speed;
				temp[2].clip = "rect(0 auto auto " + clipleft + ")";
				if (clipright <= 0)
					clearInterval(tim);
			}
			tim = setInterval("openit()", 100);
			
		</SCRIPT>

		<DIV>
			<TABLE cellSpacing=0 cellPadding=0 width=900 align=center border=0>
				<TBODY>
					<TR>
						<TD style="HEIGHT: 105px"><IMG
							src="static/login_files/login_1.gif" border=0></TD>
					</TR>
					<TR>
						<TD background=static/login_files/login_2.jpg height=300>
							<TABLE height=300 cellPadding=0 width=900 border=0>
								<TBODY>
									<TR>
										<TD colSpan=2 height=35></TD>
									</TR>
									<TR>
										<TD width=360></TD>
										<TD>
											<FORM id="myform" action="<%=basePath%>login" method="POST">
												<TABLE cellSpacing=0 cellPadding=2 border=0>
													<TBODY>
														<TR>
															<TD style="HEIGHT: 28px" width=80>登 录 名：</TD>
															<TD style="HEIGHT: 28px" width=150>
																<INPUT id=username style="WIDTH: 130px" name=username>
															</TD>
															<TD style="HEIGHT: 28px" width=370><SPAN
																id=RequiredFieldValidator_username
																style="FONT-WEIGHT: bold; VISIBILITY: hidden; COLOR: white">请输入登录名</SPAN></TD>
														</TR>
														<TR>
															<TD style="HEIGHT: 28px">登录密码：</TD>
															<TD style="HEIGHT: 28px">
																<INPUT id=password style="WIDTH: 130px" type=password name=password>
															</TD>
															<TD style="HEIGHT: 28px">
																<SPAN id=RequiredFieldValidator_passward style="FONT-WEIGHT: bold; VISIBILITY: hidden; COLOR: white">请输入密码</SPAN>
															</TD>
														</TR>
														<TR>
															<TD style="HEIGHT: 28px">验证码：</TD>
															<TD style="HEIGHT: 28px">
																<INPUT id=code style="WIDTH: 130px" name=code >
															</TD>
															<TD style="HEIGHT: 28px">
																<img title="点击刷新验证码" id="image_code" src="<%=basePath%>createImage" onclick="change_image();" />
															<SPAN id="RequiredFieldValidator_code" style="FONT-WEIGHT: bold; VISIBILITY: hidden; COLOR: white">请输入验证码</SPAN>
															</TD>
														</TR>
														<TR>
															<TD style="HEIGHT: 18px"></TD>
															<TD style="HEIGHT: 18px"></TD>
															<TD style="HEIGHT: 18px"></TD>
														</TR>
														<TR>
															<TD></TD>
															<TD>
																<a href="javascript:;">
																	<INPUT id="button_id" onclick="javascript:return webForm_OnSubmit();" style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px" type=image src="static/login_files/login_button.gif">
																	<input style='display:none' type="button" id="loginbutton" value="登录"/>
																</a>
															</TD>
														</TR>
													</TBODY>
												</TABLE>
											</FORM>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
						</TD>
					</TR>
					<TR>
						<TD><IMG src="static/login_files/login_3.jpg" border=0></TD>
					</TR>
				</TBODY>
			</TABLE>
		</DIV>
	</DIV>
</BODY>
<script type="text/javascript">

function webForm_OnSubmit(){
	if(checkLogin()){
		$.ajaxSetup({
			async: true
		});
		var result = true;
		var t = $.post("<%=basePath%>checkLogin",
				$("#myform").serialize(),
				function(data) {
					if (data == 1) {
						$("#RequiredFieldValidator_username").text("帐号不存在");
						$("#RequiredFieldValidator_password").text("");
						$("#RequiredFieldValidator_code").text(""); 
						change_image();
						result = false;
					} else if (data == 2) {
						$("#RequiredFieldValidator_password").text("密码错误");
						$("#RequiredFieldValidator_username").text("");
						$("#RequiredFieldValidator_code").text(""); 
						change_image();
						result = false;
					} else if (data == 3) {
						$("#RequiredFieldValidator_username").text("帐号被冻结，请联系管理员.");
						$("#RequiredFieldValidator_password").text("");
						$("#RequiredFieldValidator_code").text(""); 
						change_image();
						result = false;
					} else if (data == 4) {
						$("#RequiredFieldValidator_code").text("验证码错误.");
						$("#RequiredFieldValidator_password").text("");
						$("#RequiredFieldValidator_username").text(""); 
						change_image();
						result = false;
					} else {
						// 登录成功，跳转
						document.getElementById("myform").submit();
						return true;
					}
		});
		console.log(t);
		return result;
	}else{
		return false;
	}
}

function checkLogin() {
	var flag = true;
	if ($("#username").val() == "") {
		document.getElementById("RequiredFieldValidator_username").style.visibility="visible"; 
		$("#RequiredFieldValidator_username").text("请输入帐号"); 
		flag= false;
	}
	if ($("#password").val() == "") {
		document.getElementById("RequiredFieldValidator_passward").style.visibility="visible"; 
		$("RequiredFieldValidator_passward").text("请输入密码"); 
		flag = false;
	}
	//验证验证码是否为空
	if ($("#code").val() == "") {
		document.getElementById("RequiredFieldValidator_code").style.visibility="visible"; 
		$("RequiredFieldValidator_code").text("请输入验证码");
		flag = false;
	}
	return flag;
}
//刷新验证码图片
function change_image() {
	$("#image_code").attr("src",'<%=basePath%>createImage?date=' + new Date().getTime());
}
//回车事件
document.onkeydown = function(event) {
	var e = event || window.event || arguments.callee.caller.arguments[0];
	if (e && e.keyCode == 13) { // enter 键
		checkLogin();
	}
};
</script>
</HTML>
