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
        <jsp:include page="/common/competitionInfo.jsp"></jsp:include>
        <div class="row" style="margin-bottom: 50px;">
            <div class="col-md-12 text-center">

                <a href="${cp}user/loginHtml.html" class="btn btn-success">立即登录</a>

            </div>
        </div>
        <div class="row">
            <div class="col-md-12">

            </div>
        </div>
        <div class="row">
            <div class="col-md-12">

            </div>
        </div>
    </div>
</div>
<jsp:include page="/common/footer.jsp"></jsp:include>
</body>
</html>