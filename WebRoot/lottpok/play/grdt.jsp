<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>华彩在线-彩票投注网</title>
<link href="/pokercss/mainlaugh.css" rel="stylesheet" type="text/css" />

<script src="/pokerfunc/pokfunc/groupdt.js"></script>
<script src="/pokerfunc/pokfunc/pubgroup.js"></script>
<script src="/pokerfunc/global.js"></script>

<style type="text/css">
.voidre { background:url(/pkimages/pokerimage/voidre.gif) no-repeat center; text-align:center; font-weight:bold; cursor:hand; color:#FF0000; padding-bottom:10px;}
.voidch { background:url(/pkimages/pokerimage/voidch.gif) no-repeat center; text-align:center; font-weight:bold; cursor:hand; color:#FF0000; padding-bottom:10px;}
</style>

</head>
<body>

<form name="formwager" action="/servlet/servwager" method="post">
<input type="hidden" name="typepagemark" value="grdt">

<input type="hidden" name="markiss">
<input type="hidden" name="issuetype">
<input type="hidden" name="getissuenumber">
<input type="hidden" name="selling" value="false">

<input type="hidden" name="playtype" value="">
<input type="hidden" name="playmode" value="">
<input type="hidden" name="wagtotbef" value="">
<input type="hidden" name="wagertotal" value="">
<input type="hidden" name="wagernum" value="0">


<table width=712 border="0" cellspacing="0" cellpadding="5" bordercolordark="#FFFFFF" bordercolorlight="#cccccc" style="border:#1890a8 solid; border-width:0 1px;">
<tr>
  <td align="center" colspan=2><b>选择号码：</b></td>
  
</tr>
<tr>
 <td width=712  height=10 id="tdchosentog"  bgcolor="FCDDF6" style="padding-left:20px"><br>
 <span id="test"></span>
 </td>
</tr>
<tr>
  <td bgcolor="#FFFFDD" colspan=2>
    <table height=160 border="0" cellspacing="0" cellpadding="0">
    <tr><td height=30></td></tr>
		      <tr >
		        <td width=70 height=63 align=center>
		        胆 码：
		        </td>
		        <td id="">
		         <table border="0" cellspacing="0" cellpadding="0">
		            <tr>
		              <td id="grdanrow" width="43" height="63" class="voidre" onClick="grDanCho(this)">A</td>
		              <td id="grdanrow" width="43" height="63" class="voidre" onClick="grDanCho(this)">2</td>
		              <td id="grdanrow" width="43" height="63" class="voidre" onClick="grDanCho(this)">3</td>
		              <td id="grdanrow" width="43" height="63" class="voidre" onClick="grDanCho(this)">4</td>
		              <td id="grdanrow" width="43" height="63" class="voidre" onClick="grDanCho(this)">5</td>
		              <td id="grdanrow" width="43" height="63" class="voidre" onClick="grDanCho(this)">6</td>
		              <td id="grdanrow" width="43" height="63" class="voidre" onClick="grDanCho(this)">7</td>
		              <td id="grdanrow" width="43" height="63" class="voidre" onClick="grDanCho(this)">8</td>
		              <td id="grdanrow" width="43" height="63" class="voidre" onClick="grDanCho(this)">9</td>
		              <td id="grdanrow" width="43" height="63" class="voidre" onClick="grDanCho(this)">10</td>
		              <td id="grdanrow" width="43" height="63" class="voidre" onClick="grDanCho(this)">J</td>
		              <td id="grdanrow" width="43" height="63" class="voidre" onClick="grDanCho(this)">Q</td>
		              <td id="grdanrow" width="43" height="63" class="voidre" onClick="grDanCho(this)">K</td>
		            </tr>
		          </table>
		         </td>
		      </tr>
		      
		      <tr >
		        <td width=70 height=63 align=center>
		        拖 码：
		        </td>
		        <td id="">
		         <table border="0" cellspacing="0" cellpadding="0">
		            <tr>
		              <td id="grtuorow" width="43" height="63" class="voidre" onClick="grTuoCho(this)">A</td>
		              <td id="grtuorow" width="43" height="63" class="voidre" onClick="grTuoCho(this)">2</td>
		              <td id="grtuorow" width="43" height="63" class="voidre" onClick="grTuoCho(this)">3</td>
		              <td id="grtuorow" width="43" height="63" class="voidre" onClick="grTuoCho(this)">4</td>
		              <td id="grtuorow" width="43" height="63" class="voidre" onClick="grTuoCho(this)">5</td>
		              <td id="grtuorow" width="43" height="63" class="voidre" onClick="grTuoCho(this)">6</td>
		              <td id="grtuorow" width="43" height="63" class="voidre" onClick="grTuoCho(this)">7</td>
		              <td id="grtuorow" width="43" height="63" class="voidre" onClick="grTuoCho(this)">8</td>
		              <td id="grtuorow" width="43" height="63" class="voidre" onClick="grTuoCho(this)">9</td>
		              <td id="grtuorow" width="43" height="63" class="voidre" onClick="grTuoCho(this)">10</td>
		              <td id="grtuorow" width="43" height="63" class="voidre" onClick="grTuoCho(this)">J</td>
		              <td id="grtuorow" width="43" height="63" class="voidre" onClick="grTuoCho(this)">Q</td>
		              <td id="grtuorow" width="43" height="63" class="voidre" onClick="grTuoCho(this)">K</td>
		            </tr>
		          </table>
		         </td>
		      </tr>
		      <tr><td heigth=30><br></td></tr>
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
        <td height=160><table border="0" cellspacing="0" cellpadding="0">
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
