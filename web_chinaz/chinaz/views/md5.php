<html>
{chinaz:header}

<<!--ToolMain02-begin-->
<div class="wrapper wwlr"> 
    <form id="md5" method="POST" action="action.php">
    <div class="clearfix">
        <!--GuoLvWrap-begin--> 
        <div class="JsTxtW-r fl pr"><textarea form="md5" name="source" class="JsTxtCo bor-a1s h200 WrapHid wwlr-l" id="content"></textarea> 
        <b class="search-hint CentHid">请把你需要加密的内容粘贴在这里</b></div>
        
        <div class="JsTxtW-r fl ml15 pr"><textarea form="md5" name="res" class="JsTxtCo bor-a1s h200 WrapHid wwlr-r" id="result"></textarea> 
        <b class="search-hint CentHid">{if:"{?=res?}"==""} 加密后的结果 {else} {?=res?} {end if}</b></div> 
        
        
    </div>
        

          <div class="GuoLvWrapCenter pt10 clearfix">
            <div class="GuoLvCbtn ww100" id="btnlist">
                <input class="GLOkBtn mb10" type="button" value="SHA224" t="sha224"/>
                <input class="GLOkBtn mb10" type="button" value="SHA256" t="sha256" />
                <input class="GLOkBtn mb10" type="button" value="SHA384" t="sha384"/>
                <input class="GLOkBtn mb10" type="button" value="SHA512" t="sha512"/>
                <input class="GLOkBtn mb10" type="button" value="HmacSHA1" t="hmacsha1"/>
                <input class="GLOkBtn mb10" type="button" value="HmacMD5" t="hmacmd5" />  
                <input class="GLOkBtn mb10" type="button" value="HmacSHA224" t="hmacsha224"/>
                <input class="GLOkBtn mb10" type="button" value="HmacSHA256" t="hmacsha256" />
                <input class="GLOkBtn mb10" type="button" value="HmacSHA384" t="hmacsha384"/>
                <input class="GLOkBtn mb10" type="button" value="HmacSHA512" t="hmacsha512"/>
                <input class="GLOkBtn mb10" type="button" value="MD5" t="md5" onclick="md5_submit()"/>
                <input class="GLOkBtn mb10" type="button" value="SHA1" t="sha1" onclick="sha1_submit()"/>
                <input type="hidden" name="page" value="md5" />
                <input type="hidden" id="hash_method" name="method" value="" />
                <input type="button" id="clear" value="清空结果" class="bg-blue02 mb10 YaHei" />
                <div class="GlOtherWay mt3 pl10 autohide" style="float:left;" id="p_div"><span>密码：</span><input type="text" class="w70 bor-a1s tl" id="pwd"/></div>
            </div>
          </div>
          
 
        <!--GuoLvWrap-end-->
    </form>
</div>
<!--ToolMain02-end-->
<script>
    function md5_submit(){
        document.getElementById('hash_method').value="md5";
        document.getElementById('md5').submit();
    }
    function sha1_submit(){
        document.getElementById('hash_method').value="sha1";
        document.getElementById('md5').submit();
    }
</script>


{chinaz:footer}