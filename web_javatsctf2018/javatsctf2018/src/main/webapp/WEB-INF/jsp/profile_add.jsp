
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
                <div class="col-md-12 text-center">
                    <h3><strong>恋爱的季节</strong></h3>
                    <h3><strong>培训申请表</strong></h3>
                </div>
            </div>
            <div class="row" style="margin-bottom: 10px"></div>
            <div class="row">
                <div class="col-md-12">
                    <div class="row">
                        <div class="col-sm-12 text-right">
                            <div class="form-horizontal">
                                <form role="form" action="${cp}profile/add" id="profile-submit" method="post" enctype="multipart/form-data">
                                    <fieldset>
                                        <div class="row">
                                            <div class="col-md-5">
                                                <hr style="border: 3px double #ff0033">
                                            </div>
                                            <div class="col-md-2 text-center">
                                                <h5><strong>个人基本信息【必填】</strong></h5>
                                            </div>
                                            <div class="col-md-5">
                                                <hr style="border: 3px double #ff0033">
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="col-sm-3 control-label">* 姓 名:</label>
                                                    <div class="col-sm-9 input-group">
                                                        <input type="text" id="name" name="name" onblur="checkLength(this.id,this.value,2,12)" class="form-control checked-no">
                                                        <span class="input-group-addon">
                                                        <span class="glyphicon glyphicon-user"></span>
                                                    </span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="col-sm-3 control-label">* 性 别:</label>
                                                    <div class="col-sm-3"></div>
                                                    <div class="col-sm-3 input-group">
                                                        <label for="">男</label>&nbsp;
                                                        <span class="input-group-addon">
                                                    <input type="radio" name="gender" value="男" checked aria-label="...">
                                                </span>
                                                        <label for="gender">女</label>&nbsp;
                                                        <span class="input-group-addon">
                                                    <input type="radio" name="gender" value="女" aria-label="...">
                                                </span>
                                                    </div>
                                                    <div class="col-sm-3"></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="col-sm-3 control-label">* 出生年月:</label>
                                                    <div class="col-sm-9 input-group" id="datetimepicker">
                                                        <input type="text" id="birthday" name="birthday" value="2000-01-01"  class="form-control">
                                                        <span class="input-group-addon">
                                                        <span class="glyphicon glyphicon-calendar"></span>
                                                    </span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <div class="form-group">
                                                        <label class="col-sm-3 control-label">* 科 类:</label>
                                                        <div class="col-sm-1"></div>
                                                        <div class="col-sm-7 input-group">
                                                            <label for="">理科</label>&nbsp;
                                                            <span class="input-group-addon">
                                                        <input type="radio" name="type" value="理科" checked aria-label="...">
                                                    </span>
                                                            <label for="">文科</label>&nbsp;
                                                            <span class="input-group-addon">
                                                        <input type="radio" name="type" value="文科" aria-label="...">
                                                    </span>
                                                            <label for="">综合改革</label>&nbsp;
                                                            <span class="input-group-addon">
                                                        <input type="radio" name="type" value="综合改革" aria-label="...">
                                                    </span>
                                                        </div>
                                                        <div class="col-sm-1"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>



                                        <div class="row">

                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="col-sm-3 control-label">* 联系电话:</label>
                                                    <div class="col-sm-9 input-group">
                                                        <input type="text" name="phone" id="phone" onblur="checkPhoneNumber(this.id,this.value)" class="form-control checked-no">
                                                        <span class="input-group-addon">
                                                        <span class="glyphicon glyphicon-phone"></span>
                                                    </span>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="col-sm-3 control-label">* 邮箱:</label>
                                                    <div class="col-sm-9 input-group">
                                                        <input type="text" name="mail" id="mail" value="${email}"  class="form-control">
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
                                                <h5><strong>更多【必填】</strong></h5>
                                            </div>
                                            <div class="col-md-5">
                                                <hr style="border: 3px double #ff0033">
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">* 照片:</label>
                                                    <div class="col-sm-10 input-group">
                                                        <div class="col-xs-12 col-sm-4 col-md-4">
                                                            <div class="file-container" style="display:inline-block;position:relative;overflow: hidden;vertical-align:middle">
                                                                <button class="btn btn-success fileinput-button" type="button">上传</button>
                                                                <input type="file" id="image" name="image" class="checked-no" onchange="loadFile(this.files[0])" style="position:absolute;top:0;left:0;font-size:34px; opacity:0">
                                                            </div>
                                                            <span id="filename" style="vertical-align: middle">未上传文件</span>
                                                        </div>
                                                        <div class="col-sm-8 text-left">
                                                            <span style="color: red">【图片大小不超过4M，仅支持png,jpg,jpeg类型图片格式】</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="form-group">
                                                    <label class="col-sm-4 control-label">* 兴趣爱好<label class='text-danger'>【100字以内，没有则写无】</label>:</label>
                                                    <div class="col-sm-8 input-group">
                                                        <textarea name="hobby" id="hobby" onblur="checkLength(this.id,this.value,1,200)" class="form-control checked-no" rows="3"></textarea>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="form-group">
                                                    <label class="col-sm-4 control-label">* 请详细描述在网络安全技术领域参加的竞赛或者取得的突出成就<label class='text-danger'>【300字以内，没有则写无】</label>:</label>
                                                    <div class="col-sm-8 input-group">
                                                        <textarea name="description" onblur="checkLength(this.id,this.value,1,600)" id="description" class="form-control checked-no" rows="4"></textarea>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </fieldset>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12 text-right" style="padding-right: 0px;margin-bottom: 50px">
                            <button type="submit" onclick="submitProfile()" class="btn btn-success">个人信息提交</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/common/footer.jsp"></jsp:include>
</body>
<input type="hidden" name="user_id" value="${userId}"/>
<script type="text/javascript">
    $(function () {
        $("#birthday").datetimepicker({
            format: 'yyyy-mm-dd',
            language: 'zh-CN',
            weekStart: 1,
            todayBtn: 1,//显示‘今日’按钮
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 2,  //Number, String. 默认值：0, 'hour'，日期时间选择器所能够提供的最精确的时间选择视图。
            clearBtn:true,//清除按钮
            forceParse: 0
        });
        //设置为不可输入
        $('#birthday').focus(function(){
            $(this).blur();//不可输入状态
        })
        //邮箱不可编辑
        $('#mail').attr("readonly","readonly")//将email设置为readonly
        ShowMsg("${message}");
    })

    function loadFile(file){
        var fileName = file.name;
        var index = fileName.lastIndexOf('.');
        var ext = fileName.substring(index+1).toLowerCase();
        if(file.size>4*1024*1000){
            $("#image").removeClass("checked-no");
            $("#image").addClass("checked-no");
            $("#filename").html("");
            ShowWarn("文件大小超过限制");
        }
        if(ext=="jpg"||ext=="png"||ext=="jpeg"){
            $("#image").removeClass("checked-no");
            $("#filename").html(fileName);
        }else {
            $("#image").removeClass("checked-no");
            $("#image").addClass("checked-no");
            $("#filename").html("");
            ShowWarn("仅支持的图片上传格式：JPG，PNG，JPEG！");
        }
    }
    function checkPic() {

    }
    function addBackgroundWarningColor(id){
        if(id.indexOf("#")>=0){
            $(""+id).css("background-color","#FFE7BA");
            $(""+id).removeClass("checked-no");
            $(""+id).addClass("checked-no");
        }else{
            $("#"+id).css("background-color","#FFE7BA");
            $("#"+id).removeClass("checked-no");
            $("#"+id).addClass("checked-no");
        }
    }
    function removeBackgroundWarningColor(id){
        if(id.indexOf("#")>=0) {
            $("" + id).css("background-color", "#ffffff");
            $(""+ id).removeClass("checked-no");
        }else{
            $("#"+id).css("background-color","#ffffff");
            $("#"+id).removeClass("checked-no");
        }
    }

    function checkPhoneNumber(id,str) {
        str = myTrim(str);
        $("#"+id).val(str);
        if(validatePhoneNumber(str)){
            removeBackgroundWarningColor(id);
        }else {
            ShowFailure("手机号格式不合法！")
            addBackgroundWarningColor(id);
        }
    }

    function checkLength(id,str,min,max) {
        str = myTrim(str);
        $("#"+id).val(str);
        if(validateLengthMin(str,min)&&validateLengthMax(str,max)){
            removeBackgroundWarningColor(id);
        }else {
            ShowFailure("参数长度不合法！,"+min+"-"+max+"个字符")
            addBackgroundWarningColor(id);
        }
    }

    function submitProfile() {
        var name = $("#name").val();//长度2-12
        var gender = $("#gender").val();//男、女
        var type = $("#type").val();//科类：理科、文科、综合改革
        var mail = $("#mail").val();//邮箱校验

        var checkedNo = $(".checked-no");
        if(typeof checkedNo !='undefined' && checkedNo.length>=1){
            ShowWarn("请按照要求填写表单！");
        }else{
            if($("#image")[0].size>4*1024*1000){
                ShowMsg("文件大小超过限制");
            }else {
                $("#profile-submit").submit();
            }
        }
    }

</script>