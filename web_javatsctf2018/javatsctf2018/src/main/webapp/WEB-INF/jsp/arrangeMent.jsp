<%@ page import="com.bupt.utils.SysConfigUtils" %>

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
                    <a href="${cp}/profile/info.html" class="list-group-item list-group-item-info">想脱单的点进&nbsp;&nbsp;<span class="glyphicon glyphicon-fire" style="color: red"></span></a>
                    <a href="${cp}/profile/competitionRule.html" class="list-group-item list-group-item-success">恋爱经验分享&nbsp;&nbsp;<span class="glyphicon glyphicon-fire" style="color: red"></span></a>
                </div>
            </div>
            <div class="col-md-9">
                <div class="row">
                    <div class="col-sm-12">
                        <div class="col-xs-12">
                            <div class="row">
                                <div class="col-md-12 text-center">
                                    <h3>如何才能优雅的“脱单”&nbsp;&nbsp;<span class="glyphicon glyphicon-fire" style="color: red"></span></h3>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-12 text-center">
                                    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-12 text-center">

                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12 text-center">
                                    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;哈哈哈，一看就是一只还没脱单的单身狗，汪汪汪~</p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12 text-center">
                                    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>吃完今天的狗粮，早点洗洗睡吧</b></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row" style="margin-bottom: 50px"></div>
    </div>
</div>
<jsp:include page="/common/footer.jsp"></jsp:include>
</body>
</html>