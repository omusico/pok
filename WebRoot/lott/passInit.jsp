<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true" 
    import="beanpac.ServerTime,beanpac.IssConInfo"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title></title>
<link href="/pokercss/main.css" rel="stylesheet" type="text/css" />

<script src="/pokerfunc/global.js" type="text/javascript"></script>
<script src="/pokerfunc/glotime.js" type="text/javascript"></script>
<script src="/pokerfunc/laughfunc/lautimerunwin.js" type="text/javascript"></script>
<script type='text/javascript' src='/dwr/interface/InitEncryptPassword.js'></script>
<script type='text/javascript' src='/dwr/engine.js'></script>
<script type='text/javascript' src='/dwr/util.js'></script>
<script language="javascript">
<!--
	function initPass(){

		InitEncryptPassword.encryptPassword(function(data){init_callback(data)});
	}
	function init_callback(data){
		alert(data);
		document.getElementById("tab").style.display="none";
	}
//-->
</script>
 
 
 </head>

 <body>
 <br><br><br>
  <form name="frm" method="post">
		<table id="tab" name="tab" style="display:">
		<tr>
			<td>
					<input type="button" name="butInit" id="butInit" value="口令加密" onclick="initPass()">

			</td>
 		</tr>
		</table>
  </form>	
 </body>
</html>
