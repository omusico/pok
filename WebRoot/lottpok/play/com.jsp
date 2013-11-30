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

.spadere { background:url(/pkimages/pokerimage/spadere.gif) no-repeat center; text-align:center; font-weight:bold; cursor:hand; color:#7f7f7f; padding-bottom:10px;}
.spadech { background:url(/pkimages/pokerimage/spadech.gif) no-repeat center; text-align:center; font-weight:bold; cursor:hand; color:#000; padding-bottom:10px;}
.heartre { background:url(/pkimages/pokerimage/heartre.gif) no-repeat center; text-align:center; font-weight:bold; cursor:hand; color:#fdb2b2; padding-bottom:10px;}
.heartch { background:url(/pkimages/pokerimage/heartch.gif) no-repeat center; text-align:center; font-weight:bold; cursor:hand; color:#FF0000; padding-bottom:10px;}
.clubre { background:url(/pkimages/pokerimage/clubre.gif) no-repeat center; text-align:center; font-weight:bold; cursor:hand; color:#7f7f7f; padding-bottom:10px;}
.clubch { background:url(/pkimages/pokerimage/clubch.gif) no-repeat center; text-align:center; font-weight:bold; cursor:hand; color:#000; padding-bottom:10px;}
.diamondre { background:url(/pkimages/pokerimage/diamondre.gif) no-repeat center; text-align:center; font-weight:bold; cursor:hand; color:#fdb2b2; padding-bottom:10px;}
.diamondch { background:url(/pkimages/pokerimage/diamondch.gif) no-repeat center; text-align:center; font-weight:bold; cursor:hand; color:#FF0000; padding-bottom:10px;}

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

<input type="hidden" name="typepagemark" value="com">

<input type="hidden" name="markiss">
<input type="hidden" name="issuetype">
<input type="hidden" name="getissuenumber">
<input type="hidden" name="selling" value="true">

<input type="hidden" name="playtype" value="">
<input type="hidden" name="playmode" value="">
<input type="hidden" name="wagtotbef" value="">
<input type="hidden" name="wagertotal" value="">
<input type="hidden" name="wagernum" value="0">

<table width=712 border="1" cellspacing="0" cellpadding="5" bordercolordark="#FFFFFF" bordercolorlight="#cccccc" style="border:#1890a8 solid; border-width:0 1px;">
<tr>
  <td align="center" colspan=2><b>选择号码：</b></td>
</tr>
<tr>
 <td id="tdchosentog">
    <SELECT id="figure" NAME="figure" onChange="clearRadio()">
		<OPTION VALUE="spade" selected>黑桃
		<OPTION VALUE="heart">红桃
		<OPTION VALUE="club">梅花
		<OPTION VALUE="diamond">方块

	</SELECT>
     <input id="method" name="method" type="radio" value="all" onClick="choosePokerTog(this)">全
     <input id="method" name="method" type="radio" value="major" onClick="choosePokerTog(this)">大
     <input id="method" name="method" type="radio" value="minor" onClick="choosePokerTog(this)">小
     <input id="method" name="method" type="radio" value="odd" onClick="choosePokerTog(this)">奇
     <input id="method" name="method" type="radio" value="even" onClick="choosePokerTog(this)">偶
     <input id="method" name="method" type="radio" value="empty" onClick="choosePokerTog(this)">空
 </td>
</tr>
<tr>
  <td bgcolor="#FFFFDD" colspan=2>
    <table height=370 border="0" cellspacing="0" cellpadding="0">
      <tr >
        <td id="tdwordspade" align=center colspan=2>黑桃&nbsp;<img src="/pkimages/pokerimage/pic_hei.gif" />
        </td>
      </tr>
      <tr >
        <td id="tdspade">
         <table border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td id="spade" width="43" height="63" class="spadere" onClick="choosePoker('spade',this)">A</td>
              <td id="spade" width="43" height="63" class="spadere" onClick="choosePoker('spade',this)">2</td>
              <td id="spade" width="43" height="63" class="spadere" onClick="choosePoker('spade',this)">3</td>
              <td id="spade" width="43" height="63" class="spadere" onClick="choosePoker('spade',this)">4</td>
              <td id="spade" width="43" height="63" class="spadere" onClick="choosePoker('spade',this)">5</td>
              <td id="spade" width="43" height="63" class="spadere" onClick="choosePoker('spade',this)">6</td>
              <td id="spade" width="43" height="63" class="spadere" onClick="choosePoker('spade',this)">7</td>
              <td id="spade" width="43" height="63" class="spadere" onClick="choosePoker('spade',this)">8</td>
              <td id="spade" width="43" height="63" class="spadere" onClick="choosePoker('spade',this)">9</td>
              <td id="spade" width="43" height="63" class="spadere" onClick="choosePoker('spade',this)">10</td>
              <td id="spade" width="43" height="63" class="spadere" onClick="choosePoker('spade',this)">J</td>
              <td id="spade" width="43" height="63" class="spadere" onClick="choosePoker('spade',this)">Q</td>
              <td id="spade" width="43" height="63" class="spadere" onClick="choosePoker('spade',this)">K</td>
            </tr>
          </table></td>
      </tr>
      <tr>
        <td id="tdwordheart" align=center colspan=2>红桃&nbsp;<img src="/pkimages/pokerimage/pic_hong.gif" /></td>
      </tr>
      <tr>
        <td id="tdheart"><table border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td id="heart" width="43" height="63" class="heartre" onClick="choosePoker('heart',this)">A</td>
              <td id="heart" width="43" height="63" class="heartre" onClick="choosePoker('heart',this)">2</td>
              <td id="heart" width="43" height="63" class="heartre" onClick="choosePoker('heart',this)">3</td>
              <td id="heart" width="43" height="63" class="heartre" onClick="choosePoker('heart',this)">4</td>
              <td id="heart" width="43" height="63" class="heartre" onClick="choosePoker('heart',this)">5</td>
              <td id="heart" width="43" height="63" class="heartre" onClick="choosePoker('heart',this)">6</td>
              <td id="heart" width="43" height="63" class="heartre" onClick="choosePoker('heart',this)">7</td>
              <td id="heart" width="43" height="63" class="heartre" onClick="choosePoker('heart',this)">8</td>
              <td id="heart" width="43" height="63" class="heartre" onClick="choosePoker('heart',this)">9</td>
              <td id="heart" width="43" height="63" class="heartre" onClick="choosePoker('heart',this)">10</td>
              <td id="heart" width="43" height="63" class="heartre" onClick="choosePoker('heart',this)">J</td>
              <td id="heart" width="43" height="63" class="heartre" onClick="choosePoker('heart',this)">Q</td>
              <td id="heart" width="43" height="63" class="heartre" onClick="choosePoker('heart',this)">K</td>
            </tr>
          </table></td>
      </tr>
      <tr>
       <td id="tdwordclub" align=center colspan=2>梅花&nbsp;<img src="/pkimages/pokerimage/pic_mei.gif" /></td>
      </tr>
      <tr>
        <td id="tdclub"><table border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td id="club" width="43" height="63" class="clubre" onClick="choosePoker('club',this)">A</td>
              <td id="club" width="43" height="63" class="clubre" onClick="choosePoker('club',this)">2</td>
              <td id="club" width="43" height="63" class="clubre" onClick="choosePoker('club',this)">3</td>
              <td id="club" width="43" height="63" class="clubre" onClick="choosePoker('club',this)">4</td>
              <td id="club" width="43" height="63" class="clubre" onClick="choosePoker('club',this)">5</td>
              <td id="club" width="43" height="63" class="clubre" onClick="choosePoker('club',this)">6</td>
              <td id="club" width="43" height="63" class="clubre" onClick="choosePoker('club',this)">7</td>
              <td id="club" width="43" height="63" class="clubre" onClick="choosePoker('club',this)">8</td>
              <td id="club" width="43" height="63" class="clubre" onClick="choosePoker('club',this)">9</td>
              <td id="club" width="43" height="63" class="clubre" onClick="choosePoker('club',this)">10</td>
              <td id="club" width="43" height="63" class="clubre" onClick="choosePoker('club',this)">J</td>
              <td id="club" width="43" height="63" class="clubre" onClick="choosePoker('club',this)">Q</td>
              <td id="club" width="43" height="63" class="clubre" onClick="choosePoker('club',this)">K</td>
            </tr>
          </table></td>
      </tr>
      <tr>
        <td id="tdworddiamond" align=center colspan=2>方块&nbsp;<img src="/pkimages/pokerimage/pic_fang.gif" /></td>
      </tr>
      <tr>
        <td id="tddiamond"><table border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td id="diamond" width="43" height="63" class="diamondre" onClick="choosePoker('diamond',this)">A</td>
              <td id="diamond" width="43" height="63" class="diamondre" onClick="choosePoker('diamond',this)">2</td>
              <td id="diamond" width="43" height="63" class="diamondre" onClick="choosePoker('diamond',this)">3</td>
              <td id="diamond" width="43" height="63" class="diamondre" onClick="choosePoker('diamond',this)">4</td>
              <td id="diamond" width="43" height="63" class="diamondre" onClick="choosePoker('diamond',this)">5</td>
              <td id="diamond" width="43" height="63" class="diamondre" onClick="choosePoker('diamond',this)">6</td>
              <td id="diamond" width="43" height="63" class="diamondre" onClick="choosePoker('diamond',this)">7</td>
              <td id="diamond" width="43" height="63" class="diamondre" onClick="choosePoker('diamond',this)">8</td>
              <td id="diamond" width="43" height="63" class="diamondre" onClick="choosePoker('diamond',this)">9</td>
              <td id="diamond" width="43" height="63" class="diamondre" onClick="choosePoker('diamond',this)">10</td>
              <td id="diamond" width="43" height="63" class="diamondre" onClick="choosePoker('diamond',this)">J</td>
              <td id="diamond" width="43" height="63" class="diamondre" onClick="choosePoker('diamond',this)">Q</td>
              <td id="diamond" width="43" height="63" class="diamondre" onClick="choosePoker('diamond',this)">K</td>
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
