<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>华彩在线-彩票投注网</title>
<link href="/pokercss/main.css" rel="stylesheet" type="text/css" />

<script src="/pokerfunc/laughfunc/lauplay.js" type="text/javascript"></script>

<style type="text/css">
.image3 { border:#e2903a solid 1px; background:url(/pkimages/mainimages/img4.gif); color:#1d3d86;}
.image3 a:link,.image3 a:visited { margin:0px; text-decoration:none;}
.image4 { background: url(/pkimages/mainimages/layout1.gif)}
.image6 { background: url(/pkimages/mainimages/indimage6.gif) repeat-x top;margin:0px;}
.imguseque { background: url(/pkimages/mainimages/imguseque.gif) repeat-x top;margin:0px;}

</style>

</head>
<body>
<jsp:useBean id="loginmes" type="beanpac.RemindInfo" scope="session" />
<input type="hidden" name="pagemark" value="happy">

<input type="hidden" name="hidmarkiss" value="">
<input type="hidden" name="hidmulissvalue" value="">
<input type="hidden" name="hidissuetype" value="">
<input type="hidden" name="hidselling" value="">

<table border=0 cellpadding="0" cellspacing="0">
   <tr>
      <td>
         <iframe name="fratitle" id="fratitle" frameborder="0" width="920" height="220"  scrolling="no" src="/title.jsp"></iframe>
      </td> 
   </tr>
</table>

<table border=0 cellpadding="0" cellspacing="0">
   <tr>
      <td>
         <iframe name="fralogin" id="fralogin" frameborder="0" width="920" height="30"  scrolling="no" src="/login.jsp"></iframe>
      </td> 
   </tr>
</table>
<table width="920" class="image4" border=0>
<tr><td align="center"><span id="palytitle" style="font:bold; font-size:13px; color:red">福 彩 3 D</span></td></tr>
</table>
    <!--Main Body-->
    <table width="920" align=center border="2" bordercolordark="#FF0000" bordercolorlight="#FFFF00" cellspacing="0" cellpadding="0" class="image4">
      <tr>  
      	<!-- Flow Chart in the Left Frame -->
		<td width="200"  valign=top>
          <table width="200" border="1" cellspacing="0" cellpadding="0" >
            <tr>
              <td>
              <iframe name="frabulent" id="frabulent" frameborder="0" width="200" height="100"  scrolling="no" src="/pubpage/bulletin.jsp"></iframe>
              </td>
            </tr> 
            <tr>
              <td>
              <iframe name="fraspecuserentry" id="fraspecuserentry" frameborder="0" width="200" height="30"  scrolling="no" src="/pubpage/specuserentry.jsp"></iframe>
              </td>
            </tr>       
            <tr>
             <td align=center>
                <iframe name="frawinnumshow" id="frawinnumshow" frameborder="0" width="200" height="290"  scrolling="no" src="/servlet/winnumshow?play=happy"></iframe>
             </td>
            </tr>
            <tr>
			  <td height="30" colspan="3" align="center" bgcolor="#F4F4F4">
				 <iframe name="fratreent" id="fratreent" frameborder="0" width="200" height="30"  scrolling="no" src="/pubpage/trend/hapent.jsp"></iframe>
              </td>
			</tr>
			<tr>
		        <td bgcolor=white align=center>
		        <form name="formhapque" action="/servlet/hapwinquery" target="_blank" style="margin-bottom:10px;margin-top:10px;">
		          <input type="submit" name="bhapwinquery" value="中奖用户查询"> 
		          <br>请输入期号:<input type="text" name="hapissnum" value="" size=9>
				</form>        
		        </td>
		      </tr>
			<tr>
             <td></td>
            </tr>
          </table>
    </td>
  
    <td width="700"  valign=top>
         <table width="700" border="3" cellspacing="0" cellpadding="0" bordercolordark="#FFFFFF" bordercolorlight="#cccccc">
          <tr>
              <td align=center colspan=2>
                 <iframe name="fratime" id="fratime" frameborder="0" width="700" height="30"  scrolling="no" src="/lott/hap/hapisstime.jsp"></iframe>
              </td>
          </tr>
          <tr>
		     <td width="100" height="30" align="center" bgcolor="#E8E3FB">选择类型：</td>
		     <td width="86%" valign="bottom" bgcolor="#E8E3FB" style="padding:0px; padding-left:0px;">
		        <iframe src="/lott/hap/typetitle.jsp" name="fratyptit" id="fratyptit" frameborder="0" width="600" height="30"  scrolling="no"></iframe>
		     </td>
		   </tr>
		   <tr>
			   <td align="center" bgcolor="#F9E3F9">投注方式：</td>
			   <td>
			      <iframe src="/lott/hap/mode/modtitcom.jsp" name="framodtit" id="framodtit" frameborder="0" width="600" height="30"  scrolling="no"></iframe>
			   </td>
		   </tr>
		   <tr>
			   <td align="center" bgcolor="#E8E3FB">选择号码： </td>
			   <td>
               <iframe name="fraplay" id="fraplay" frameborder="0" width="600" height="400"  scrolling="no" src="/lott/hap/play/commul.jsp" onreadystatechange="playLoad()"></iframe>
             </td>
		   </tr>
       </table>         
    </td>

  </tr>
</table>

</body>
</html>
