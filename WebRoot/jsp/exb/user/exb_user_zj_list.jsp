<%@ page contentType="text/html; charset=gb2312"
     session="true" 
    import="dbconnpac.DataBaseConn"%>
<%@ page import="beanpac.RemindInfo"%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
 
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<html>
<head>
<title>管理平台-快乐十分 用户中奖信息显示</title>
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
<%
 	String userId = request.getParameter("userId");
    String haveLogin = (String)request.getAttribute("haveLogin");
    String totalZjZhushu = (String)request.getAttribute("totalZjZhushu");
    String totalZjMoney = (String)request.getAttribute("totalZjMoney");

	
	String userName = "";
	Object obj =  session.getAttribute("loginmes");
	if(obj != null){	
	  RemindInfo remindInfo = (RemindInfo)obj;		 
	  userName = remindInfo.getLoginUserName();
	}

//判断是否已经登陆
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
<link type="text/css" rel="stylesheet" href="/css/common.css" >

<SCRIPT LANGUAGE="JavaScript">
<!--
	function to_del(tzId,userId){
		if(confirm("删除后将无法恢复，确实要删除吗?")){

 		var url = "/exb/exbPublicInfo.do?method=delUserZjInfo&tzId="+tzId+"&userId=<%=userId%>";
 		window.location.href = url; 
		}
	}
//-->
</SCRIPT>


</head>

<body>
 
<table  align="center">

<tr> 

<td>
		<display:table name="userZjList" cellspacing="0" cellpadding="0"   pagesize="10" class="table"  requestURI="/exb/exbPublicInfo.do?method=viewUserZjInfo"  decorator="com.onmyway.common.displaytag.DisplayTagWrapper">
			<display:column property="issueNum"  title="中奖期号"/>
			<display:column property="userName"  title="用户名"/> 
			<display:column property="playType" title="玩法类型"/>
			<display:column property="playMode" title="玩法模式"/>
			<display:column property="tzNum" title="投注号码"/>
			<display:column property="zjType" title="判断规则"/>
			<display:column property="zjZhushu" title="中奖注数"/>
			<display:column property="tzTimes" title="倍数"/>
			<display:column property="totalZjZhushu" title="总注数"/>
			<display:column property="zjMoney" title="奖金"/>
       <%
			if(userName.equals("admin")){
		%>
			<display:column property="exbUserZhongjiangHref" title="操作" />
 
		<%
			}
		%>
		</display:table>

</td>
</tr>
<tr><td>中奖总注数：<%=totalZjZhushu%>注;&nbsp;&nbsp;中奖总金额<%=totalZjMoney%>元</td></tr>
</table>
<!-- 以上为记录区 -->
 <%
}
%>
</body>
 </html>