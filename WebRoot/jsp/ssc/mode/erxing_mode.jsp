<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>��������-��ƱͶע��-����</title>
<link href="/pokercss/mainlaugh.css" rel="stylesheet" type="text/css" />

<script src="/jsp/ssc/js/ssc_typetitle.js"></script>

<style type="text/css">
.modeready { background:url(/pkimages/pokerimage/modeready.gif) no-repeat center; text-align:center; cursor:hand}
.modechosen { background:url(/pkimages/pokerimage/modechosen.gif) no-repeat center; text-align:center; cursor:hand}


</style>
</head>
<body>

<table width="100%" height=30 border="0">
  <tr>
		<td width="10%" align="right" valign="middle"><b>ֱѡ��</b></td>
	    <td width="35%">
			<input name="radiobutton" type="radio" value="radiobutton" checked onClick="chMode('erxing_zhixuan_play_fushi')">��ʽ
				<input name="radiobutton" type="radio" value="radiobutton"  onClick="chMode('erxing_zhixuan_play_danshi')">��ʽ
	</td>
 		<td width="10%" align="right"  valign="middle"><b>��ѡ��</b></td>

		<td width="40%">
			<input name="radiobutton" type="radio" value="radiobutton"  onClick="chMode('erxing_zuxuan_play_fushi')">��ʽ
			<input name="radiobutton" type="radio" value="radiobutton"  onClick="chMode('erxing_zuxuan_play_danshi')">��ʽ
				<input name="radiobutton" type="radio" value="radiobutton"  onClick="chMode('erxing_zuxuan_play_hezhi')">��ֵ
	</td>
   </tr>
</table>
</body>
</html>
