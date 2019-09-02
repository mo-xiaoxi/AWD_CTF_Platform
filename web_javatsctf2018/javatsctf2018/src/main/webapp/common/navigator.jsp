<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wust_
  Date: 2018/3/21
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-default navbar-static-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/index.html" target="_top" >恋爱的季节入口</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <%--<ul class="nav navbar-nav navbar-left">--%>
                <%--<li class="dropdown">--%>
                    <%--<a href="/info.html" target="_top">--%>
                        <%--信息公告--%>
                    <%--</a>--%>
                <%--</li>--%>
            <%--</ul>--%>
            <ul class="nav navbar-nav navbar-right">

                <c:if test="${username!=null}">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" target="_top" >
                            Hi~ ${username}<span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="/profile/my" target="_top" >查看恋爱心得</a></li>
                            <li><a href="/profile/myProfile" target="_top" >查看个人信息</a></li>
                            <li><a href="/user/logout" target="_top" >注销</a></li>
                        </ul>
                    </li>
                </c:if>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>
