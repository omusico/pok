<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/tagtld/custaglib" prefix="ctl" %>
<%@ page import="dbconnpac.DataBaseConn, beanpac.PageShow" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>华彩在线-彩票投注网</title>
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
					    <tr><td colspan=11 height=30 align=center>快乐扑克 投注记录</td></tr>
					    <tr height=20 align=center>
					     <td width=60>标识期号</td>
					     <td width=60>投注期号</td>
					     <td width=30>次数序号</td>
					     <td width=30>多期跟号</td>
					     <td width=70>投注类型<br></td>
					     <td width=70>投注模式</td>
					     <td width=90>组合方式(仅限于几种类型)</td>
					     <td width=330>投注号码</td>
					     <td width=60>注数</td>
					     <td width=40>倍数</td>
					     <td></td>
					    </tr>
					    <%
					    while(dbcInfo.rsNext()){
					    	//int intMarkIss=Integer.parseInt(dbcInfo.rsGetString("markiss").trim());
					    	//int intIssueNum=Integer.parseInt(dbcInfo.rsGetString("issuenum").trim());
					    	String intMarkIss= dbcInfo.rsGetString("markiss").trim();
					    	String intIssueNum= dbcInfo.rsGetString("issuenum").trim();


					    	String strHasFolPok=dbcInfo.rsGetString("hasfol");
					        String hasFolShowPok="";
					        if(strHasFolPok.equals("n")){
					    	    hasFolShowPok="无";
					        }else{
					    	    hasFolShowPok="有";
					        }
					     %>
					    <tr height=20>
					     <td width=60 align=center><%=intMarkIss%><BR></td>
					     <td width=60 align=center><%=intIssueNum%><BR></td>
					     <td width=30 align=center><%=dbcInfo.rsGetString("markissseq")%><BR></td>
					     <td width=30 align=center><%=hasFolShowPok%><BR></td>
					     <td width=70 align=center><%=dbcInfo.rsGetString("playtype")%><BR></td>
					     <td width=70 align=center><%=dbcInfo.rsGetString("playmode")%><BR></td>
					     <td width=90 align=center><%=dbcInfo.rsGetString("wagtotbef")%><BR></td>
					     <td style= "WIDTH: 430; WORD-WRAP: break-word; align:left" >
					     <%=dbcInfo.rsGetString("wagertotal")%><BR></td>
					     <td style= "WIDTH: 60" align=center><%=dbcInfo.rsGetString("wagernum")%><BR></td>
					     <td style= "WIDTH: 40" align=center><%=dbcInfo.rsGetString("times")%><BR></td>
					     <%
					       String strPageType=(String)session.getAttribute("pagetype");
					       if(intMarkIss==intIssueNum && strPageType.equals("man")){
					    	   out.print("<td width=30><a href=/servlet/useinfwagrem?play=pok&wagerid="+dbcInfo.rsGetString("id")+">删除</a></td>");
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
			            <td>所投总注数:</td>
			            <td style="color:red"><%=request.getAttribute("wag")%></td>
			            <td width=30%><br><td>
			            <td>所投总金额:</td>
			            <td style="color:red"><%=request.getAttribute("mon")%>元</td>
			          </tr>
			        </table>
			     </td>
			 </tr>
			 <tr>
			  <td><ctl:page curpage="<%=psInfo.getCurrentPage()%>" maxpage="<%=psInfo.getMaxPage()%>" prevpage="<%=psInfo.getPrevPage()%>" nextpage="<%=psInfo.getNextPage()%>" servhref="servlet/servuserinfo?mark=pokwag&"></ctl:page>
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