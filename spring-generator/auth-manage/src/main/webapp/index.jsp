<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/pages/common/global.jsp"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<!-- 浏览器图片  -->
<%-- <link rel="shortcut icon" type="image/x-icon" href="${ctx }html/favicon.ico" /> --%>
<title>登录</title>
<link rel="stylesheet" type="text/css" href="${ctx}static/css/common.css">
<style type="text/css">
html,body,.container {
	height: 100%;
}

.container {
	min-width: 1280px;
	max-width:100%;
	min-height: 600px;
}
</style>
<script type="text/javascript">
	$(function(){
		$("#loginbutton").on("click",function(){
			checkLogin();
		});
	});
	function checkLogin() {
		if ($("#username").val() == "") {
			alert("请输入帐号");
			return;
		}
		if ($("#password").val() == "") {
			alert("请输入密码");
			return;
		}
		//验证验证码是否为空
		if ($("#code").val() == "") {
			alert("请输入验证码！");
			return;
		}
		$.post("${ctx}login/checkLogin", 
			$("#myform").serialize(), 
			function(data) {
				if (data == 1) {
					//$("#admin_code_result").text("帐号不存在");
					alert("帐号不存在");
					change_image();
				} else if (data == 2) {
					//$("#password_result").text("密码错误");
					alert("密码错误");
					change_image();
				} else if (data == 4) {
					//$("#password_result").text("密码错误");
					alert("帐号被冻结，请联系管理员.");
					change_image();
				} else if (data == 0) {
					// 登录成功，跳转
					location.href = "${ctx}login/main";
				} else {
					//$("#image_msg").text("验证码错误.");
					alert("验证码错误");
					change_image();
				}
		});
	}
	//刷新验证码图片
	function change_image() {
		$("#image_code").attr("src","${ctx }login/createImage?date=" + new Date().getTime());
	}
	//回车事件
	document.onkeydown = function(event) {
		var e = event || window.event || arguments.callee.caller.arguments[0];
		if (e && e.keyCode == 13) { // enter 键
			checkLogin();
		}
	};
</script>
</head>
<body>
	<div class="container" style='background: transparent url("${ctx}static/images/bg.png") repeat-y scroll 0% 0% / 100% auto;'>
		<div class="header">
			<div class="headleft">
				<!-- <em class="logo1"></em> -->
			</div>
		</div>
		<div class="banner">
			<div class="banner_right">
				<form id="myform" action="">
					<div class="clearfix">
						<h3>登录</h3>
						<a href="${ctx}toFindPassword" class="forget">忘记密码？</a>
					</div>
					<div class="user_form">
						<div class="username border">
							<input id="username" type="text" placeholder="用户名"
								name="username" value="" class="txt icon1" />
						</div>
						<div class="username">
							<input id="password" type="password" placeholder="密码"
								name="pass" value="" class="txt icon2" />
						</div>
					</div>
					<div class="pic clearfix">
						<input id="code" type="text" class="txt check" name="code"
							placeholder="图片验证码"><img id="image_code"
							src="${ctx }login/createImage" onclick="change_image();" />
					</div>
					<p class="little_text">看不清？请点击图片刷新验证码</p>
					<a href="javascript:;"> <input type="button" id="loginbutton"
						value="登录" class="btn" /> </a>
				</form>
			</div>
		</div>
	</div>
</body>
</html>