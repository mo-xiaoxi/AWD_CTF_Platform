
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

        <div class="row" style="margin-bottom: 10px"></div>
        <div class="row">
            <div class="col-md-3  text-left">
                <div class="list-group">
                    <a href="${cp}/profile/profileEditHtml.html" class="list-group-item list-group-item-success">修改个人信息</a>
                    <a href="${cp}/profile/info.html" class="list-group-item list-group-item-info">线上预赛安排</a>
                    <a href="${cp}/profile/competitionRule.html" class="list-group-item list-group-item-success">竞赛规则说明</a>
                </div>
            </div>
            <div class="col-md-9">
                <jsp:include page="/common/competitionInfo.jsp"></jsp:include>
            </div>
        </div>
        <div class="row" style="margin-bottom: 50px"></div>
    </div>
</div>
<jsp:include page="/common/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
    $(function () {
        $('input').attr("readonly","readonly")//将input元素设置为readonly
        $('textarea').attr("readonly","readonly")//将input元素设置为readonly
    })
</script>
