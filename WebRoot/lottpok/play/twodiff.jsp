<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>快乐扑克</title>
<link href="/pokercss/main.css" rel="stylesheet" type="text/css" />

<script src="/pokerfunc/global.js"></script>
<script src="/pokerfunc/pokfunc/comtotdiff.js"></script>

<style type="text/css">

.totre { background:url(/pkimages/pokerimage/totre.gif) no-repeat center; text-align:center; font-weight:bold; cursor:hand; color:black; padding-bottom:3px;}
.totch { background:url(/pkimages/pokerimage/totch.gif) no-repeat center; text-align:center; font-weight:bold; cursor:hand; color:black; padding-bottom:3px;}


.button_ssl { background:url(/pkimages/pokerimage/button_bg_01.gif); height:20px; padding-top:1px; border:#a5a9b0 solid 1px; border-bottom:#71747b solid 1px;}
.button_ssl_add { background:url(/pkimages/pokerimage/ssl_add_line.gif); height:20px; width:70px; padding-top:1px; border:#a5a9b0 solid 1px;border-bottom:#71747b solid 1px;}
.tab1 { background:url(/pkimages/pokerimage/img_tab1.gif) no-repeat; width:70px; height:24px; padding-top:4px; font-weight:bold; padding-right:3px; text-align:center; color:#fff; cursor:hand;}
.tab1 a,.tab1 a:hover,.tab1 a:visited { color:#FFF;}
.tab2 { background:url(/pkimages/pokerimage/img_tab2.gif) no-repeat; width:70px; height:24px; padding-top:4px; padding-right:3px; text-align:center; cursor:hand;}
body { background:#fff}
</style>
</head>

<body>

<form name="formwager" action="/servlet/servwager" method="post">

<input type="hidden" name="typepagemark" value="twodiff">

<input type="hidden" name="markiss">
<input type="hidden" name="issuetype">
<input type="hidden" name="getissuenumber">
<input type="hidden" name="selling" value="false">

<input type="hidden" name="playtype" value="">
<input type="hidden" name="playmode" value="">
<input type="hidden" name="wagtotbef" value="">
<input type="hidden" name="wagertotal" value="">
<input type="hidden" name="wagernum" value="0">

<table width=712 border="1" cellspacing="0" cellpadding="5" bordercolordark="#FFFFFF" bordercolorlight="#cccccc" style="border:#1890a8 solid; border-width:0 1px;">
<tr>
  <td height=40 align="center" colspan=2><b>选择号码：</b></td>
</tr>
<tr><td><span id="test"></span></td></tr>
<tr>
 <td height=40 id="tdchosentog">
     <input id="comp" name="comp" type="radio" value="sh" checked>黑桃-<font color=red>红桃</font>
     <input id="comp" name="comp" type="radio" value="sc" >黑桃-梅花
     <input id="comp" name="comp" type="radio" value="sd" >黑桃-<font color=red>方块</font>
     <input id="comp" name="comp" type="radio" value="hc" ><font color=red>红桃</font>-梅花
     <input id="comp" name="comp" type="radio" value="hd" ><font color=red>红桃</font>-<font color=red>方块</font>
     <input id="comp" name="comp" type="radio" value="cd" >梅花-<font color=red>方块</font>
 </td>
</tr>
<tr>
 <td height=40>
   <span></span>
 </td>
</tr>
<tr>
  <td bgcolor="#FFFFDD" colspan=2>
    <table height=200 border="0" cellspacing="0" cellpadding="0">
      <tr><td height=30></td></tr>
      <tr >
        <td id="tdhundred">
         <table width=600 border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td align=center  id="numtot" width="6%" height="43" class="totre" onClick="ChoTot(this)">0</td>
              <td align=center  id="numtot" width="6%" height="43" class="totre" onClick="ChoTot(this)">1</td>
              <td align=center  id="numtot" width="6%" height="43" class="totre" onClick="ChoTot(this)">2</td>
              <td align=center  id="numtot" width="6%" height="43" class="totre" onClick="ChoTot(this)">3</td>
              <td align=center  id="numtot" width="6%" height="43" class="totre" onClick="ChoTot(this)">4</td>
              <td align=center  id="numtot" width="6%" height="43" class="totre" onClick="ChoTot(this)">5</td>
              <td align=center  id="numtot" width="6%" height="43" class="totre" onClick="ChoTot(this)">6</td>
              <td align=center  id="numtot" width="6%" height="43" class="totre" onClick="ChoTot(this)">7</td>
              <td align=center  id="numtot" width="6%" height="43" class="totre" onClick="ChoTot(this)">8</td>
              <td align=center  id="numtot" width="6%" height="43" class="totre" onClick="ChoTot(this)">9</td>
              <td align=center  id="numtot" width="6%" height="43" class="totre" onClick="ChoTot(this)">10</td>
              <td align=center  id="numtot" width="6%" height="43" class="totre" onClick="ChoTot(this)">11</td>
              <td align=center  id="numtot" width="6%" height="43" class="totre" onClick="ChoTot(this)">12</td>
              
            </tr>
          </table>
         </td>
      </tr>

      <tr><td height=30></td></tr>
    </table>
    <table width="100%" border="0" cellspacing="5" cellpadding="0" style="border-bottom:#ccc solid 1px; margin-bottom:5px;;">
      <tr>
        <td align="left">
        <!-- 
		注数：<span id="showsinglewager" style="color:#FF0000; font-weight:bold ">0</span>
		金额：<span id="showsinglemoney" style="color:#FF0000; font-weight:bold ">￥0.00</span>
		 -->
		</td>
		<td align="left">
		  <input name="butplus" type="button" class="button_ssl" value="添加" onClick="wagerPlus()">
          <input name="butremove" type="button" class="button_ssl" onclick="pokerTextRemove(this)" value="删除" />
          <input name="butempty" type="button" class="button_ssl" onclick="wagerEmpty()" value="清空" />  
       </td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="3">
      <tr>
        <td><table border="0" cellspacing="0" cellpadding="0">
        
          <tr>
            <td>
            <select name="pokertext" size="4" style="width:210px;" onChange="pokerTextRemove(this)"> </select>
            </td>
            <td>
            <select name="wagereachnumtext" size="4" style="width=70px;" disabled> </select>
            </td>
            <td style="padding-left:10px; height:130">
             <table cellpadding="5">
                <tr>
                  <td>总注数：<span id="showtotalwager" style="color:#FF0000; font-weight:bold ">0</span>
                         ，总金额：<span id="showtotalmoney" style="color:#FF0000; font-weight:bold ">￥0.00</span>元
                  </td>
                </tr>
                <tr>
                  <td> 
                     <input type="submit" name="submit" value="确认投注">
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
    </table>
  </form>
 </body>
</html>