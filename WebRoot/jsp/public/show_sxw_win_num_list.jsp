<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
<%@page import="java.util.List"%>
<%@page import="com.onmyway.sxw.manage.model.SxwZjNumConfig"%>
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
	 List<SxwZjNumConfig> list = (List<SxwZjNumConfig>)request.getAttribute("zjNumList");
	 int i = 0;
	 for(SxwZjNumConfig zjNum : list){
		if(i > 10){
			break;
		}
		i++;
		String wan = zjNum.getWan();
		String qian = zjNum.getQian();
		String bai = zjNum.getBai();
		String shi = zjNum.getShi();
		String ge = zjNum.getGe();

		wan = Integer.parseInt(wan) < 10 ? ("0"+wan) : wan;
		qian = Integer.parseInt(qian) < 10 ? ("0"+qian) : qian;
		bai = Integer.parseInt(bai) < 10 ? ("0"+bai) : bai;
		shi = Integer.parseInt(shi) < 10 ? ("0"+shi) : shi;
		ge = Integer.parseInt(ge) < 10 ? ("0"+ge) : ge;
	%>
	    <tr>
		   <td align="center"><%=zjNum.getIssueNum()%></td>
		   <td align="center"><%=wan%>,<%=qian%>,<%=bai%>,<%=shi%>,<%=ge%></td>
		</tr>		
 <%
	 }
%>

  </table>
</body>
</html>
