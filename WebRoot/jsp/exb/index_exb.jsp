<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>��������-��ƱͶע��-����ʮ��-ѡһ��Ͷ</title>
<link href="/pokercss/main.css" rel="stylesheet" type="text/css" />

<script src="/jsp/exb/js/exb_play.js" type="text/javascript"></script>

<script language="javascript"></script>
<style type="text/css">
.typeready { background:url(/pkimages/pokerimage/img_tab2.gif) no-repeat center; text-align:center; cursor:hand}
.typechosen { background:url(/pkimages/pokerimage/img_tab1.gif) no-repeat center; text-align:center; cursor:hand}
.modeready { background:url(/pkimages/pokerimage/modeready.gif) no-repeat center; text-align:center; cursor:hand}
.modeforbid { background:url(/pkimages/pokerimage/modeforbid.gif) no-repeat center; text-align:center; cursor:hand}
.modechosen { background:url(/pkimages/pokerimage/modechosen.gif) no-repeat center; text-align:center; cursor:hand}

.image3 { border:#e2903a solid 1px; background:url(/pkimages/mainimages/img4.gif); color:#1d3d86;}
.image3 a:link,.image3 a:visited { margin:0px; text-decoration:none;}
.image4 { background: url(/pkimages/mainimages/layout1.gif)}
.image6 { background: url(/pkimages/mainimages/indimage6.gif) repeat-x top;margin:0px;}
.winmovieback{ background: url(/pkimages/pokerimage/pokpic/back1.gif)}
</style>
</head>

<body>
<input type="hidden" name="pagemark" value="exb">

<input type="hidden" name="hidmarkiss" value="">
<input type="hidden" name="hidmulissvalue" value="">
<input type="hidden" name="hidissuetype" value="">
<input type="hidden" name="hidselling" value="false">

<input type="hidden" name="typechosenmark" value="one">
<input type="hidden" name="modechosenmark" value="multi">

<table border=0 cellpadding="0" cellspacing="0">
   <tr>
      <td>
         <iframe name="fratitle" id="fratitle" frameborder="0" width="920" height="220"  scrolling="no" src="/title.jsp"></iframe>
      </td> 
   </tr>
</table>

<table border=0 cellpadding="0" cellspacing="0">
   <tr>
      <td>
         <iframe name="fralogin" id="fralogin" frameborder="0" width="920" height="30"  scrolling="no" src="/login.jsp"></iframe>
      </td> 
   </tr>        
</table>
<table width="920" class="image4" border=0>
<tr><td align="center"><span id="palytitle" style="font:bold; font-size:13px; color:red">����ʮ��</span></td></tr>
</table>
<!---------��������----------------->
<table  border=0 cellpadding="0" cellspacing="0">
     <tr>
        <td>
		
			<iframe name="frawinmovie" id="frawinmovie" frameborder="0" width="920" height="120"  scrolling="no" src="/exb/exbPublicInfo.do?method=showExbWinMovie"></iframe>
        
		</td>
     </tr>
</table>  
    
    <!--Main Body-->
    <table width="920" align=center border="2" bordercolordark="#FF0000" bordercolorlight="#FFFF00" cellspacing="0" cellpadding="0" class="image4">
      <tr>  
      	<!-- Flow Chart in the Left Frame -->
		<td width="200"  valign=top>
          <table width="200" border="1" cellspacing="0" cellpadding="0" >
            <tr>
              <td>
              <iframe name="frabulent" id="frabulent" frameborder="0" width="215" height="100"  scrolling="no" src="/pubpage/bulletin.jsp"></iframe>
              </td>
            </tr> 
            <tr>
              <td>
              <iframe name="fraspecuserentry" id="fraspecuserentry" frameborder="0" width="215" height="30"  scrolling="no" src="/pubpage/specuserentry.jsp"></iframe>
              </td>
            </tr>       
			<!-- �����н���Ϣ begin-->
            <tr>
             <td align=center>
                <iframe name="frawinnumshow" id="frawinnumshow" frameborder="0" width="215" height="290"  scrolling="no" src="/exb/exbPublicInfo.do?method=showExbZjNum"></iframe>
             </td>
            </tr>
			<!-----------������������ͼ begin------------>
            <tr>
			  <td height="30" colspan="3" align="center" bgcolor="#F4F4F4">
				  <iframe name="fratreent" id="fratreent" frameborder="0" width="215" height="30"  scrolling="no" src="/jsp/public/exb_zj_zoushi.jsp"></iframe>
              </td>
			</tr>
			<tr bgcolor=white>
		        <td align=center bgcolor=white>
		        <form name="formpokque" action="/exb/exbManage.do?method=findZjUserInfo" method="post" target="_blank" style="margin-bottom:10px;margin-top:10px;">
		          <input type="submit" name="butpokquery" value="�н��û���ѯ"> 
		          <br>�������ں�:<input type="text"  name="queryIssueNum" id="queryIssueNum" value="" size=9>
				</form>        
		        </td>
		    </tr>
			<tr>
             <td>
               <img src="/pkimages/mainimages/flowchart.gif" />
            </td>
            </tr>
          </table>
        </td>
  
        <td width="700" align=center>
           <table border=0> 
		   <!-----------ʱ����Ϣ----------->
            <tr>
                <td align=center>
                   <iframe name="fratime" id="fratime" frameborder="0" width="700" height="30"  scrolling="no" src="/exb/exbIssueTime.do?method=showIssueTime"></iframe>
                </td>
            </tr>
			<tr>
			  <td>
			  <table border="1" width=700 bgcolor=white bordercolordark="#FFFFFF" bordercolorlight="#cccccc"  style="border-bottom:#185c8e solid 2px;">
			 <!-------------����------------>
			   <tr>
			    <td align=center width="7%">�� ��</td>
			    <td>
			     <iframe name="fratyptit" id="fratyptit" frameborder="0" width="100%" height="30"  scrolling="no" src="/jsp/exb/exb_type.jsp"></iframe>
			    </td>
			   </tr>
			   <!-------------ģʽ------------->
			   <tr>
			    <td align="center" width="7%">ģ ʽ</td>
			    <td align="left">
			     <iframe name="framodtit" id="framodtit" frameborder="0" width="100%" height="30"  scrolling="no" src="/jsp/exb/mode/xuan1_mode.jsp"></iframe>
		        </td>
			   </tr>
			   <tr>
			    <td align="center" width=7%>˵ ��</td>
			   	<td id="expl" width=700 align=left bgcolor="#FFFFDD" colspan=2>
			   	����:�Ӻڡ��졢÷�������ֻ�ɫ��������ѡ1�ֻ�ɫ�������Ͷע��<br>��ʽ������һ��ѡ������ɫ��ÿ����ɫ��ѡ������롣��:(3,4|3,4|3|4)��ϵͳ�ᰴ�ա���ѡһ�������ж�Ϊ6ע��<br>��ʽ:����һ��ѡ������ɫ����ÿ����ɫֻ��ѡһ�����롣��:(3|4|2|J)��ϵͳ�ᰴ�ա���ѡһ�������ж�Ϊ4ע��<br></td>
			   </tr>
			  </table>
			 </td>
			</tr>
			  <!------------------Ͷע��------------------->
	        <tr>
               <td>
                 <iframe name="frameTouzhu" id="frameTouzhu" frameborder="0" style="height:320px;width:700px;" scrolling="no" src="/jsp/exb/play/xuan1_play_shutou.jsp" onreadystatechange="playLoad()"></iframe>
               </td>
            </tr>
       
			<!---------------------׷Ͷҳ��--------------------->
			<tr>
               <td>
                  <iframe name="frameZhuitou"  frameborder="0" width="700" height="440"  scrolling="yes" src="/exb/exbPublicInfo.do?method=showExbZhuitou"></iframe>
               </td>
			 
            </tr>


         </table>         
		</td>
		
		
      </tr>
    </table>

</body>
</html>