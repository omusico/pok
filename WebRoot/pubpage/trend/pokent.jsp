<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>华彩在线-彩票投注网</title>
<link href="/pokercss/main.css" rel="stylesheet" type="text/css" />

<script src="/pokerfunc/xmlmeth/xmlglobal.js"></script>
<script src="/pokerfunc/xmlmeth/xmllogin.js"></script>

<style type="text/css">
.imguseque { background: url(/pkimages/mainimages/imguseque.gif) repeat-x top;margin:0px;}
</style>
</head>
<body>
<table> 
<tr>
<td align="center" bgcolor="#F4F4F4">
   <form name="formtrend" action="/servlet/numtrendchart" method="post" target="_blank">
      <input type="hidden" name="daynum" value="3">
      <input type="hidden" name="currissuenum" value="">
      <input type="hidden" name="play" value="poker">
      <input type="submit" name="subtrend" value="开奖号码走势图">
   </form>
  </td>
</tr> 
</table>
</body>
</html>