

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

<div class="container">
    <div class="row">
        <div class="col-md-12 text-center">
            <h3>${username}</h3>
        </div>
    </div>
    <div class="row" style="margin-top: 30px">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        提示信息信息
                    </h3>
                </div>
                <div class="panel-body">
                    <div class="row col-md-6">${message}</div>
                    <div class="row col-md-6 text-right">
                        <a href="${cp}profile/profileAdd.html" type="button" class="btn btn-success">完善报名信息</a>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<jsp:include page="/common/footer.jsp"></jsp:include>
</body>
</html>