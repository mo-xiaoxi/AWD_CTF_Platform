<%@ page import="com.bupt.utils.SystemDateTimeChecker" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="cp" value="<%=basePath%>" />
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="/common/head.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/common/navigator.jsp"></jsp:include>
<div class="content">
	<div class="container">
		<div class="row" style="margin-bottom: 50px"></div>

		<div class="row">
			<div class="col-md-12 text-center">
				<h2>用户登录</h2>
			</div>
		</div>
		<div class="row" style="margin-bottom: 50px"></div>
		<div class="row">
			<div class="col-md-3"></div>

			<div class="col-md-6">
				<div class="row">
					<div class="col-sm-12 text-right">
						<div class="form-horizontal">
							<form role="form" action="${cp}user/login.html" id="login-submit" method="post">
								<fieldset>
									<div class="form-group">
										<label class="col-sm-3 control-label">* 用户名:</label>
										<div class="col-sm-9 input-group">
											<input type="text" id="username" name="username" class="form-control">
											<span class="input-group-addon">
										<span class="glyphicon glyphicon-user"></span>
									</span>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label">* 密 码:</label>
										<div class="col-sm-9 input-group">
											<input type="password" id="password" name="password" class="form-control">
											<span class="input-group-addon">
												<span class="glyphicon glyphicon-eye-close"></span>
											</span>
										</div>
									</div>
									<%--<div class="form-group">--%>
										<%--<label class="col-sm-3 control-label">* 验证码:</label>--%>
										<%--<div class="col-sm-9 input-group">--%>
											<%--<div class="row">--%>
												<%--<div class="col-sm-7 col-md-7">--%>
													<%--<input type="text" id="verCode" name="verCode" class="form-control">--%>
												<%--</div>--%>
												<%--<div class="col-sm-5 col-md-5">--%>
													<%--<img id="img" style="cursor:pointer" onclick="javascript:changeImg()" src="${cp}user/verCode" />--%>
													<%--<a href='#' onclick="javascript:changeImg()" style="color:black;">--%>
														<%--<label style="color:red;">看不清？</label></a>--%>
												<%--</div>--%>
											<%--</div>--%>
										<%--</div>--%>
									<%--</div>--%>
								</fieldset>
							</form>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12 text-right" style="padding-right: 0px">
						<button type="submit" onclick="loginSubmit()" class="btn btn-success">登录</button>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12 text-center" style="padding-right: 0px">
						<%
							if(SystemDateTimeChecker.checkNowOk()){
						%>
						<h5>没有账号，<a href="${cp}user/registerHtml.html">马上注册</a></h5>
						<%
							}
						%>
						<h5><a href="${cp}user/findHtml.html">忘记密码</a></h5>
					</div>
				</div>
			</div>

			<div class="col-md-3"></div>
		</div>
	</div>
</div>
	<jsp:include page="/common/footer.jsp"></jsp:include>
</body>
<!-- 触发JS刷新-->
<script type="text/javascript">
	$(function () {
		ShowMsg("${message}");
	})
	function changeImg(){
		var img = document.getElementById("img");
		img.src = "${cp}user/verCode?rnd="+Math.random();
	}
	function loginSubmit() {
		var username = $("#username").val();
		var password = $("#password").val();
		// var code = $("#verCode").val();
		if(validateUserName(username)){
			if(validatePassword(password)){
				// if(code==""){
				// 	ShowWarn("请填写验证码");
				// }else {
					$("#login-submit").submit();
				// }
			}else {
				ShowWarn("密码不合法！仅包含字母数字，6-12字符")
			}
		}else {
			ShowWarn("用户名不合法！仅包含字母数字及@.+-_，不超过30个字符");
		}
	}
</script>