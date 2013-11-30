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
		window.location.href = "/sxw/sxwManage.do?method=deleteZhongjianghaoma&issueNum="+issueNum;
	}
//-->
</SCRIPT>
</head>

<body>
<table  align="center">
<tr>
<td align=center>11选5中奖信息
</td>
</tr>
<tr>
<td>
<display:table name="zjhmList" cellspacing="0" cellpadding="0"  pagesize="10"
 requestURI="/sxw/sxwManage.do?method=showZhongjianghaoma" decorator="com.onmyway.common.displaytag.DisplayTagWrapper">
<display:setProperty name="sort.amount" value="zjhmList"/>
<display:column property="issueNum"  title="期号"/>
<display:column property="wan" title="万位"/>
<display:column property="qian" title="千位"/>
<display:column property="bai" title="百位"/>
<display:column property="shi" title="十位"/>
<display:column property="ge" title="个位"/>
 <display:column property="sxwZhongjianghaomaHref" title="操作" />

</display:table>

</td>
</tr>
</table>
<!-- 以上为记录区 -->

 </body>
</html>