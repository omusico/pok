<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="dbconnpac.DataBaseConn" %>
<html>
<head>
<title>�û���ϸ��Ϣ</title>
<script Language="JavaScript">
function subRemit(act){
  document.all("dowhat").value=act;
  document.formremit.submit();
}
function subFre(act){
  document.all("fredowhat").value=act;
  document.formfre.submit();
}
function refre(){
  parent.document.frames['fraspuseinfo'].location.href="servlet/servuserinfo?mark=spuseinf";
}
</script>
<style type="text/css">
body,td,th { font-size:13px;}
*{margin:0px}
ul { list-style:none;}
.image1 a:link,.image1 a:visited { color:#000; text-decoration:none}
.image1 a:hover,.image1 a:active { color:#FF0000; text-decoration:underline;}
.image1 { background: url(/pkimages/mainimages/layout.gif) repeat-x top;}
.image2 { padding-top:2px; padding-left:20px;}
.image2 li { float:left; color:#FFF; margin:0 20px}
.image2 a:link,.image2 a:visited { color:#FFF; font-weight:bold; margin:0 2px 0 5px; text-decoration:none;}
.image2 a:active,.image2 a:hover { color:#FFFF00;}

.pad1 { padding-left:20px}
</style>
</head>

<body>
<%
    DataBaseConn dbcInfo = (DataBaseConn) request.getAttribute("infodbc");
    dbcInfo.connCloseUpdate();
  %>
<table align=center width=910 height=200 border=1 cellpadding=0 cellspacing=0 bordercolor=red>
  <tr>
  <td>
   <table align=center width=910 height=30 border=1 cellpadding=0 cellspacing=0 bordercolor=pink>
	    <tr>
	     <td colspan=2 height=30 align=center>��Ϣ����</td>
	    </tr>
	    <tr height=30 valign=top>
		     <td width=300 colspan=2>
		      <form name="formremit" action="/servlet/useinfoper?oper=remitoper" method="post">
		       ���
		      <input name="remitquantity" type="text"size="12" value="0">Ԫ
		      <input type="hidden" name="dowhat">
		      <input type="button" name="plusremit" value="���" onClick="subRemit('plus')">
		      <input type="button" name="delremit" value="����" onClick="subRemit('del')">
		      </form>
		     </td>
	    </tr>
	    <tr height=30 valign=top>
		     <td width=300 colspan=2>
		      <form name="formfre" action="/servlet/useinfoper?oper=freeze" method="post">
		      �û�Ͷע����:
		      <input type="hidden" name="fredowhat">
		      <input type="button" name="fre" value="����" onClick="subFre('fre')">
		      <input type="button" name="unfre" value="�ⶳ" onClick="subFre('unfre')">
		      </form>
		     </td>
	    </tr>
	    <tr>
	        <tr height=30 valign=top>
		     <td width=300 colspan=2>
		      <form name="formresrequ" action="/servlet/useinfoper?oper=resrequ" method="post">
		      ��ԭ����״̬:
		      <input type="submit" name="restore" value="��ԭ">
		      </form>
		     </td>
	    </tr>
        
        <tr>
          <td align=left style="font-size:13px;font-weight:bold;color:red">
          ��ʾ���Զ��ڸ��Ž���ɾ��ʱ����"��ʶ�ں�"����������"ͬ��"�ںŽ�һ��ɾ�����û�������ԭ��<br>
          ��ʶ�ں�:��ָ�û�����һ������ʱ����Ͷע��<br>
          ͬ�Σ���ָ�û���ͬһ�ڣ�ͬһ�������(��Ϊ�û�������ͬһ��Ͷע��Ρ�)
          </td>
        </tr>
</table>
</body>
</html>