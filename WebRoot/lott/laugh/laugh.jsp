<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true" import="beanpac.ServerTime"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>华彩在线-彩票投注网</title>
<link href="/pokercss/mainlaugh.css" rel="stylesheet" type="text/css" />

<script src="/pokerfunc/laughfunc/lauplay.js" type="text/javascript"></script>

<style type="text/css">
.image4 { background: url(/pkimages/mainimages/layout1.gif)}
.image6 { background: url(/pkimages/mainimages/indimage6.gif) repeat-x top;margin:0px;}
</style>

</head>
<body>
<input type="hidden" name="pagemark" value="laugh">

<input type="hidden" name="hidmarkiss" value="">
<input type="hidden" name="hidmulissvalue" value="">
<input type="hidden" name="hidissuetype" value="">
<input type="hidden" name="hidselling" value="false">
    
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
<tr><td align="center"><span id="palytitle" style="font:bold; font-size:13px; color:red">时 时 乐</span></td></tr>
</table>
<!----显示中奖号码--->
<table  border=0 cellpadding="0" cellspacing="0">
     <tr>
        <td>
           <iframe name="frawinmovie" id="frawinmovie" frameborder="0" width="920" height="120"  scrolling="no" src="/lott/laugh/laughmovie.jsp"></iframe>
        </td>
     </tr>
</table>  
<!----显示中奖号码 end--->

<table width="920" align=center border="2" bordercolordark="#FF0000" bordercolorlight="#FFFF00" cellspacing="0" cellpadding="0" class="image4">
      <tr>
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
                <iframe name="frawinnumshow" id="frawinnumshow" frameborder="0" width="200" height="290"  scrolling="no" src="/servlet/winnumshow?play=laugh"></iframe>
             </td>
            </tr>
            <tr>
			  <td height="30" colspan="3" align="center" bgcolor="#F4F4F4">
				  <iframe name="fratreent" id="fratreent" frameborder="0" width="200" height="30"  scrolling="no" src="/pubpage/trend/lauent.jsp"></iframe>
              </td>
			</tr>
			<tr>
		        <td align=center bgcolor=white>
		        <form name="formlauque" action="/servlet/lauwinquery" target="_blank" style="margin-bottom:10px;margin-top:10px;">
		          <input type="submit" name="butlauquery" value="中奖用户查询"> 
		          <br>请输入期号:<input type="text" name="lauissnum" value="" size=9>
				</form>        
		        </td>
		    </tr>
			<tr>
             <td></td>
            </tr>
          </table>
        </td>
        
        <td width="700"  valign=top>
		   <table width="700" border="1" cellspacing="0" cellpadding="0" bordercolordark="#FFFFFF" bordercolorlight="#cccccc">
		   <tr>
              <td align=center colspan=2>
                 <iframe name="fratime" id="fratime" frameborder="0" width="700" height="30"  scrolling="no" src="/lott/laugh/lauisstime.jsp"></iframe>
              </td>
           </tr>
		   <tr>
		     <td width="100" height="30" align="center" bgcolor="#E8E3FB">选择类型：</td>
		     <td width="86%" valign="bottom" bgcolor="#E8E3FB" style="padding:0px; padding-left:0px;">
		        <iframe src="/lott/laugh/typetitle.jsp" name="fratyptit" id="fratyptit" frameborder="0" width="600" height="30"  scrolling="no"></iframe>
		     </td>
		   </tr>
		   <tr>
			   <td align="center" bgcolor="#F9E3F9">投注方式：</td>
			   <td>
			      <iframe src="/lott/laugh/mode/modtitcom.jsp" name="framodtit" id="framodtit" frameborder="0" width="600" height="30"  scrolling="no"></iframe>
			   </td>
		   </tr>
		   <tr>
			   <td align="center" bgcolor="#E8E3FB">选择号码： </td>
			   <td bgcolor="#E8E3FB">
			    <iframe src="/lott/laugh/play/commul.jsp" name="fraplay" id="fraplay" frameborder="0" width="600" height="400"  scrolling="no" onreadystatechange="playLoad()"></iframe>
			   </td>
		   </tr>
		   <tr>
               <td colspan=2>
                  <iframe name="framulissue" id="framulissue" frameborder="0" width="700" height="310"  scrolling="yes" src="/servlet/servmulissue?play=laugh"></iframe>
               </td>
            </tr>
		  </table>
       </td>
      </tr>
</table>
</body>
</html>
