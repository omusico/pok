<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>华彩在线-彩票投注网</title>
<link href="/pokercss/main.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.image1 { border:#e2903a solid 0px; background:url(/pkimages/mainimages/loginman.gif); color:#1d3d86;}
.image3 { border:#e2903a solid 0px; background:url(/pkimages/mainimages/img4.gif); color:#1d3d86;}
.image3 a:link,.image3 a:visited { margin:0px; text-decoration:none;}
</style>
</head>
<body background="/pkimages/mainimages/bg.gif">
<table width=920 height=720 border=0>
<tr>
<td align=center>
	<table width=600 height=300 border="0" cellpadding="0" cellspacing="0" class="image1">
	<tr>
	<td valign=bottom>
		<form name="formlogin" action="/man" method="post">
		  <table align=center width=300 height=130 border="0" cellpadding="0" cellspacing="0">
		    <tr><td><br></td></tr>
		    <tr>
		     <td width=60></td>
		     <td width=60>用户名</td>
		     <td><input name="username" type="text" class="input" id="username" size="15" value="admin"></td>
		    </tr>
		    <tr>
		     <td></td>
		     <td width=60>密码</td>
		     <td><input name="password" type="password" class="input" id="password" size="15" value="1"></td>
		    </tr>
		    <tr>
		     <td></td>
		     <td><input type="submit" name="sublogin" value="登录"></td>
		     <td style="color:red">
		       <%=(String)session.getAttribute("mesislogin")
               %></td>
		    </tr>
		  </table>
		</form>
	</td>
	</tr>
	</table>
</td>
</tr>
<tr><td><br></td></tr>
</table>
</body>
</html>