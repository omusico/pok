<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="dbconnpac.DataBaseConn" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>��������-��ƱͶע��</title>
<link href="/pokercss/main.css" rel="stylesheet" type="text/css" />
<script Language="JavaScript">
function refre(){
  parent.document.frames['fraspuseinfo'].location.href="servlet/servuserinfo?mark=spuseinf";
}
</script>
<style type="text/css">
.image1 a:link,.image1 a:visited { color:#00F; text-decoration:none}
.image1 a:hover,.image1 a:active { color:#FF0000; text-decoration:underline;}
.image1 { background: url(/pkimages/mainimages/layout.gif) repeat-x top;}
.image2 { padding-top:2px; padding-left:20px;}
.image2 li { float:left; color:#FFF; margin:0 20px}
.image2 a:link,.image2 a:visited { color:#FFF; font-weight:bold; margin:0px; text-decoration:none;}
.image2 a:active,.image2 a:hover { color:#FFFF00;} 
.image3 { background:url(/pkimages/mainimages/layout6.gif);}
.image3 a:link,.image3 a:visited { margin:0px; text-decoration:none;}
.image6 { background:url(/pkimages/mainimages/userinfoimag1.gif) repeat-y;}

.pad1 { padding-left:20px}
</style>
</head>

<body>
<%
    DataBaseConn dbcInfo = (DataBaseConn) request.getAttribute("infodbc");
    dbcInfo.connCloseUpdate();
  %>
<table align=center width=910 height=300 border=1 cellpadding=0 cellspacing=0 bordercolor=red class=image6>
 
 <tr>
  <td>
   <table align=center width=910 height=30 border=1 cellpadding=0 cellspacing=0 bordercolor=pink>
    <tr >
     <td  colspan=4 align=center style="font-size:13px;font-weight:bold;color:red">�û�������</td>
    </tr>
    <tr>
     <td  align=left>
       <form name="formdrawreq" action="/servlet/specuseroper" style="margin-left:10px;margin-bottom:3px;margin-top:3px;">
		<input type="hidden" name="hidusername" value="<%=(String)session.getAttribute("usernamestr")%>">
        <input type="submit" name="butdrawreq" value="�������">
 		<font color=red>
 		  <% String strDrawReqRem=(String)request.getAttribute("drawreqrem"); 
 		     if(strDrawReqRem!=null){out.println(strDrawReqRem);}%>
 		</font>
	   </form>
     </td>
     <td  align=left>
       <a href="/servlet/specuserinfup" target="_blank">�û���Ϣ�޸�</a>
     </td>
    </tr>
   </table>
  </td>
 </tr>
 
 <tr><td align=center style="font-size:13px;font-weight:bold;color:red">˵ ��</td></tr>
 <tr>
   <td style="padding-left:10px">
   <font color=red>��ʶ�ں�</font>����ָÿ���������۵��ںš���ʾ�ڵ���ʱ����ࡣ<br>
   <font color=red>Ͷע�ں�</font>����ָ���ڱ�ʶ�ںŵĻ�������׷�ӵ��ںš�ע�⣺Ͷע�ں���ͬ����һ����ʶ�ں���ͬ�����㿴��Ͷע�ں�ʱ��һ��Ҫ��һ��ǰ��ı�ʶ�ںš����磺Ͷע�ں�Ϊ0006����ʶ�ں�Ϊ0003����ʾ��ʱ��������0003�������0003��������Ͷע������׷����0006�ڡ������׷���ں����н�����������׷���ں��Զ�ȡ�����������Ժ���н���������Ͷ�Ľ��Զ�������<br>
   <font color=red>�������</font>����ָ���ڱ�ʶ�ں�ʱ���ڼ��ν���Ͷע������ע�⣺���һ�Ρ�ȷ��Ͷע����ť��Ϊһ�Ρ�<br>
   <font color=red>���ڸ���</font>����ָ�Ա�ʶ�ں��Ƿ��и��ڡ�<br>
   <font color=red>Ͷע����</font>����ָ��ͬ���淨���͡��磺��ѡһ����ѡ����<br>
   <font color=red>Ͷעģʽ</font>����ָ������Ĳ�ͬģʽ���磺����ѡһ���еġ���ʽ��������ʽ��������ѡ��ʽ���еġ���4��������6��<br>
   <font color=red>Ͷע����</font>����ָ��Ͷ�ĺ��롣��Ͷע��������ʾ��ÿһ��Ͷע������<font color=red>$</font>���Ÿ�����<br>
   <font color=red>ע��</font>����ָ���Ӧ��Ͷע�����ע����<br>
   <font color=red>����</font>����ָͶע�ں��е�Ͷע�����Ĺ�������<br>
   </td>
 </tr>
</table>

</body>
</html>