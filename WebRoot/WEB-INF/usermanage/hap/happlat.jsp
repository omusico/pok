<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true" 
    import="beanpac.IssConInfo,beanpac.PrMoney,pubmethpac.GlobalMeth"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%GlobalMeth objGM=new GlobalMeth(); %>
<html>
<head>
<title>����ƽ̨</title>
<script type='text/javascript' src='/dwr/interface/DwrBean.js'></script>
<script type='text/javascript' src='/dwr/engine.js'></script>
<script type='text/javascript' src='/dwr/util.js'></script>

<script Language="JavaScript">
function subHapIssCon(act){
  document.all("hidhapisscon").value=act;
  document.formhapisscon.submit();
}
function subHap(act){
  if(act=="plus"){
    document.formhapwin.action="/servlet/hapwinnumplus";
    document.formhapwin.submit();
  }
  if(act=="query"){
    document.formhapque.action="/servlet/hapwinquery";
    document.formhapque.submit();
  }
  
}
//�õ���Ӧ��ֵ
function change_val(val){
	//alert(val);
	DwrBean.getMoneyInfo("hap",val,function(data){callback_fun(data);});
}
function callback_fun(data){
	//alert(data);
	if(data != null){
		var str = data.split(",");
	//	alert(str[0]);
		document.getElementById("money").value = str[0];
		document.getElementById("limitDegree").value = str[1];
	}
}


//һ��ɾ��
function toDelAllRecord(){
	alert("�ò�����ɾ����ǰ�淨�����е�Ͷע��¼���н���¼�����������! ");
    if(confirm("ɾ�����޷��ָ���ȷʵҪɾ����")){
		DwrBean.delAllRecordOfHappy(function(data){callback_delAll(data);});
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
</style>
</head>

<body>

<input type="hidden" name="pagemark" value="happlat">

<table width="910" border="1" align="center" cellpadding="0" cellspacing="0">
<tr><td align=center style="font:bold; font-size=13px; color:red"><span>�� �� 3 D</span></td></tr>
		              <tr>
		                <td>
		                 <table>
		                  <tr>
				          <td align=center>
                            <iframe name="fratime" id="fratime" frameborder="0" width="700" height="30"  scrolling="no" src="/lott/hap/hapisstime.jsp"></iframe>
                          </td>  
                          <td align=center width=220></td>
                          </tr>
                         </table>
                        </td>          
				      </tr>
                      <tr>
	                      <td>
	                      <table border=0>
	                      <tr>
	                        <td align=left style="padding-bottom:10px;padding-top:10px;padding-left:10px;">
	                          <a href=/servlet/hapwinpageshow>�н������ѯ</a>
	                        </td>
	                        <td align=left style="padding-bottom:0px;padding-top:0px;padding-left:10px;">
					            <iframe name="fratreent" id="fratreent" frameborder="0" width="200" height="30"  scrolling="no" src="/pubpage/trend/hapent.jsp"></iframe>
				            </td>
				          </tr>
				          </table>
				          </td>
                      </tr>
                      <tr>
                      <td>
	                    <table border=1>
	                      <form name="formhapwin" action="" style="margin-bottom:10px;margin-top:10px;">
	                       <tr>
	                          <td>
		                               �ں�:<input type="text" name="hapissnum" value="" size=12>
							         �н�����:
							     <%
							       String[] arrNumValue={"0","1","2","3","4","5","6","7","8","9"};
								   String[] arrPlaType={"��λ","ʮλ","��λ"};
								   String[] arrPlaTypeNamePlus={"hundred","ten","one"};
								   for(int i=0;i<arrPlaType.length;i++){
									   out.println(arrPlaType[i]+":<select name='"+arrPlaTypeNamePlus[i]+"'>");
									   for(int j=0;j<arrNumValue.length;j++){
										   out.println("<option value='"+arrNumValue[j]+"'>"+arrNumValue[j]);
									   }
									   out.println("</select>");
								   }%>
							   </td>
							   <td>
						         <input type="button" name="bhapwinplus" value="�н��������" onClick="subHap('plus')"> 
								 <font color=red><%=objGM.removeNull((String)request.getAttribute("remMessage")) %></font>
							 
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
		        <form name="formhapque" action="" style="margin-bottom:10px;margin-top:10px;">
		           �ں�:<input type="text" name="hapissnum" value="" size=12>
		          <input type="button" name="bhapwinquery" value="�н��û���ѯ" onClick="subHap('query')"> 
				  <font color=red></font>
		        </form>        
		        </td>
		      </tr>
		      </table>
		      </td>
		   </tr>
		   
                    <tr>
           <td>
           <%IssConInfo objIssCI=new IssConInfo();
           objIssCI.getIssCon("hapisscon");%>
              <table border=1>
                 <form name="formregtime" action="/servlet/pokisscon?play=hap" method="post" style="margin-bottom:10px;margin-top:10px;">
                  <tr>					                    
                     <td >
                        �ںŵ���:
                        <font color=red>
                        ˵��:Ĭ�ϴ��糿(9:00)��ʼ���ۣ�ÿ������(480)����(8��Сʱ)һ��һ�ڡ����Ų��ֿ��������á�
                        </font>
			         </td>
					  <td rowspan=5>
			           <input type="submit" name="subreg" value="����">   				   
					   <font color=red></font>
			         </td>
			      </tr>
			      <tr>
			        <td colspan=1>
                        ÿ�쿪ʼʱ��:
                        <input type="text" name="beghour" value="<%=objIssCI.getStrBegHour() %>" size=2>ʱ
                        <input type="text" name="begmin" value="<%=objIssCI.getStrBegMin() %>" size=2>��					 
                        ,ÿ������:
                        <input type="text" name="dursell" value="<%=objIssCI.getStrDurSell() %>" size=2>����
                        ,����
                        <input type="text" name="durwin" value="<%=objIssCI.getStrDurWin() %>" size=2>���ӵĿ���            
                        ,ÿ�칲����
                        <input type="text" name="howmanyiss" value="<%=objIssCI.getStrHMIss() %>" size=2>��
					 </td>
			      </tr>
			      <tr>
			        <td>
			        ���쿪ʼ�ں�Ϊ:
			        <input type="text" name="begiss" value="<%=objIssCI.getStrBegIss() %>" size=10>
			        �ں���д��ʽΪ��7000001�� 7��ʾ2007��.000001��ʾ�ںš�<font color=red>ע�⣺7ǰ���ɼ�0��7�󱣳�6λ���粻��д��07001</font>
			        </td>
			       
			      </tr>


               <tr>
                 <td><b>����/����ʱ�����(����Ϊ��λ)</b></td>
               </tr>
                <tr>
                  <td >
					   ����/����ʱ�����������ʱ��
					   <select name="offsetFlag" id="offsetFlag"> 
					      <option value="+" <%if(objIssCI.getOffsetFlag().equals("+")){%>selected<%}%>>��ǰ</option>
					      <option value="-" <%if(objIssCI.getOffsetFlag().equals("-")){%>selected<%}%>>�ͺ�</option>
					   </select>&nbsp;&nbsp;
                        <input type="text" name="offsetTime" id="offsetTime" class="style_int_num"  size=10 value='<%=objIssCI.getOffsetTime() %>'/>��
					   <br>
                        <font color=red>
                        ˵��:(�Է�����ʱ����15:30:00Ϊ��)<br>
                         (1)��ǰ10�룺��ʾʵ�ʵ�����/����ʱ����"15:29:50";<br>
						 (2)�ͺ�10�룺��ʾʵ�ʵ�����/����ʱ���ǡ�15:30:10����<br>
						 (3)�������/����ʱ����Ҫ�ͷ�����ʱ��һ�£���ô��ƫ��ʱ������Ϊ0���ɣ�Ĭ��)
                        </font>
			         </td>
               </tr>


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
                   <form name="formprmonup" action="/servlet/prmon" method="post" style="margin-bottom:10px;margin-top:10px;">
                      <input type="hidden" name="play" value="hap">
                      <SELECT id="rule" NAME="rule" onchange="change_val(this.value)">
					  <option value="">��ѡ��...</option>
		                 <OPTION VALUE="hapsingthr"  >ֱѡ
		                 <OPTION VALUE="hapgrthr" >����
		                 <OPTION VALUE="hapgrsix" >����
		                 
		              </SELECT>
		              <input type="text" name="money" value="0" size=20>
					   &nbsp;&nbsp;
					  ���Ʊ�����<input type="text" name="limitDegree" id="limitDegree" value="0" size=10>��
					   &nbsp;&nbsp;
		              <input type="submit" name="subprmon" value="����">   		
                   </form>
                 </td>
              </tr>   
              <tr>
              <%PrMoney objPM=new PrMoney(); %>
                 <td>��ǰ����Ϊ:<br>
                 ֱѡ:<%=objPM.getPrMoney("happrmoney","hapsingthr") %>Ԫ
                 ����:<%=objPM.getPrMoney("happrmoney","hapgrthr") %>Ԫ
                 ����:<%=objPM.getPrMoney("happrmoney","hapgrsix") %>Ԫ
                 
                 </td>
              </tr>
           </table>
        </td>
      </tr>
      
      
</table>

</body>
</html>