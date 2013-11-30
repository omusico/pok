<%@ page contentType="text/html; charset=gb2312"  pageEncoding="gb2312" session="true"%>
<%@ include file="/common/taglibs.jsp"%> 
<%@ page import="java.util.*, beanpac.SingleIssueInfo" %>
<html >
<head>
<title>华彩在线-彩票投注网-追投页面</title>

<script src="/jsp/ssc/js/ssc_zhuitou.js" type="text/javascript"></script>

<style type="text/css">
* { margin:0;}
body,td,th { font-size: 12px;}
</style>
</head>
<body>
 <table width="100%" border="0" cellspacing="1" cellpadding="2" bgcolor="#BC9E00" align="center">
    <tr> 
      <td bgcolor=FFFFFF height=30 colspan="10"> 
        <input type="checkbox" name="chestmuliss" id="chestmuliss" onClick="switchMulIss()">
         <font color=red><b>对以上选择的方案进行多期追号投注。</b><br>
         注意：1. 选此功能后，以上方案不可再更改。如需要更改，请先取消此功能。确定所选方案后，再使用。<br>
         &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp2. 用户选定一个（组）号码多期投注，当其中的某一期中奖时，后面各期的投注则自动取消。</font>
      </td>
    </tr>
    <tr> 
      <td bgcolor=FFFFFF height=30 colspan="10">
        <font color="#0000FF">当前期为系统默认购买，请您根据需要选择当天剩余期号，并设置购买倍数！</font> 
      </td>
    </tr>
    <tr> 
      <td bgcolor=FFFFFF height=30 colspan="10">
          以上方案总注数为：<span id="divIssueTzNum" style="color:red; font-weight:bold "></span>
          总金额为：<span id="divIssueTzMoney" style="color:red; font-weight:bold "></span>元
      </td>
    </tr>
    <tr>
      <td bgcolor=FFFFFF height=30 colspan="10">
        多期总注数为：<span id="zhuitouTzNum" style="color:red; font-weight:bold "></span>
          总金额为：<span id="zhuitouTzMoney" style="color:red; font-weight:bold "></span>元
      </td>
    </tr>
    <tr> 
      <td bgcolor=FFFFFF height=20 align="center">选择</td>
      <td bgcolor=FFFFFF height=20 align="center">期号</td>
      <td bgcolor=FFFFFF height=20 align="center">销售截至时间</td>
      <td bgcolor=FFFFFF height=20 align="center">开奖截至时间</td>
      <td bgcolor=FFFFFF height=20 align="center">购买倍数</td>
      <td bgcolor=FFFFFF height=20 align="center">注数</td>
      <td bgcolor=FFFFFF height=20 align="center">金额</td>
    </tr>
    <%
      //Vector otherIssueList=(Vector)session.getAttribute("wholeissnumvec");
      List<SingleIssueInfo> otherIssueList  = (List<SingleIssueInfo>)request.getAttribute("otherList");

	  int issLength = otherIssueList.size();
      String prefixIssCheck = "isscheck";
      String prefixIssTd = "isstd";
      String prefixTimesText="timestext";

      for(int i=0;i<otherIssueList.size();i++){
    	SingleIssueInfo objSinIssInfo=(SingleIssueInfo)otherIssueList.get(i);
        String issCheckID=prefixIssCheck+String.valueOf(i);
        String isstdID=prefixIssTd+String.valueOf(i);
        String TimesTextID=prefixTimesText+String.valueOf(i);
        String wagNumID="wagnumid"+String.valueOf(i);
        String wagMonID="wagmonid"+String.valueOf(i);
        String indexStr=String.valueOf(i);
    %>
	    <tr bgcolor="ffffff" align=center> 
		  <td height=20>
		   <input type="checkbox" name="<%=issCheckID%>" value="<%=String.valueOf(i)%>" onClick="switchTimesText('<%=String.valueOf(i)%>')" disabled>
		  </td>
		  <td height=20 id="<%=isstdID %>"><%=(String)objSinIssInfo.getIssueNumber()%></td>
		  <td height=20><%=(String)objSinIssInfo.getWagerEndTime() %>
		    <%if (i==0){ %><font color=red>[当前期]</font>
		    <%} %>
		  </td>
		  <td height=20><%=(String)objSinIssInfo.getWinPrizeTime() %></td>
		  <td height=20>
		   <input type="text" name="<%=TimesTextID %>" value="0" size=2 disabled>
		   <span style="cursor:hand" onclick="plusTimes('<%=TimesTextID %>','<%=indexStr %>')"><img src="/pkimages/pokerimage/plussign.gif" border=0></span>
           <span style="cursor:hand" onclick="minusTimes('<%=TimesTextID %>','<%=indexStr %>')"><img src="/pkimages/pokerimage/minussign.gif" border=0></span>
		  </td>
		  <td><span id="<%=wagNumID %>">0</span></td>
		  <td><span id="<%=wagMonID %>">0</span>元</td>
		</tr>
     <%} %>
 </table>
<input type="hidden" name="zhuitouIssueLen" value="<%=issLength%>">
</body>
</html>
