<html>
{chinaz:header}

<!--ToolMain02-begin-->
<div class="wrapper"> 
    <div class="wwlr auto">
    <form id="phpcom" method="POST" action="action.php">
        <!--GuoLvWrap-begin--> 
   		<div class="clearfix">
            <div class="pr JsTxtW-r fl">
		        <textarea form="phpcom" name="source" class="JsTxtCo bor-a1s h200 WrapHid wwlr-l" id="txtInitCode"></textarea> <b class="search-hint CentHid">原始代码</b></div>  
            <div class="pr JsTxtW-r ml20 fl">
            <div class="JsTxtCo h200 autohide pa" style=" top:0;left:0; line-height:200px; text-align:center" id="loading">正在解析<img src="http://cdn..com/tools/images/public/loader.gif" /></div>
            <textarea form="phpcom" name="res" class="JsTxtCo bor-a1s h200 WrapHid wwlr-l" id="txtResultCode"></textarea> <b class="search-hint CentHid"> {if:"{?=res?}"==""} 结果代码 {else} {?=res?} {end if}</b></div>
            <input type="hidden" name="page" value="phpcom" />
     </div>
        
          <div class="GuoLvWrapCenter pt10 clearfix">
            <div class="GuoLvCbtn" id="btndiv">
            <input type="button" value="排版" class="GLOkBtn" ref="beauty" />
            <input type="button" value="去除注释" class="GLOkBtn" ref="filtercomment" />
            <input type="submit" value="普通压缩" class="GLOkBtn" ref="basiccompress" />
            <input type="button" value="加密压缩" class="GLOkBtn" ref="encodecompress" />
            <input type="button" value="解密" class="GLOkBtn" ref="decodebeauty" /> 
            <input type="button" value="复制" class="bg-blue02" id="clip" data-clipboard-target="txtInitCode" />
            <input type="button" id="clear" value="清空" class="bg-blue02" />
            </div>
          </div>
        <!--GuoLvWrap-end-->
    </form>
    </div>
</div>
<script>    hcj.jstool.init('http://cdn..com/tools/images','7KutWgYPLyOk18Cbz2L9K817XbENwcVH');</script>
<!--ToolMain02-end-->

{chinaz:footer}