<%@ page contentType="text/html; charset=gb2312"  pageEncoding="gb2312" session="true"%>
<%@ include file="/common/taglibs.jsp"%> 
<%@ page import="java.util.*, beanpac.SingleIssueInfo" %>
<html >
<head>
<title>��������-��ƱͶע��-׷Ͷҳ��</title>

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
         <font color=red><b>������ѡ��ķ������ж���׷��Ͷע��</b><br>
         ע�⣺1. ѡ�˹��ܺ����Ϸ��������ٸ��ġ�����Ҫ���ģ�����ȡ���˹��ܡ�ȷ����ѡ��������ʹ�á�<br>
         &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp2. �û�ѡ��һ�����飩�������Ͷע�������е�ĳһ���н�ʱ��������ڵ�Ͷע���Զ�ȡ����</font>
      </td>
    </tr>
    <tr> 
      <td bgcolor=FFFFFF height=30 colspan="10">
        <font color="#0000FF">��ǰ��ΪϵͳĬ�Ϲ�������������Ҫѡ����ʣ���ںţ������ù�������</font> 
      </td>
    </tr>
    <tr> 
      <td bgcolor=FFFFFF height=30 colspan="10">
          ���Ϸ�����ע��Ϊ��<span id="divIssueTzNum" style="color:red; font-weight:bold "></span>
          �ܽ��Ϊ��<span id="divIssueTzMoney" style="color:red; font-weight:bold "></span>Ԫ
      </td>
    </tr>
    <tr>
      <td bgcolor=FFFFFF height=30 colspan="10">
        ������ע��Ϊ��<span id="zhuitouTzNum" style="color:red; font-weight:bold "></span>
          �ܽ��Ϊ��<span id="zhuitouTzMoney" style="color:red; font-weight:bold "></span>Ԫ
      </td>
    </tr>
    <tr> 
      <td bgcolor=FFFFFF height=20 align="center">ѡ��</td>
      <td bgcolor=FFFFFF height=20 align="center">�ں�</td>
      <td bgcolor=FFFFFF height=20 align="center">���۽���ʱ��</td>
      <td bgcolor=FFFFFF height=20 align="center">��������ʱ��</td>
      <td bgcolor=FFFFFF height=20 align="center">������</td>
      <td bgcolor=FFFFFF height=20 align="center">ע��</td>
      <td bgcolor=FFFFFF height=20 align="center">���</td>
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
		    <%if (i==0){ %><font color=red>[��ǰ��]</font>
		    <%} %>
		  </td>
		  <td height=20><%=(String)objSinIssInfo.getWinPrizeTime() %></td>
		  <td height=20>
		   <input type="text" name="<%=TimesTextID %>" value="0" size=2 disabled>
		   <span style="cursor:hand" onclick="plusTimes('<%=TimesTextID %>','<%=indexStr %>')"><img src="/pkimages/pokerimage/plussign.gif" border=0></span>
           <span style="cursor:hand" onclick="minusTimes('<%=TimesTextID %>','<%=indexStr %>')"><img src="/pkimages/pokerimage/minussign.gif" border=0></span>
		  </td>
		  <td><span id="<%=wagNumID %>">0</span></td>
		  <td><span id="<%=wagMonID %>">0</span>Ԫ</td>
		</tr>
     <%} %>
 </table>
<input type="hidden" name="zhuitouIssueLen" value="<%=issLength%>">
</body>
</html>
