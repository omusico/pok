<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
<%@ taglib uri="/WEB-INF/tagtld/custaglib" prefix="ctl" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="dbconnpac.DataBaseConn, beanpac.PageShow" %>
<%
DataBaseConn userPS = (DataBaseConn) session.getAttribute("userpc");
PageShow objPS = (PageShow) session.getAttribute("bepagshow");
%>
<html>
<head>
<title>用户信息记录</title>
<style type="text/css">
body,td,th { font-size:13px;}
* { margin:0}
ul { list-style:none;}
.image1 a:link,.image1 a:visited { color:#000; text-decoration:none}
.image1 a:hover,.image1 a:active { color:#FF0000; text-decoration:underline;}
.image1 { background: url(/pkimages/mainimages/layout.gif) repeat-x top;}
.image2 { padding-top:2px; padding-left:20px;}
.image2 li { float:left; color:#FFF;margin:0 20px}
.image2 a:link,.image2 a:visited { color:#FFF; font-weight:bold; margin:0 2px 0 5px; text-decoration:none;}
.image2 a:active,.image2 a:hover { color:#FFFF00;} 

</style>
</head>

<body>
<table  align="center">
<tr>
<td align=center>时 时 乐 中 奖 信 息
</td>
</tr>
<tr>
<td>
<table width="910" height="300" border="2" cellpadding=0 cellspacing=0 bordercolorlight="#F1F554" bordercolordark="#FD4F4A">
  <tr height="10" align=center>
    <td>期号</td>
    <td>百位</td>
    <td>十位</td>
    <td>个位</td>
    <td>操作</td>
  </tr>

<%
for(int i = 0;i<objPS.getPageRecordNum();i++){
	int bil = i + (objPS.getCurrentPage()-1)*objPS.getPageRecordNum();
	if(userPS.rsNext()){	
%>
 <tr height=10 align=center>
    <td width=20%><%=userPS.rsGetString("issuenum")%><BR></td>
    <td width=20%><%=userPS.rsGetString("hundred")%><BR></td>
    <td width=20%><%=userPS.rsGetString("ten")%><BR></td>
    <td width=20%><%=userPS.rsGetString("one")%><BR></td>
    <td width=20%><%out.print("<a href=/servlet/lauwinpageshow?prizeid="+userPS.rsGetString("id")+">删除</a>");%><BR></td>
  </tr>
<%}else{%>
  <tr height=10 align=center>
    <td width=20%><BR></td>
    <td width=20%><BR></td>
    <td width=20%><BR></td>
    <td width=20%><BR></td>
    <td width=20%><BR></td>
  </tr>
<%}} %>
</table>
</td>
</tr>
<!-- 以上为记录区 -->

<tr>
<td><ctl:page curpage="<%=objPS.getCurrentPage()%>" maxpage="<%=objPS.getMaxPage()%>" prevpage="<%=objPS.getPrevPage()%>" nextpage="<%=objPS.getNextPage()%>" servhref="servlet/lauwinpageshow?"></ctl:page>
  </td>
</tr>
</table>
<%userPS.connClose(); %>
</body>
</html>