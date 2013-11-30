<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>快乐扑克</title>
<link href="/pokercss/main.css" rel="stylesheet" type="text/css" />

<script src="/pokerfunc/global.js"></script>
<script src="/pokerfunc/pokfunc/commonsel.js"></script>

<style type="text/css">
.voidre { background:url(/pkimages/pokerimage/voidre.gif) no-repeat center; text-align:center; font-weight:bold; cursor:hand; color:#FF0000; padding-bottom:10px;}
.voidch { background:url(/pkimages/pokerimage/voidch.gif) no-repeat center; text-align:center; font-weight:bold; cursor:hand; color:#FF0000; padding-bottom:10px;}


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

<input type="hidden" name="typepagemark" value="gro">

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
  <td align="center" colspan=2><b>选择号码：</b></td>
</tr>
<!--
<tr>
 <td id="tdchosentog">
    <SELECT id="figure" NAME="figure" onChange="clearRadio()">
		<OPTION VALUE="rowone" selected>第一排
		<OPTION VALUE="rowtwo">第二排
		<OPTION VALUE="rowthree">第三排

	</SELECT>
     <input id="method" name="method" type="radio" value="all" onClick="choPokGrTog(this)">全
     <input id="method" name="method" type="radio" value="major" onClick="choPokGrTog(this)">大
     <input id="method" name="method" type="radio" value="minor" onClick="choPokGrTog(this)">小
     <input id="method" name="method" type="radio" value="odd" onClick="choPokGrTog(this)">奇
     <input id="method" name="method" type="radio" value="even" onClick="choPokGrTog(this)">偶
     <input id="method" name="method" type="radio" value="empty" onClick="choPokGrTog(this)">空
 </td>
</tr>
-->
<tr>
  <td bgcolor="#FFFFDD" colspan=2>
    <table height=370 border="0" cellspacing="0" cellpadding="0">
      <tr >
        <td id="rowonetdword" align=center colspan=2>第一排
        </td>
      </tr>
      <tr >
        <td id="rowonetd"><table border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td id="rowone" width="43" height="63" class="voidre" onClick="choPokGr('rowone',this)">A</td>
              <td id="rowone" width="43" height="63" class="voidre" onClick="choPokGr('rowone',this)">2</td>
              <td id="rowone" width="43" height="63" class="voidre" onClick="choPokGr('rowone',this)">3</td>
              <td id="rowone" width="43" height="63" class="voidre" onClick="choPokGr('rowone',this)">4</td>
              <td id="rowone" width="43" height="63" class="voidre" onClick="choPokGr('rowone',this)">5</td>
              <td id="rowone" width="43" height="63" class="voidre" onClick="choPokGr('rowone',this)">6</td>
              <td id="rowone" width="43" height="63" class="voidre" onClick="choPokGr('rowone',this)">7</td>
              <td id="rowone" width="43" height="63" class="voidre" onClick="choPokGr('rowone',this)">8</td>
              <td id="rowone" width="43" height="63" class="voidre" onClick="choPokGr('rowone',this)">9</td>
              <td id="rowone" width="43" height="63" class="voidre" onClick="choPokGr('rowone',this)">10</td>
              <td id="rowone" width="43" height="63" class="voidre" onClick="choPokGr('rowone',this)">J</td>
              <td id="rowone" width="43" height="63" class="voidre" onClick="choPokGr('rowone',this)">Q</td>
              <td id="rowone" width="43" height="63" class="voidre" onClick="choPokGr('rowone',this)">K</td>
            </tr>
          </table></td>
      </tr>
      <tr>
        <td id="rowtwotdword" align=center colspan=2>
        </td>
      </tr>
      <tr>
        <td id="rowtwotd"><table border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td id="rowtwo" width="43" height="63" class="voidre" onClick="choPokGr('rowtwo',this)">A</td>
              <td id="rowtwo" width="43" height="63" class="voidre" onClick="choPokGr('rowtwo',this)">2</td>
              <td id="rowtwo" width="43" height="63" class="voidre" onClick="choPokGr('rowtwo',this)">3</td>
              <td id="rowtwo" width="43" height="63" class="voidre" onClick="choPokGr('rowtwo',this)">4</td>
              <td id="rowtwo" width="43" height="63" class="voidre" onClick="choPokGr('rowtwo',this)">5</td>
              <td id="rowtwo" width="43" height="63" class="voidre" onClick="choPokGr('rowtwo',this)">6</td>
              <td id="rowtwo" width="43" height="63" class="voidre" onClick="choPokGr('rowtwo',this)">7</td>
              <td id="rowtwo" width="43" height="63" class="voidre" onClick="choPokGr('rowtwo',this)">8</td>
              <td id="rowtwo" width="43" height="63" class="voidre" onClick="choPokGr('rowtwo',this)">9</td>
              <td id="rowtwo" width="43" height="63" class="voidre" onClick="choPokGr('rowtwo',this)">10</td>
              <td id="rowtwo" width="43" height="63" class="voidre" onClick="choPokGr('rowtwo',this)">J</td>
              <td id="rowtwo" width="43" height="63" class="voidre" onClick="choPokGr('rowtwo',this)">Q</td>
              <td id="rowtwo" width="43" height="63" class="voidre" onClick="choPokGr('rowtwo',this)">K</td>
            </tr>
          </table></td>
      </tr>
      <tr>
       <td id="rowthreetdword" align=center colspan=2>
       </td>
      </tr>
      <tr>
        <td id="rowthreetd"><table border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td id="rowthree" width="43" height="63" class="voidre" onClick="choPokGr('rowthree',this)">A</td>
              <td id="rowthree" width="43" height="63" class="voidre" onClick="choPokGr('rowthree',this)">2</td>
              <td id="rowthree" width="43" height="63" class="voidre" onClick="choPokGr('rowthree',this)">3</td>
              <td id="rowthree" width="43" height="63" class="voidre" onClick="choPokGr('rowthree',this)">4</td>
              <td id="rowthree" width="43" height="63" class="voidre" onClick="choPokGr('rowthree',this)">5</td>
              <td id="rowthree" width="43" height="63" class="voidre" onClick="choPokGr('rowthree',this)">6</td>
              <td id="rowthree" width="43" height="63" class="voidre" onClick="choPokGr('rowthree',this)">7</td>
              <td id="rowthree" width="43" height="63" class="voidre" onClick="choPokGr('rowthree',this)">8</td>
              <td id="rowthree" width="43" height="63" class="voidre" onClick="choPokGr('rowthree',this)">9</td>
              <td id="rowthree" width="43" height="63" class="voidre" onClick="choPokGr('rowthree',this)">10</td>
              <td id="rowthree" width="43" height="63" class="voidre" onClick="choPokGr('rowthree',this)">J</td>
              <td id="rowthree" width="43" height="63" class="voidre" onClick="choPokGr('rowthree',this)">Q</td>
              <td id="rowthree" width="43" height="63" class="voidre" onClick="choPokGr('rowthree',this)">K</td>
            </tr>
          </table></td>
      </tr>
      <tr>
        <td id="rowfourtdword" align=center colspan=2>
        </td>
      </tr>
      <tr>
        <td id="rowfourtd"><table border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td id="rowfour" width="43" height="63" class="voidre" onClick="choPokGr('rowfour',this)">A</td>
              <td id="rowfour" width="43" height="63" class="voidre" onClick="choPokGr('rowfour',this)">2</td>
              <td id="rowfour" width="43" height="63" class="voidre" onClick="choPokGr('rowfour',this)">3</td>
              <td id="rowfour" width="43" height="63" class="voidre" onClick="choPokGr('rowfour',this)">4</td>
              <td id="rowfour" width="43" height="63" class="voidre" onClick="choPokGr('rowfour',this)">5</td>
              <td id="rowfour" width="43" height="63" class="voidre" onClick="choPokGr('rowfour',this)">6</td>
              <td id="rowfour" width="43" height="63" class="voidre" onClick="choPokGr('rowfour',this)">7</td>
              <td id="rowfour" width="43" height="63" class="voidre" onClick="choPokGr('rowfour',this)">8</td>
              <td id="rowfour" width="43" height="63" class="voidre" onClick="choPokGr('rowfour',this)">9</td>
              <td id="rowfour" width="43" height="63" class="voidre" onClick="choPokGr('rowfour',this)">10</td>
              <td id="rowfour" width="43" height="63" class="voidre" onClick="choPokGr('rowfour',this)">J</td>
              <td id="rowfour" width="43" height="63" class="voidre" onClick="choPokGr('rowfour',this)">Q</td>
              <td id="rowfour" width="43" height="63" class="voidre" onClick="choPokGr('rowfour',this)">K</td>
            </tr>
          </table></td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="5" cellpadding="0" style="border-bottom:#ccc solid 1px; margin-bottom:5px;;">
      <tr>
        <td align="left">
		注数：<span id="showsinglewager" style="color:#FF0000; font-weight:bold ">0</span>
		金额：<span id="showsinglemoney" style="color:#FF0000; font-weight:bold ">￥0.00</span>
		<input type="hidden" name="textcomgrhidden" value="">
		</td>
		<td align="left">
		  <input name="butplus" type="button" class="button_ssl" value="添加" onClick="wagerPlusGr()">
          <input name="butremove" type="button" class="button_ssl" onclick="pokerTextRemove(this)" value="删除" />
          <input name="butempty" type="button" class="button_ssl" onclick="wagerEmpty()" value="清空" />  
       </td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="3">
      <tr>
        <td>
          <input id="randone" name="randone" type="button" class="button_ssl" value="机选一注" onClick="randSelect(1)">
          <input id="randfive" name="randfive" type="button" class="button_ssl" value="机选五注" onClick="randSelect(5)">
          <input id="randten" name="randten" type="button" class="button_ssl" value="机选十注" onClick="randSelect(10)">
          </td>
      </tr>
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