<?php
require_once("library/common.php");
require_once("library/view.php");
$view_class = new View();
$data = array();
if (isset($_GET['page']))
{
	$data['page'] = filter($_GET['page']);
}
else{
	$data['page'] = 'js';
}
$view_class->echoContent($data['page'], $data);
?>