<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="java.util.*, beanpac.FigWinInfo" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Win Prize Num Page</title>

<style type="text/css">
* { margin:0;}
body,td,th { font-size: 12px;}
</style>
</head>
<body>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr><td height=3></td></tr>
	<tr>
	  <td width="100%" height=30 align="center" bgcolor=#6691F4>
	   <font color="#DD3432" style="font:12px;"><b>最近开奖号码</b></font></td>
	</tr>
	</table>
	<table width="100%" border="1" bgcolor=white cellpadding="3" cellspacing="0" bordercolordark="#FFFFFF" bordercolorlight="#3f91b9" id="TodayOpencodeTb">
	<tr>
	  <td align="center" bgcolor="#DBE1F4">期号</td>
	  <td align="center" bgcolor="#DBE1F4">号码</td>
	</tr>
	<%Vector vecWNS=(Vector)session.getAttribute("winnumshowinfo"); 
	  int intVecWNSSize=vecWNS.size();
	  for(int i=0;i<intVecWNSSize;i++){
		  FigWinInfo beanFWI=(FigWinInfo)vecWNS.elementAt(i);
		  String strWin=beanFWI.getWinWagerNum();
		  out.println("<tr><td align=center>"+beanFWI.getStrIssueNum()+"</td>");
		  out.println("<td align=center>"+strWin+"</td></tr>");
	  }
	  for(int i=0;i<10-intVecWNSSize;i++){
		  out.println("<tr><td align=center><br></td>");
		  out.println("<td align=center><br></td></tr>");
	  }
	  %>
  </table>
</body>
</html>
