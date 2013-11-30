<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
<%@page import="beanpac.IssConInfo"%>
<%@page import="beanpac.ServerTime"%>   
<%@ include file="/common/taglibs.jsp"%>

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
<script src="/pokerfunc/glotime.js" type="text/javascript"></script>
<script src="/jsp/sxw/js/sxw_timerun.js" type="text/javascript"></script>

<style type="text/css">
.image6 { background: url(/pkimages/mainimages/indimage6.gif) repeat-x top;margin:0px;}
</style>

</head>
<body onload="timeLoad()">
    <% ServerTime objServerTime = new ServerTime();%>
    <INPUT TYPE="HIDDEN" NAME="stimeyear" VALUE="<%=objServerTime.getYear()%>">
    <INPUT TYPE="HIDDEN" NAME="stimemonth" VALUE="<%=objServerTime.getMonth()%>">
    <INPUT TYPE="HIDDEN" NAME="stimeday" VALUE="<%=objServerTime.getDay()%>">
    <INPUT TYPE="HIDDEN" NAME="stimehour" VALUE="<%=objServerTime.getHour()%>">
    <INPUT TYPE="HIDDEN" NAME="stimeminute" VALUE="<%=objServerTime.getMinute()%>">
    <INPUT TYPE="HIDDEN" NAME="stimesecond" VALUE="<%=objServerTime.getSecond()%>">
    
    
<%//IssConInfo objICI=new IssConInfo();
  //objICI.getIssCon("pokisscon");%>
<input type="hidden" name="beginDate" value='<c:out value="${configInfo.beginDate}"/>'>
<input type="hidden" name="beginIssue" value='<c:out value="${configInfo.beginIssue}"/>'>
<input type="hidden" name="beginHour" value='<c:out value="${configInfo.beginHour}"/>'>
<input type="hidden" name="begmin" value='<c:out value="${configInfo.beginMin}"/>'>
<input type="hidden" name="sellMin" value='<c:out value="${configInfo.sellMin}"/>'>
<input type="hidden" name="winMin" value='<c:out value="${configInfo.winMin}"/>'>
<input type="hidden" name="dayIssueTimes" value='<c:out value="${configInfo.dayIssueTimes}"/>'>
<input type="hidden" name="offsetFlag" id="offsetFlag" value='<c:out value="${configInfo.offsetFlag}"/>'>
<input type="hidden" name="offsetTime" id="offsetTime" value='<c:out value="${configInfo.offsetTime}"/>'>
    
 
     <table>
         <tr>
            <td>
            <table width="700" border="0" cellpadding="0" cellspacing="0" class="image6" style="margin-bottom:0px;">
            <tr>
            <td width=20></td>
            <td width="50%" height=30>
             <span id="currentIssueShow" style="color:#FF6600;font-weight:bold"></span>期
		     <span id="issueStateShow" style="color:#FF6600;font-weight:bold"></span>
             还剩<span id="remainsecond" style="background:#FFFF00; color:red;font-weight:bold; font-size:12px"></span>    
            </td>
            <td width="50%" align="left" style="">
              <font color="#CC0000">系统时间:</font>
              <font color><span id="servertime"></span></font>
            </td>
         </tr>     
    </table>

</body>
</html>
