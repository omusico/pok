<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>华彩在线-彩票投注网</title>
<link href="/pokercss/mainlaugh.css" rel="stylesheet" type="text/css" />

<script src="/pokerfunc/global.js"></script>
<script src="/pokerfunc/pokfunc/tytitle.js"></script>

<style type="text/css">
.tab1 {cursor:hand;}
.typeready { background:url(/pkimages/pokerimage/img_tab2.gif) no-repeat center; text-align:center; cursor:hand}
.typechosen { background:url(/pkimages/pokerimage/img_tab1.gif) no-repeat center; text-align:center; cursor:hand}
</style>

</head>
<body>

<table width="630" height="30" border="0" cellpadding="0" cellspacing="0" style="border-bottom:#6A80F5 solid 2px">
   <tr>
	<td id="one" align=center width=70 class='typechosen' style="color:white" onClick="changeType('modtitcom','com','multi',this)">任选一</td>
	<td id="two" align=center width=70 class='typeready'  onClick="changeType('modtitcomtwo','com','multi',this)">任选二</td>
	<td id="three" align=center width=70 class='typeready'  onClick="changeType('modtitcom','com','multi',this)">任选三</td>
	<td id="four" align=center width=70 class='typeready'  onClick="changeType('modtitcom','com','multi',this)">选四直选</td>
	<td id="fourgr" align=center width=70 class='typeready'  onClick="changeType('modtitgr','gro','gr4',this)">组选单式</td>
	<td id="grmulti" align=center width=70 class='typeready'  onClick="changeType('modtitgr','grmul','gr4',this)">组选复式</td>
	<td id="grdantuo" align=center width=70 class='typeready'  onClick="changeType('modtitgr','grdt','gr4',this)">组选胆拖</td>
	<td id="compound" align=center width=70 class='typeready'  onClick="changeType('modtitemp','compcom','',this)">组合投注</td> 
	<td width=130><br></td>
   </tr>
  </table>
     
</body>
</html>
