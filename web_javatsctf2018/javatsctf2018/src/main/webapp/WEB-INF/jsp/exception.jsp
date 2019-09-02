

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
            <h3>请求出错</h3>
        </div>
    </div>
    <div class="row" style="margin-top: 30px">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        错误信息
                    </h3>
                </div>
                <div class="panel-body">
                    请求异常！
                </div>
            </div>

        </div>
    </div>
</div>
<jsp:include page="/common/footer.jsp"></jsp:include>
</body>
</html>