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

<input type="hidden" name="typepagemark" value="compcom">

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
        <td id="tdspade"><table border="0" cellspacing="0" cellpadding="0">
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
    <table width="670" height=90 border="3" cellspacing="0" cellpadding="3">
      <tr>
        <td colspan=4 width=100% style="padding:0px">
	       <table width=100% border=0>
	         <tr>
		       <td>所选号码：
		       </td>
		       <td><input name="buttotclear" type="button" class="button_ssl" value="删除" onClick="deleteAll()">
		       </td>
	         </tr>
	       </table>
        </td>
      </tr>
      <tr>
       <td colspan=4 width=200 height=20>
        <span id="selpokertext"></span>
       </td>
      </tr>
      <tr>
        <td colspan=2 width=220>
           黑桃：<span id="seltextspade" style="color:#FF0000;font-weight:bold "></span>
        </td>
        <td colspan=2 width=220>
           红桃：<span id="seltextheart" style="color:#FF0000;font-weight:bold "></span>
        </td>
      </tr>
      <tr>
        <td colspan=2 width=220>
           梅花：<span id="seltextclub" style="color:#FF0000;font-weight:bold "></span>
        </td>
        <td colspan=2 width=220>
           方块：<span id="seltextdiamond" style="color:#FF0000;font-weight:bold "></span>
        </td>
      </tr>
      <tr>
       <td width=100>任选一：<span id="freeone" style="color:#FF0000;font-weight:bold ">0</span>注</td>
       <td width=100>任选二：<span id="freetwo" style="color:#FF0000;font-weight:bold ">0</span>注</td>
       <td width=100>任选三：<span id="freethree" style="color:#FF0000;font-weight:bold ">0</span>注</td>
       <td width=100>任选四：<span id="freefour" style="color:#FF0000;font-weight:bold ">0</span>注</td>
      </tr>
      <tr>
        <td colspan=4>
           总注数：<span id="showtotalwager" style="color:#FF0000; font-weight:bold ">0</span>
            ，总金额：<span id="showtotalmoney" style="color:#FF0000; font-weight:bold ">￥0.00</span>元
        </td>
      </tr>
      <tr>
        <td colspan=4> 
           <input type="submit" name="submit" value="确认投注">
            <font color="red">
              <%String strRem=(String)request.getAttribute("wagrem");
                if(strRem==null){strRem="";}%>
               <span id="subretshow"><%=strRem %></span>
               <input type="hidden" name="subreturn" value="<%=strRem %>">
           </font>
        </td>
      </tr>
    </table>
  </td>
 </tr>
</table>
  </form>
 </body>
</html>