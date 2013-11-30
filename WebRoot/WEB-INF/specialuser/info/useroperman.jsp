<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="dbconnpac.DataBaseConn" %>
<html>
<head>
<title>用户详细信息</title>
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
	     <td colspan=2 height=30 align=center>信息操作</td>
	    </tr>
	    <tr height=30 valign=top>
		     <td width=300 colspan=2>
		      <form name="formremit" action="/servlet/useinfoper?oper=remitoper" method="post">
		       汇款额：
		      <input name="remitquantity" type="text"size="12" value="0">元
		      <input type="hidden" name="dowhat">
		      <input type="button" name="plusremit" value="添加" onClick="subRemit('plus')">
		      <input type="button" name="delremit" value="减少" onClick="subRemit('del')">
		      </form>
		     </td>
	    </tr>
	    <tr height=30 valign=top>
		     <td width=300 colspan=2>
		      <form name="formfre" action="/servlet/useinfoper?oper=freeze" method="post">
		      用户投注限制:
		      <input type="hidden" name="fredowhat">
		      <input type="button" name="fre" value="冻结" onClick="subFre('fre')">
		      <input type="button" name="unfre" value="解冻" onClick="subFre('unfre')">
		      </form>
		     </td>
	    </tr>
	    <tr>
	        <tr height=30 valign=top>
		     <td width=300 colspan=2>
		      <form name="formresrequ" action="/servlet/useinfoper?oper=resrequ" method="post">
		      还原请求状态:
		      <input type="submit" name="restore" value="还原">
		      </form>
		     </td>
	    </tr>
        
        <tr>
          <td align=left style="font-size:13px;font-weight:bold;color:red">
          提示：对多期跟号进行删除时，与"标识期号"连带的所有"同次"期号将一并删除，用户金额将被还原。<br>
          标识期号:是指用户在这一期销售时进行投注。<br>
          同次：是指用户在同一期，同一次数序号(因为用户可以在同一期投注多次。)
          </td>
        </tr>
</table>
</body>
</html>