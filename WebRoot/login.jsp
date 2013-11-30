<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>华彩在线-彩票投注网</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0"> 

<link href="/pokercss/main.css" rel="stylesheet" type="text/css" />

<script src="/pokerfunc/xmlmeth/xmlglobal.js"></script>
<script src="/pokerfunc/xmlmeth/xmllogin.js"></script>
<script language="javascript">
</script>
<style type="text/css">
.image3 { border:#e2903a solid 1px; background:url(/pkimages/mainimages/img4.gif); color:#1d3d86;}
.image3 a:link,.image3 a:visited { margin:0px; text-decoration:none;}
</style>
</head>

<body>
<form name="formlogin" action="/servlet/login" method="post">
  <table align=center width="920" border="0" cellpadding="0" cellspacing="0" class="image3">
    <tr>
     <td width=40><br></td>
     <td width=160>用户名<input name="username" type="text" class="input" id="username" size="13" value="ljy"></td>
     <td width=160>密码<input name="password" type="password" class="input" id="password" size="13" value="1"></td>
     <td width=130>验证码<input name="valcode" type="text" class="input" id="valcode" size="10"></td>
     <td width=60  onClick="refreImg()" style="cursor:hand" title="请点击刷新">
       <img id="valimg" name="valimg" border=0 src="/servlet/valimage">
     </td>
     <td width=30><br></td>
     <td><input type="button" name="sublogin" value="登录" onClick="userLogin()"></td>
     <td width="20"></td>
     <td width="40" align="left"><a href="servlet/entrymain?type=reg" target="_blank">注册</a></td>
     <td width="270" align="center">
        <span id="stlogmes">
     	<%
     		String strLoginMes=(String)request.getParameter("loginmes");
			//System.out.println(strLoginMes);
     		if(strLoginMes!=null){
     			strLoginMes=new String(strLoginMes.getBytes("ISO8859_1"),"gb2312").trim();
     			out.println(strLoginMes);
     		}
     	%>
     	</span>
     </td>
    </tr>
  </table>
</form>
</body>
</html>