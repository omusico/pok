<%@ page contentType="text/html; charset=gb2312"
     session="true" 
    import="dbconnpac.DataBaseConn"%>
<%@ page import="beanpac.RemindInfo"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
 
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<html>
<head>
<title>����ƽ̨-11ѡ5 �û�Ͷע��Ϣ��ʾ</title>
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

	//�ж��Ƿ��Ѿ���½
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
<SCRIPT LANGUAGE="JavaScript">
<!--
function to_del(tzId,userId){
	if(confirm("ɾ�����޷��ָ���ȷʵҪɾ����?")){
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

				<display:column property="parentIssueNum"  title="��ʶ�ں�"/>
			<display:column property="issueNum"  title="Ͷע�ں�"/>
			<display:column property="tzSeq"     title="�������"/>
			<display:column property="haveZtFlag"   title="���ڸ���"/> 
			<display:column property="playType" title="Ͷע����"/>
			<display:column property="playMode" title="Ͷעģʽ"/>
			<display:column  title="Ͷע��ʽ"/>
			<display:column property="touzhuNum" title="Ͷע����"/>
			<display:column property="touzhuCount" title="ע��"/>
			<display:column property="touzhuTimes" title="����"/>
		<%
			if(userName.equals("admin")){
		%>
			<display:column property="sxwUserTouzhuHref" title="����" />
		<%
			}
		%>
		</display:table>

</td>
</tr>
<tr><td>Ͷע��ע����<%=totalTzZhushu%>ע;&nbsp;&nbsp;Ͷע�ܽ�<%=totalTzMoney%>Ԫ</td></tr>

</table>
<!-- ����Ϊ��¼�� -->
 <%
}
%>
</body>
 </html>