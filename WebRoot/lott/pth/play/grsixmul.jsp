<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>��������-��ƱͶע��-������</title>
<link href="/pokercss/mainlaugh.css" rel="stylesheet" type="text/css" />

<script src="/pokerfunc/pthfunc/pthgr.js"></script>
<script src="/pokerfunc/global.js"></script>
<script src="/pokerfunc/pthfunc/pthmuti.js"></script>
<style type="text/css">
.plare { background:url(/pkimages/permimage/plare.gif) no-repeat center; text-align:center; font-weight:bold; cursor:hand; color:#7f7f7f; padding-bottom:0px;}
.plach { background:url(/pkimages/permimage/plach.gif) no-repeat center; text-align:center; font-weight:bold; cursor:hand; color:black; padding-bottom:0px;}
</style>

</head>
<body>

<form name="formwager" action="/servlet/pthwager" method="post">
<input type="hidden" name="typepagemark" value="grsixmul">

<input type="hidden" name="markiss">
<input type="hidden" name="issuetype">
<input type="hidden" name="getissuenumber">
<input type="hidden" name="selling" value="false">

<input type="hidden" name="playtype" value="��ѡ">
<input type="hidden" name="playmode" value="��6">
<input type="hidden" name="wagertotal" value="">
<input type="hidden" name="wagernum" value="0">

<table width=600 border="0" cellspacing="0" cellpadding="5" bordercolordark="#FFFFFF" bordercolorlight="#cccccc" style="border:#1890a8 solid; border-width:0 1px;">
<tr>
  <td align="center" colspan=2><b>ѡ����룺</b></td>
</tr>
<tr>
 <td width=600  id="tdchosentog"  bgcolor="FCDDF6" style="padding-left:20px">
 </td>
</tr>
<tr>
  <td bgcolor="#FFFFDD" colspan=2>
    <table height=130 border="0" cellspacing="0" cellpadding="0">
		      <tr >
		        <td width=10 height=43 align=center>
		        </td>
		        <td id="tdhundred">
		         <table border="0" cellspacing="0" cellpadding="0">
		            <tr>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="mulCho(this)">0</td>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="mulCho(this)">1</td>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="mulCho(this)">2</td>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="mulCho(this)">3</td>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="mulCho(this)">4</td>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="mulCho(this)">5</td>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="mulCho(this)">6</td>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="mulCho(this)">7</td>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="mulCho(this)">8</td>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="mulCho(this)">9</td>
		            </tr>
		          </table>
		         </td>
		      </tr>
		    </table>
    <table width="100%" border="0" cellspacing="5" cellpadding="0" style="border-bottom:#ccc solid 1px; margin-bottom:5px;;">
      <tr>
        <td align="left">
		ע����<span id="showsinglewager" style="color:#FF0000; font-weight:bold ">0</span>
		��<span id="showsinglemoney" style="color:#FF0000; font-weight:bold ">��0.00</span>
		</td>
		<td align="left">
		  <input name="butplus" type="button" class="button_ssl" value="���" onClick="wagerPlus()">
          <input name="butremove" type="button" class="button_ssl" onclick="pokerTextRemove(this)" value="ɾ��" />
          <input name="butempty" type="button" class="button_ssl" onclick="wagerEmpty()" value="���" />  
       </td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="3">
      <tr>
        <td><table border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td>
            <select name="pokertext" size="4" style="width=210px;" onChange="pokerTextRemove(this)"> </select>
            </td>
            <td>
            <select name="wagereachnumtext" size="4" style="width=70px;" disabled> </select>
            </td>
            <td style="padding-left:10px;">
              <table cellpadding="5">
                <tr>
                  <td>��ע����<span id="showtotalwager" style="color:#FF0000; font-weight:bold ">0</span>
                         ���ܽ�<span id="showtotalmoney" style="color:#FF0000; font-weight:bold ">��0.00</span>Ԫ
                  </td>
                </tr>
                <tr>
                  <td> 
                     <input type="submit" name="submit" value="ȷ��Ͷע">
                      <font color="red">
                        <%String strRem=(String)request.getAttribute("wagrem");
                         if(strRem!=null){out.println(strRem);}%>
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
	   <tr>     
        <td>
			<input type="checkbox" name="chestmuliss" onClick="switchMulIss()">�Ա��ڽ��б�ע&nbsp;&nbsp;&nbsp;&nbsp;
��Ͷ����<!--<input id="mulTimesOld" name="mulTimesOld" type="text" size="3"  value="1" disabled onchange="changeVal()"  onkeypress="checkNumeric(this.value)">   -->

			<input type="button" name="mulTimesSub" id="mulTimesSub" onclick="sub_sum()" value=" - " disabled>
			<input type="text" name="readMulTimes" id="readMulTimes" value="1" size="5" readonly>
			<input type="hidden" name="mulTimes" id="mulTimes" value="1" >

			<input type="button" name="mulTimesPlus" id="mulTimesPlus" onclick="plus_sum()" value=" + " disabled>
		</td>    
      </tr>
	  <tr>     
        <td> 
			��ע��Ϊ��<span id="curisswagnum" style="color:red; font-weight:bold "></span>
            �ܽ��Ϊ��<span id="curissmon" style="color:red; font-weight:bold "></span>Ԫ       
          </td>    
      </tr>
		<input type="hidden" name="tempIssuetype" value="">
		<input type="hidden" name="tempGetissuenumber"  value="">
<SCRIPT LANGUAGE="JavaScript">
<!--
		//��ʼ����־λ
    var frMulIssue=parent.document.frames['fratime'];
    if(frMulIssue.document.readyState=='complete'){
		document.all("tempIssuetype").value="singleissue";
		document.all("tempGetissuenumber").value=timeFrm.document.all("currentissueshow").innerHTML;
	}

//-->
</SCRIPT>

	  <!------------��Ͷ end ------------->
    </table>
  </form>
</body>
</html>
