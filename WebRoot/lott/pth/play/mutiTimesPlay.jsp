<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>华彩在线-彩票投注网-排列三</title>
<link href="/pokercss/mainlaugh.css" rel="stylesheet" type="text/css" />

<script src="/pokerfunc/laughfunc/lcom.js"></script>
<script src="/pokerfunc/pthfunc/pthmuti.js"></script>
<script src="/pokerfunc/global.js"></script>

<style type="text/css">
.plare { background:url(/pkimages/permimage/plare.gif) no-repeat center; text-align:center; font-weight:bold; cursor:hand; color:#7f7f7f; padding-bottom:0px;}
.plach { background:url(/pkimages/permimage/plach.gif) no-repeat center; text-align:center; font-weight:bold; cursor:hand; color:black; padding-bottom:0px;}
</style>

</head>
<body>


	<table width=600 border="0" cellspacing="0" cellpadding="5" bordercolordark="#FFFFFF" bordercolorlight="#cccccc" style="border:#1890a8 solid; border-width:0 1px;">
	  <!------------倍投------------->
	   <tr>     
        <td>
			<input type="checkbox" name="chestmuliss" onClick="switchMulIss()">对本期进行倍注&nbsp;&nbsp;&nbsp;&nbsp;
倍投数：<!--<input id="mulTimesOld" name="mulTimesOld" type="text" size="3"  value="1" disabled onchange="changeVal()"  onkeypress="checkNumeric(this.value)">   -->

			<input type="button" name="mulTimesSub" id="mulTimesSub" onclick="sub_sum()" value=" - " disabled>
			<input type="text" name="readMulTimes" id="readMulTimes" value="1" size="5" readonly>
			<input type="hidden" name="mulTimes" id="mulTimes" value="1" >

			<input type="button" name="mulTimesPlus" id="mulTimesPlus" onclick="plus_sum()" value=" + " disabled>
		</td>    
      </tr>
	  <tr>     
        <td> 
			总注数为：<span id="curisswagnum" style="color:red; font-weight:bold "></span>
            总金额为：<span id="curissmon" style="color:red; font-weight:bold "></span>元       
          </td>    
      </tr>
		<input type="hidden" name="tempIssuetype" value="">
		<input type="hidden" name="tempGetissuenumber"  value="">

    </table>
</body>
</html>
