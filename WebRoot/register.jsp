<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>华彩在线-彩票投注网</title>
<link href="/pokercss/main.css" rel="stylesheet" type="text/css" />

<script src="/pktrade/pk/dg/public.js" type="text/javascript"></script>
<script src="/pktrade/pk/dg/onload.js" type="text/javascript"></script>

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

<table border=0 cellpadding="0" cellspacing="0">
   <tr>
      <td>
         <iframe name="fratitle" id="fratitle" frameborder="0" width="920" height="220"  scrolling="no" src="/title.jsp"></iframe>
      </td> 
   </tr>
</table>


<table align=center width=910 border=0 class="image6">
<tr>
<td> 
    <form name="formregister" action="/servlet/servregister" method="post">
      <table align=center width=700 border="0" bordercolor="#FFDBC4" cellpadding="3" cellspacing="0">
        <jsp:useBean id="reginforemind" type="beanpac.RegRemindInfo" scope="session" />
        <tr>
         <td><br>
         </td>
        </tr>
        <tr align="center">
          <td height="30" colspan="2" style="color:#FF0000;font-weight:bold "><font size=3 bold>会 员 注 册</font></td>
        </tr>
        <tr align="left">
          <td height="30" colspan="2"  >说明：注册会员后可登陆代购平台进行代购操作！</td>
        </tr>
        <tr align="left">
          <td height="30" colspan="2"><font color="red"> 以下带星号(*)的资料为必填项目！</font></td>
        </tr>
        <tr>
          <td width="20%" height="24" align="left" >用户名：<font color="red">(*)</font></td>
          <td align="left" >
           <input type="text" name="username" size="16" value="">
           [<font color=red>注册后不能修改！</font>]
           <font color=red>
            <jsp:getProperty name="reginforemind" property="userName"/>
           </font>
          </td>
        </tr>
        <tr>
          <td width="20%" height="24" align="left" >投注密码：<font color="red">(*)</font></td>
          <td align="left" >
          <input type="password" name="password" size="16" value="">
          <font color=red>
           <jsp:getProperty name="reginforemind" property="passWord"/>
          </font>
          </td>
        </tr>
        <tr>
          <td width="20%" height="24" align="left" >确认密码：<font color="red">(*)</font></td>
          <td align="left" >
          <input type="password" name="confirm" size="16" value="">
          <font color=red>
           <jsp:getProperty name="reginforemind" property="confirmPass"/>
          </font>
          </td>
        </tr>
        <tr>
          <td width="20%" height="24" align="left" >验证码：<font color="red">(*)</font></td>
          <td align="left" >
          <input type="text" name="valcode" size="16" value="">
          <img border=0 src="/servlet/valimage">
          <font color=red>
           <jsp:getProperty name="reginforemind" property="valCode"/>
          </font>
          </td>
        </tr>
        <tr>
          <td width="20%" height="24" align="left" >性别：<font color="red">(*)</font></td>
          <td align="left" >
          <input type="radio" name="sex" size="16" value="男" checked>男
          <input type="radio" name="sex" value="女" size="16">女
          </td>
        </tr>
        <tr>
          <td width="20%" height="24" align="left" >真实姓名：<font color="red">(*)</font></td>
          <td align="left" >
            <input type="text" name="realname" size="16" value="">
            [必须是中文姓名。<font color=red>注册后不能修改！</font>]
            <font color=red>
             <jsp:getProperty name="reginforemind" property="realName"/><br>
            </font>
              <font color="blue">这是您提款的一个重要依据，提款的银行卡的户名必须是这里写的真实姓名，否则无法办理提现。</font>
          </td>
        </tr>
        <tr>
          <td width="20%" height="24" align="left" >身份证号码：<font color="red">(*)</font></td>
          <td align="left" >
            <input type="text" name="idcardnumber" size="16" value="">
            [提款的重要依据，必须是15位或者18位的号码。<font color=red>注册后不能修改！</font>]
            <font color=red>
             <jsp:getProperty name="reginforemind" property="idNum"/>
            </font>
          </td>
        </tr>
        <tr>
          <td width="20%" height="24" align="left" >E-mail：<font color="red">(*)</font></td>
          <td align="left" >
          <input type="text" name="email" size="16" value="">
          [请填写您的常用邮箱，用来接收网站通知]
          <font color=red>
           <jsp:getProperty name="reginforemind" property="email"/>
          </font>
          </td>
        </tr>
        <tr>
          <td width="20%" height="24" align="left" >联系电话：<font color="red">(*)</font></td>
          <td align="left" >
          <input type="text" name="contactphone" size="16" value="">
          [重要联系方式 格式：区号-电话号码]
          <font color=red>
           <jsp:getProperty name="reginforemind" property="conPhone"/>
          </font>
          </td>
        </tr>
        <tr>
          <td width="20%" height="24" align="left" >手机号码：<font color="red">(*)</font></td>
          <td align="left" >
          <input type="text" name="mobilephone" size="16" value="">
          [重要联系方式]
          <font color=red>
           <jsp:getProperty name="reginforemind" property="cellPhone"/>
          </font>
          </td>
        </tr>
        <tr>
          <td width="20%" height="24" align="left" >联系地址：</td>
          <td align="left" >
          <input type="text" name="address" size="16" value="">
          </td>
        </tr>
        <tr>
          <td width="20%" height="24" align="left" >MSN：</td>
          <td align="left" >
          <input type="text" name="msnnum" size="16" value="">
          </td>
        </tr>
        <tr>
          <td width="20%" height="24" align="left" >QQ：</td>
          <td align="left" >
          <input type="text" name="qqnum" size="16" value="">
          </td>
        </tr>
        <tr>
          <td align=center colspan=2>
            <table border=0 width=100%>
	             <tr>
		              <td width=60% align="right">
		                <input type="submit" name="submit" value="提交注册">
		              </td>
		              <td align="left">
		               <font color=red>
		                 <jsp:getProperty name="reginforemind" property="sucMes"/>
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
    <tr>
     <td class="image3"><br>
     </td>
    </tr>
    </table>
</body>
</html>