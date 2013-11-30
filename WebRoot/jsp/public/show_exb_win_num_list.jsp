<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
<%@page import="java.util.List"%>
<%@page import="com.onmyway.exb.manage.model.ExbZjNumConfig"%>
<%@ include file="/common/taglibs.jsp"%>

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
	<%
	 List<ExbZjNumConfig> list = (List<ExbZjNumConfig>)request.getAttribute("zjNumList");
	 int i = 0;
	 for(ExbZjNumConfig zjNum : list){
		if(i > 10){
			break;
		}
		i++;
		String p1 = zjNum.getPosition1();
		String p2 = zjNum.getPosition2();
		String p3 = zjNum.getPosition3();
		String p4 = zjNum.getPosition4();
		String p5 = zjNum.getPosition5();
		String p6 = zjNum.getPosition6();
		String p7 = zjNum.getPosition7();
		String p8 = zjNum.getPosition8();

 

		p1 = Integer.parseInt(p1) < 10 ? ("0"+p1) : p1;
		p2 = Integer.parseInt(p2) < 10 ? ("0"+p2) : p2;
		p3 = Integer.parseInt(p3) < 10 ? ("0"+p3) : p3;
		p4 = Integer.parseInt(p4) < 10 ? ("0"+p4) : p4;
		p5 = Integer.parseInt(p5) < 10 ? ("0"+p5) : p5;
		p6 = Integer.parseInt(p6) < 10 ? ("0"+p6) : p6;
		p7 = Integer.parseInt(p7) < 10 ? ("0"+p7) : p7;
		p8 = Integer.parseInt(p8) < 10 ? ("0"+p8) : p8;




	%>
	    <tr>
		   <td align="center"><%=zjNum.getIssueNum()%></td>
		   <td align="center"><%=p1%>,<%=p2%>,<%=p3%>,<%=p4%>,<%=p5%>,<%=p6%>,<%=p7%>,<%=p8%></td>
		</tr>		
 <%
	 }
%>

  </table>
</body>
</html>
