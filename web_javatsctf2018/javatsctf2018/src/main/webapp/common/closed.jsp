<%@ page import="com.bupt.utils.SystemDateTimeChecker" %><%--
  Created by IntelliJ IDEA.
  User: wust_
  Date: 2018/3/22
  Time: 1:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <div class="col-md-12 text-center">
        <h3>等待系统开放.......</h3>
        <h4>系统开放时间段：<%=SystemDateTimeChecker.from%> ~ <%=SystemDateTimeChecker.to%></h4>
        <h4>当前时间：<span id="currentTime"></span></h4>
    </div>
</div>
<script type="text/javascript">
    window.onload = function() {
        var show = document.getElementById("currentTime");
        setInterval(function() {
            var time = new Date();   // 程序计时的月从0开始取值后+1
            var m = time.getMonth() + 1;
            var t = time.getFullYear() + "-" + m + "-"
                    + time.getDate() + " " + time.getHours() + ":"
                    + time.getMinutes() + ":" + time.getSeconds();
            show.innerHTML = t;
        }, 1000);
    };
</script>