<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	String userId = request.getParameter("userid");
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>��������-��ƱͶע��</title>

<script Language="JavaScript">
function linkPlat(loc,obj){
  document.frames['fraplat'].location.href=loc;
  var arrMark=new Array("tdspuseinf","tdspuseoper","tdpokwag","tdpokpri","tdlauwag","tdlaupri","tdhapwag","tdhappri","tdpthwag","tdpthpri");
  for(var i=0;i<arrMark.length;i++){
	if(obj.id==arrMark[i]){
	  obj.style.color="yellow";
	}else{
	  document.all(arrMark[i]).style.color="white";
	}
  }
}
</script>

<link href="/pokercss/main.css" rel="stylesheet" type="text/css" />

<style type="text/css">
.image2 { padding-top:2px; padding-left:20px;}
.image2 td { color:#FFF;margin:20px;cursor:hand}
.image2 .image2 a:visited { color:#FFF; font-weight:bold; margin:0 2px 0 5px; text-decoration:none;}
.image2 a:active,.image2 a:hover { color:#FFFF00;} 
.image6 { background:url(/pkimages/mainimages/userinfoimag1.gif) repeat-y;}

.pad1 { padding-left:20px}
</style>
</head>

<body>
<!--  
<table border=0 cellpadding="0" cellspacing="0">
   <tr>
      <td>
         <iframe name="fratitle" id="fratitle" frameborder="0" width="920" height="220"  scrolling="no" src="/title.jsp"></iframe>
      </td> 
   </tr>
</table>
-->
<center>
<table width="80%" border="1" bordercolor=yellow align="center" cellpadding="0" cellspacing="0" style="margin-bottom:3px;">
	    <tr width="100%"align="center">
	    <td width="1"></td>
	    <td background="" bgcolor=orange class="image2">
	      <table border="1" bordercolor=yellow cellpadding=0 cellspacing=0>
	        <tr>
	           <td id="tdspuseinf" onClick=linkPlat('/servlet/servuserinfo?mark=spuseinf',this) style="color:yellow">�û���ϸ��Ϣ</td>
	           <td id="tdspuseoper" onClick=linkPlat('/servlet/servuserinfo?mark=spuseoper',this)>�û�������</td>
	           <td>
	             <table>
	               <tr><td align=center colspan=2>�����˿�</td></tr>
	               <tr>
	                 <td id="tdpokwag" onClick=linkPlat('/servlet/servuserinfo?mark=pokwag',this)>Ͷע��¼|
	                 </td>
	                 <td id="tdpokpri" onClick=linkPlat('/servlet/servuserinfo?mark=pokpri',this)>�н���¼
	                 </td>
	               </tr>
	             </table>
	           </td>
	           <td>
	             <table>
	               <tr><td align=center colspan=2>ʱʱ��</td></tr>
	               <tr>
	                 <td id="tdlauwag" onClick=linkPlat('/servlet/servuserinfo?mark=lauwag',this)>Ͷע��¼|
	                 </td>
	                 <td id="tdlaupri" onClick=linkPlat('/servlet/servuserinfo?mark=laupri',this)>�н���¼
	                 </td>
	               </tr>
	             </table>
	           </td>
	           <td>
	             <table>
	               <tr><td align=center colspan=2>����3D</td></tr>
	               <tr>
	                 <td id="tdhapwag" onClick=linkPlat('/servlet/servuserinfo?mark=hapwag',this)>Ͷע��¼|
	                 </td>
	                 <td id="tdhappri" onClick=linkPlat('/servlet/servuserinfo?mark=happri',this)>�н���¼
	                 </td>
	               </tr>
	             </table>
	           </td>
	           <td>
	             <table>
	               <tr><td align=center colspan=2>������</td></tr>
	               <tr>
	                 <td id="tdpthwag" onClick=linkPlat('/servlet/servuserinfo?mark=pthwag',this)>Ͷע��¼|
	                 </td>
	                 <td id="tdpthpri" onClick=linkPlat('/servlet/servuserinfo?mark=pthpri',this)>�н���¼
	                 </td>
	               </tr>
	             </table>
	           </td>
	            <td>
	             <table>
	               <tr><td align=center colspan=2>ʱʱ��</td></tr>
	               <tr>
	                 <td id="tdSscTz" onClick=linkPlat('/ssc/sscPublicInfo.do?method=viewUserTzInfo&userId=<%=userId%>',this)>Ͷע��¼|
	                 </td>
	                 <td id="tdSscZj" onClick=linkPlat('/ssc/sscPublicInfo.do?method=viewUserZjInfo&userId=<%=userId%>',this)>�н���¼
	                 </td>
	               </tr>
	             </table>
	           </td>
			   <td>
	             <table>
	               <tr><td align=center colspan=2>11ѡ5</td></tr>
	               <tr>
	                 <td id="tdSxwTz" onClick="linkPlat('/sxw/sxwPublicInfo.do?method=viewUserTzInfo&userId=<%=userId%>',this)">Ͷע��¼|
	                 </td>
	                 <td id="tdSxwZj" onClick="linkPlat('/sxw/sxwPublicInfo.do?method=viewUserZjInfo&userId=<%=userId%>',this)">�н���¼
	                 </td>
	               </tr>
	             </table>
	           </td>
			   <td>
	             <table>
	               <tr><td align=center colspan=2>����ʮ��</td></tr>
	               <tr>
	                 <td id="tdExbTz" onClick="linkPlat('/exb/exbPublicInfo.do?method=viewUserTzInfo&userId=<%=userId%>',this)">Ͷע��¼|
	                 </td>
	                 <td id="tdExbZj" onClick="linkPlat('/exb/exbPublicInfo.do?method=viewUserZjInfo&userId=<%=userId%>',this)">�н���¼
	                 </td>
	               </tr>
	             </table>
	           </td>
	        </tr>
	      </table>
	    </td>
	    <td width="1"></td>
	    </tr>
    </table>

<table width="80%" border="1" align="center" cellpadding="0" cellspacing="0">
<tr>
<td>
  <iframe name="fraplat" id="fraplat" frameborder="0" width="100%" height="470"  scrolling="yes" src="/servlet/servuserinfo?mark=spuseinf"></iframe>  
</td>
</tr>
</table>

</center>


</body>
</html>