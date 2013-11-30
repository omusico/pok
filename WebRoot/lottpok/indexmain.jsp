<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>华彩在线-彩票投注网</title>
<link href="/pokercss/main.css" rel="stylesheet" type="text/css" />

<script src="/pokerfunc/pokfunc/play.js" type="text/javascript"></script>

<script language="javascript"></script>
<style type="text/css">
.typeready { background:url(/pkimages/pokerimage/img_tab2.gif) no-repeat center; text-align:center; cursor:hand}
.typechosen { background:url(/pkimages/pokerimage/img_tab1.gif) no-repeat center; text-align:center; cursor:hand}
.modeready { background:url(/pkimages/pokerimage/modeready.gif) no-repeat center; text-align:center; cursor:hand}
.modeforbid { background:url(/pkimages/pokerimage/modeforbid.gif) no-repeat center; text-align:center; cursor:hand}
.modechosen { background:url(/pkimages/pokerimage/modechosen.gif) no-repeat center; text-align:center; cursor:hand}

.image3 { border:#e2903a solid 1px; background:url(/pkimages/mainimages/img4.gif); color:#1d3d86;}
.image3 a:link,.image3 a:visited { margin:0px; text-decoration:none;}
.image4 { background: url(/pkimages/mainimages/layout1.gif)}
.image6 { background: url(/pkimages/mainimages/indimage6.gif) repeat-x top;margin:0px;}
.winmovieback{ background: url(/pkimages/pokerimage/pokpic/back1.gif)}
</style>
</head>

<body>
<input type="hidden" name="pagemark" value="indexmain">

<input type="hidden" name="hidmarkiss" value="">
<input type="hidden" name="hidmulissvalue" value="">
<input type="hidden" name="hidissuetype" value="">
<input type="hidden" name="hidselling" value="false">

<input type="hidden" name="typechosenmark" value="one">
<input type="hidden" name="modechosenmark" value="multi">

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
<tr><td align="center"><span id="palytitle" style="font:bold; font-size:13px; color:red">快 乐 扑 克</span></td></tr>
</table>
<table  border=0 cellpadding="0" cellspacing="0">
     <tr>
        <td>
           <iframe name="frawinmovie" id="frawinmovie" frameborder="0" width="920" height="120"  scrolling="no" src="/servlet/winnumpost"></iframe>
        </td>
     </tr>
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
                <iframe name="frawinnumshow" id="frawinnumshow" frameborder="0" width="200" height="290"  scrolling="no" src="/servlet/winnumshow?play=poker"></iframe>
             </td>
            </tr>
            <tr>
			  <td height="30" colspan="3" align="center" bgcolor="#F4F4F4">
				  <iframe name="fratreent" id="fratreent" frameborder="0" width="200" height="30"  scrolling="no" src="/pubpage/trend/pokent.jsp"></iframe>
              </td>
			</tr>
			<tr bgcolor=white>
		        <td align=center bgcolor=white>
		        <form name="formpokque" action="/servlet/winquery" target="_blank" style="margin-bottom:10px;margin-top:10px;">
		          <input type="submit" name="butpokquery" value="中奖用户查询"> 
		          <br>请输入期号:<input type="text" name="pokissnum" value="" size=9>
				</form>        
		        </td>
		    </tr>
			<tr>
             <td>
               <img src="/pkimages/mainimages/flowchart.gif" />
            </td>
            </tr>
          </table>
        </td>
  
        <td width="700" align=center>
           <table border=0> 
            <tr>
                <td align=center>
                   <iframe name="fratime" id="fratime" frameborder="0" width="700" height="30"  scrolling="no" src="/lottpok/isstime.jsp"></iframe>
                </td>
            </tr>
			<tr>
			  <td>
			  <table border="1" width=700 bgcolor=white bordercolordark="#FFFFFF" bordercolorlight="#cccccc"  style="border-bottom:#185c8e solid 2px;">
			   <tr>
			    <td align=center width=7%>类 型</td>
			    <td>
			     <iframe name="fratyptit" id="fratyptit" frameborder="0" width="630" height="30"  scrolling="no" src="/lottpok/typetitle.jsp"></iframe>
			    </td>
			   </tr>
			   <tr>
			    <td align=center width=7%>模 式</td>
			    <td>
			     <iframe name="framodtit" id="framodtit" frameborder="0" width="220" height="30"  scrolling="no" src="/lottpok/mode/modtitcom.jsp"></iframe>
		        </td>
			   </tr>
			   <tr>
			    <td align=center width=7%>说 明</td>
			   	<td id="expl" width=700 align=left bgcolor="#FFFFDD" colspan=2>
			   	规则:从黑、红、梅、方四种花色号码中任选1种花色号码进行投注。<br>复式：可以一次选择多个花色，每个花色可选多个号码。如:(3,4|3,4|3|4)，系统会按照“任选一”规则判断为6注。<br>单式:可以一次选择多个花色，但每个花色只可选一个号码。如:(3|4|2|J)，系统会按照“任选一”规则判断为4注。<br></td>
			   </tr>
			  </table>
			 </td>
			</tr>
			  
	        <tr>
               <td>
                 <iframe name="fraplay" id="fraplay" frameborder="0" width="700" height="620"  scrolling="no" src="/lottpok/play/com.jsp" onreadystatechange="playLoad()"></iframe>
               </td>
            </tr>
            <tr>
               <td>
                  <iframe name="framulissue" id="framulissue" frameborder="0" width="700" height="310"  scrolling="yes" src="/servlet/servmulissue?play=poker"></iframe>
               </td>
			 
            </tr>
         </table>         
		</td>
		
		
      </tr>
    </table>

</body>
</html>
