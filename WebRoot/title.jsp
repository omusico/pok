<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>华彩在线-彩票投注网</title>
<link href="/pokercss/main.css" rel="stylesheet" type="text/css" />

<style type="text/css">
.image1 a:link,.image1 a:visited { color:#00F; text-decoration:none}
.image1 a:hover,.image1 a:active { color:#FF0000; text-decoration:underline;}
.image1 { background: url(/pkimages/mainimages/layout.gif) repeat-x top;}
.image2 { padding-top:2px; padding-left:20px;}
.image2 li { float:left; color:#FFF;margin:0 20px; cursor:hand}
.image2 a:link,.image2 a:visited { color:#FFF; font-weight:bold; margin:0px; text-decoration:none;}
.image2 a:active,.image2 a:hover { color:#FFFF00;}
</style>

<script language="javascript">
function navigate(strL){
  parent.location.href=strL;
}
function highLabel(){
	  var playTit=parent.document.all("pagemark").value;
	  var arrPage=new Array("indexmain","happy","laugh","pth","ssc","sxw","exb");
	  var arrMark=new Array("pok","hap","lau","pth","ssc","sxw","exb");
	  for(var i=0;i<arrPage.length;i++){
		  if(playTit==arrPage[i]){
		    document.all(arrMark[i]).style.color="yellow";
		    break;
		  }
	  }
}
</script> 

</head>
<body onload=highLabel()>
<table width="920" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
   <td height=190 class="image1">
   </td>
  </tr>
  <tr>
  <td>
    <table width="920" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-bottom:3px;">
    <tr>
    <td width="1"><img src="/pkimages/mainimages/img1.gif" /></td>
    <td background="/pkimages/mainimages/img3.gif" class="image2">
      <ul>
        <li id="pok" style="" onClick="navigate('/servlet/entrymain?type=indexmain')">快 乐 扑 克|</li>        
        <li id="pth" style="" onClick="navigate('/servlet/entrymain?type=pth')">排 列 三|</li>        
        <li id="lau" style="" onClick="navigate('/servlet/entrymain?type=laugh')">时 时 乐|</li>        
        <li id="hap" style="" onClick="navigate('/servlet/entrymain?type=happy')">福 彩 3D|</li>
 		<li id="ssc" style="" onClick="navigate('/ssc/sscPublicInfo.do?method=showIndex')">时 时 彩|</li>
		<li id="sxw" style="" onClick="navigate('/sxw/sxwPublicInfo.do?method=showIndex')">11 选 5|</li>
		<li id="exb" style="" onClick="navigate('/exb/exbPublicInfo.do?method=showIndex')">快乐十分|</li>
       </ul>
    </td>
    <td width="1"><img src="/pkimages/mainimages/img2.gif" /></td>
    </tr>
    </table>
  </td>
  </tr>
</table>
</body>
</html>
