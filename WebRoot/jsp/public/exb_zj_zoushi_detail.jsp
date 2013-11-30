<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
<%@ page import="dbconnpac.DataBaseConn" %>
<%@ page import="java.util.List" %>
<%@ page import="com.onmyway.exb.manage.model.ExbZjNumConfig" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>华彩在线-彩票投注网--快乐十分--中奖走势</title>
<link href="/pokercss/main.css" rel="stylesheet" type="text/css" />
<link href="/css/ssc_zoushi.css" rel="stylesheet" type="text/css" />

<style type="text/css">
* { margin:0;}
body,td,th { font-size: 12px;}
td { font-family:Verdana, Arial, Helvetica, sans-serif;color: #999999;}
.line_top_1 { border-top:#000000 solid 1px;}
.line_right_1 {
	border-right:#000000 solid 1px;
}
.line_bottom_1 { border-bottom:#000000 solid 1px;}
.line_left_1 { border-left:#000000 solid 2px;}
.ball_1 {
	background-image: url(/pkimages/pokerimage/ball_1.gif);
	background-repeat: no-repeat;
	background-position: center center;
	color:#FFFFFF;
}
.ball_2 {
	background-image: url(/pkimages/pokerimage/ball_2.gif);
	background-repeat: no-repeat;
	background-position: center center;
	color:#FFFFFF;
}
.font_1 { color:#DE040A;}
.font_2 { color:#015C01;}
.fenxi_a { color:#fff}
.fenxi_a a:link,.fenxi_a a:visited { color:#fff;}
.fenxi_a a:hover { color:#FFFF00; text-decoration:none; position:relative; top:1px; left:1px;}
.image1 a:link,.image1 a:visited { color:#00F; text-decoration:none}
.image1 a:hover,.image1 a:active { color:#FF0000; text-decoration:underline;}
.image1 { background: url(/pkimages/mainimages/layout.gif) repeat-x top;}
.image2 { padding-top:2px; padding-left:20px;}
.image2 li { float:left; color:#FFF;margin:0 20px}
.image2 a:link,.image2 a:visited { color:#FFF; font-weight:bold; margin:0px; text-decoration:none;}
.image2 a:active,.image2 a:hover { color:#FFFF00;} 
</style>
</head>
<body>
<table width="1300" border="1" cellspacing="3" cellpadding="3" bordercolorlight=blue bordercolordark=yellow>
  <tr>
    <td bgcolor="#C4B7F4" align=center style="font-size:36px; color:red">华彩在线中奖号码分布图</td>
  </tr>
</table>

<% String queryDaynum = (String)request.getAttribute("queryDaynum");%>
<table width="1300" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#000000">
   <tr>
      <td width=10% align=center bgcolor="#f5f5f5">&nbsp;</a>
      </td>
      <td height="25" align="left" bgcolor="#f5f5f5">
	      <form name="queryForm" action="/exb/exbPublicInfo.do?method=showExbZoushi" method="post" style="margin:0px; padding:0px">
	           <input type="hidden" name="play" value="exb">
	             请选择:&nbsp;&nbsp;
	  	        <select name="daynum" id="daynum" onChange="queryForm.submit()" >
				  <option value="">请选择..</option>
				  <option value="1" <%if(queryDaynum.equals("1")){%>selected<%}%>>最近一天
				  <option value="2" <%if(queryDaynum.equals("2")){%>selected<%}%>>最近二天
				  <option value="3" <%if(queryDaynum.equals("3")){%>selected<%}%>>最近三天
				  <option value="5" <%if(queryDaynum.equals("5")){%>selected<%}%>>最近五天
				  <option value="10" <%if(queryDaynum.equals("10")){%>selected<%}%>>最近十天	  	         
	            </select>
	       </form>
      </td>
    </tr>
</table>		
<table width="1700" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#000000">
      <tr>			
      <td bgcolor="#FFFFFF"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
        <tbody>
          <tr>
            <td width="88" rowspan="2" align="center" bgcolor="#e5e5e5" class="line_bottom_1">
			<strong><font color="#000000">期号</font></strong>			</td>
            <td width="50" rowspan="2" align="center" bgcolor="#e5e5e5" class="line_left_1 line_bottom_1"><strong><font color="#000000">开奖号码</font></strong></td>
            <td height="25" colspan="20" align="center" bgcolor="#e5e5e5" class="line_bottom_1 line_left_1"><strong><font color="#000000">第1个</font></strong></td>
            <td colspan="20" align="center" bgcolor="#e5e5e5" class="line_bottom_1 line_left_1"><strong><font color="#000000">第2个</font></strong></td>
            <td colspan="20" align="center" bgcolor="#e5e5e5" class="line_bottom_1 line_left_1"><strong><font color="#000000">第3个</font></strong></td>
            <td colspan="20" align="center" bgcolor="#e5e5e5" class="line_bottom_1 line_left_1"><strong><font color="#000000">第4个</font></strong></td>
            <td colspan="20" align="center" bgcolor="#e5e5e5" class="line_bottom_1 line_left_1"><strong><font color="#000000">第5个</font></strong></td>
            <td colspan="20" align="center" bgcolor="#e5e5e5" class="line_bottom_1 line_left_1"><strong><font color="#000000">第6个</font></strong></td>
            <td colspan="20" align="center" bgcolor="#e5e5e5" class="line_bottom_1 line_left_1"><strong><font color="#000000">第7个</font></strong></td>
            <td colspan="20" align="center" bgcolor="#e5e5e5" class="line_bottom_1 line_left_1"><strong><font color="#000000">第8个</font></strong></td>
            
          </tr>
          <tr>
            <td width="23" align="center" bgcolor="#f5f5f5" class="line_left_1"><strong><font color="#000000">&nbsp;&nbsp;1</font></strong></td>			 
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;2</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;3</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;4</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;5</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;6</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;7</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;8</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;9</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">10</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">11</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">12</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">13</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">14</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">15</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">16</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">17</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">18</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">19</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">20</font></strong></td>

            <td width="23" align="center" bgcolor="#f5f5f5" class="line_left_1"><strong><font color="#000000">&nbsp;&nbsp;1</font></strong></td>			 
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;2</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;3</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;4</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;5</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;6</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;7</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;8</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;9</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">10</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">11</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">12</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">13</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">14</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">15</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">16</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">17</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">18</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">19</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">20</font></strong></td>
			
            <td width="23" align="center" bgcolor="#f5f5f5" class="line_left_1"><strong><font color="#000000">&nbsp;&nbsp;1</font></strong></td>			 
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;2</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;3</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;4</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;5</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;6</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;7</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;8</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;9</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">10</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">11</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">12</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">13</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">14</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">15</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">16</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">17</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">18</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">19</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">20</font></strong></td>

            <td width="23" align="center" bgcolor="#f5f5f5" class="line_left_1"><strong><font color="#000000">&nbsp;&nbsp;1</font></strong></td>			 
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;2</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;3</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;4</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;5</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;6</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;7</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;8</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;9</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">10</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">11</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">12</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">13</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">14</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">15</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">16</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">17</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">18</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">19</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">20</font></strong></td>

            <td width="23" align="center" bgcolor="#f5f5f5" class="line_left_1"><strong><font color="#000000">&nbsp;&nbsp;1</font></strong></td>			 
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;2</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;3</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;4</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;5</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;6</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;7</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;8</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;9</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">10</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">11</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">12</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">13</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">14</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">15</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">16</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">17</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">18</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">19</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">20</font></strong></td>

            <td width="23" align="center" bgcolor="#f5f5f5" class="line_left_1"><strong><font color="#000000">&nbsp;&nbsp;1</font></strong></td>			 
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;2</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;3</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;4</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;5</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;6</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;7</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;8</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;9</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">10</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">11</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">12</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">13</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">14</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">15</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">16</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">17</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">18</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">19</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">20</font></strong></td>

            <td width="23" align="center" bgcolor="#f5f5f5" class="line_left_1"><strong><font color="#000000">&nbsp;&nbsp;1</font></strong></td>			 
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;2</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;3</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;4</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;5</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;6</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;7</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;8</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;9</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">10</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">11</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">12</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">13</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">14</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">15</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">16</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">17</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">18</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">19</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">20</font></strong></td>

            <td width="23" align="center" bgcolor="#f5f5f5" class="line_left_1"><strong><font color="#000000">&nbsp;&nbsp;1</font></strong></td>			 
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;2</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;3</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;4</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;5</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;6</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;7</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;8</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">&nbsp;&nbsp;9</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">10</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">11</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">12</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">13</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">14</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">15</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">16</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">17</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">18</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">19</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">20</font></strong></td>

          </tr>
          
    <%
          String[] arrNum={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};
          int[][] arrInt=new int[8][20];
          String[] aryNumText={"position1","position2","position3","position4","position5","position6","position7","position8"};
          
	      List<ExbZjNumConfig> list = (List<ExbZjNumConfig>)request.getAttribute("zjNumList");
		  
		  for(int m=0; m < list.size(); m++){
			ExbZjNumConfig info = list.get(m);
	%>	
           <tr class='td10'>
           <td align='center' bgcolor='#FFFFFF' style='color:#666666' height=24 ><%=info.getIssueNum()%>

		   <td align='center' bgcolor='#FFFFFF' style='color:red' height=24  class='line_left_1' >
            <%=info.getPosition1()%>,<%=info.getPosition2()%>,<%=info.getPosition3()%>,<%=info.getPosition4()%>,
			<%=info.getPosition5()%>,<%=info.getPosition6()%>,<%=info.getPosition7()%>,<%=info.getPosition8()%>
           </td>

			 <%
              for(int i=0;i<aryNumText.length;i++){
               for(int j=0;j<arrNum.length;j++){//循环10个数字
                 if(arrNum[j].equals(info.getValueByText(aryNumText[i]))){//判断哪个数字是当期中奖的号码
                     arrInt[i][j]=0;%>
                     <td align='center' bgcolor='#FFFFFF' style='color:#666666' class="ball_1 <%if(j==0){out.println("line_left_1");} %>">
                      <font color=white><%=arrNum[j].toString()%></font>
                     </td>
                 <%}else{
                     arrInt[i][j]=arrInt[i][j]+1;%>
                     <td align='center' bgcolor='#FFFFFF' style='color:#666666' class="<%if(j==0){out.println("line_left_1");} %>">
                      <%=String.valueOf(arrInt[i][j])%>
                     </td>
                 <%} 
               }
              }%>

			 </tr>
          <%} %>
         
       </tbody>
      </table></td>
    </tr>
</table>

</body>
</html>
