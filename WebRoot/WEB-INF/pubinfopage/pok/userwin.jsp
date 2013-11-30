<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true" 
    import="dbconnpac.DataBaseConn"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>管理平台</title>

<script src="/pokerfunc/timerunmanage.js" type="text/javascript"></script>
<%
  String haveLogin = (String)request.getAttribute("haveLogin");
  if(haveLogin == null || haveLogin.equals("0")){
%>
<script language="javascript">
<!--
	 alert("请先登陆!");
	 window.close();
//-->
</script>
<%
  }else{
%>
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
.image6 { background: url(/pkimages/mainimages/indimage6.gif) repeat-x top;margin:0px;}
</style>
</head>

<body>
<%DataBaseConn uwDBC=(DataBaseConn)session.getAttribute("userwininfo");%>
<table width="910" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="white">
<tr><td>期号:<%=(String)session.getAttribute("issnum") %></td></tr>
    <tr>
      <td bgcolor="#FFFFFF">
       <table width="910" border="1" align="center" cellpadding="0" cellspacing="1" bgcolor="white">
        <tbody>
          <tr>
            <td width="10%" height="20" align="center">用户名</td>
            <td width="10%" align="center">玩法类型</td>
            <td width="10%" align="center">玩法模式</td>
            <td width="10%" align="center">投注号码</td>
            <td width="10%" align="center">判断规则</td>
            <td width="10%" align="center">中奖注数</td>
            <td width="10%" align="center">倍数</td>
            <td width="10%" align="center">总注数</td>
            <td width="10%" align="center">奖金</td>
          </tr>
          <%while(uwDBC.rsNext()){%>
          <tr>
            <td align="center"><%=uwDBC.rsGetString("username") %></td>
            <td align="center"><%=uwDBC.rsGetString("playtype") %></td>
            <td align="center"><%=uwDBC.rsGetString("playmode")%><br></td>
            <td align="center"><%=uwDBC.rsGetString("onewager") %></td>
            <td align="center"><%=uwDBC.rsGetString("rule") %></td>
            <td align="center"><%Integer IntWagNum=Integer.valueOf(uwDBC.rsGetString("wagernum"));
                                 out.println(IntWagNum);%></td>
            <td align="center"><%Integer IntTimes=Integer.valueOf(uwDBC.rsGetString("times"));
                                 out.println(IntTimes);%></td>
            <td align="center"><%out.println(IntWagNum*IntTimes); %></td>
            <td align="center"><%=uwDBC.rsGetString("prize") %></td>
          </tr>
          <%  
          }%>
       </tbody>
      </table>
     </td>
    </tr>
    <%uwDBC.connClose(); %>
</table>
</body>
<%
  }
%>
</html>