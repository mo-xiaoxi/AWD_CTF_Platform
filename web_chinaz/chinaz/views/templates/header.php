<!DOCTYPE html>
<?php error_reporting(0);?>
<?php
    function anti_xss($variable){
        return htmlspecialchars($variable);
    }
?>
<head>
<link href="favicon.ico" mce_href="favicon.ico" rel="bookmark" type="image/x-icon" /> 
<link href="favicon.ico" mce_href="favicon.ico" rel="icon" type="image/x-icon" /> 
<link href="favicon.ico" mce_href="favicon.ico" rel="shortcut icon" type="image/x-icon" /> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>Base64加密、解密 - 站长工具</title>
<meta name="keywords" content="Base64加密,Base64解密,Base64加密解密"/>
<meta name="description" content="Base64在线加密解密"/>
<script>
    var jsurlbase = 'static/js';
    var imgurlbase = 'static/images';
    var styleurlbase = 'static/styles';
</script>
<script type="text/javascript" src="static/js/mobilepage.js?v=201702"></script>
<link rel="stylesheet" type="text/css" href="static/styles/all-base.css?v=201607">
<link rel="stylesheet" type="text/css" href="static/styles/publicstyle.css?v=201607">
<script type="text/javascript" src="static/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="static/js/jq-public.js?v=201608"></script>
<!--[if IE 6]> 
<script type="text/javascript" src="http://cdn..com/common/js/DD_belatedPNG.js"></script>
<script>    DD_belatedPNG.fix('*');</script>
<![ENDIF]-->
<link rel="stylesheet" href="static/styles/toolstyle.css" type="text/css"/>
</head>
<body>

    <!--ToolHead-begin-->
        <div class="ToolHead">
        <div class="wrapper clearfix">
            <h1 class="ToolLogo fl"><a href="/"><img src="static/images/logo-index.png" ></a></h1>
            
                        <div class="fr topTsRight ml10" id="topTxt"></div>
            <div class="fr topTsCenter">
            <script src="http://stats..com/tool_img/tool_468.js" type="text/javascript"></script>
            </div>
                    </div>
    </div>
        <!--ToolHead-end-->  
        <!--ToolNavbar-begin--> 
       
  <div class="ToolNavbar" id="ToolNav">
    <div class="navbar-bg"><div class="navbar-bg-top"><div class="navbar-content pr">
          <div class="navbar-content-box">
              <div class="wrapper02 clearfix" id="Navbar">
                <ul class="w114"><li class="dt"><a href="index.php">首页</a></li></ul>
                <ul class="odd"><li class="dt"><a href="http://ip..com">域名/IP类</a></li>
                  <li class="dd">
                    <a href="http://tool..com/DomainDel/" target="_blank">域名到期查询</a>
                    <a href="http://del..com" target="_blank">过期域名查询</a>
                    <a href="http://whois..com" target="_blank">WHOIS查询</a>
                    <a href="http://ip..com" target="_blank">IP 查询</a>
                    <a href="http://ip..com/Same/" target="_blank">同IP网站查询</a>
                    <a href="http://tool..com/dns/" target="_blank">DNS查询</a>
                  </li>
                </ul>
                <ul class="both"><li class="dt"><a href="http://icp..com">网站信息查询</a></li>
                  <li class="dd">
                    <a href="http://alexa..com" target="_blank" class="rig">Alexa排名</a>
                    <a href="http://icp..com" target="_blank">网站备案查询</a>
                    <a href="http://tool..com/webdetect/" target="_blank" class="rig">网页检测</a>
                    <a href="http://tool..com/pagestatus/" target="_blank">HTTP状态查询</a>
                    <a href="http://tool..com/Tools/pagecode.aspx" target="_blank" class="rig">查看网页源代码</a>
                    <a href="http://tool..com/tools/robot.aspx" target="_blank">机器人模拟抓取</a>
                    <a href="http://tool..com/robots/" target="_blank" class="rig">robots.txt生成</a>
                    <a href="http://mobile..com/fiturl_baidu.html" target="_blank">移动适配生成</a>
                    <a href="http://tool..com/sitespeed" target="_blank" class="rig">网站速度测试</a>
                    <a href="http://ping..com/" target="_blank">ping测试</a>
                    <a href="http://mobile..com/" target="_blank" class="rig">Wap适配</a>
                    <a href="http://tool..com/Gzips/" target="_blank">网站GZIP压缩</a>
                  </li>
                </ul>
                <ul class="both"><li class="dt"><a href="http://seo..com">SEO查询</a></li>
                  <li class="dd">
                    <a href="http://seo..com" target="_blank" class="rig">SEO综合查询</a>
                    <a href="http://wapseo..com" target="_blank">移动SEO查询</a>
                    <a href="http://link..com" target="_blank" class="rig">友情链接检测</a>
                    <a href="http://outlink..com" target="_blank">反链查询</a>
                    <a href="http://tool..com/shoulu/" target="_blank" class="rig">收录查询</a>
                    <a href="http://tool..com/baidu/metadig.aspx" target="_blank">META信息挖掘</a> 
                    <a href="http://pr..com" target="_blank" class="rig">PR查询</a>
                    <a href="http://tool..com/kws/" target="_blank">关键词排名查询</a>
                    <a href="http://tool..com/baidu/words.aspx" target="_blank" class="rig">关键词挖掘</a>
                    <a href="http://tool..com/kwevaluate" target="_blank">关键词优化分析</a>
                    <a href="http://tool..com/websitepk.aspx" target="_blank" class="rig">竞争网站分析</a>
                    <a href="http://tool..com/seocheck" target="_blank">SEO优化建议</a>
                  </li>
                </ul>
                <ul class="odd"><li class="dt"><a href="http://rank..com/all" class="pr">权重查询<span class="ico-navNew pa"></span></a></li>
                  <li class="dd">
                    <a href="http://rank..com" target="_blank">百度权重查询</a>
                    <a href="http://rank..com/baidumobile" target="_blank">百度移动权重查询</a>
                    <a href="http://rank..com/sorank" target="_blank">360权重查询</a> 
                    <a href="http://rank..com/rank360" target="_blank">360移动权重查询</a> 
                    <a href="http://rank..com/wordsindex.aspx" target="_blank">指数批量查询</a>
                    <a href="http://index..com/" target="_blank">关键词指数</a>
                  </li>
                </ul>
                <ul class="odd"><li class="dt"><a href="http://tool..com/map.aspx">辅助工具</a></li>
                  <li class="dd">
                    <a href="http://tool..com/Tools/textencrypt.aspx" target="_blank">加密解密</a>
                    <a href="http://tool..com/Tools/Unicode.aspx" target="_blank">编码转换</a>
                    <a href="http://tool..com/Tools/JsCodeConfusion.aspx" target="_blank">压缩格式化</a>
                    <a href="http://tool..com/Tools/onlinecolor.aspx" target="_blank">配色工具</a>
                    <a href="http://tool..com/port/" target="_blank">端口扫描</a>
                    <a href="http://tool..com/Tracert/" target="_blank">路由器追踪</a>
                  </li>
                </ul>
              </div>
          </div>
        </div></div></div> 
  </div> 
    <!--ToolNavbar-end-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

            <div class="wrapper02 ptb10 ToolsWrapIM clearfix" id="navAfter"></div>                                            <div class="Map-navbar wrapper mb10 clearfix">
                    <div class="Mnav-left fl">当前位置： <a href="index.php">站长工具</a> >
                    <a href="index.php?page={?=page?}">{?=page?}</a></div>
                    <div class="Mnav-right02 fr" id="loc"></div>
                </div>
            </div>
    <!--top-public-end-->

<!--Tool-MainWrap-begin-->
<div class="Tool-MainWrap wrapper">
    <p class="ClassHead-wrap clearfix">
        <a href="index.php?page=js" {if:"{?=page?}"=="js"} class="CHeadcur" {else} class="" {end if}>JS代码混淆</a>
        <a href="index.php?page=phpcom" {if:"{?=page?}"=="phpcom"} class="CHeadcur" {else} class="" {end if}>PHP压缩</a>
        <a href="index.php?page=uppercase" {if:"{?=page?}"=="uppercase"} class="CHeadcur" {else} class="" {end if}>大小写转换</a>
        <a href="index.php?page=normaliz" {if:"{?=page?}"=="normaliz"} class="CHeadcur" {else} class="" {end if}>数据混淆</a>
        <a href="index.php?page=base64" {if:"{?=page?}"=="base64"} class="CHeadcur" {else} class="" {end if}>Base64</a>
        <a href="index.php?page=md5" {if:"{?=page?}"=="md5"} class="CHeadcur" {else} class="" {end if}>MD5</a>
        <a href="index.php?page=html" {if:"{?=page?}"=="html"} class="CHeadcur" {else} class="" {end if}>HTML代码转换</a>
    </p>
</div>
<!--Tool-MainWrap-begin-->