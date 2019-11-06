

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
        <div class="col-md-12">
            <h3>系统管理</h3>
        </div>
    </div>
    <div class="row" style="margin-top: 30px">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        下载报名表信息
                    </h3>
                </div>
                <div class="panel-body">
                    <form action="<%=basePath%>admin/excel.html" method="post">
                        <input type="submit" class="btn btn-success" value="下载">
                    </form>
                    <p>${flag}</p>
                </div>
            </div>

        </div>
    </div>
</div>
<jsp:include page="/common/footer.jsp"></jsp:include>
</body>
</html>
