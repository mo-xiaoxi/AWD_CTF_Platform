<%@ page import="com.bupt.utils.SysConfigUtils" %>

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

        <div class="row" style="margin-bottom: 10px"></div>
        <div class="row">
            <div class="col-md-3  text-left">
                <div class="list-group">
                    <a href="${cp}/profile/profileEditHtml.html" class="list-group-item list-group-item-success">修改个人信息</a>
                    <a href="${cp}/profile/info.html" class="list-group-item list-group-item-success">想脱单的点进&nbsp;&nbsp;<span class="glyphicon glyphicon-fire" style="color: red"></span></a>
                    <a href="${cp}/profile/competitionRule.html" class="list-group-item list-group-item-info">恋爱经验分享&nbsp;&nbsp;<span class="glyphicon glyphicon-fire" style="color: red"></span></a>
                </div>
            </div>
            <div class="col-md-9">
                <div class="row">
            <div class="col-sm-12">
                <div class="col-xs-12">
                    <div class="row">
                        <div class="col-md-12 text-center">
                            <h3>恋爱的季节-经验分享&nbsp;&nbsp;<span class="glyphicon glyphicon-fire" style="color: red"></span></h3>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12 text-center">
                            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12 text-center">

                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="row">
                                <div class="col-md-12 text-left" style="padding-left:7%">
                                    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;无论从生物学人类繁衍，物种延续，基因传承的本能来说，还是社会学人们对亲密关系的需求，爱情是世世代代人类需要面临的问题</p>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12" style="padding-left:5%">
                            <p>&nbsp;&nbsp;&nbsp;&nbsp;<b>1. 增加魅力</b></p>

                            <div class="row">
                                <div class="col-md-12" style="padding-left:5%">
                                    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;吸引异性的魅力有两种，一种是和性别无关的魅力，这种魅力男女都可以有，例如：外貌姣好，情商高；另一种是和性别有关的魅力，例如：男性的魁梧高大、事业有成，女性的温柔可爱。因此不管是追求阶段还是恋爱当中，都需要注意这种魅力的展示与提升。从外在形象，人品学识，到才华能力都是产生吸引力的因素，很多时候，一个人的魅力往往在细节中体现出来，比如：男生约女生吃饭的时候主动帮对方调节座位则被认为是有风度的表现。
                                    </p>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-12" style="padding-left:5%">
                                <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>2. 增加相处机会</b></p>

                                <div class="row">
                                    <div class="col-md-12" style="padding-left:5%">
                                        <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日远日疏，日近日亲”，两人接触在一起的时间长了，就会制造出机会来。所以看到一个中意的男生，一定要制造机会缠住他，有机会要接近他，没有机会创造机会也要接近他。《倚天屠龙记》赵敏就是心有灵犀，也自觉不自觉地运用了这一计策，正是在去灵蛇岛的途中，张无忌和赵敏才有了深厚的感情。 共同经历更容易产生共鸣。一起有过一段美好的经历，彼此可能产生性吸引。一起经历过苦难，在苦难中相互帮助，给与彼此温暖的感觉，彼此也可能产生性吸引。要有共同的经历，首先得增加接触，在早期可以从做朋友开始，相处的机会多了，在一起有了共同的经历，相互之间了解更多，就可能逐渐产生感情
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12" style="padding-left:5%;margin-bottom: 50px">
                                    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>3. 恋爱经验下载</b></p>

                                    <div class="row">
                                        <div class="col-md-4" style="padding-left:5%">
                                            <a href="${cp}download/files?file=dlive.pdf" class="btn btn-primary btn-sm">
                                                <span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span> D师傅的恋爱经验
                                            </a>
                                        </div>
                                        <div class="col-md-4" style="padding-left:5%">
                                            <a href="${cp}download/files?file=spike.pdf" class="btn btn-primary btn-sm">
                                                <span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span> S师傅的恋爱经验
                                            </a>
                                        </div>
                                        <div class="col-md-4" style="padding-left:5%">
                                            <a href="${cp}download/files?file=afternoon.pdf" class="btn btn-primary btn-sm">
                                                <span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span> A师傅的恋爱经验
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>

                    </div>
                </div>

            </div>
        </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">

            </div>
        </div>
        <div class="row" style="margin-bottom: 50px">
            <div class="col-md-12">

            </div>
        </div>
    </div>
</div>
<jsp:include page="/common/footer.jsp"></jsp:include>
</body>
</html>