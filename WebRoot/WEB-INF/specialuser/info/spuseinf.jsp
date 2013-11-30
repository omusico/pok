<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="dbconnpac.DataBaseConn" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>华彩在线-彩票投注网</title>
<link href="/pokercss/main.css" rel="stylesheet" type="text/css" />

<style type="text/css">
.image1 a:link,.image1 a:visited { color:#00F; text-decoration:none}
.image1 a:hover,.image1 a:active { color:#FF0000; text-decoration:underline;}
.image1 { background: url(/pkimages/mainimages/layout.gif) repeat-x top;}
.image2 { padding-top:2px; padding-left:20px;}
.image2 li { float:left; color:#FFF; margin:0 20px}
.image2 a:link,.image2 a:visited { color:#FFF; font-weight:bold; margin:0px; text-decoration:none;}
.image2 a:active,.image2 a:hover { color:#FFFF00;} 
.image3 { background:url(/pkimages/mainimages/layout6.gif);}
.image3 a:link,.image3 a:visited { margin:0px; text-decoration:none;}
.image6 { background:url(/pkimages/mainimages/userinfoimag1.gif) repeat-y;}

.pad1 { padding-left:20px}
</style>
</head>

<body>
<%
DataBaseConn dbcInfo = (DataBaseConn) request.getAttribute("infodbc");
dbcInfo.rsNext();
%>
<table align=center width=910 height=200 border=1 cellpadding=0 cellspacing=0 bordercolor=red class=image6>
 <tr>
  <td>
   <table align=center width=910 height=170 border=1 cellpadding=0 cellspacing=0 bordercolor=pink>
    <tr><td colspan=6 height=30 align=center style="font-size:13px;font-weight:bold;color:red">用户详细信息</td></tr>
    <tr height=20 class=pad1>
     <td  align=left>用户名:<%=dbcInfo.rsGetString("username")%><br></td>
     <td  align=left>身份证号:<%=dbcInfo.rsGetString("idcardnumber")%><br></td>
     <td  align=left>手机:<%=dbcInfo.rsGetString("mobilephone")%><br></td>
    </tr>
    <tr height=20 class=pad1>
     <td  align=left>性别:<%=dbcInfo.rsGetString("sex")%><br></td>
     <td  align=left>E-mail:<%=dbcInfo.rsGetString("email")%><br></td>
     <td  align=left>联系电话:<%=dbcInfo.rsGetString("contactphone")%><br></td>
    </tr>
    <tr height=20 class=pad1>
     <td  align=left>真实姓名:<%=dbcInfo.rsGetString("realname")%><br></td>
     <td  align=left>联系地址:<%=dbcInfo.rsGetString("address")%><br></td>
     <td  align=left>MSN:<%=dbcInfo.rsGetString("msnnum")%><br></td>
    </tr>
    <tr height=20 class=pad1>
     <td  align=left>QQ:<%=dbcInfo.rsGetString("qqnum")%><br></td>
     <td  align=left>账户金额:<%=dbcInfo.rsGetString("totalmoney")%>元<br></td>
     <td  align=left>提款状态:<%=dbcInfo.rsGetString("drawstate")%><br></td>
    </tr>
    <tr height=20 class=pad1>
     <td  align=left>冻结状态:<%=dbcInfo.rsGetString("freeze")%><br></td>
     <td  align=left>
     </td>
     <td  align=left><br></td>
    </tr>
   </table>
  </td>
 </tr>
</table>

<% dbcInfo.connClose();
%>
</body>
</html>