<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>华彩在线-彩票投注网-快乐时分-选4-胆拖</title>
<link href="/pokercss/mainlaugh.css" rel="stylesheet" type="text/css" />
<script src="/js/checkform.js" type="text/javascript"></script>
<script src="/js/jquery/jquery.js" type="text/javascript"></script>

<script src="/pokerfunc/global.js"></script>
<script src="/jsp/exb/js/exb_xuan4_touzhu.js"></script>
<style type="text/css">
.plare { background:url(/pkimages/permimage/plare.gif) no-repeat center; text-align:center; font-weight:bold; cursor:hand; color:#7f7f7f; padding-bottom:0px;}
.plach { background:url(/pkimages/permimage/plach.gif) no-repeat center; text-align:center; font-weight:bold; cursor:hand; color:black; padding-bottom:0px;}
</style>
<SCRIPT LANGUAGE="JavaScript">
<!--
	//提交投注信息
	function touzhuSubmit(){
		//校验
		var tempIssueNum = $("input[name='markiss']").val();
		if(tempIssueNum == null || tempIssueNum == ""){
			alert("当前没有销售信息，不能投注!");
			return;
		}
		var tempTzNum = $("input[name='wagertotal']").val();
		if(tempTzNum == null || tempTzNum == ""){
			alert("请至少选择一注要投注的号码!");
			return;
		}
		var frameZhuitou=parent.document.frames['frameZhuitou'];
		//判读追投页面是否已经刷新2012-01-03
		var ztPageCurIssueNum = frameZhuitou.document.getElementById("ztPageCurIssueNum").value;
		//alert("ztPage="+ztPageCurIssueNum+",tempIssueNum="+tempIssueNum);
		if(ztPageCurIssueNum != tempIssueNum){
			alert("请刷新页面后重新投注!");
			return false;
		}
		$("#ztPageCurIssueNum").val(ztPageCurIssueNum);

		$("#playType").attr("value","xuan4");
		$("#palyMode").attr("value","renxuandantuo");
		$("#issueType").attr("value","exb");
		
		$("#curIssueNum").attr("value","fushi");//当前发现的号
		
		//var haveZhuitou = $("#haveZhuitou").attr("value");
		//是否有追投
		var ztFlag = 0;//追投flag
		var objHaveZhuitou = frameZhuitou.document.getElementById("chestmuliss");
		if(objHaveZhuitou.checked == true){
			$("#haveZhuitou").attr("value","1");
			ztFlag = 1;
		}
		//当前期的投注注数及金额设置
		var tempTzNum =  $("#curIssueTzNum").attr("innerHTML");
		var tempTzMoney = eval(tempTzNum*2);
		$("#curTotalTzCount").attr("value",tempTzNum);
		$("#curTotalTzMoney").attr("value",tempTzMoney);

 		//总注数
		if(ztFlag == 0){
			//为0，没有追投，只取本期的投注金额和注数即可
			 $("#totalTzCount").attr("value",tempTzNum);
			 $("#totalTzMoney").attr("value",tempTzMoney);
			//alert("222="+tempTzNum+",money="+tempTzMoney);
		}else{
			var tempTzNum =  $("#curIssueTzNum").attr("innerHTML");
			$("#haveZhuitou").attr("value","1");//设置标志位为有追投
			//有追投，计算总注数，及相应的期号及倍数
			var tempZhuitouNum = frameZhuitou.document.getElementById("zhuitouTzNum").innerHTML;
			var tempZhuitouMoney = eval(tempZhuitouNum*2);
			 $("#totalTzCount").attr("value",tempZhuitouNum);
			 $("#totalTzMoney").attr("value",tempZhuitouMoney);

			//追投信息
			var ztInfo = getZhuitouIssueInfo();
			//alert("zhuitou:"+tempZhuitouNum+",info="+ztInfo);
			$("#zhuitouInfo").attr("value",ztInfo);
			$("#selling").attr("value",1);

		}
		var len = $("#touzhuInfoList option").length+1;
		//alert(len);

		formwager.action = "/exb/exbPlay.do?method=savePlayInfo";
		//进行验证
		//如果没有选择就不用
		formwager.submit();

///////////////////给表单赋值、、、、、、、、、、、
	}
//-->
</SCRIPT>
</head>
<body>

<form name="formwager"   method="post">
<input type="hidden" name="typepagemark" value="commul">

<!---------old----------->
<input type="hidden" name="markiss">
<input type="hidden" name="issuetype">
<input type="hidden" name="getissuenumber">
<input type="hidden" name="selling" value="0">


<input type="hidden" name="wagertotal" value="">
<input type="hidden" name="wagernum" value="0">
<!-------------me----------->
<input type="hidden" name="playType" id="playType" value="xuan4">
<input type="hidden" name="playMode" id="playMode" value="renxuandantuo">

<input type="hidden" name="issueType" id="issueType" value="ssc">
<input type="hidden" name="curIssueNum" id="curIssueNum" value="">

<input type="hidden" name="curTotalTzMoney" id="curTotalTzMoney" value="">
<input type="hidden" name="curTotalTzCount" id="curTotalTzCount" value="">

<input type="hidden" name="totalTzMoney" id="totalTzMoney" value="">
<input type="hidden" name="totalTzCount" id="totalTzCount" value="">

 
<input type="hidden" name="haveZhuitou" id="haveZhuitou" value="0">
<input type="hidden" name="zhuitouInfo" id="zhuitouInfo" value="">
<input type="hidden" name="isZuhe" id="isZuhe" value="1">
<input type="hidden" name="ztPageCurIssueNum" id="ztPageCurIssueNum" value="">
 

<table width="700" border="0" cellspacing="0" cellpadding="5" bordercolordark="#FFFFFF" bordercolorlight="#cccccc" style="border:#1890a8 solid; border-width:0 1px;">
<tr>
  <td align="left" colspan=2><b>选择号码：</b></td>
</tr>
<tr>
  <td bgcolor="#FFFFDD" colspan=2>
    <table height="43" border="0" cellspacing="0" cellpadding="0">
		     
			  <tr>
		        <td height="43" align="center" width="10%"><b>胆 码：</b></td>
		        <td id="tdge"><table border="0" cellspacing="0" cellpadding="0">
					<tr>
		              <td  align="center" id="shi" width="66" height="43" class="plare" onClick="chooseDantuoPlay('shi',this)">1</td>
		              <td  align="center" id="shi" width="66" height="43" class="plare" onClick="chooseDantuoPlay('shi',this)">2</td>
		              <td  align="center" id="shi" width="66" height="43" class="plare" onClick="chooseDantuoPlay('shi',this)">3</td>
		              <td  align="center" id="shi" width="66" height="43" class="plare" onClick="chooseDantuoPlay('shi',this)">4</td>
		              <td  align="center" id="shi" width="66" height="43" class="plare" onClick="chooseDantuoPlay('shi',this)">5</td>
		              <td  align="center" id="shi" width="66" height="43" class="plare" onClick="chooseDantuoPlay('shi',this)">6</td>
		              <td  align="center" id="shi" width="66" height="43" class="plare" onClick="chooseDantuoPlay('shi',this)">7</td>
		              <td  align="center" id="shi" width="66" height="43" class="plare" onClick="chooseDantuoPlay('shi',this)">8</td>
		              <td  align="center" id="shi" width="66" height="43" class="plare" onClick="chooseDantuoPlay('shi',this)">9</td>
		              <td  align="center" id="shi" width="66" height="43" class="plare" onClick="chooseDantuoPlay('shi',this)">10</td> 
			        </tr>  
					<tr>				  
		              <td  align="center" id="shi" width="66" height="43" class="plare" onClick="chooseDantuoPlay('shi',this)">11</td>
		              <td  align="center" id="shi" width="66" height="43" class="plare" onClick="chooseDantuoPlay('shi',this)">12</td>
		              <td  align="center" id="shi" width="66" height="43" class="plare" onClick="chooseDantuoPlay('shi',this)">13</td>
		              <td  align="center" id="shi" width="66" height="43" class="plare" onClick="chooseDantuoPlay('shi',this)">14</td>
		              <td  align="center" id="shi" width="66" height="43" class="plare" onClick="chooseDantuoPlay('shi',this)">15</td>
		              <td  align="center" id="shi" width="66" height="43" class="plare" onClick="chooseDantuoPlay('shi',this)">16</td>
		              <td  align="center" id="shi" width="66" height="43" class="plare" onClick="chooseDantuoPlay('shi',this)">17</td>
		              <td  align="center" id="shi" width="66" height="43" class="plare" onClick="chooseDantuoPlay('shi',this)">18</td>
		              <td  align="center" id="shi" width="66" height="43" class="plare" onClick="chooseDantuoPlay('shi',this)">19</td>
		              <td  align="center" id="shi" width="66" height="43" class="plare" onClick="chooseDantuoPlay('shi',this)">20</td> 
		            </tr>
		          </table></td>
		      </tr>
		     <tr>
		        <td height="25" colspan="2">&nbsp</td>
		      <tr>
		        <td height="43" align="center" width="10%"><b>拖 码：</b></td>
		        <td id="tdge"><table border="0" cellspacing="0" cellpadding="0">
		            <tr> 
		              <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseDantuoPlay('ge',this)">1</td>
		              <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseDantuoPlay('ge',this)">2</td>
		              <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseDantuoPlay('ge',this)">3</td>
		              <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseDantuoPlay('ge',this)">4</td>
		              <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseDantuoPlay('ge',this)">5</td>
		              <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseDantuoPlay('ge',this)">6</td>
		              <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseDantuoPlay('ge',this)">7</td>
		              <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseDantuoPlay('ge',this)">8</td>
		              <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseDantuoPlay('ge',this)">9</td>
		              <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseDantuoPlay('ge',this)">10</td> 
		            </tr>  
					<tr>
					  <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseDantuoPlay('ge',this)">11</td>
		              <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseDantuoPlay('ge',this)">12</td>
		              <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseDantuoPlay('ge',this)">13</td>
		              <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseDantuoPlay('ge',this)">14</td>
		              <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseDantuoPlay('ge',this)">15</td>
		              <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseDantuoPlay('ge',this)">16</td>
		              <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseDantuoPlay('ge',this)">17</td>
		              <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseDantuoPlay('ge',this)">18</td>
		              <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseDantuoPlay('ge',this)">19</td>
		              <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseDantuoPlay('ge',this)">20</td> 
		            </tr>  

		          </table></td>
		      </tr>
		    </table>
    <table width="100%" border="0" cellspacing="5" cellpadding="0" style="border-bottom:#ccc solid 1px; margin-bottom:5px;;">
      <tr height="43">
        <td align="left">
		注数：<span id="clickTzNum" style="color:#FF0000; font-weight:bold ">0</span>
		金额：<span id="clickTzMoney" style="color:#FF0000; font-weight:bold ">￥0.00</span>
		</td>
		<td align="left">
		  <input name="buttonPlus" id="buttonPlus" type="button" class="button_ssl" value="添加" onClick="xuanerDantuoPlug()">
          <input name="buttonRemove" id="buttonRemove" type="button" class="button_ssl" onclick="sscTextRemove(this)" value="删除" />
          <input name="buttonEmpty" id="buttonEmpty" type="button" class="button_ssl" onclick="wagerEmpty()" value="清空" />  
       </td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="3">
      <tr>
      
        <td>
          <input id="randone" name="randone" type="button" class="button_ssl" value="机选一注" onClick="jixuanRandSelect(1)">
          <input id="randfive" name="randfive" type="button" class="button_ssl" value="机选五注" onClick="jixuanRandSelect(5)">
          <input id="randten" name="randten" type="button" class="button_ssl" value="机选十注" onClick="jixuanRandSelect(10)">
          </td>
   
      </tr>
      <tr>
        <td><table border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td>
            <select name="touzhuInfoList" size="6" style="width=210px;" multiple="multiple" onChange="sscTextRemove(this)"> </select>
            </td>
            <td>
            <select name="touzhuNumList" size="6" style="width=70px;" disabled> </select>
            </td>
            <td style="padding-left:10px;"><table cellpadding="5">
                <tr>
                  <td>总注数：<span id="curIssueTzNum"  name="curIssueTzNum" style="color:#FF0000; font-weight:bold ">0</span>
                         ，总金额：<span id="curIssueTzMoney" style="color:#FF0000; font-weight:bold ">￥0.00</span>元
                  </td>
                </tr>
                <tr>
                  <td> 
                     <input type="button" name="submitTouzhu" value="确认投注" onclick="touzhuSubmit()">
                      <font color="red"> 
						 <%String strRem=(String)request.getAttribute("wagrem");
                         if(strRem==null){strRem="";}%>
                         <span id="subretshow"><%=strRem %></span>
                         <input type="hidden" name="subreturn" value="<%=strRem %>">

                      </font>
                  </td>
                </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
    </table>
    </td>

    </tr>
 <!------------倍投------------->
<!--	 
   <tr>     
        <td>
			<input type="checkbox" name="chestmuliss" onClick="switchMulIss()">对本期进行倍注&nbsp;&nbsp;&nbsp;&nbsp;
倍投数：

			<input type="button" name="mulTimesSub" id="mulTimesSub" onclick="sub_sum()" value=" - " disabled>
			<input type="text" name="readMulTimes" id="readMulTimes" value="1" size="5" readonly>
			<input type="hidden" name="mulTimes" id="mulTimes" value="1" >

			<input type="button" name="mulTimesPlus" id="mulTimesPlus" onclick="plus_sum()" value=" + " disabled>
		</td>    
      </tr>
	  <tr>     
        <td> 
			总注数为：<span id="divIssueTzNum" style="color:red; font-weight:bold "></span>
            总金额为：<span id="divIssueTzMoney" style="color:red; font-weight:bold "></span>元       
          </td>    
      </tr>

		<input type="hidden" name="tempIssuetype" value="">
		<input type="hidden" name="tempGetissuenumber"  value="">
-->
<SCRIPT LANGUAGE="JavaScript">
<!--
		//初始化标志位
    /*
    var frMulIssue=parent.document.frames['fratime'];
    if(frMulIssue.document.readyState=='complete'){
		document.all("tempIssuetype").value="singleissue";
		document.all("tempGetissuenumber").value=timeFrm.document.all("currentissueshow").innerHTML;
	}
*/
//-->
</SCRIPT>

	  <!------------倍投 end ------------->    </table>
  </form>
</body>
</html>
