<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="cp" value="<%=basePath%>" />
<html>
<html>
<head>
    <title>index</title>
</head>
<body>
<h1>index</h1>
<%
    response.sendRedirect(basePath+"index.html");
%>
</body>
</html>