<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>华彩在线-彩票投注网</title>
<link href="/pokercss/mainlaugh.css" rel="stylesheet" type="text/css" />

<script src="/pokerfunc/laughfunc/lgr.js"></script>
<script src="/pokerfunc/global.js"></script>
<script src="/pokerfunc/laughfunc/partotlist.js"></script>

<style type="text/css">
.plare { background:url(/pkimages/laughimg/lauplare.gif) no-repeat center; text-align:center; font-weight:bold; cursor:hand; color:#7f7f7f; padding-bottom:0px;}
.plach { background:url(/pkimages/laughimg/lauplach.gif) no-repeat center; text-align:center; font-weight:bold; cursor:hand; color:black; padding-bottom:0px;}
</style>

</head>
<body>

<form name="formwager" action="/servlet/lauwager" method="post">
<input type="hidden" name="typepagemark" value="grsixtotal">

<input type="hidden" name="markiss">
<input type="hidden" name="issuetype">
<input type="hidden" name="getissuenumber">
<input type="hidden" name="selling" value="false">

<input type="hidden" name="playtype" value="组6">
<input type="hidden" name="playmode" value="和值">
<input type="hidden" name="wagertotal" value="">
<input type="hidden" name="wagernum" value="0">

<table width=600 border="0" cellspacing="0" cellpadding="5" bordercolordark="#FFFFFF" bordercolorlight="#cccccc" style="border:#1890a8 solid; border-width:0 1px;">
<tr>
  <td align="center" colspan=2><b>选择号码：</b></td>
</tr>
<tr>
 <td width=600  id="tdchosentog"  bgcolor="FCDDF6" style="padding-left:20px">
 </td>
</tr>
<tr>
  <td bgcolor="#FFFFDD" colspan=2>
    <table height=130 border="0" cellspacing="0" cellpadding="0">
		      <tr >
		        <td id="tdhundred">
		         <table border="0" cellspacing="0" cellpadding="0">
		            <tr>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="totalChoPla('hundred',this)">3</td>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="totalChoPla('hundred',this)">4</td>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="totalChoPla('hundred',this)">5</td>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="totalChoPla('hundred',this)">6</td>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="totalChoPla('hundred',this)">7</td>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="totalChoPla('hundred',this)">8</td>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="totalChoPla('hundred',this)">9</td>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="totalChoPla('hundred',this)">10</td>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="totalChoPla('hundred',this)">11</td>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="totalChoPla('hundred',this)">12</td>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="totalChoPla('hundred',this)">13</td>
		              
		            </tr>
		          </table>
		         </td>
		      </tr>

		      <tr>
		        <td height=43 align=center>
                  <table border="0" cellspacing="0" cellpadding="0">
		            <tr>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="totalChoPla('hundred',this)">14</td>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="totalChoPla('hundred',this)">15</td>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="totalChoPla('hundred',this)">16</td>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="totalChoPla('hundred',this)">17</td>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="totalChoPla('hundred',this)">18</td>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="totalChoPla('hundred',this)">19</td>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="totalChoPla('hundred',this)">20</td>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="totalChoPla('hundred',this)">21</td>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="totalChoPla('hundred',this)">22</td>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="totalChoPla('hundred',this)">23</td>
		              <td align=center  id="hundred" width="66" height="43" class="plare" onClick="totalChoPla('hundred',this)">24</td>
		            </tr>
		          </table>
		        </td>
		      </tr>
		    </table>
    <table width="100%" border="0" cellspacing="5" cellpadding="0" style="border-bottom:#ccc solid 1px; margin-bottom:5px;;">
      <tr>
        <td align="left">
		</td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="3">
      <tr>
        <td>
         <table border="0" cellspacing="0" cellpadding="0">
          <tr>
             <td style="padding-left:3px;"> 所选号码：</td>
          </tr>
          <tr>
            <td style="padding-left:3px;padding-top:10px">
              <span id="showwagertext" style="color:#FF0000 "></span>
              <br>
            </td>
          </tr>
          <tr>
            <td style="padding-left:2px;padding-top:20px;">
              <table cellpadding="1">
                
                <tr>
                  <td>总注数：<span id="showtotalwager" style="color:#FF0000; font-weight:bold ">0</span>
                         ，总金额：<span id="showtotalmoney" style="color:#FF0000; font-weight:bold ">￥0.00</span>元
                  </td>
                </tr>
                <tr>
                  <td style="padding-left:2px;padding-top:20px;"> 
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
