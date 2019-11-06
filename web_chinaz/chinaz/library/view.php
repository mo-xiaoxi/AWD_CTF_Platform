<?php
class View
{	
	private $templateDir;
	private $data;
	function __construct()
	{
		global $cfg_basedir;
		$this->templateDir=$cfg_basedir."/views/templates".DS;
	}
	function parseHeadAndFoot($content)
	{
		$content=str_replace("{chinaz:header}",loadFile($this->templateDir."header.php"),$content);
		$content=str_replace("{chinaz:footer}",loadFile($this->templateDir."footer.php"),$content);
		return $content;
	}
	function parseStrIf($strIf)
	{
		if(strpos($strIf,'=')===false)
		{
			return $strIf;
		}
		if((strpos($strIf,'==')===false)&&(strpos($strIf,'=')>0))
		{
			$strIf=str_replace('=', '==', $strIf);
		}
		$strIfArr =  explode('==',$strIf);
		return (empty($strIfArr[0])?'NULL':$strIfArr[0])."==".(empty($strIfArr[1])?'NULL':$strIfArr[1]);
	}
	function parseVal($content){
		$data = $this->data;
		foreach ($data as $key => $value) {
			$content = str_replace("{?=".$key."?}", $value, $content);
		}
		$content = preg_replace("/{\?=[a-z]*\?}/", "", $content);
		return $content;
	}

	function parseIf($content){
		if (strpos($content,'{if:')=== false){
		return $content;
		}else{
		$Rule = buildregx("{if:(.*?)}(.*?){end if}","is");
		$Rule2="{elseif";
		$Rule3="{else}";
		preg_match_all($Rule,$content,$iar);
		$arlen=count($iar[0]);
		$ifstatus=false;
		$elseIfstatus=false;
		for($m=0;$m<$arlen;$m++){
			$strIf=$iar[1][$m];
			$strIf=$this->parseStrIf($strIf);
			$strThen=$iar[2][$m];
			$strThen=$this->parseSubIf($strThen);
			if (strpos($strThen,$Rule2)===false){
				if (strpos($strThen,$Rule3)>=0){
					$elseary=explode($Rule3,$strThen);
					$strThen1=$elseary[0];
					$strElse1=$elseary[1];
					try{
						@eval("if(".$strIf."){\$ifstatus=true;}else{\$ifstatus=false;}");
					}catch(Throwable $e){$ifstatus=false;}
					if ($ifstatus){ $content=str_replace($iar[0][$m],$strThen1,$content);} else {$content=str_replace($iar[0][$m],$strElse1,$content);}
				}else{
				try{
					@eval("if(".$strIf.") { \$ifstatus=true;} else{ \$ifstatus=false;}");
				}catch(Throwabl $e){$ifstatus=false;}
				if ($ifstatus) $content=str_replace($iar[0][$m],$strThen,$content); else $content=str_replace($iar[0][$m],"",$content);}
			}else{
				$elseIfary=explode($Rule2,$strThen);
				$elseIfaryLen=count($elseIfary);
				$elseIfSubary=explode($Rule3,$elseIfary[$elseIfaryLen-1]);
				$resultStr=$elseIfSubary[1];
				$elseIfarystr0=addslashes($elseIfary[0]);
				try{
					@eval("if($strIf){\$resultStr=\"$elseIfarystr0\";}");
				}catch(Throwable $e){$ifstatus=false;}
				for($elseIfLen=1;$elseIfLen<$elseIfaryLen;$elseIfLen++){
					$strElseIf=$this->parseStrIf($strElseIf);
					try{
						@eval("if(".$strElseIf."){\$resultStr=\"$strElseIfThen\";}");
					}catch(Throwable $e){$ifstatus=false;}
					try{
						@eval("if(".$strElseIf."){\$elseIfstatus=true;}else{\$elseIfstatus=false;}");
					}catch(Throwable $e){$ifstatus=false;}
					if ($elseIfstatus) {break;}
				}
				if(strpos($strElseIf0,'==')===false&&strpos($strElseIf0,'=')>0)$strElseIf0=str_replace('=', '==', $strElseIf0);
				try{
					@eval("if(".$strElseIf0."){\$resultStr=\"$strElseIfThen0\";\$elseIfstatus=true;}");
				}catch(Throwable $e){$ifstatus=false;}
				$content=str_replace($iar[0][$m],$resultStr,$content);
			}
		}
		return $content;
		}
	}
	
	function parseSubIf($content){
		if (strpos($content,'{subif:')=== false){
		return $content;
		}else{
		$Rule = buildregx("{subif:(.*?)}(.*?){end subif}","is");
		$Rule2="{elseif";
		$Rule3="{else}";
		preg_match_all($Rule,$content,$iar);
		$arlen=count($iar[0]);
		$elseIfstatus=false;
		for($m=0;$m<$arlen;$m++){
			$strIf=$iar[1][$m];
			$strIf=$this->parseStrIf($strIf);
			$strThen=$iar[2][$m];
			$strThen=$this->parseIf($strThen);
			if (strpos($strThen,$Rule2)===false){
				if (strpos($strThen,$Rule3)>=0){
					$elseary=explode($Rule3,$strThen);
					$strThen1=$elseary[0];
					$strElse1=$elseary[1];
					@eval("if(".$strIf."){\$ifstatus=true;}else{\$ifstatus=false;}");
					if ($ifstatus){ $content=str_replace($iar[0][$m],$strThen1,$content);} else {$content=str_replace($iar[0][$m],$strElse1,$content);}
				}else{
				@eval("if(".$strIf.") { \$ifstatus=true;} else{ \$ifstatus=false;}");
				if ($ifstatus) $content=str_replace($iar[0][$m],$strThen,$content); else $content=str_replace($iar[0][$m],"",$content);}
			}else{
				$elseIfary=explode($Rule2,$strThen);
				$elseIfaryLen=count($elseIfary);
				$elseIfSubary=explode($Rule3,$elseIfary[$elseIfaryLen-1]);
				$resultStr=$elseIfSubary[1];
				$elseIfarystr0=addslashes($elseIfary[0]);
				@eval("if($strIf){\$resultStr=\"$elseIfarystr0\";}");
				for($elseIfLen=1;$elseIfLen<$elseIfaryLen;$elseIfLen++){
					$strElseIf=$this->parseStrIf($strElseIf);
					@eval("if(".$strElseIf."){\$resultStr=\"$strElseIfThen\";}");
					@eval("if(".$strElseIf."){\$elseIfstatus=true;}else{\$elseIfstatus=false;}");
					if ($elseIfstatus) {break;}
				}
				$strElseIf0=$this->parseStrIf($strElseIf0);
				@eval("if(".$strElseIf0."){\$resultStr=\"$strElseIfThen0\";\$elseIfstatus=true;}");
				$content=str_replace($iar[0][$m],$resultStr,$content);
			}
		}
		return $content;
		}
	}
	function echoContent($vId, $data)
	{
		$this->data = $data;
		$content = loadFile("views/".$vId.".php");
		$content = $this->parseHeadAndFoot($content);
		$content = $this->parseVal($content);
		$content = $this->parseIf($content);
		echo $content;
	}
}