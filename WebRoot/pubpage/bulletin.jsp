<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"
    import="java.io.*"%>
<%@page import="beanpac.Bulletin"%>
   
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>华彩在线-彩票投注网</title>
<link href="/pokercss/main.css" rel="stylesheet" type="text/css" />

<script src="/pokerfunc/xmlmeth/xmlglobal.js"></script>
<script src="/pokerfunc/xmlmeth/xmllogin.js"></script>

<style type="text/css">
.imguseque { background: url(/pkimages/mainimages/imguseque.gif) repeat-x top;margin:0px;}
</style>
</head>
<body>
<table width="220" height=100>
<tr>
<td bgcolor="#DBE1F4">
  <table border=0  width=100% height=100%>
     <tr>
       <td align=center height=10% style="border-bottom:#6A80F5 solid 2px; color:red; font-weight:bold; font-size:13px">
          通 知 栏
       </td>
     </tr>
     <tr>
       <td align=center height=90%>
         <marquee align=center width=197 height=100 direction="up" scrollamount="1" behavīor="scroll">
         <%
           Bulletin objBul=new Bulletin();
           out.println(objBul.getMes());
         %>
         </marquee>
       <td>
     </tr>
  </table>
</td>
</tr>
</table>
</body>
</html>