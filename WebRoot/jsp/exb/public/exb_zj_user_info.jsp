<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true" 
    import="dbconnpac.DataBaseConn"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
 
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>�н�����</title>

<script src="/pokerfunc/timerunmanage.js" type="text/javascript"></script>
<%
  String haveLogin = (String)request.getAttribute("haveLogin");
  if(haveLogin == null || haveLogin.equals("0")){
%>
<script language="javascript">
<!--
	 alert("���ȵ�½!");
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
<tr><td>�ں�:<%=request.getAttribute("issueNum") %></td></tr>
    <tr>
      <td bgcolor="#FFFFFF">
	
		<display:table name="zjInfoList" cellspacing="0" cellpadding="0"  >
			<display:column property="userName"  title="�û���"/>
			<display:column property="playType" title="�淨����"/>
			<display:column property="playMode" title="�淨ģʽ"/>
			<display:column property="tzNum" title="Ͷע����"/>
			<display:column property="zjType" title="�жϹ���"/>
			<display:column property="zjZhushu" title="�н�ע��"/>
			<display:column property="tzTimes" title="����"/>
			<display:column property="totalZjZhushu" title="��ע��"/>
			<display:column property="zjMoney" title="����"/>
		</display:table>

     </td>
    </tr>
 </table>
 <%
}
%>
</body>
 </html>