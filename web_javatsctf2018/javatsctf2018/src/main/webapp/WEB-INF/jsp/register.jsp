<%@ page import="com.bupt.utils.SystemDateTimeChecker" %>
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
        <%
            if(!SystemDateTimeChecker.checkNowOk()){
        %>
        <jsp:include page="/common/closed.jsp"></jsp:include>
        <%
            }else {
        %>
        <div class="row">
            <div class="col-md-12 text-center">
                <h2>用户注册</h2>
            </div>
        </div>
        <div class="row" style="margin-bottom: 50px"></div>
        <div class="row">
            <div class="col-md-1"></div>

            <div class="col-md-8">
                <div class="row">
                    <div class="col-sm-12 text-right">
                        <div class="form-horizontal">
                            <form role="form" action="${cp}user/register.html" id="regist-submit" method="post">
                                <fieldset>
                                    <div class="form-group">
                                        <label class="col-sm-5 control-label" style="padding-top: 0px">
                                            <div class="row">
                                                <div class="col-sm-12">
                                                    <div class="row">* 用户名:&nbsp;&nbsp;&nbsp;&nbsp;</div>
                                                    <div class="row" style="color: red;font-size: 11px">【支持大小写字母、数字及@.+-_,不超过30个字符】</div>
                                                </div>
                                            </div>
                                        </label>
                                        <div class="col-sm-7 input-group">
                                            <input type="text"  id="username" name="username" class="form-control">
                                            <span class="input-group-addon">
                                                <span class="glyphicon glyphicon-user"></span>
                                            </span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-5 control-label" style="padding-top: 0px">
                                            <div class="row">
                                                <div class="col-sm-12">
                                                    <div class="row">* 密 码:&nbsp;&nbsp;&nbsp;&nbsp;</div>
                                                    <div class="row" style="color: red;font-size: 11px">【支持大小写字母及数字，6-12个字符】</div>
                                                </div>
                                            </div>
                                        </label>
                                        <div class="col-sm-7 input-group">
                                            <input type="password" id="password" name="password" class="form-control">
                                            <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-eye-close"></span>
                                         </span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-5 control-label">* 邮 箱:</label>
                                        <div class="col-sm-7 input-group">
                                            <input type="text" id="email" name="email" class="form-control">
                                            <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-envelope"></span>
                                         </span>
                                        </div>
                                        <div class="col-sm-3">

                                        </div>
                                    </div>
                                </fieldset>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12 text-right" style="padding-right: 0px">
                        <button type="submit" onclick="registSubmit()" class="btn btn-success">注册</button>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12 text-center" style="padding-right: 0px">
                        <h5>已有账号？<a href="${cp}user/loginHtml.html">马上登录</a></h5>
                    </div>
                </div>
            </div>

            <div class="col-md-3"></div>
        </div>
        <%
            }
        %>
    </div>
</div>
    <jsp:include page="/common/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
    $(function () {
        ShowMsg("${message}");
    })
    function registSubmit() {
        var username = $("#username").val();
        var password = $("#password").val();
        var email = $("#email").val();
        if(validateUserName(username)){
            if(validatePassword(password)){
                if(validateEmail(email)){
                    $("#regist-submit").submit();
                }else {
                    ShowWarn("邮箱格式不合法！")
                }
            }else {
                ShowWarn("密码不合法！仅包含字母数字，6-12字符")
            }
        }else {
            ShowWarn("用户名不合法！仅包含字母数字及@.+-_，不超过30个字符");
        }
    }
</script>
