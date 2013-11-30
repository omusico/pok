<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="dbconnpac.DataBaseConn" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>华彩在线-彩票投注网</title>
<link href="/pokercss/main.css" rel="stylesheet" type="text/css" />
<script Language="JavaScript">
function refre(){
  parent.document.frames['fraspuseinfo'].location.href="servlet/servuserinfo?mark=spuseinf";
}
</script>
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
    dbcInfo.connCloseUpdate();
  %>
<table align=center width=910 height=300 border=1 cellpadding=0 cellspacing=0 bordercolor=red class=image6>
 
 <tr>
  <td>
   <table align=center width=910 height=30 border=1 cellpadding=0 cellspacing=0 bordercolor=pink>
    <tr >
     <td  colspan=4 align=center style="font-size:13px;font-weight:bold;color:red">用户操作区</td>
    </tr>
    <tr>
     <td  align=left>
       <form name="formdrawreq" action="/servlet/specuseroper" style="margin-left:10px;margin-bottom:3px;margin-top:3px;">
		<input type="hidden" name="hidusername" value="<%=(String)session.getAttribute("usernamestr")%>">
        <input type="submit" name="butdrawreq" value="请求提款">
 		<font color=red>
 		  <% String strDrawReqRem=(String)request.getAttribute("drawreqrem"); 
 		     if(strDrawReqRem!=null){out.println(strDrawReqRem);}%>
 		</font>
	   </form>
     </td>
     <td  align=left>
       <a href="/servlet/specuserinfup" target="_blank">用户信息修改</a>
     </td>
    </tr>
   </table>
  </td>
 </tr>
 
 <tr><td align=center style="font-size:13px;font-weight:bold;color:red">说 明</td></tr>
 <tr>
   <td style="padding-left:10px">
   <font color=red>标识期号</font>：是指每次正在销售的期号。显示在倒计时的左侧。<br>
   <font color=red>投注期号</font>：是指你在标识期号的基础上所追加的期号。注意：投注期号相同，不一定标识期号相同。当你看到投注期号时，一定要看一下前面的标识期号。例如：投注期号为0006，标识期号为0003，表示当时正在销售0003，而你对0003期所做的投注方案又追加了0006期。如果在追加期号上中奖，其后的所有追加期号自动取消，不参与以后的中奖，并且所投的金额将自动反还。<br>
   <font color=red>次数序号</font>：是指你在标识期号时，第几次进行投注操作。注意：点击一次“确认投注”按钮，为一次。<br>
   <font color=red>多期跟号</font>：是指对标识期号是否有跟期。<br>
   <font color=red>投注类型</font>：是指不同的玩法类型。如：任选一，任选二。<br>
   <font color=red>投注模式</font>：是指类型里的不同模式。如：“任选一”中的“复式”，“单式”，“组选复式”中的“组4”，“组6”<br>
   <font color=red>投注号码</font>：是指所投的号码。在投注区里所显示的每一行投注号码用<font color=red>$</font>符号隔开。<br>
   <font color=red>注数</font>：是指相对应的投注号码的注数。<br>
   <font color=red>倍数</font>：是指投注期号中的投注方案的购买倍数。<br>
   </td>
 </tr>
</table>

</body>
</html>