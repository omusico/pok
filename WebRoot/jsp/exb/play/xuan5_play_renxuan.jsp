<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>��������-��ƱͶע��-����ʱ��-ѡ5-��ʽ</title>
<link href="/pokercss/mainlaugh.css" rel="stylesheet" type="text/css" />
<script src="/js/checkform.js" type="text/javascript"></script>
<script src="/js/jquery/jquery.js" type="text/javascript"></script>

<script src="/pokerfunc/global.js"></script>
<script src="/jsp/exb/js/exb_xuan5_touzhu.js"></script>
<style type="text/css">
.plare { background:url(/pkimages/permimage/plare.gif) no-repeat center; text-align:center; font-weight:bold; cursor:hand; color:#7f7f7f; padding-bottom:0px;}
.plach { background:url(/pkimages/permimage/plach.gif) no-repeat center; text-align:center; font-weight:bold; cursor:hand; color:black; padding-bottom:0px;}
</style>
<SCRIPT LANGUAGE="JavaScript">
<!--
	//�ύͶע��Ϣ
	function touzhuSubmit(){
		//У��
		var tempIssueNum = $("input[name='markiss']").val();
		if(tempIssueNum == null || tempIssueNum == ""){
			alert("��ǰû��������Ϣ������Ͷע!");
			return;
		}
		var tempTzNum = $("input[name='wagertotal']").val();
		if(tempTzNum == null || tempTzNum == ""){
			alert("������ѡ��һעҪͶע�ĺ���!");
			return;
		}
		var frameZhuitou=parent.document.frames['frameZhuitou'];
		//�ж�׷Ͷҳ���Ƿ��Ѿ�ˢ��2012-01-03
		var ztPageCurIssueNum = frameZhuitou.document.getElementById("ztPageCurIssueNum").value;
		//alert("ztPage="+ztPageCurIssueNum+",tempIssueNum="+tempIssueNum);
		if(ztPageCurIssueNum != tempIssueNum){
			alert("��ˢ��ҳ�������Ͷע!");
			return false;
		}
		$("#ztPageCurIssueNum").val(ztPageCurIssueNum);

		$("#playType").attr("value","xuan5");
		$("#palyMode").attr("value","renxuan");
		$("#issueType").attr("value","exb");
		
		$("#curIssueNum").attr("value","fushi");//��ǰ���ֵĺ�
		
		//var haveZhuitou = $("#haveZhuitou").attr("value");
		//�Ƿ���׷Ͷ
		var ztFlag = 0;//׷Ͷflag
		var objHaveZhuitou = frameZhuitou.document.getElementById("chestmuliss");
		if(objHaveZhuitou.checked == true){
			$("#haveZhuitou").attr("value","1");
			ztFlag = 1;
		}
		//��ǰ�ڵ�Ͷעע�����������
		var tempTzNum =  $("#curIssueTzNum").attr("innerHTML");
		var tempTzMoney = eval(tempTzNum*2);
		$("#curTotalTzCount").attr("value",tempTzNum);
		$("#curTotalTzMoney").attr("value",tempTzMoney);

 		//��ע��
		if(ztFlag == 0){
			//Ϊ0��û��׷Ͷ��ֻȡ���ڵ�Ͷע����ע������
			 $("#totalTzCount").attr("value",tempTzNum);
			 $("#totalTzMoney").attr("value",tempTzMoney);
			//alert("222="+tempTzNum+",money="+tempTzMoney);
		}else{
			var tempTzNum =  $("#curIssueTzNum").attr("innerHTML");
			$("#haveZhuitou").attr("value","1");//���ñ�־λΪ��׷Ͷ
			//��׷Ͷ��������ע��������Ӧ���ںż�����
			var tempZhuitouNum = frameZhuitou.document.getElementById("zhuitouTzNum").innerHTML;
			var tempZhuitouMoney = eval(tempZhuitouNum*2);
			 $("#totalTzCount").attr("value",tempZhuitouNum);
			 $("#totalTzMoney").attr("value",tempZhuitouMoney);

			//׷Ͷ��Ϣ
			var ztInfo = getZhuitouIssueInfo();
			//alert("zhuitou:"+tempZhuitouNum+",info="+ztInfo);
			$("#zhuitouInfo").attr("value",ztInfo);
			$("#selling").attr("value",1);

		}
		var len = $("#touzhuInfoList option").length+1;
		//alert(len);

		formwager.action = "/exb/exbPlay.do?method=savePlayInfo";
		//������֤
		//���û��ѡ��Ͳ���
		formwager.submit();

///////////////////������ֵ����������������������
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
<input type="hidden" name="playType" id="playType" value="xuan5">
<input type="hidden" name="playMode" id="playMode" value="renxuan">

<input type="hidden" name="issueType" id="issueType" value="ssc">
<input type="hidden" name="curIssueNum" id="curIssueNum" value="">

<input type="hidden" name="curTotalTzMoney" id="curTotalTzMoney" value="">
<input type="hidden" name="curTotalTzCount" id="curTotalTzCount" value="">

<input type="hidden" name="totalTzMoney" id="totalTzMoney" value="">
<input type="hidden" name="totalTzCount" id="totalTzCount" value="">

 
<input type="hidden" name="haveZhuitou" id="haveZhuitou" value="0">
<input type="hidden" name="zhuitouInfo" id="zhuitouInfo" value="">
<input type="hidden" name="isZuhe" id="isZuhe" value="0">
<input type="hidden" name="ztPageCurIssueNum" id="ztPageCurIssueNum" value="">
 

<table width="700" border="0" cellspacing="0" cellpadding="5" bordercolordark="#FFFFFF" bordercolorlight="#cccccc" style="border:#1890a8 solid; border-width:0 1px;">
<tr>
  <td align="left" colspan=2><b>ѡ����룺</b></td>
</tr>
	<tr>
	 <td width="100%"  id="tdchosentog"  bgcolor="FCDDF6" style="padding-left:20px" colspan="2">
		
		 <input id="method" name="method" type="radio" value="all" onClick="choosePlayByRadio(this)">ȫ
		 <input id="method" name="method" type="radio" value="major" onClick="choosePlayByRadio(this)">��
		 <input id="method" name="method" type="radio" value="minor" onClick="choosePlayByRadio(this)">С
		 <input id="method" name="method" type="radio" value="odd" onClick="choosePlayByRadio(this)">��
		 <input id="method" name="method" type="radio" value="even" onClick="choosePlayByRadio(this)">ż
		 <input id="method" name="method" type="radio" value="empty" onClick="choosePlayByRadio(this)">��
	 </td>
	</tr>

			<tr>
		        <td height=43 align="center">&nbsp;
		        </td>
		        <td id="tdge"><table border="0" cellspacing="0" cellpadding="0">
		            <tr>
		              <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseFushiPlay('ge',this)">1</td>
		              <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseFushiPlay('ge',this)">2</td>
		              <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseFushiPlay('ge',this)">3</td>
		              <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseFushiPlay('ge',this)">4</td>
		              <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseFushiPlay('ge',this)">5</td>
		              <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseFushiPlay('ge',this)">6</td>
		              <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseFushiPlay('ge',this)">7</td>
		              <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseFushiPlay('ge',this)">8</td>
		              <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseFushiPlay('ge',this)">9</td>
		              <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseFushiPlay('ge',this)">10</td> 
		            </tr>
					<tr>
					  <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseFushiPlay('ge',this)">11</td>
		              <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseFushiPlay('ge',this)">12</td>
		              <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseFushiPlay('ge',this)">13</td>
		              <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseFushiPlay('ge',this)">14</td>
		              <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseFushiPlay('ge',this)">15</td>
		              <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseFushiPlay('ge',this)">16</td>
		              <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseFushiPlay('ge',this)">17</td>
		              <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseFushiPlay('ge',this)">18</td>
		              <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseFushiPlay('ge',this)">19</td>
		              <td  align="center" id="ge" width="66" height="43" class="plare" onClick="chooseFushiPlay('ge',this)">20</td> 
		            </tr> 
		          </table></td>
		      </tr>
		    </table>
    <table width="100%" border="0" cellspacing="5" cellpadding="0" style="border-bottom:#ccc solid 1px; margin-bottom:5px;;">
      <tr>
        <td align="left">
		ע����<span id="clickTzNum" style="color:#FF0000; font-weight:bold ">0</span>
		��<span id="clickTzMoney" style="color:#FF0000; font-weight:bold ">��0.00</span>
		</td>
		<td align="left">
		  <input name="buttonPlus" id="buttonPlus" type="button" class="button_ssl" value="���" onClick="wagerPlus()">
          <input name="buttonRemove" id="buttonRemove" type="button" class="button_ssl" onclick="sscTextRemove(this)" value="ɾ��" />
          <input name="buttonEmpty" id="buttonEmpty" type="button" class="button_ssl" onclick="wagerEmpty()" value="���" />  
       </td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="3">
      <tr>
      
        <td>
          <input id="randone" name="randone" type="button" class="button_ssl" value="��ѡһע" onClick="randSelect(1)">
          <input id="randfive" name="randfive" type="button" class="button_ssl" value="��ѡ��ע" onClick="randSelect(5)">
          <input id="randten" name="randten" type="button" class="button_ssl" value="��ѡʮע" onClick="randSelect(10)">
          </td>
   
      </tr>
      <tr>
        <td><table border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td>
            <select name="touzhuInfoList" size="4" style="width=210px;" multiple="multiple" onChange="sscTextRemove(this)"> </select>
            </td>
            <td>
            <select name="touzhuNumList" size="4" style="width=70px;" disabled> </select>
            </td>
            <td style="padding-left:10px;"><table cellpadding="5">
                <tr>
                  <td>��ע����<span id="curIssueTzNum"  name="curIssueTzNum" style="color:#FF0000; font-weight:bold ">0</span>
                         ���ܽ�<span id="curIssueTzMoney" style="color:#FF0000; font-weight:bold ">��0.00</span>Ԫ
                  </td>
                </tr>
                <tr>
                  <td> 
                     <input type="button" name="submitTouzhu" value="ȷ��Ͷע" onclick="touzhuSubmit()">
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
 <!------------��Ͷ------------->
<!--	 
   <tr>     
        <td>
			<input type="checkbox" name="chestmuliss" onClick="switchMulIss()">�Ա��ڽ��б�ע&nbsp;&nbsp;&nbsp;&nbsp;
��Ͷ����

			<input type="button" name="mulTimesSub" id="mulTimesSub" onclick="sub_sum()" value=" - " disabled>
			<input type="text" name="readMulTimes" id="readMulTimes" value="1" size="5" readonly>
			<input type="hidden" name="mulTimes" id="mulTimes" value="1" >

			<input type="button" name="mulTimesPlus" id="mulTimesPlus" onclick="plus_sum()" value=" + " disabled>
		</td>    
      </tr>
	  <tr>     
        <td> 
			��ע��Ϊ��<span id="divIssueTzNum" style="color:red; font-weight:bold "></span>
            �ܽ��Ϊ��<span id="divIssueTzMoney" style="color:red; font-weight:bold "></span>Ԫ       
          </td>    
      </tr>

		<input type="hidden" name="tempIssuetype" value="">
		<input type="hidden" name="tempGetissuenumber"  value="">
-->
<SCRIPT LANGUAGE="JavaScript">
<!--
		//��ʼ����־λ
    /*
    var frMulIssue=parent.document.frames['fratime'];
    if(frMulIssue.document.readyState=='complete'){
		document.all("tempIssuetype").value="singleissue";
		document.all("tempGetissuenumber").value=timeFrm.document.all("currentissueshow").innerHTML;
	}
*/
//-->
</SCRIPT>

	  <!------------��Ͷ end ------------->    </table>
  </form>
</body>
</html>
