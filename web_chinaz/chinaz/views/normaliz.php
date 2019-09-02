<html>
{chinaz:header}

<!--ToolMain02-begin-->
<div class="wrapper wwlr">  
    <form method="post" action="action.php" id="normaliz">
        <!--GuoLvWrap-begin--> 
        <div class="clearfix">
            <div class="pr JsTxtW-r fl">
                <textarea form="normaliz" class="JsTxtCo bor-a1s h200 WrapHid wwlr-l" id="content" name="source"></textarea> 
                <b class="search-hint CentHid">原始数据</b>
            </div>
            <div class="pr JsTxtW-r ml15 fl">
                <textarea form="normaliz" class="JsTxtCo bor-a1s h200 WrapHid wwlr-r" id="result" name="res"></textarea> 
                <b class="search-hint CentHid">{if:"{?=res?}"==""} 结果数据 {else} {?=res?} {end if}</b>
            </div> 
        </div>
            <div class="GuoLvWrapCenter ptb10 clearfix">
            <div class="GuoLvCbtn">
                <input class="GLOkBtn mb10" type="button" value="IP混淆" t="ip" onclick="ip_submit()"/>
                <input class="GLOkBtn mb10" type="button" value="邮箱混淆" t="mail" onclick="mail_submit()"/>
                <input type="hidden" name="page" value="normaliz" />
                <input type="hidden" id="normaliz_method" name="method" value="" />
            <input type="reset" id="clear" value="清空结果" class="bg-blue02 YaHei" />
            </div>
          </div>

        <!--GuoLvWrap-end-->
    </form> 
</div>
<!--ToolMain02-end-->
<script>
    function ip_submit(){
        document.getElementById('normaliz_method').value="/\\d+\\.\\d+\\.\\d+\\.\\d+/";
        document.getElementById('normaliz').submit();
    }
    function mail_submit(){
        document.getElementById('normaliz_method').value="/([0-9A-Za-z\\-_\\.]+)@([0-9a-z]+\\.[a-z]{2,3}(\\.[a-z]{2})?)/";
        document.getElementById('normaliz').submit();
    }
</script>


{chinaz:footer}