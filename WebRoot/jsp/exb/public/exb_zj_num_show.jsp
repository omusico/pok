<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
 <%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<%@ page import="com.onmyway.common.displaytag.DisplayTagWrapper"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
  <html>
<head>
<title>各期开奖号码查看</title>
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
<link type="text/css" rel="stylesheet" href="/css/displaytag.css">
<SCRIPT LANGUAGE="JavaScript">
<!--
	function to_del(issueNum){
		//alert("del  :"+issueNum);
		window.location.href = "/exb/exbManage.do?method=deleteZhongjianghaoma&issueNum="+issueNum;
	}
//-->
</SCRIPT>
</head>

<body>
<table  align="center">
<tr>
<td align=center>快乐十分中奖信息
</td>
</tr>
<tr>
<td>
<display:table name="zjhmList" cellspacing="0" cellpadding="0"  pagesize="10"
 requestURI="/exb/exbManage.do?method=showZhongjianghaoma" decorator="com.onmyway.common.displaytag.DisplayTagWrapper">
<display:setProperty name="sort.amount" value="zjhmList"/>
<display:column property="issueNum"  title="期号"/>
<display:column property="position1" title="第1位"/>
<display:column property="position2" title="第2位"/>
<display:column property="position3" title="第3位"/>
<display:column property="position4" title="第4位"/>
<display:column property="position5" title="第5位"/>
<display:column property="position6" title="第6位"/>
<display:column property="position7" title="第7位"/>
<display:column property="position8" title="第8位"/>
 <display:column property="exbZhongjianghaomaHref" title="操作" />

</display:table>

</td>
</tr>
</table>
<!-- 以上为记录区 -->

 </body>
</html>