<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/tagtld/custaglib" prefix="ctl" %>
<%@ page import="dbconnpac.DataBaseConn, beanpac.PageShow" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>��������-��ƱͶע��</title>
<link href="/pokercss/main.css" rel="stylesheet" type="text/css" />

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
<table align=center width=910 height=300 border=1 cellpadding=0 cellspacing=0 bordercolor=red class=image6>

 <tr>
    <%
    DataBaseConn dbcInfo = (DataBaseConn) request.getAttribute("infodbc");
    PageShow psInfo = (PageShow) request.getAttribute("infopage");
  %>
        <td valign=top>
	        <table>
			 <tr>
			  <td>
			   <table align=center width=910 height=90 border=1 cellpadding=0 cellspacing=0 bordercolor=pink>
			    <tr><td colspan=11 height=30 align=center>�����˿� �н���¼</td></tr>
			    <tr height=20 align="center">
			     <td width="6%" >�н��ں�</td>
			     <td width="6%" >�淨����</td>
	             <td width="6%" >�淨ģʽ</td>
	             <td width="6%" >��Ϸ�ʽ</td>
	             <td width="26%" >Ͷע����</td>
	             <td width="6%" >�жϹ���</td>
	             <td width="6%" >�н�ע��</td>
	             <td width="6%" >����</td>
	             <td width="6%" >��ע��</td>
	             <td width="6%" >����</td>
	             <td></td>
			    </tr>
			    <%while(dbcInfo.rsNext())
			    {%>
			    <tr height=20 align=center>
			     <td ><%=dbcInfo.rsGetString("issuenum")%><BR></td>
			     <td ><%=dbcInfo.rsGetString("playtype")%><BR></td>
			     <td ><%=dbcInfo.rsGetString("playmode")%><BR></td>
			     <td ><%=dbcInfo.rsGetString("onewagbef")%><BR></td>
			     <td ><%=dbcInfo.rsGetString("onewager")%><BR></td>
			     <td ><%=dbcInfo.rsGetString("rule")%><BR></td>
			     <td ><%=dbcInfo.rsGetString("wagernum")%><BR></td>
			     <td ><%=dbcInfo.rsGetString("times")%><BR></td>
			     <td >
			     <% int intEachWinWag=Integer.parseInt(dbcInfo.rsGetString("wagernum"))*Integer.parseInt(dbcInfo.rsGetString("times"));
			     out.println(intEachWinWag);%><BR></td>
			     <td ><%=dbcInfo.rsGetString("prize")%>Ԫ<BR></td>
			     <%
			       String strPageType=(String)session.getAttribute("pagetype");
			       if(strPageType.equals("man")){
			    	   out.print("<td width='6%'><a href=/servlet/useinfprdel?play=pok&prid="+dbcInfo.rsGetString("id")+">ɾ��</a></td>");
			       }%>
			    </tr>
			   <%}%>
			   </table>
			  </td>
			 </tr>
			 <tr align=center>
			     <td>
			        <table width=300 border=0 cellpadding=0 cellspacing=0 bordercolor=yellow>
			          <tr>
			            <td>�н���ע��:</td>
			            <td style="color:red"><%=request.getAttribute("wag")%></td>
			            <td width=30%><br><td>
			            <td>�н��ܽ��:</td>
			            <td style="color:red"><%=request.getAttribute("mon")%>Ԫ</td>
			          </tr>
			        </table>
			     </td>
			 </tr>
			 <tr>
			  <td><ctl:page curpage="<%=psInfo.getCurrentPage()%>" maxpage="<%=psInfo.getMaxPage()%>" prevpage="<%=psInfo.getPrevPage()%>" nextpage="<%=psInfo.getNextPage()%>" servhref="/servlet/servuserinfo?mark=pokpri&"></ctl:page>
			  </td>
			 </tr>
	       </table>
        </td>
 </tr>
</table>

<% 
   dbcInfo.connClose();
%>
</body>
</html>