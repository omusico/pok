<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true" import="beanpac.ServerTime,pubmethpac.GlobalMeth"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%GlobalMeth objGM=new GlobalMeth(); %>
<html>
<head>
<title>管理平台</title>
<script Language="JavaScript">
function linkPlat(loc){
  document.frames['fraplat'].location.href=loc;
}
</script>
<style type="text/css">
body,td,th { font-size:13px;}
ul { list-style:none;}
.image1 a:link,.image1 a:visited { color:#000; text-decoration:none}
.image1 a:hover,.image1 a:active { color:#FF0000; text-decoration:underline;}
.image1 { background: url(/pkimages/mainimages/layout.gif) repeat-x top;}
.image2 { padding-top:2px; padding-left:20px;}
.image2 td { color:#FFF;margin:20px;cursor:hand}
.image2 .image2 a:visited { color:#FFF; font-weight:bold; margin:0 2px 0 5px; text-decoration:none;}
.image2 a:active,.image2 a:hover { color:#FFFF00;} 
.image6 { background: url(/pkimages/mainimages/indimage6.gif) repeat-x top;margin:0px;}
</style>
</head>

<body>
<table width="900"  border="1" bordercolor=orange align="center" cellpadding="0" cellspacing="0">
<tr><td align=center colspan="6" style="font:bold; font-size=13px; color:red"><span>通 知 栏 修 改</span></td></tr>
<tr>
  <td>
	<table><tr>
		<td width=30% align="left" valign=top bgcolor="">
		   <iframe name="frabulent" id="frabulent" frameborder="0" width="210" height="100"  scrolling="no" src="/pubpage/bulletin.jsp"></iframe>
		</td>
		<td width=40% align=left style="padding-bottom:0px;padding-left:0px">
		  <form name="formlogin" action="/servlet/bulplus" method="post" style="margin:0px">
		    <table border=2 bordercolor=yellow>
		     <tr>
		      <td><textarea name="mes" cols="60" rows="4"></textarea></td>
			  <td><input type="submit" name="plus" value="添加"></td>
			 </tr>
			</table>
		  </form>  
		</td>
		<td width=30%><br>
		</td>
	</tr></table>
  </td>
</tr>

<tr><td><br></td></tr>

<tr><td align=center colspan="3" style="font:bold; font-size=13px; color:red"><span>管理员密码修改</span></td></tr>
<tr>
<tr>
 <td>
  <form name="formpassup" action="/servlet/updatpass" method="post" style="margin:2px">
    <table border=2 bordercolor=yellow>
     <tr>
      <td>旧密码：<input type="password" name="passold" size="16" value=""></td>
      <td>新密码：<input type="password" name="passnew" size="16" value=""></td>
      <td>确认密码(重新输入新密码)：<input type="password" name="passconf" size="16" value=""></td>
	  <td><input type="submit" name="update" value="更新"></td>
	 </tr>
	 <tr>
	  <td colspan="4" align=center><font color=red><%=objGM.removeNull((String)request.getAttribute("warn"))%></font></td>
	 </tr>
	</table>
  </form>
 </td>
</tr>
</table>

</body>
</html>