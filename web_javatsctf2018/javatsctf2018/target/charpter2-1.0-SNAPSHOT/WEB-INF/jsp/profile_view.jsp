
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
        <div class="row" ></div>
        <div class="row">
            <div class="col-md-2 text-center">
            </div>
            <div class="col-md-8 text-center">
                <h3><strong>用户${profile.name} 报名信息</strong></h3>
                <div class="row">
                    <div class="col-md-4 text-center">
                        <a href="${cp}/profile/my" class="btn btn-info btn-small">查看竞赛信息</a>
                    </div>
                    <div class="col-md-4"><h4><strong>已填写</strong><span class="glyphicon glyphicon-ok"></span></h4></div>
                    <div class="col-md-4 text-center">
                        <a href="${cp}/profile/profileEditHtml.html" class="btn btn-info btn-small">修改信息</a>
                    </div>
                </div>
            </div>
            <div class="col-md-2 text-center">
                <img src="${cp}${profile.photo}" alt="">
            </div>
        </div>
        <div class="row" style="margin-bottom: 10px"></div>
        <div class="row">
            <div class="col-md-12">
                <div class="row">
                    <div class="col-sm-12 text-right">
                        <div class="form-horizontal">
                            <form role="form">
                                <fieldset>
                                    <div class="row">
                                        <div class="col-md-5">
                                            <hr style="border: 3px double #ff0033">
                                        </div>
                                        <div class="col-md-2 text-center">
                                            <h5><strong>个人基本信息</strong></h5>
                                        </div>
                                        <div class="col-md-5">
                                            <hr style="border: 3px double #ff0033">
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label"> 姓 名:</label>
                                                ${Template}
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label"> 性 别:</label>
                                                <div class="col-sm-9 input-group">
                                                    <input type="text" name="gender" value="${profile.gender}" class="form-control">
                                                    <span class="input-group-addon">
                                                        <span class="glyphicon glyphicon-user"></span>
                                                    </span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label"> 出生年月:</label>
                                                <div class="col-sm-9 input-group">
                                                    <input type="text" name="birthday" value="${profile.birthday}" class="form-control">
                                                    <span class="input-group-addon">
                                                        <span class="glyphicon glyphicon-calendar"></span>
                                                    </span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label"> 科 类:</label>
                                                <div class="col-sm-9 input-group">
                                                    <input type="text" name="type" value="${profile.type}" class="form-control">
                                                    <span class="input-group-addon">
                                                        <span class="glyphicon glyphicon-pushpin"></span>
                                                    </span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label"> 联系电话:</label>
                                                <div class="col-sm-9 input-group">
                                                    <input type="text" name="phone" value="${profile.phone}" class="form-control">
                                                    <span class="input-group-addon">
                                                        <span class="glyphicon glyphicon-phone"></span>
                                                    </span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label"> 邮箱:</label>
                                                <div class="col-sm-9 input-group">
                                                    <input type="text" name="mail" value="${profile.mail}" class="form-control">
                                                    <span class="input-group-addon">
                                                        <span class="glyphicon glyphicon-envelope"></span>
                                                    </span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-5">
                                            <hr style="border: 3px double #ff0033">
                                        </div>
                                        <div class="col-md-2 text-center">
                                            <h5><strong>更多</strong></h5>
                                        </div>
                                        <div class="col-md-5">
                                            <hr style="border: 3px double #ff0033">
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label"> 兴趣爱好:</label>
                                                <div class="col-sm-9 input-group">
                                                    <textarea name="hobby" class="form-control" rows="3">${profile.hobby}</textarea>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label"> 请详细描述在网络安全技术领域参加的竞赛或者取得的突出成就:</label>
                                                <div class="col-sm-9 input-group">
                                                    <textarea name="description" class="form-control" rows="3">${profile.description}</textarea>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </fieldset>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row" style="margin-bottom: 30px"></div>
    </div>
</div>
<jsp:include page="/common/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
    $(function () {
        $('input').attr("readonly","readonly")//将input元素设置为readonly
        $('textarea').attr("readonly","readonly")//将input元素设置为readonly
    })
</script>
