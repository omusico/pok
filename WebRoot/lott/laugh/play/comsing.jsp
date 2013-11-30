<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>华彩在线-彩票投注网</title>
<link href="/pokercss/mainlaugh.css" rel="stylesheet" type="text/css" />

<script src="/pokerfunc/laughfunc/lcom.js"></script>
<script src="/pokerfunc/global.js"></script>

<style type="text/css">
.plare { background:url(/pkimages/laughimg/lauplare.gif) no-repeat center; text-align:center; font-weight:bold; cursor:hand; color:#7f7f7f; padding-bottom:0px;}
.plach { background:url(/pkimages/laughimg/lauplach.gif) no-repeat center; text-align:center; font-weight:bold; cursor:hand; color:black; padding-bottom:0px;}
</style>

</head>
<body>

<form name="formwager" action="/servlet/lauwager" method="post">
<input type="hidden" name="typepagemark" value="comsing">

<input type="hidden" name="markiss">
<input type="hidden" name="issuetype">
<input type="hidden" name="getissuenumber">
<input type="hidden" name="selling" value="false">

<input type="hidden" name="playtype" value="单选三">
<input type="hidden" name="playmode" value="单式">
<input type="hidden" name="wagertotal" value="">
<input type="hidden" name="wagernum" value="0">

<table width=600 border="0" cellspacing="0" cellpadding="5" bordercolordark="#FFFFFF" bordercolorlight="#cccccc" style="border:#1890a8 solid; border-width:0 1px;">
<tr>
  <td align="center" colspan=2><b>选择号码：</b></td>
</tr>
<tr>
 <td width=600  id="tdchosentog"  bgcolor="FCDDF6" style="padding-left:20px" disabled>
    <SELECT id="place" NAME="place" onChange="clearRadio()">
		<OPTION VALUE="hundred" selected>百位
		<OPTION VALUE="ten">十位
		<OPTION VALUE="one">个位	
	</SELECT>
     <input id="method" name="method" type="radio" value="all" onClick="chPlTog(this)">全
     <input id="method" name="method" type="radio" value="major" onClick="chPlTog(this)">大
     <input id="method" name="method" type="radio" value="minor" onClick="chPlTog(this)">小
     <input id="method" name="method" type="radio" value="odd" onClick="chPlTog(this)">奇
     <input id="method" name="method" type="radio" value="even" onClick="chPlTog(this)">偶
     <input id="method" name="method" type="radio" value="empty" onClick="chPlTog(this)">空
 </td>
</tr>
<tr>
  <td bgcolor="#FFFFDD" colspan=2>
    <table height=130 border="0" cellspacing="0" cellpadding="0">
		      <tr >
		        <td width=73 height=43 align=center>百 位
		        </td>
		        <td id="tdhundred">
		         <table border="0" cellspacing="0" cellpadding="0">
		            <tr>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="choosePlaSing('hundred',this)">0</td>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="choosePlaSing('hundred',this)">1</td>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="choosePlaSing('hundred',this)">2</td>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="choosePlaSing('hundred',this)">3</td>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="choosePlaSing('hundred',this)">4</td>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="choosePlaSing('hundred',this)">5</td>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="choosePlaSing('hundred',this)">6</td>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="choosePlaSing('hundred',this)">7</td>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="choosePlaSing('hundred',this)">8</td>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="choosePlaSing('hundred',this)">9</td>
		            </tr>
		          </table>
		         </td>
		      </tr>

		      <tr>
		        <td height=43 align=center>十 位
		        </td>
		        <td id="tdten"><table border="0" cellspacing="0" cellpadding="0">
		            <tr>
		              <td  align=center id="ten" width="66" height="43" class="plare" onClick="choosePlaSing('ten',this)">0</td>
		              <td  align=center id="ten" width="66" height="43" class="plare" onClick="choosePlaSing('ten',this)">1</td>
		              <td  align=center id="ten" width="66" height="43" class="plare" onClick="choosePlaSing('ten',this)">2</td>
		              <td  align=center id="ten" width="66" height="43" class="plare" onClick="choosePlaSing('ten',this)">3</td>
		              <td  align=center id="ten" width="66" height="43" class="plare" onClick="choosePlaSing('ten',this)">4</td>
		              <td  align=center id="ten" width="66" height="43" class="plare" onClick="choosePlaSing('ten',this)">5</td>
		              <td  align=center id="ten" width="66" height="43" class="plare" onClick="choosePlaSing('ten',this)">6</td>
		              <td  align=center id="ten" width="66" height="43" class="plare" onClick="choosePlaSing('ten',this)">7</td>
		              <td  align=center id="ten" width="66" height="43" class="plare" onClick="choosePlaSing('ten',this)">8</td>
		              <td  align=center id="ten" width="66" height="43" class="plare" onClick="choosePlaSing('ten',this)">9</td>
		            </tr>
		          </table></td>
		      </tr>
		      <tr>
		        <td height=43 align=center>个 位
		        </td>
		        <td id="tdclub"><table border="0" cellspacing="0" cellpadding="0">
		            <tr>
		              <td  align=center id="one" width="66" height="43" class="plare" onClick="choosePlaSing('one',this)">0</td>
		              <td  align=center id="one" width="66" height="43" class="plare" onClick="choosePlaSing('one',this)">1</td>
		              <td  align=center id="one" width="66" height="43" class="plare" onClick="choosePlaSing('one',this)">2</td>
		              <td  align=center id="one" width="66" height="43" class="plare" onClick="choosePlaSing('one',this)">3</td>
		              <td  align=center id="one" width="66" height="43" class="plare" onClick="choosePlaSing('one',this)">4</td>
		              <td  align=center id="one" width="66" height="43" class="plare" onClick="choosePlaSing('one',this)">5</td>
		              <td  align=center id="one" width="66" height="43" class="plare" onClick="choosePlaSing('one',this)">6</td>
		              <td  align=center id="one" width="66" height="43" class="plare" onClick="choosePlaSing('one',this)">7</td>
		              <td  align=center id="one" width="66" height="43" class="plare" onClick="choosePlaSing('one',this)">8</td>
		              <td  align=center id="one" width="66" height="43" class="plare" onClick="choosePlaSing('one',this)">9</td>
		            </tr>
		          </table></td>
		      </tr>
		    </table>
    <table width="100%" border="0" cellspacing="5" cellpadding="0" style="border-bottom:#ccc solid 1px; margin-bottom:5px;;">
      <tr>
        <td align="left">
		注数：<span id="showsinglewager" style="color:#FF0000; font-weight:bold ">0</span>
		金额：<span id="showsinglemoney" style="color:#FF0000; font-weight:bold ">￥0.00</span>
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
            <select name="pokertext" size="4" style="width=210px;" onChange="pokerTextRemove(this)"> </select>
            </td>
            <td>
            <select name="wagereachnumtext" size="4" style="width=70px;" disabled> </select>
            </td>
            <td style="padding-left:10px;"><table cellpadding="5">
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
