<?php
require_once("library/common.php");
require_once("library/view.php");
function action($post_data, $ip_replacement, $mail_replacement){
	foreach ($post_data as $key => $value) {
		$$key = $value;
	}
	try{
		if ($method == '/\\d+\\.\\d+\\.\\d+\\.\\d+/')
		{
			$res = preg_replace($method, $ip_replacement, $source);
		}
		else
		{
			$res = preg_replace($method, $mail_replacement, $source);
		}

	}
	catch(Exception $e)
	{
		write_log($e->getMessage());
		$res=$source;
	}
	return $res;
}
$view_class = new View();
$data = array();
$data['page'] = 'normaliz';
$ip_replacement = '222.222.222.222';
$mail_replacement = 'lollol@lol.com';
$data['res'] = action($post_data, $ip_replacement, $mail_replacement);
$view_class->echoContent($data['page'], $data);
?>