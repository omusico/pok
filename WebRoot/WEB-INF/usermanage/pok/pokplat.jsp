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
function subPok(act){
  if(act=="plus"){
    document.formpokwin.action="/servlet/winnumplus";
    document.formpokwin.submit();
  }
  if(act=="query"){
    document.formpokque.action="/servlet/winquery";
    document.formpokque.submit();
  }
  
}
//�õ���Ӧ��ֵ
function change_val(val){
	//alert(val);
	DwrBean.getMoneyInfo("poker",val,function(data){callback_fun(data);});
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
		DwrBean.delAllRecordOfPoker(function(data){callback_delAll(data);});
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
<input type="hidden" name="pagemark" value="pokplat">

<table width="910" border="1" align="center" cellpadding="0" cellspacing="0">
<input type="hidden" name="hidmulissvalue" value="">
<input type="hidden" name="hidissuetype" value="">
<input type="hidden" name="hidselling" value="false">
<tr><td align=center style="font:bold; font-size=13px; color:red"><span>�� �� �� ��</span></td></tr>						  
      <tr>
         <td>
          <table>
           <tr>
           <td align=center>
             <iframe name="fratime" id="fratime" frameborder="0" width="700" height="30"  scrolling="no" src="/lottpok/isstime.jsp"></iframe>
           </td>  
           <td align=center width=220></td>
           </tr>
          </table>
         </td>          
       </tr>
        <tr>
           <td>
            <table>
              <tr>
	           <td align=left style="padding-bottom:10px;padding-top:10px;padding-left:10px;">
	              <a href=/servlet/winpageshow>�н������ѯ</a>
	           </td>
	           <td>
				  <iframe name="fratreent" id="fratreent" frameborder="0" width="200" height="30"  scrolling="no" src="/pubpage/trend/pokent.jsp"></iframe>
              </td>
              </tr>
            </table>
           </td>
        </tr>
        <tr>
           <td>
              <table border=0>
                 <form name="formpokwin" action="" style="margin-bottom:10px;margin-top:10px;">
                 <tr>
                  <td style="color:red">���"�н��������"��ť��ϵͳ�Զ�ȡ���н��û���Ϣ��
                  </td>
                 </tr>
                  <tr>					                    
                     <td>
                        �ں�:<input type="text" name="pokissnum" value="" size=12>
					 �н�����:
					     <% 
						   String[] arrNumValue={"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
						   String[] arrPlaType={"����","����","÷��","����"};
						   String[] arrPlaTypeNamePlus={"spade","heart","club","diamond"};
						   for(int i=0;i<arrPlaType.length;i++){
							   out.println(arrPlaType[i]+":<select name='"+arrPlaTypeNamePlus[i]+"'>");
							   for(int j=0;j<arrNumValue.length;j++){
								   out.println("<option value='"+arrNumValue[j]+"'>"+arrNumValue[j]);
							   }
							   out.println("</select>");
						   }%>
					 </td>
					 <td>
			           <input type="button" name="butpokplus" value="�н��������" onClick="subPok('plus')"> 
   				       <font color=red><%=objGM.removeNull((String)request.getAttribute("remMessage"))%></font>
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
        <form name="formpokque" action="" style="margin-bottom:10px;margin-top:10px;">
           �ں�:<input type="text" name="pokissnum" value="" size=12>
          <input type="button" name="butpokquery" value="�н��û���ѯ" onClick="subPok('query')"> 
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
           objIssCI.getIssCon("pokisscon");%>
              <table border=1>
                 <form name="formregtime" action="/servlet/pokisscon?play=pok" method="post" style="margin-bottom:10px;margin-top:10px;">
                  <tr>					                    
                     <td >
                        �ںŵ���:
                        <font color=red>
                        ˵��:Ĭ�ϴ��糿(9:00)��ʼ���ۣ�ÿ������(10)���ӣ�Ȼ��ֹͣ���۽���(5)���ӵĿ�����ÿ��һ������(53)�ڡ����Ų��ֿ��������á�
                        </font>
			         </td>
					 <td rowspan="5">
			           <input type="submit" name="subreg" value="����">   				   
					   <font color=red></font>
			         </td>
			      </tr>
			      <tr>
			        <td >
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
			        <input type="text" name="begiss"  size=10 value="<%=objIssCI.getStrBegIss() %>">
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
                      <input type="hidden" name="play" value="pok">
                      <SELECT id="rule" NAME="rule"  onchange="change_val(this.value)">
						<option value="">��ѡ��...</option>
		                 <OPTION VALUE="pokone" >��ѡһ
		                 <OPTION VALUE="poktwo" >��ѡ��
		                 <OPTION VALUE="pokthrthr" >��ѡ����3
		                 <OPTION VALUE="pokthrtwo" >��ѡ����2
		                 <OPTION VALUE="pokfourfour" >ѡ��ֱѡ��4
		                 <OPTION VALUE="pokfourthr" >ѡ��ֱѡ��3
		                 <OPTION VALUE="pokgrfour" >ѡ����ѡ4
		                 <OPTION VALUE="pokgrsix" >ѡ����ѡ6
		                 <OPTION VALUE="pokgrtwelve" >ѡ����ѡ12
		                 <OPTION VALUE="pokgrtwfour" >ѡ����ѡ24
		              </SELECT>
		              <input type="text" name="money" value="0" size=20>
					  &nbsp;&nbsp;
					  ���Ʊ�����<input type="text" name="limitDegree" id="limitDegree" value="0" size=10>��
		              <input type="submit" name="subprmon" value="����">   		
                   </form>
                 </td>
              </tr>   
              <tr>
              <%PrMoney objPM=new PrMoney(); %>
                 <td>��ǰ����Ϊ: <br>
                 ��ѡһ:<%=objPM.getPrMoney("pokprmoney","pokone") %>Ԫ
                     ��ѡ��:<%=objPM.getPrMoney("pokprmoney","poktwo") %>Ԫ
                     ��ѡ����3:<%=objPM.getPrMoney("pokprmoney","pokthrthr") %>Ԫ
                     ��ѡ����2:<%=objPM.getPrMoney("pokprmoney","pokthrtwo") %>Ԫ
                     ѡ��ֱѡ��4:<%=objPM.getPrMoney("pokprmoney","pokfourfour") %>Ԫ
                     ѡ��ֱѡ��3:<%=objPM.getPrMoney("pokprmoney","pokfourthr") %>Ԫ
                     ��4:<%=objPM.getPrMoney("pokprmoney","pokgrfour") %>Ԫ
                     ��6:<%=objPM.getPrMoney("pokprmoney","pokgrsix") %>Ԫ
                     ��12:<%=objPM.getPrMoney("pokprmoney","pokgrtwelve") %>Ԫ
                     ��24:<%=objPM.getPrMoney("pokprmoney","pokgrtwfour") %>Ԫ
                 </td>
              </tr>
           </table>
        </td>
      </tr>
</table>

</body>
</html>