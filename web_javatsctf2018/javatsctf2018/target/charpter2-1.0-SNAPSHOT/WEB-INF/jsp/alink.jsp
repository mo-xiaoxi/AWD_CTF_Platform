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
    <div class="row text-center">
        <h2>信息公告栏</h2>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="row" style="margin-top: 50px">
                <div class="col-md-1"></div>
                <div class="col-md-10">
                    <div class="media" style="margin-bottom: 30px">
                        <div class="media-left media-middle">
                            <a href="#">
                                <img class="media-object" src="${cp}asset/image/icon.jpg" alt="...">
                            </a>
                        </div>
                        <div class="media-body">
                            <h4 class="media-heading" style="color: red"><a href="#">恋爱的季节</a></h4>
                            <div class="row">
                                <div class="col-md-12">一首《明天会更好》送给单身狗们</div>
                                <!--<div class="col-md-4 text-right"><a href="#" class="btn btn-success">查看</a></div>-->
                            </div>
                        </div>
                    </div>
                    <div class="media" style="margin-bottom: 30px">
                        <div class="media-left media-middle">
                            <a href="#">
                                <img class="media-object" src="${cp}asset/image/icon.jpg" alt="...">
                            </a>
                        </div>
                        <div class="media-body">
                            <h4 class="media-heading" style="color: red"><a href="#">恋爱的季节</a></h4>
                            <div class="row">
                                <div class="col-md-12"> 春天是一个恋爱的季节</div>
                                <!--<div class="col-md-4 text-right"><a href="#" class="btn btn-success">查看</a></div>-->
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-1"></div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/common/footer.jsp"></jsp:include>
</body>
</html>