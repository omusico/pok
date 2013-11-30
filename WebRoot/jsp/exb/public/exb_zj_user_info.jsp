<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true" 
    import="dbconnpac.DataBaseConn"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
 
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>中奖号码</title>

<script src="/pokerfunc/timerunmanage.js" type="text/javascript"></script>
<%
  String haveLogin = (String)request.getAttribute("haveLogin");
  if(haveLogin == null || haveLogin.equals("0")){
%>
<script language="javascript">
<!--
	 alert("请先登陆!");
	 window.close();
//-->
</script>
<%
  }else{
%>
<link type="text/css" rel="stylesheet" href="/css/displaytag.css">
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
.image6 { background: url(/pkimages/mainimages/indimage6.gif) repeat-x top;margin:0px;}
</style>

</head>

<body>
<%//DataBaseConn uwDBC=(DataBaseConn)session.getAttribute("userwininfo");%>
<table width="910" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="white">
<tr><td>期号:<%=request.getAttribute("issueNum") %></td></tr>
    <tr>
      <td bgcolor="#FFFFFF">
	
		<display:table name="zjInfoList" cellspacing="0" cellpadding="0"  >
			<display:column property="userName"  title="用户名"/>
			<display:column property="playType" title="玩法类型"/>
			<display:column property="playMode" title="玩法模式"/>
			<display:column property="tzNum" title="投注号码"/>
			<display:column property="zjType" title="判断规则"/>
			<display:column property="zjZhushu" title="中奖注数"/>
			<display:column property="tzTimes" title="倍数"/>
			<display:column property="totalZjZhushu" title="总注数"/>
			<display:column property="zjMoney" title="奖金"/>
		</display:table>

     </td>
    </tr>
 </table>
 <%
}
%>
</body>
 </html>