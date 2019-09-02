<%--
  Created by IntelliJ IDEA.
  User: wust_
  Date: 2018/3/21
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="cp" value="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--bootstrap CSS-->
<link rel="stylesheet" href="${cp}bootstrap3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="${cp}css/bootstrap-datetimepicker.min.css">
<!--JavaScript-->
<script src="${cp}js/jquery-1.9.1.min.js"></script>
<!--bootstrap js-->
<script src="${cp}bootstrap3.3.5/js/bootstrap.min.js"></script>
<script src="${cp}js/bootstrap-datetimepicker.min.js"></script>
<script src="${cp}js/bootstrap-datetimepicker.zh-CN.js"></script>
<!--validation js-->
<script src="${cp}js/myValidator.js"></script>
<!--show Tips js-->
<script src="${cp}js/showTips.js"></script>