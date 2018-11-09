<?php 
error_reporting(0);
require_once(dirname(__FILE__).'/../config.php');
function loadFile($filePath)
{
	global $cfg_basedir;
	if(!file_exists($filePath)){
		write_log('Try to open Null file:'.$filePath);
		return file_get_contents($cfg_basedir.'/error.php');
	}
	$fp = @fopen($filePath,'r');
	$sourceString = @fread($fp,filesize($filePath));
	@fclose($fp);
	return $sourceString;
}
function buildregx($regstr,$regopt)
{
	return '/'.str_replace('/','\/',$regstr).'/'.$regopt;
}
function filter($input)
{
	return str_replace('.', '', $input);
}
function write_log($input)
{
	global $cfg_logfile;
    file_put_contents($cfg_logfile, $input, FILE_APPEND);
}

//中文截取2，单字节截取模式
//如果是request的内容，必须使用这个函数
function cn_substrR($str,$slen,$startdd=0)
{
	$str = cn_substr(stripslashes($str),$slen,$startdd);
	return addslashes($str);
}

//中文截取2，单字节截取模式
function cn_substr_utf8($str, $length, $start=0)
{
	$lgocl_str=$str;
	//echo strlen($lgocl_str)."||".$length;
    if(strlen($str) < $start+1)
    {
        return '';
    }
    preg_match_all("/./su", $str, $ar);
    $str = '';
    $tstr = '';

    //为了兼容mysql4.1以下版本,与数据库varchar一致,这里使用按字节截取
    for($i=0; isset($ar[0][$i]); $i++)
    {
        if(strlen($tstr) < $start)
        {
            $tstr .= $ar[0][$i];
        }
        else
        {
            if(strlen($str) < $length  )
            {
                $str .= $ar[0][$i];
            }
            else
            {
                break;
            }
        }
    }
    return $str;
}
function ExecTime()
{
	$time = explode(" ", microtime());
	$usec = (double)$time[0];
	$sec = (double)$time[1];
	return $sec + $usec;
}

function getRunTime($t1)
{
	$t2=ExecTime() - $t1;
	return "页面执行时间: ".number_format($t2, 6)."秒";
}
function GetIP()
{
	if(!empty($_SERVER["HTTP_CLIENT_IP"]))
	{
		$cip = $_SERVER["HTTP_CLIENT_IP"];
	}
	else if(!empty($_SERVER["HTTP_X_FORWARDED_FOR"]))
	{
		$cip = $_SERVER["HTTP_X_FORWARDED_FOR"];
	}
	else if(!empty($_SERVER["REMOTE_ADDR"]))
	{
		$cip = $_SERVER["REMOTE_ADDR"];
	}
	else
	{
		$cip = '';
	}
	preg_match("/[\d\.]{7,15}/", $cip, $cips);
	$cip = isset($cips[0]) ? $cips[0] : 'unknown';
	unset($cips);
	return $cip;
}
?>