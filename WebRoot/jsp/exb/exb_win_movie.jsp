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
<script src="/js/jquery/jquery.js" type="text/javascript"></script>
<script type='text/javascript' src='/dwr/interface/DwrBean.js'></script>
<script type='text/javascript' src='/dwr/engine.js'></script>

<script src="/jsp/exb/js/exb_win_movie.js" type="text/javascript"></script>
<style type="text/css">
.winmovieback{ background: url(/pkimages/pokerimage/pokpic/back1.gif)}
</style>
<style type="text/css">
.ballDiv {
	font-size:35px;
	font-weight:25;
	color:black;
}
</style>
</head>

<body onload="movieLoad()">
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
  
  <table width="920"  height=120 border="1" bordercolor=orange cellspacing="2" cellpadding="2" class="winmovieback">
	<tr>
	  <td align="left" >
      	<table width="100%" height="70" border="0" bordercolor=orange cellspacing="2" cellpadding="2" style="color:white;font-weight:bold">
		 <tr>
			
	         <td style="font-size:13px;color:white" width="240">第
				<span id="issuewinresult" name="issuewinresult" style="font-weight:bold;color:yellow;font-size:20px">
				<%=(String)request.getAttribute("curWinIssueNum")%>
				</span>
			 期开奖结果:</td>

			<td id="wan" width="60" height="60" background="/images/huang-22.gif" align="center">
				<div id="div1" name="div1" class="ballDiv" valign="center"><%=(String)request.getAttribute("position1")%></div>
			</td>
			<td width="10">&nbsp;</td>
			<td id="qian"  width="60" height="60" background="/images/huang-22.gif" align="center">
				<div id="div2" name="div2" class="ballDiv"><%=(String)request.getAttribute("position2")%></div>
			</td>
			<td width="10">&nbsp;</td>
			<td id="bai"  width="60" height="62" background="/images/huang-22.gif" align="center">
				<div id="div3" name="div3" class="ballDiv" ><%=(String)request.getAttribute("position3")%></div>
			</td>
			<td width="10">&nbsp;</td>
			<td id="shi"  width="60" height="62" background="/images/huang-22.gif" align="center">
				<div id="div4"  name="div4" class="ballDiv"><%=(String)request.getAttribute("position4")%></div>
			</td>
			<td width="10">&nbsp;</td>
			<td id="ge"  width="60" height="62" background="/images/huang-22.gif" align="center">
				<div id="div5"  name="div5" class="ballDiv"><%=(String)request.getAttribute("position5")%></div>
			</td>
			<td >&nbsp;</td>
			<td id="ge"  width="60" height="62" background="/images/huang-22.gif" align="center">
				<div id="div6"  name="div6" class="ballDiv"><%=(String)request.getAttribute("position6")%></div>
			</td>
			<td >&nbsp;</td>
			<td id="ge"  width="60" height="62" background="/images/huang-22.gif" align="center">
				<div id="div7"  name="div7" class="ballDiv"><%=(String)request.getAttribute("position7")%></div>
			</td>
			<td >&nbsp;</td>
			<td id="ge"  width="60" height="62" background="/images/huang-22.gif" align="center">
				<div id="div8"  name="div8" class="ballDiv"><%=(String)request.getAttribute("position8")%></div>
			</td>
			<td >&nbsp;</td>
	     </tr>
       </table>
	 </td>
	</tr>
  </table>
  
</body>
</html>
