<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true" 
    import="beanpac.IssConInfo,beanpac.PrMoney,pubmethpac.GlobalMeth"%>
<%@ include file="/common/taglibs.jsp"%>

<%GlobalMeth objGM=new GlobalMeth(); %>
<html>
<head>
<title>����ƽ̨--ʱʱ��</title>
<%@ include file="/common/meta.jsp"%>

<script type='text/javascript' src='/dwr/interface/DwrBean.js'></script>
<script type='text/javascript' src='/dwr/engine.js'></script>
<script type='text/javascript' src='/dwr/util.js'></script>
<script src="/js/jquery/jquery.js" type="text/javascript"></script>

<script Language="JavaScript">
function toSubmitForm(act){
  //�н��û���ѯ
  if(act == "showZjUser"){
	var temp = document.getElementById("queryIssueNum").value;
	if(temp == ""){
		alert("������Ҫ��ѯ���ں�!");
		return;
	}
	document.formZjUser.action="/ssc/sscManage.do?method=findZjUserInfo";
	document.formZjUser.submit();
  }
  //�н�����
  if(act == "saveZhongjiang"){
	  
	var tempIssueNum = document.getElementById("zjIssueNum").value;
	if(tempIssueNum == ""){
		alert("�������ں�!");
		return;
	}
	if(fucCheckInt(tempIssueNum) == 0){
		alert("�ں�ֻ��Ϊ���֣�����������!");
		return;
	}

	DwrBean.getZjConfig(tempIssueNum,function(data){callback_saveZj(data);});
   
  }
  
  //������Ϣ 
  if(act == "saveBaseInfo"){
	// alert("baseInfo");
		var offsetTime = $("#offsetTime").val();
    if(offsetTime > (10*60)){
		alert("������ƫ��ʱ����10����(600��)����!");
		return false;

	}

 	document.formregtime.action="/ssc/sscManage.do?method=saveBaseInfo";
	
    document.formregtime.submit();
  }
}
//�����н�����ص�����
function callback_saveZj(data){
 	if(data.issueNum == null){
		document.formpokwin.action="/ssc/sscManage.do?method=saveZhongjianghaoma";
		
	    document.formpokwin.submit();
	}else if(data.issueNum == "-"){
		alert("�ڲ���������������һ�� :(");
		return;
	}else{
		if(confirm(data.issueNum+"���Ѿ������н����룬�Ƿ�Ҫ�������ã�")){
			if(confirm(" �������ý���ɾ��֮ǰ���õĸ��ڵ��н���Ϣ���Ƿ����?")){
				$("#isResetIssueInfo").attr("value","1");
 				document.formpokwin.action="/ssc/sscManage.do?method=saveZhongjianghaoma";
				document.formpokwin.submit();
			}
		}
	}
}
//�õ���Ӧ��ֵ
function change_val(val){
	//alert(val);
	DwrBean.getMoneyLimitInfo("ssc",val,function(data){callback_fun(data);});
}
function callback_fun(data){
	//alert(data);
	if(data != null){
		if(data == "," || data == ""){
			document.getElementById("money").value ="";
			document.getElementById("limitOneIssue").value = "";
		}else{
			var str = data.split(",");
		//	alert(str[0]);
			document.getElementById("money").value = str[0];
			document.getElementById("limitOneIssue").value = str[1];
		}
	}
}
//�����޺���Ϣ
function toSubmitLimit(){
	//alert("000");
	//alert($("#money").attr("value"));
	var rule=document.getElementById("rule").value;
	var money=document.getElementById("money").value;
	var limit=document.getElementById("limitOneIssue").value;
	if(rule == ""){
		alert("��ѡ��!");
		return;
	}
	//var ttt = $("#money").attr("value");
 	if(money == ""){
		alert("��������!");
		return;
	}
	if(limit == ""){
		alert("���������Ʊ���!");
		return;
	}
	 
 	document.formprmonup.action = "/ssc/sscManage.do?method=saveLimitInfo";
	formprmonup.submit();
}
//ҳ�����
$(document).ready(function(){
	//ֻ����������
	$(".style_int_num").bind("keydown",function(){
		return onlyNumbers();
	});
});

//һ��ɾ��
function toDelAllRecord(){
	alert("�ò�����ɾ����ǰ�淨�����е�Ͷע��¼���н���¼�����������! ");
    if(confirm("ɾ�����޷��ָ���ȷʵҪɾ����")){
		DwrBean.delAllRecordOfSsc(function(data){callback_delAll(data);});
	}
}
function callback_delAll(data){
	if(data){
		alert("ɾ���ɹ�!");
	}else{
		alert("ɾ��ʧ��!");
	}
}

</script>
<style type="text/css">
body,td,th { font-size:13px;}
*{margin:0px}
ul { list-style:none;}
.image1 a:link,.image1 a:visited { color:#000; text-decoration:none}
.image1 a:hover,.image1 a:active { color:#FF0000; text-decoration:underline;}
.image1 { background: url(/pkimages/mainimages/layout.gif) repeat-x top;}
.image2 { padding-top:2px; padding-left:20px;}
.image2 li { float:left; color:#FFF;margin:0 20px}
.image2 a:link,.image2 a:visited { color:#FFF; font-weight:bold; margin:0 2px 0 5px; text-decoration:none;}
.image2 a:active,.image2 a:hover { color:#FFFF00;} 
.image6 { background: url(/pkimages/mainimages/indimage6.gif) repeat-x top;margin:0px;}
</style>
</head>

<body>
<%@ include file="/common/messages.jsp"%>

<input type="hidden" name="pagemark" value="pokplat">

<table width="100%" border="1" align="center" cellpadding="0" cellspacing="0">
<input type="hidden" name="hidmulissvalue" value="">
<input type="hidden" name="hidissuetype" value="">
<input type="hidden" name="hidselling" value="false">
<tr><td align=center style="font:bold; font-size=13px; color:red" height="30"><span>ʱ ʱ ��</span></td></tr>						  
      <tr width="100%">
         <td>
          <table  width="100%" border="0" >
           <tr width="100%">
           <td align=center>
             <iframe name="fratime" id="fratime" frameborder="0" width="100%" height="30"  scrolling="no" src="/ssc/sscIssueTime.do?method=showIssueTime"></iframe>
           </td>  
           </tr>
          </table>
         </td>          
       </tr>
        <tr>
           <td>
            <table>
              <tr>
	           <td align=left style="padding-bottom:10px;padding-top:10px;padding-left:10px;">
	              <a href="/ssc/sscManage.do?method=showZhongjianghaoma">�н������ѯ</a>
	           </td>
	           <td>
				  <iframe name="fratreent" id="fratreent" frameborder="0" width="200" height="30"  scrolling="no" src="/jsp/public/ssc_zj_zoushi.jsp"></iframe>
              </td>
              </tr>
            </table>
           </td>
        </tr>
        <tr>
           <td>
              <table border=0>
                 <form name="formpokwin" method="post"  style="margin-bottom:10px;margin-top:10px;">
					<input type="hidden" name="isResetIssueInfo"  id="isResetIssueInfo" value="0" size=12>
                 <tr>
                  <td style="color:red">���"�н��������"��ť��ϵͳ�Զ�ȡ���н��û���Ϣ��
                  </td>
                 </tr>
                  <tr>					                    
                     <td>
                        �ں�:<input type="text" name="zjIssueNum" value="" size=12   class="style_int_num" >
					 �н�����:
					     <% 
						   String[] arrNumValue={"0","1","2","3","4","5","6","7","8","9"};
						   String[] arrPlaType={"��λ","ǧλ","��λ","ʮλ","��λ"};
						   String[] arrPlaTypeNamePlus={"wan","qian","bai","shi","ge"};
						   for(int i=0;i<arrPlaType.length;i++){
							   out.println(arrPlaType[i]+":<select name='"+arrPlaTypeNamePlus[i]+"' id='"+arrPlaTypeNamePlus[i]+"'>");
							   for(int j=0;j<arrNumValue.length;j++){
								   out.println("<option value='"+arrNumValue[j]+"'>"+arrNumValue[j]);
							   }
							   out.println("</select>");
						   }%>
					 </td>
					 <td>
			           <input type="button" name="butpokplus" value="�н��������" onClick="toSubmitForm('saveZhongjiang')"> 
   				       <font color=red><%=objGM.removeNull((String)request.getAttribute("remMessage"))%></font>

					   <c:if test="${saveZhongjianghaoma != null && saveZhongjianghaoma != ''}">
							  <font color=red><c:out value="${saveZhongjianghaoma}"/></font>
					    </c:if>
			         </td>
			      </tr>
		       </form>
	         </table>
         </td>
      </tr>
      
      <tr>
      <td>
      <table border=0>
      <tr><td style="color:red">������"�ں�"ֵ,�����"�н��û���ѯ"��ť��ѯ��</td>
      </tr>
      <tr>
        <td>
        <form name="formZjUser" method="post" style="margin-bottom:10px;margin-top:10px;">
           �ں�:<input type="text" name="queryIssueNum"  id="queryIssueNum" value="" size="12" class="style_int_num">
          <input type="button" name="butpokquery" value="�н��û���ѯ" onClick="toSubmitForm('showZjUser')"> 
		  <font color="red"></font>
        </form>        
        </td>
      </tr>
      </table>
      </td>
      </tr>
      <tr>
           <td>
           <%IssConInfo objIssCI=new IssConInfo();
           objIssCI.getIssCon("pokisscon");%>
              <table border=1>
                 <form name="formregtime"  method="post" style="margin-bottom:10px;margin-top:10px;">
                  <tr>					                    
                     <td >
                        �ںŵ���:
                        <font color=red>
                        ˵��:Ĭ�ϴ��糿(9:00)��ʼ���ۣ�ÿ������(10)���ӣ�Ȼ��ֹͣ���۽���(5)���ӵĿ�����ÿ��һ������(53)�ڡ����Ų��ֿ��������á�
                        </font>
			         </td>
					 <td rowspan="4">
			           <input type="button" name="subreg" value="����" onClick="toSubmitForm('saveBaseInfo')">   				   
					   <font color=red></font>
			         </td>
			      </tr>
			    
				  <tr>
			        <td >
                        ÿ�쿪ʼʱ��:
                        <input type="text" name="beginHour"  class="style_int_num"  value="<bean:write name="sscConfigInfo" property="beginHour"/>" size=2>ʱ
                        <input type="text" name="beginMin"  class="style_int_num"  value="<bean:write name="sscConfigInfo" property="beginMin"/>" size=2>��					 
                        ,ÿ������:
                        <input type="text" name="sellMin"  class="style_int_num"  value="<bean:write name="sscConfigInfo" property="sellMin"/>" size=2>����
                        ,����
                        <input type="text" name="winMin"  class="style_int_num"  value="<bean:write name="sscConfigInfo" property="winMin"/>" size=2>���ӵĿ���
                        ,ÿ�칲����
                        <input type="text" name="dayIssueTimes"  class="style_int_num"  value="<bean:write name="sscConfigInfo" property="dayIssueTimes"/>" size=2>��


						<c:if test="${saveBaseInfo != null && saveBaseInfo != ''}">
							  <font color="red"><c:out value="${saveBaseInfo}"/></font>
					    </c:if>
					 </td>
			      </tr>

			      <tr>
			        <td>
			        ���쿪ʼ�ں�Ϊ:
			        <input type="text" name="beginIssue"  class="style_int_num"  value="<bean:write name="sscConfigInfo" property="beginIssue"/>" size=10>
			        �ں���д��ʽΪ��7000001�� 7��ʾ2007��.000001��ʾ�ںš�<font color=red>ע�⣺7ǰ���ɼ�0��7�󱣳�6λ���粻��д��07001</font>
			        </td>
			        
			      </tr>

 		   <%
             //��������ʾ����ʱ���������ʱ���ƫ��������+����ʾϵͳ������/����ʱ��ȷ�������ʱ����ǰ����-����ʾϵͳ�Ŀ���ʱ��ȷ�������ʱ���ͺ�
		   %>
 
				  <tr>					                    
                     <td >
                       <b>����/����ʱ�����(����Ϊ��λ)</b><br>
					   ����/����ʱ�����������ʱ��
					   <select name="offsetFlag" id="offsetFlag"> 
					      <option value="+" <c:if test="${sscConfigInfo.offsetFlag=='+'}">selected</c:if>>��ǰ</option>
					      <option value="-" <c:if test="${sscConfigInfo.offsetFlag=='-'}">selected</c:if>>�ͺ�</option>
					   </select>&nbsp;&nbsp;
                        <input type="text" name="offsetTime" id="offsetTime" class="style_int_num"  size=10 value='<c:out value="${sscConfigInfo.offsetTime}"/>'/>��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 					   <br>
                        <font color=red>
                        ˵��:<br>���������ʱ����15:30:00����ô����ǰ10�롱����ʾʵ�ʵ�����/����ʱ����"15:29:50";"�ͺ�10��"��ʾʵ�ʵ�����/����ʱ���ǡ�15:30:10����<br>
						 �������/����ʱ����Ҫ�ͷ�����ʱ��һ�£���ô��ƫ��ʱ������Ϊ0���ɣ�Ĭ��)
                        </font>
			         </td>
			      </tr>
<%/*
        <tr>
           <td colspan=1>
             <table border=1 width="100%">
                  <tr>					                    
                     <td >
                       <b>����/����ʱ�����(����Ϊ��λ)</b><br>
					   ����/����ʱ�����������ʱ��
					   <select name="offsetFlag" id="offsetFlag"> 
					      <option value="+" <c:if test="${sscConfigInfo.offsetFlag=='+'}">selected</c:if>>��ǰ</option>
					      <option value="-" <c:if test="${sscConfigInfo.offsetFlag=='-'}">selected</c:if>>�ͺ�</option>
					   </select>&nbsp;&nbsp;
                        <input type="text" name="offsetTime" id="offsetTime" class="style_int_num"  size=10 value='<c:out value="${sscConfigInfo.offsetTime}"/>'/>��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="button" name="subreg" value="���沢����" onClick="toSubmitForm('saveBaseInfo')">   
					   <br>
                        <font color=red>
                        ˵��:<br>���������ʱ����15:30:00����ô����ǰ10�롱����ʾʵ�ʵ�����/����ʱ����"15:29:50";"�ͺ�10��"��ʾʵ�ʵ�����/����ʱ���ǡ�15:30:10����<br>
						 �������/����ʱ����Ҫ�ͷ�����ʱ��һ�£���ô��ƫ��ʱ������Ϊ0���ɣ�Ĭ��)
                        </font>
			         </td>
			      </tr>
				  
				</table>
		    </td>
	   </tr>
*/%>



		       </form>
	         </table>
         </td>
      </tr>
      
      <%
  //����һ��ɾ�� 2013-5-27
%>
      <tr>
        <td>
           <table>
              <tr>
                 <td>
				     <b>����һ��ɾ��</b> <input type="button" name="btndel" value="������м�¼" onClick="toDelAllRecord()">   <br><br>
				  
                        <font color=red>
                        ˵��:�ò�����ɾ����ǰ�淨�����е�Ͷע��¼���н���¼�����������!
                        </font>
                 </td>
              </tr> 
		   </table>
         </td>
      </tr>  


      <tr>
        <td>
           <table>
              <tr>
                 <td>�޸Ľ�����
                 </td>
              </tr>
              <tr>
                 <td>
                   <form name="formprmonup" action="" method="post" style="margin-bottom:10px;margin-top:10px;">
                      <input type="hidden" name="play" value="ssc">
                      <SELECT id="rule" name="rule"  onchange="change_val(this.value)">
						<option value="">��ѡ��...</option>
		                 <OPTION VALUE="wuxingzhixuan" >����ֱѡ
		                 
						 
						 <OPTION VALUE="wuxingtx" >����ͨѡһ�Ƚ�
						 <OPTION VALUE="wuxingqian3" >����ͨѡ���Ƚ�
						 <!--
		                 <OPTION VALUE="wuxinghou3" >����ֱѡ����
						 -->
		                 <OPTION VALUE="wuxingqian2" >����ͨѡ���Ƚ�
						 <!--
		                 <OPTION VALUE="wuxinghou2" >����ֱѡ���
						 -->
		                 <OPTION VALUE="wuxingzu120" >������ѡһ�ٶ�ʮ
		                 <OPTION VALUE="wuxingzu60" >������ѡ��ʮ
		                 <OPTION VALUE="wuxingzu30" >������ѡ��ʮ
		                 <OPTION VALUE="wuxingzu20" >������ѡ��ʮ
		                 <OPTION VALUE="wuxingzu10" >������ѡʮ
		                 <OPTION VALUE="wuxingzu5" >������ѡ��
		                 <OPTION VALUE="wuxingzuchong2" >������ѡ���غ�ȫ��
		                 <OPTION VALUE="wuxingzuchong3" >������ѡ���غ�ȫ��
		                 <OPTION VALUE="wuxingzuchong4" >������ѡ���غ�ȫ��

		                 <OPTION VALUE="sixingzhixuan" >����ֱѡ
		                 <OPTION VALUE="sixingzu24" >������ѡ��ʮ��
		                 <OPTION VALUE="sixingzu12" >������ѡʮ��
		                 <OPTION VALUE="sixingzu6" >������ѡ��
		                 <OPTION VALUE="sixingzu4" >������ѡ��

		                 <OPTION VALUE="sanxingzhixuan" >����ֱѡ
		                 <OPTION VALUE="sanxingzu3" >������3
		                 <OPTION VALUE="sanxingzu6" >������6
		                 <OPTION VALUE="erxingzhixuan" >����ֱѡ
		                 <OPTION VALUE="erxingzuxuan" >������ѡ
		                 <OPTION VALUE="yixingzhixuan" >һ��ֱѡ
		                 <OPTION VALUE="dxdszhixuan" >��С��˫
		                 <OPTION VALUE="renxuan1" >��ѡ1
		                 <OPTION VALUE="renxuan2" >��ѡ2
		                 <OPTION VALUE="renxuan3" >��ѡ3
		              </SELECT>
		              <input type="text" name="money" id="money" value="0" class="style_int_num" size=20 >
					  &nbsp;&nbsp;
					  ���Ʊ�����<input type="text" name="limitOneIssue" id="limitOneIssue" value="0" size=10>��
		              <input type="button" name="subprmon"   class="style_int_num"  value="����" onclick="toSubmitLimit()">   	
					  
					   <c:if test="${saveLimitInfo != null && saveLimitInfo != ''}">
							  <font color="red"><c:out value="${saveLimitInfo}"/></font>
					    </c:if>
                   </form>
                 </td>
              </tr>   
              <tr>
              <%PrMoney objPM=new PrMoney(); %>
                 <td>��ǰ����Ϊ: <br>
                    <%=objPM.getSscMoneyAndLimit("ssc")%>
                 </td>
              </tr>
           </table>
        </td>
      </tr>
</table>

</body>
</html>