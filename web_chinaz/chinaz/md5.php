<?php
require_once("library/common.php");
require_once("library/view.php");
function action($post_data){
	foreach ($post_data as $key => $value) {
		$$key = $value;
	}
	if ($method==='md5'){
		$res = md5($source);
	}
	if ($method==='sha1'){
		$res = sha1($source);
	}
	return $res;
}
$view_class = new View();
$data = array();
$data['page'] = 'md5';
$data['res'] = action($post_data);
$view_class->echoContent($data['page'], $data);
?>