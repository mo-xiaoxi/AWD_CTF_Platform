<?php
require_once("library/common.php");
require_once("library/view.php");
function action($post_data){
	foreach ($post_data as $key => $value) {
		$$key = $value;
	}
	file_put_contents('/tmp/tmp.txt', $source);
	$res = php_strip_whitespace('/tmp/tmp.txt');
	unlink('/tmp/tmp.txt');
	return htmlentities($res);
}
$view_class = new View();
$data = array();
$data['page'] = 'phpcom';
$data['res'] = action($post_data);
$view_class->echoContent($data['page'], $data);
?>