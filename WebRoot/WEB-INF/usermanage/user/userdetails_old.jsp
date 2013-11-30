<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

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

<table align=center width=910 height=400 border=1 cellpadding=0 cellspacing=0 bordercolor=red>
 <tr>
  <td><iframe name="fraspuseinfo" id="fraspuseinfo" frameborder="0" width="920" height="200"  scrolling="no" src="/servlet/servuserinfo?mark=spuseinf"></iframe>  
  </td>
 </tr>
 <tr>
  <td><iframe name="frauseroperman" id="frauseroperman" frameborder="0" width="920" height="200"  scrolling="no" src="/servlet/servuserinfo?mark=useroperman"></iframe>  
  </td>
 </tr>
  <tr>
  <td>
   <table align=center width=910 height=30 border=1 cellpadding=0 cellspacing=0 bordercolor=pink>
  <tr>
   <td>
       <table>
         <tr>
           <td align=center style="font-size:13px;font-weight:bold;color:red">快乐扑克
           </td>
         </tr>
         <tr>
	            <td align=center style="font-size:13px;font-weight:bold;color:red">投注记录
	            </td>
	          </tr>
         <tr>
			<td>
			  <iframe name="frapokwag" id="frapokwag" frameborder="0" width="920" height="320"  scrolling="yes" src="/servlet/servuserinfo?mark=pokwag"></iframe>  
			</td>
			</tr>
			<tr>
	            <td align=center style="font-size:13px;font-weight:bold;color:red">中奖记录
	            </td>
	          </tr>
			<tr >
			<td >
			  <iframe name="frapokpri" id="frapokpri" frameborder="0" width="920" height="320"  scrolling="yes" src="/servlet/servuserinfo?mark=pokpri"></iframe>  
			</td>
		</tr>    
	   </table>
  </td>
 </tr>
 <tr>
        <td>
	        <table>
	          <tr>
	            <td align=center style="font-size:13px;font-weight:bold;color:red">时时乐
	            </td>
	          </tr>
	          <tr>
	            <td align=center style="font-size:13px;font-weight:bold;color:red">投注记录
	            </td>
	          </tr>
	          <tr>
<td>
  <iframe name="fralauwag" id="fralauwag" frameborder="0" width="920" height="320"  scrolling="yes" src="/servlet/servuserinfo?mark=lauwag"></iframe>  
</td>
</tr>
<tr>
	            <td align=center style="font-size:13px;font-weight:bold;color:red">中奖记录
	            </td>
	          </tr>
<tr>
<td>
  <iframe name="fralaupri" id="fralaupri" frameborder="0" width="920" height="320"  scrolling="yes" src="/servlet/servuserinfo?mark=laupri"></iframe>  
</td>
</tr>
	          
	       </table>
        </td>
 </tr>
 <tr>
     <td>
	        <table>
	          <tr>
	            <td align=center style="font-size:13px;font-weight:bold;color:red">福彩3D
	            </td>
	          </tr>
	          <tr>
	            <td align=center style="font-size:13px;font-weight:bold;color:red">投注记录
	            </td>
	          </tr>
	          <tr>
<td>
  <iframe name="frahapwag" id="frahapwag" frameborder="0" width="920" height="320"  scrolling="yes" src="/servlet/servuserinfo?mark=hapwag"></iframe>  
</td>
</tr>
<tr>
	            <td align=center style="font-size:13px;font-weight:bold;color:red">中奖记录
	            </td>
	          </tr>
<tr>
<td>
  <iframe name="frahappri" id="frahappri" frameborder="0" width="920" height="320"  scrolling="yes" src="/servlet/servuserinfo?mark=happri"></iframe>  
</td>
</tr>
	          
	       </table>
        </td>
 </tr>
 <tr>
        <td>
	        <table>
	          <tr>
	            <td align=center style="font-size:13px;font-weight:bold;color:red">排列三
	            </td>
	          </tr>
	          <tr>
	            <td align=center style="font-size:13px;font-weight:bold;color:red">投注记录
	            </td>
	          </tr>
	          <tr>
<td>
  <iframe name="frapthwag" id="frapthwag" frameborder="0" width="920" height="320"  scrolling="yes" src="/servlet/servuserinfo?mark=pthwag"></iframe>  
</td>
</tr>
<tr>
	            <td align=center style="font-size:13px;font-weight:bold;color:red">中奖记录
	            </td>
	          </tr>
<tr>
<td>
  <iframe name="frapthpri" id="frapthpri" frameborder="0" width="920" height="320"  scrolling="yes" src="/servlet/servuserinfo?mark=pthpri"></iframe> 
</td>
</tr>
	          
	       </table>
        </td>
 </tr>
</table>
</body>
</html>