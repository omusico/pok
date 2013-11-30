<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true" 
    import="beanpac.ServerTime,beanpac.IssConInfo"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title></title>
<link href="/pokercss/main.css" rel="stylesheet" type="text/css" />

<script src="/pokerfunc/global.js" type="text/javascript"></script>
<script src="/pokerfunc/glotime.js" type="text/javascript"></script>
<script src="/pokerfunc/pokfunc/timerunwin.js" type="text/javascript"></script>

<style type="text/css">
.winmovieback{ background: url(/pkimages/pokerimage/pokpic/back1.gif)}
</style>
</head>

<body onload=movieLoad()>
    <input type="hidden" name="isgetwinval" value="<%=(String)session.getAttribute("isgetwinvalue")%>">
    <input type="hidden" name="hidwinmaxiss" value="<%=(String)session.getAttribute("hidwinmaxissnum")%>">
    <input type="hidden" name="hidspadeval" value="<%=(String)session.getAttribute("hidspadevalue")%>">
    <input type="hidden" name="hidheartval" value="<%=(String)session.getAttribute("hidheartvalue")%>">
    <input type="hidden" name="hidclubval" value="<%=(String)session.getAttribute("hidclubvalue")%>">
    <input type="hidden" name="hiddiamondval" value="<%=(String)session.getAttribute("hiddiamondvalue")%>">
    <input type="hidden" name="hidspadepicl" value="<%=(String)session.getAttribute("hidspadepiclink")%>">
    <input type="hidden" name="hidheartpicl" value="<%=(String)session.getAttribute("hidheartpiclink")%>">
    <input type="hidden" name="hidclubpicl" value="<%=(String)session.getAttribute("hidclubpiclink")%>">
    <input type="hidden" name="hiddiamondpicl" value="<%=(String)session.getAttribute("hiddiamondpiclink")%>">
      
 
	<table width="920"  height=120 border="0" bordercolor=orange cellspacing="2" cellpadding="2" class="winmovieback">
	<tr>
	  <td align=right >
      	<table width="300" height="90" border="0" bordercolor=orange cellspacing="2" cellpadding="2" style="color:white;font-weight:bold">
	      <tr>
	        <td width="300" style="margin-left:0px;color:white;font-size:13px">

	        </td>
	      </tr>
	      <tr>
	         <td>
	           <table border=0 style="">
	            <tr>
	             <td style="font-size:13px;color:white">
	                          第
	             </td>
	             <td>
	                <span id="issuewinresult" style="font-weight:bold;color:yellow;font-size:20px">
	                <%=(String)session.getAttribute("winmaxissnum")%>
	                </span>
	             </td>
	             <td style="font-size:13px;color:white">期开奖结果:
	             </td>
	            </tr>
	           </table>
	         </td>
	      </tr>
	      <tr>
	        <td>
	         <table border=0 width="300" style="">
		         <tr>
		         <td style="font-size:13px;color:white">黑桃:
		         </td>
		         <td width=30>
		             <span id="spadewin" style="font-weight:bold;color:yellow">
		               <%=(String)session.getAttribute("spadevalue") %>
		             </span>
		         </td>
		         <td width=60><br></td>
		         <td style="font-size:13px;color:white">梅花:
		         </td>
		         <td width=30>
		             <span id="clubwin" style="font-weight:bold;color:yellow">
		                <%=(String)session.getAttribute("clubvalue") %>
		                </span>
		         </td>
		         <td width=60><br></td>
		         </tr>
		         <tr>
		         <td style="font-size:13px;color:white">红桃:
		         </td>
		         <td>
		             <span id="heartwin" style="font-weight:bold;color:yellow">
		                <%=(String)session.getAttribute("heartvalue") %>
		                </span>
		         </td>
		         <td><br></td>
		         <td style="font-size:13px;color:white">方块:
		         </td>
		         <td>
		             <span id="diamondwin" style="font-weight:bold;color:yellow">
		                <%=(String)session.getAttribute("diamondvalue") %>
		                </span>
		         </td>
		         <td><br></td>
		         </tr>
	         </table>
	         </td>
	         </tr>
	         
	    </table>
      </td>
      <td>
      	<table width="420" border="0" bordercolor=orange cellspacing="0" cellpadding="0" >
	      <tr>
	        <td width="43" height="63">
	         <img id="spadeimg" src="<%=(String)session.getAttribute("spadepiclink")%>" style="filter:alpha(opacity=100)">
	        </td>
	        <td width="43" height="63">
	         <img id="heartimg" src="<%=(String)session.getAttribute("heartpiclink")%>" style="filter:alpha(opacity=100)">
	        </td>
	        <td width="43" height="63">
	         <img id="clubimg" src="<%=(String)session.getAttribute("clubpiclink")%>" style="filter:alpha(opacity=100)">
	        </td>
	        <td width="43" height="63">
	         <img id="diamondimg" src="<%=(String)session.getAttribute("diamondpiclink")%>" style="filter:alpha(opacity=100)">
	        </td>
	      </tr>
	    </table>
      </td>
    </tr>
    </table>
</body>
</html>
