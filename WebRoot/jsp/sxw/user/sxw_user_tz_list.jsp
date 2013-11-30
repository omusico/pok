<%@ page contentType="text/html; charset=gb2312"
     session="true" 
    import="dbconnpac.DataBaseConn"%>
<%@ page import="beanpac.RemindInfo"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
 
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<html>
<head>
<title>管理平台-11选5 用户投注信息显示</title>
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
    String totalTzZhushu = (String)request.getAttribute("totalTzZhushu");
    String totalTzMoney = (String)request.getAttribute("totalTzMoney");

	 String userName = "";
	 Object obj =  session.getAttribute("loginmes");
	 if(obj != null){	
		  RemindInfo remindInfo = (RemindInfo)obj;		 
		  userName = remindInfo.getLoginUserName();
	 }
	// System.out.println("userName="+userName);

	//判断是否已经登陆
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
<SCRIPT LANGUAGE="JavaScript">
<!--
function to_del(tzId,userId){
	if(confirm("删除后将无法恢复，确实要删除吗?")){
 		var url = "/sxw/sxwPublicInfo.do?method=delUserTzInfo&tzId="+tzId+"&userId=<%=userId%>";
 		//window.location.href = url; 
				parent.document.frames['fraplat'].location.href=url;
	}

}
//-->
</SCRIPT>

<link type="text/css" rel="stylesheet" href="/css/displaytag.css">
<link type="text/css" rel="stylesheet" href="/css/common.css" >

</head>

<body>
 
<table  align="center">

<tr>
<td>
		<display:table name="tzInfoList" cellspacing="0" cellpadding="0"   pagesize="10" class="table"  requestURI="/sxw/sxwPublicInfo.do?method=viewUserTzInfo"  decorator="com.onmyway.common.displaytag.DisplayTagWrapper">

				<display:column property="parentIssueNum"  title="标识期号"/>
			<display:column property="issueNum"  title="投注期号"/>
			<display:column property="tzSeq"     title="次数序号"/>
			<display:column property="haveZtFlag"   title="多期跟号"/> 
			<display:column property="playType" title="投注类型"/>
			<display:column property="playMode" title="投注模式"/>
			<display:column  title="投注方式"/>
			<display:column property="touzhuNum" title="投注号码"/>
			<display:column property="touzhuCount" title="注数"/>
			<display:column property="touzhuTimes" title="倍数"/>
		<%
			if(userName.equals("admin")){
		%>
			<display:column property="sxwUserTouzhuHref" title="操作" />
		<%
			}
		%>
		</display:table>

</td>
</tr>
<tr><td>投注总注数：<%=totalTzZhushu%>注;&nbsp;&nbsp;投注总金额：<%=totalTzMoney%>元</td></tr>

</table>
<!-- 以上为记录区 -->
 <%
}
%>
</body>
 </html>