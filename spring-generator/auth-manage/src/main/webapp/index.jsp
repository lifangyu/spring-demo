<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/pages/common/global.jsp"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<!-- 浏览器图片  -->
<%-- <link rel="shortcut icon" type="image/x-icon" href="${ctx }html/favicon.ico" /> --%>
<title>登录</title>
<link rel="stylesheet" type="text/css" href="${ctx }css/style.css">
<style type="text/css">
html,body,.container {
	height: 100%;
}
.logo1{width:420px;height:50px;display:inline-block;background:url(..static/images/bg.jpg) no-repeat center center;float:left;margin-top:15px;margin-left:-114px}
.clearfix{zoom:1}
.little_text{text-align:left;color:#999;font-size:12px}
.header{width:100%;height:80px;background:#3a5a96;color:#fff}
.headleft{height:80px;float:left}

.banner{width:100%;left:0;top:80px;bottom:0;background:url(../static/images/banner.jpg) no-repeat;background-size:100% auto;position:absolute;overflow:hidden}
.banner_right{width:340px;height:380px;background:#fff;position:absolute;top:50px;right:80px;padding:30px}
.banner_right h3{color:#333;font-size:20px;float:left}
.banner_right .forget{color:#ff8560;font-size:12px;float:right;text-decoration:none;margin-top:8px}
.user_form{width:320px;height:130px;border:1px solid #b2b2b2;margin-top:15px;margin-bottom:15px;padding-left:10px;padding-right:10px}
.username{width:320px;height:65px}

.pic img{vertical-align:middle}


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
		$.post("${ctx}login/checkLogin", $("#myform").serialize(), function(
				data) {
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
				location.href = "${ctx}index";
			} else {
				//$("#image_msg").text("验证码错误.");
				alert("验证码错误");
				change_image();
			}
		});
	}
	//刷新验证码图片
	function change_image() {
		$("#image_code").attr("src","${ctx}image/createImage?date=" + new Date().getTime());
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
				<em class="logo1"></em>
			</div>
		</div>
		<div class="banner">
			<div class="banner_right">
				<form id="myform" action="">
					<div class="clearfix">
						<h3>登录</h3>
						<a href="${ctx }toFindPassword" class="forget">忘记密码？</a>
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
							src="${ctx }image/createImage" onclick="change_image();" />
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