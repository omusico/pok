<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>华彩在线-彩票投注网</title>
<link href="/pokercss/mainlaugh.css" rel="stylesheet" type="text/css" />

<script src="/pokerfunc/global.js"></script>
<script src="/jsp/ssc/js/ssc_typetitle.js"></script>

<style type="text/css">
.tab1 {cursor:hand;}
.typeready { background:url(/pkimages/pokerimage/img_tab2.gif) no-repeat center; text-align:center; cursor:hand}
.typechosen { background:url(/pkimages/pokerimage/img_tab1.gif) no-repeat center; text-align:center; cursor:hand}
</style>

</head>
<body>

<table width="100%" height="30" border="0" cellpadding="0" cellspacing="0" style="border-bottom:#6A80F5 solid 2px">
   <tr>
	<td id="wuxingTx" align=center width="10%" class='typechosen' style="color:white" onClick="changeType('wuxing_tx_mode','wuxing_tx_play','fushi',this)" nowrap>五星通选</td>
	<td id="wuxing" align=center width="6%" class='typeready'  onClick="changeType('wuxing_mode','wuxing_play_fushi','fushi',this)">
		五星
	</td>
	<td id="wuxingZuxuan" align=center width="9%" class='typeready'  onClick="changeType('wuxing_zuxuan_mode','wuxing_zuxuan_play_zu120','fushi',this)" nowrap>
		五星组选
	</td>
	<td id="sixing" align=center width="6%" class='typeready'  onClick="changeType('sixing_mode','sixing_play_fushi','fushi',this)">
		四星
	</td>
	<td id="sixingZuxuan" align=center width="9%" class='typeready'  onClick="changeType('sixing_zuxuan_mode','sixing_zuxuan_play_zu24','fushi',this)" nowrap>四星组选</td>
	<td id="sanxingZhixuan" align=center width="8%" class='typeready'  onClick="changeType('sanxing_zhixuan_mode','sanxing_zhixuan_play_fushi','fushi',this)" nowrap>三星直选</td>
	<td id="sanxingZuxuan" align=center width="8%" class='typeready'  onClick="changeType('sanxing_zuxuan_mode','sanxing_zuxuan_play_zu3','zuhe',this)" nowrap>三星组选</td>
	<td id="erxing" align=center width="7%" class='typeready'  onClick="changeType('erxing_mode','erxing_zhixuan_play_fushi','fushi',this)">二星</td>
<!--	<td id="erxingZuxuan" align=center width="10%" class='typeready'  onClick="changeType('modtitgr','grmul','gr4',this)">二星组选</td>-->
	<td id="yixing" align=center width="6%" class='typeready'  onClick="changeType('yixing_mode','yixing_zhixuan_play_fushi','fushi',this)">一星</td>
	<td id="zuhe" align=center width="7%" class='typeready'  onClick="changeType('zuhe_mode','zuhe_play_wuxing','zuhe',this)">组合</td> 
	<td id="daxiaoDs" align=center width="6%" class='typeready'  onClick="changeType('dxds_mode','dxds_play_fushi','fushi',this)" nowrap>大小单双</td> 
	<td id="renxuan" align=center width="6%" class='typeready'  onClick="changeType('renxuan_mode','renxuan1','fushi',this)" >任选</td> 
 
    </tr>
  </table>
     
</body>
</html>
