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
<script src="/pokerfunc/laughfunc/lautimerunwin.js" type="text/javascript"></script>
<script type='text/javascript' src='/dwr/interface/LauWinNumGet.js'></script>
<script type='text/javascript' src='/dwr/engine.js'></script>
<script type='text/javascript' src='/dwr/util.js'></script>
<script language="javascript">
<!--
	
	function getLauNum(){
		LauWinNumGet.getLauNumWinInfo(function(data){lauNumCallback(data)});
	}
	function lauNumCallback(data){
		document.getElementById("hidMaxLauIssNum").value=data[0];
		document.getElementById("hidHundred").value=data[1];
		document.getElementById("hidTen").value=data[2];
		document.getElementById("hidOne").value=data[3];
		document.all("issuewinresult").innerHTML=document.getElementById("hidMaxLauIssNum").value;

	}
	LauWinNumGet.getLastLauNumWinInfo(function(data){lauNumCallback_begin(data)});

	function lauNumCallback_begin(data){
		document.all("issuewinresult").innerHTML=data[0];
		document.all("hundredWin").innerHTML=data[1];
		document.all("tenWin").innerHTML=data[2];
		document.all("oneWin").innerHTML=data[3];
	  
	}
//-->
</script>
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
      
 
    <input type="hidden" name="hidMaxLauIssNum"  id="hidMaxLauIssNum"  value="">
    <input type="hidden" name="hidHundred" id="hidHundred" value="">
    <input type="hidden" name="hidTen" id="hidTen" value="">
    <input type="hidden" name="hidOne" id="hidOne" value="">
    
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
	         <table border=0 width="100%" style="">
		         <tr>
		         <td style="font-size:13px;color:white" width=60>百 位:
		         </td>
		         <td width=60>
		             <span id="hundredWin" style="font-weight:bold;color:yellow">
		             </span>
		         </td>
		         <td width=60><br></td>
		         <td style="font-size:13px;color:white" width=60>十 位:
		         </td>
		         <td width=60>
		             <span id="tenWin" style="font-weight:bold;color:yellow">
		                </span>
		         </td>
				 <td width=60><br></td>
				  <td style="font-size:13px;color:white" width=60>个 位:
		         </td>
		         <td>
		             <span id="oneWin" style="font-weight:bold;color:yellow">	                
		                </span>
		         </td>
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
	         
	        </td>
	        <td width="43" height="63">
	         
	        </td>
	        <td width="43" height="63">
	        
	        </td>
	      </tr>
	    </table>
      </td>
    </tr>
    </table>
</body>
</html>
<SCRIPT LANGUAGE="JavaScript">
<!--
	  document.all("issuewinresult").innerHTML=document.getElementById("hidMaxLauIssNum").value;
	  document.all("hundredWin").innerHTML=document.getElementById("hidHundred").value;
	  document.all("tenWin").innerHTML=document.getElementById("hidTen").value;
	  document.all("oneWin").innerHTML=document.getElementById("hidOne").value;
	  

//-->
</SCRIPT>