<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="dbconnpac.DataBaseConn,pubmethpac.GlobalMeth" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>华彩在线-彩票投注网</title>
<link href="/pokercss/main.css" rel="stylesheet" type="text/css" />
<script Language="JavaScript">
</script>
<style type="text/css">
.image1 a:link,.image1 a:visited { color:#00F; text-decoration:none}
.image1 a:hover,.image1 a:active { color:#FF0000; text-decoration:underline;}
.image1 { background: url(/pkimages/mainimages/layout.gif) repeat-x top;}
.image2 { padding-top:2px; padding-left:20px;}
.image2 li { float:left; color:#FFF;margin:0 20px}
.image2 a:link,.image2 a:visited { color:#FFF; font-weight:bold; margin:0px; text-decoration:none;}
.image2 a:active,.image2 a:hover { color:#FFFF00;} 
.image3 { background:url(/pkimages/mainimages/layout6.gif);}
.image3 a:link,.image3 a:visited { margin:0px; text-decoration:none;}
.image4 { background:url(/pkimages/mainimages/regimage4.gif);}
.image6 { background:url(/pkimages/mainimages/regimage6.gif) repeat-y;}
</style>
</head>

<body>

<table width="920" border=0 cellpadding="0" cellspacing="0">
   <tr>
   <td height=190 class="image1">
   </td>
  </tr>
</table>

<%
DataBaseConn specUserDC = (DataBaseConn) session.getAttribute("specuserdc");
specUserDC.rsNext();
%>
<table align=center width=910 border=0 class="image6">
<tr>
<td> 
    <form name="formupdate" action="/servlet/specuserinfup" method="post">
      <input type="hidden" name="userinfo" value="update">
      <table align=center width=700 border="1" bordercolor="#FFDBC4" cellpadding="3" cellspacing="0">

        <tr align="center">
          <td height="30" colspan="6" style="color:#FF0000;font-weight:bold "><font size=3 bold>会 员 信 息 修 改</font></td>
        </tr>
        <tr>
          <td width="20%" height="24" align="left" >
             用户名：<%=specUserDC.rsGetString("username")%>
          </td>
        </tr>
        <tr>
          <td width="20%" height="24" align="left" >E-mail：</td>
          <td align="left" >
            <input type="text" name="email" size="16" value="<%=specUserDC.rsGetString("email")%>" >
          </td>
        </tr>
        <tr>
          <td width="20%" height="24" align="left" >联系电话：</td>
          <td align="left" >
          <input type="text" name="contactphone" size="16" value="<%=specUserDC.rsGetString("contactphone")%>" >
          </td>
        </tr>
        <tr>
          <td width="20%" height="24" align="left" >手机号码：</td>
          <td align="left" >
          <input type="text" name="mobilephone" size="16" value="<%=specUserDC.rsGetString("mobilephone")%>" >
          </td>
        </tr>
        <tr>
          <td width="20%" height="24" align="left" >联系地址：</td>
          <td align="left" >
          <input type="text" name="address" size="16" value="<%=specUserDC.rsGetString("address")%>" >
          </td>
        </tr>
        <tr>
          <td width="20%" height="24" align="left" >MSN：</td>
          <td align="left" >
          <input type="text" name="msnnum" size="16" value="<%=specUserDC.rsGetString("msnnum")%>" >
          </td>
        </tr>
        <tr>
          <td width="20%" height="24" align="left" >QQ：</td>
          <td align="left" >
          <input type="text" name="qqnum" size="16" value="<%=specUserDC.rsGetString("qqnum")%>" >
          </td>
        </tr>
        <tr>
          <td align=center colspan=2>
            <table border=0 width=100%>
	             <tr>
		              <td width=60% align="right">
		                <input type="submit" name="submit" value="提交修改">
		              </td>
		              <td align="left">
		               <font color=red>
		                 <% String strMes=(String)request.getAttribute("infoupmes"); 
 		                    if(strMes!=null){out.println(strMes);}%>
		               </font>
		              </td>
	              </tr>
              </table>
           </td>
        </tr>
      </table>
    </form>
    </td>
    </tr>
    <% specUserDC.connClose();%>
    
    
    <tr>
     <td align=center colspan="3" style="font:bold; font-size=13px; color:red"><span>个人登录密码修改</span>
     </td>
    </tr>
<tr>
<tr>
 <td>
  <form name="formpassup" action="/servlet/specuserinfup" method="post" style="margin:2px">
  <input type="hidden" name="userpass" value="up">
    <table border=2 bordercolor=yellow>
     <tr>
      <td>旧密码：<input type="password" name="passold" size="16" value=""></td>
      <td>新密码：<input type="password" name="passnew" size="16" value=""></td>
      <td>确认密码(重新输入新密码)：<input type="password" name="passconf" size="16" value=""></td>
	  <td><input type="submit" name="update" value="更新"></td>
	 </tr>
	 <tr>
	  <td colspan="4" align=center><font color=red>
	  <%GlobalMeth objGM=new GlobalMeth(); %>
	  <%=objGM.removeNull((String)request.getAttribute("warn"))%></font></td>
	 </tr>
	</table>
  </form>
 </td>
</tr>
    <tr>
     <td class="image3"><br>
     </td>
    </tr>
    </table>
</body>
</html>