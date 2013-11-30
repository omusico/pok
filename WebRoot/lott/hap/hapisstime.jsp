<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true" 
    import="beanpac.ServerTime,beanpac.IssConInfo"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>时间</title>
<link href="/pokercss/main.css" rel="stylesheet" type="text/css" />

<script type='text/javascript' src='/dwr/interface/ServerTime.js'></script>
<script type='text/javascript' src='/dwr/engine.js'></script>
<script type='text/javascript' src='/dwr/util.js'></script>
<script src="/pokerfunc/global.js" type="text/javascript"></script>
<script src="/pokerfunc/happth/happthtime.js" type="text/javascript"></script>
<script src="/pokerfunc/hapfunc/haprefr.js" type="text/javascript"></script>
<script src="/pokerfunc/glotime.js" type="text/javascript"></script>

<style type="text/css">
.image6 { background: url(/pkimages/mainimages/indimage6.gif) repeat-x top;margin:0px;}
</style>

</head>
<body onload=timeLoad()>
    <% ServerTime objServerTime = new ServerTime();%>
    <INPUT TYPE="HIDDEN" NAME="stimeyear" VALUE="<%=objServerTime.getYear()%>">
    <INPUT TYPE="HIDDEN" NAME="stimemonth" VALUE="<%=objServerTime.getMonth()%>">
    <INPUT TYPE="HIDDEN" NAME="stimeday" VALUE="<%=objServerTime.getDay()%>">
    <INPUT TYPE="HIDDEN" NAME="stimehour" VALUE="<%=objServerTime.getHour()%>">
    <INPUT TYPE="HIDDEN" NAME="stimeminute" VALUE="<%=objServerTime.getMinute()%>">
    <INPUT TYPE="HIDDEN" NAME="stimesecond" VALUE="<%=objServerTime.getSecond()%>">
    
    
<%IssConInfo objICI=new IssConInfo();
  objICI.getIssCon("hapisscon");%>
<input type="hidden" name="begdate" value="<%=objICI.getStrBegDate() %>">
<input type="hidden" name="begiss" value="<%=objICI.getStrBegIss() %>">
<input type="hidden" name="beghour" value="<%=objICI.getStrBegHour() %>">
<input type="hidden" name="begmin" value="<%=objICI.getStrBegMin() %>">
<input type="hidden" name="dursell" value="<%=objICI.getStrDurSell() %>">
<input type="hidden" name="durwin" value="<%=objICI.getStrDurWin() %>">
<input type="hidden" name="hmiss" value="<%=objICI.getStrHMIss() %>">


<input type="hidden" name="offsetFlag" id="offsetFlag" value='<%=objICI.getOffsetFlag()%>'>
<input type="hidden" name="offsetTime" id="offsetTime" value='<%=objICI.getOffsetTime()%>'>    
 
     <table>
         <tr>
            <td>
            <table width="700" border="0" cellpadding="0" cellspacing="0" class="image6" style="margin-bottom:0px;">
            <tr>
            <td width=20></td>
            <td width="340" height=30>
             <span id="currentissueshow" style="color:#FF6600;font-weight:bold"></span>期
		     <span id="issstatshow" style="color:#FF6600;font-weight:bold"></span>
             <span id="remainsecond" style="background:#FFFF00; color:red;font-weight:bold; font-size:12px"></span>    
            </td>
            <td width="330" align="left" style="">
              <font color="#CC0000">系统时间:</font>
              <font color><span id="servertime"></span></font>
            </td>
         </tr>     
    </table>

</body>
</html>
