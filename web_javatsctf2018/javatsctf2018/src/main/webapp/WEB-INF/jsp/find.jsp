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

        <div class="row">
            <div class="col-md-12 text-center">
                <h2>找回密码</h2>
            </div>
        </div>
        <div class="row" style="margin-bottom: 50px"></div>
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <div class="row">
                    <div class="col-sm-12 text-right">
                        <div class="form-horizontal">
                            <form role="form" action="${cp}user/find.html" id="find-reset-submit" method="post">
                                <fieldset>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">* 用户名:</label>
                                        <div class="col-sm-7 input-group">
                                            <input type="text" id="username" name="username" class="form-control">
                                            <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-user"></span>
                                        </span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">* 邮箱:</label>
                                        <div class="col-sm-7 input-group">
                                            <input type="text" id="email" name="email" class="form-control">
                                            <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-envelope"></span>
                                        </span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">* 邮箱验证码:</label>
                                        <div class="col-sm-7 input-group">
                                            <div class="row">
                                                <div class="col-md-8">
                                                    <input type="text" id="checkcode" name="checkcode" class="form-control">
                                                </div>
                                                <div class="col-md-4" id="messageFollow">
                                                    <a href="javascript:getVerCode();" class="btn btn-success">获取验证码</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">* 新密码:</label>
                                        <div class="col-sm-7 input-group">
                                            <input type="password" id="password1" name="password1" class="form-control">
                                            <span class="input-group-addon">
                                                <span class="glyphicon glyphicon-eye-close"></span>
                                             </span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">* 密码确认:</label>
                                        <div class="col-sm-7 input-group">
                                            <input type="password" id="password2" name="password2" class="form-control">
                                            <span class="input-group-addon">
                                                <span class="glyphicon glyphicon-eye-close"></span>
                                             </span>
                                        </div>
                                    </div>
                                </fieldset>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-10 text-right" style="padding-right: 0px">
                        <button onclick="findAndResetPassword()" class="btn btn-success">确认</button>
                    </div>
                </div>
            </div>
            <div class="col-md-3"></div>
        </div>
    </div>
</div>
    <jsp:include page="/common/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
    function getVerCode() {
        var username = $("#username").val();
        var email = $("#email").val();
        var url = "${cp}user/send_email.json";
        if(validateLengthMin(username,6)&&validateLengthMax(username,12)){
            if(validateEmail(email)){
                $("#messageFollow").html("");
                $("#messageFollow").html("<span class='label label-info'>邮件发送中...</span>");
                $.ajax({
                    type: 'POST',
                    url: url,
                    data: {
                        'username': username,
                        'email': email
                    },
                    success: function (result) {
                        if (result.ret) {
                            ShowSuccess(result.msg);
                            $("#messageFollow").html();
                            $("#messageFollow").html("<label id='info' class='text-success'></label>");
                            time = 60;
                            var t = setInterval(function() {
                                $("#info").html(time  + "秒后重新获取");
                                time--;
                                if (time == 0) {
                                    clearInterval(t);
                                    $("#messageFollow").html("");
                                    $("#messageFollow").html("<a href='javascript:getVerCode();' class='btn btn-success'>获取邮箱验证</a>");
                                }
                            }, 1000)
                        } else {
                            ShowFailure(result.msg);$("#messageFollow").html("");
                            $("#messageFollow").html("<a href='javascript:getVerCode();' class='btn btn-success'>获取邮箱验证</a>");
                        }
                    }
                });

            }else {
                ShowWarn("邮箱格式错误！")
            }
        }else {
            ShowWarn("用户名必须在6-12个字符之间！");
        }

    }
    function check(username,email){
        if(validateLengthMin(username,6)&&validateLengthMax(username,12)){
            if(validateEmail(email)){
                return true;
            }else {
                ShowWarn("邮箱格式错误！")
            }
        }else {
            ShowWarn("用户名必须在6-12个字符之间！");
        }
        return false;
    }
    function findAndResetPassword(){
        var username = $("#username").val();
        var email = $("#email").val();
        var password1 = $("#password1").val();
        var password2 = $("#password2").val();
        if(password1==password2){
            if(validateLengthMin(username,6)&&validateLengthMax(username,12)){
                if(validateLengthMin(password1,6)&&validateLengthMax(password1,12)){
                    $("#find-reset-submit").submit();
                }else {
                    ShowWarn("密码必须在6-12个字符之间")
                }
            }else {
                ShowWarn("用户名必须在6-12个字符之间");
            }
        }else {
            ShowWarn("两次输入的密码不一致！");
        }
    }
</script>
</html>