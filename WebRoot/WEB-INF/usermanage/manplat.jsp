<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true" import="beanpac.ServerTime"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>管理平台</title>
<script Language="JavaScript">
function linkPlat(loc,obj){
  document.frames['fraplat'].location.href=loc;
  
  var arrMark=new Array("user","pok","lau","pth","hap","bul","ssc","sxw","exb");
  for(var i=0;i<arrMark.length;i++){
	if(obj.id==arrMark[i]){
	  obj.style.color="yellow";
	}else{
	  document.all(arrMark[i]).style.color="white";
	}
  }
}
</script>
<style type="text/css">
body,td,th { font-size:13px;}
ul { list-style:none;}
.image1 a:link,.image1 a:visited { color:#000; text-decoration:none}
.image1 a:hover,.image1 a:active { color:#FF0000; text-decoration:underline;}
.image1 { background: url(/pkimages/mainimages/layout.gif) repeat-x top;}
.image2 { padding-top:2px; padding-left:20px;}
.image2 td { color:#FFF;margin:20px;cursor:hand}
.image2 .image2 a:visited { color:#FFF; font-weight:bold; margin:0 2px 0 5px; text-decoration:none;}
.image2 a:active,.image2 a:hover { color:#FFFF00;} 
.image6 { background: url(/pkimages/mainimages/indimage6.gif) repeat-x top;margin:0px;}
</style>
</head>

<body>
<table width="933" border="0" align="center" cellpadding="0" cellspacing="0" class="image1">
  <tr>
  <td height=192 ></td>
  </tr>
  <tr>
  <td>
    <table width="933" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-bottom:3px;">
	    <tr>
	    <td width="1"><img src="/pkimages/mainimages/img1.gif" /></td>
	    <td background="/pkimages/mainimages/img3.gif" class="image2">
	      <table>
	        <tr>
	           <td id="user" onClick=linkPlat('/servlet/userpageshow',this)>用户操作|</td>
	           <td id="pok" onClick=linkPlat('/servlet/loginmaninit?play=pok',this)>快乐扑克|</td>
	           <td id="lau" onClick=linkPlat('/servlet/loginmaninit?play=lau',this)>时 时 乐|</td>
	           <td id="pth" onClick=linkPlat('/servlet/loginmaninit?play=pth',this)>排 列 三|</td>
	           <td id="hap" onClick=linkPlat('/servlet/loginmaninit?play=hap',this)>福 彩 3D|</td>			   
	           <td id="ssc" onClick=linkPlat('/ssc/sscManage.do?method=showPage',this)>时 时 彩|</td>			   
	           <td id="sxw" onClick=linkPlat('/sxw/sxwManage.do?method=showPage',this)>11 选 5 |</td>
	           <td id="exb" onClick=linkPlat('/exb/exbManage.do?method=showPage',this)>快乐十分 |</td>
	           <td id="bul" onClick=linkPlat('/servlet/loginmaninit?play=bul',this)>信息修改|</td>
	        </tr>
	      </table>
	    </td>
	    <td width="1"><img src="/pkimages/mainimages/img2.gif" /></td>
	    </tr>
    </table>
  </td>
  </tr>
</table>

<table width="927" border="1" align="center" cellpadding="0" cellspacing="0">
<tr>
<td>
  <iframe name="fraplat" id="fraplat" frameborder="0" width="933" height="470"  scrolling="yes" src="/servlet/userpageshow"></iframe>  
</td>
</tr>
</table>

</body>
</html>