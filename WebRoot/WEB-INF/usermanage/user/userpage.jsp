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
*{margin:0px}
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
<td align=center>用 户 信 息
</td>
</tr>
<tr>
<td>
<table width="910"  border="2" cellpadding=0 cellspacing=0 bordercolorlight="#F1F554" bordercolordark="#FD4F4A">
  <tr height="30" align=center>
    <td>用户名</td>
    <td>性别</td>
    <td>真实姓名</td>
    <td>身份证号</td>
    <td>email</td>
    <td>联系电话</td>
    <td>提款状态</td>
    <td>详细信息</td>
  </tr>

<%
for(int i = 0;i<objPS.getPageRecordNum() && userPS.rsNext();i++){
	int bil = i + (objPS.getCurrentPage()-1)*objPS.getPageRecordNum();
	//if(userPS.rsNext()){
%>
 <tr height=25 align=center>
    <td width=9%><%=userPS.rsGetString("username")%><BR></td>
    <td width=6%><%=userPS.rsGetString("sex")%><BR></td>
    <td width=9%><%=userPS.rsGetString("realname")%><BR></td>
    <td width=22%><%=userPS.rsGetString("idcardnumber")%><BR></td>
    <td width=13%><%=userPS.rsGetString("email")%><BR></td>
    <td width=13%><%=userPS.rsGetString("contactphone")%><BR></td>
    <td width=13%><%=userPS.rsGetString("drawstate")%><BR></td>
    <td width=6%><%out.print("<a href=/servlet/useinfread?frompage=man&userid="+userPS.rsGetString("id")+">查看</a>");%><BR></td>
  </tr>
<%//}else{%>
  <!--<tr height=10 align=center>
    <td width=100><BR></td>
    <td width=100><BR></td>
    <td width=100><BR></td>
    <td width=100><BR></td>
    <td width=100><BR></td>
    <td width=100><BR></td>
    <td width=100><BR></td>
    <td width=100><BR></td>
  </tr>
  -->
<%}//} %>
</table>
</td>
</tr>
<!-- 以上为记录区 -->

<tr>
  <td><ctl:page curpage="<%=objPS.getCurrentPage()%>" maxpage="<%=objPS.getMaxPage()%>" prevpage="<%=objPS.getPrevPage()%>" nextpage="<%=objPS.getNextPage()%>" servhref="servlet/userpageshow?"></ctl:page>
  </td>
</tr>

</table>
<%userPS.connClose(); %>
</body>
</html>