<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true" 
    import="beanpac.IssConInfo,beanpac.PrMoney,pubmethpac.GlobalMeth"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%GlobalMeth objGM=new GlobalMeth(); %>
<html>
<head>
<title>管理平台</title>
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
//得到相应的值
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

//一键删除
function toDelAllRecord(){
	alert("该操作将删除当前玩法下所有的投注记录和中奖记录，请谨慎操作! ");
    if(confirm("删除后将无法恢复，确实要删除吗？")){
		DwrBean.delAllRecordOfPoker(function(data){callback_delAll(data);});
	}
}
function callback_delAll(data){
	if(data){
		alert("删除成功!");
	}else{
		alert("删除失败!");
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
<tr><td align=center style="font:bold; font-size=13px; color:red"><span>快 乐 扑 克</span></td></tr>						  
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
	              <a href=/servlet/winpageshow>中奖号码查询</a>
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
                  <td style="color:red">点击"中奖号码添加"按钮后，系统自动取得中奖用户信息。
                  </td>
                 </tr>
                  <tr>					                    
                     <td>
                        期号:<input type="text" name="pokissnum" value="" size=12>
					 中奖号码:
					     <% 
						   String[] arrNumValue={"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
						   String[] arrPlaType={"黑桃","红桃","梅花","方块"};
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
			           <input type="button" name="butpokplus" value="中奖号码添加" onClick="subPok('plus')"> 
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
      <tr><td style="color:red">请输入"期号"值,并点击"中奖用户查询"按钮查询。</td>
      </tr>
      <tr>
        <td>
        <form name="formpokque" action="" style="margin-bottom:10px;margin-top:10px;">
           期号:<input type="text" name="pokissnum" value="" size=12>
          <input type="button" name="butpokquery" value="中奖用户查询" onClick="subPok('query')"> 
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
                        期号调整:
                        <font color=red>
                        说明:默认从早晨(9:00)开始销售，每期销售(10)分钟，然后停止销售进行(5)分钟的开奖，每天一共销售(53)期。括号部分可重新设置。
                        </font>
			         </td>
					 <td rowspan="5">
			           <input type="submit" name="subreg" value="启动">   				   
					   <font color=red></font>
			         </td>
			      </tr>
			      <tr>
			        <td >
                        每天开始时间:
                        <input type="text" name="beghour" value="<%=objIssCI.getStrBegHour() %>" size=2>时
                        <input type="text" name="begmin" value="<%=objIssCI.getStrBegMin() %>" size=2>分					 
                        ,每期销售:
                        <input type="text" name="dursell" value="<%=objIssCI.getStrDurSell() %>" size=2>分钟
                        ,进行
                        <input type="text" name="durwin" value="<%=objIssCI.getStrDurWin() %>" size=2>分钟的开奖
                        ,每天共销售
                        <input type="text" name="howmanyiss" value="<%=objIssCI.getStrHMIss() %>" size=2>期
					 </td>
			      </tr>
			      <tr>
			        <td>
			        今天开始期号为:
			        <input type="text" name="begiss"  size=10 value="<%=objIssCI.getStrBegIss() %>">
			        期号填写样式为：7000001。 7表示2007年.000001表示期号。<font color=red>注意：7前不可加0，7后保持6位，如不可写成07001</font>
			        </td>
			        
			      </tr>


               <tr>
                 <td><b>销售/开奖时间调整(以秒为单位)</b></td>
               </tr>
                <tr>
                  <td >
					   销售/开奖时间与服务器的时间
					   <select name="offsetFlag" id="offsetFlag"> 
					      <option value="+" <%if(objIssCI.getOffsetFlag().equals("+")){%>selected<%}%>>提前</option>
					      <option value="-" <%if(objIssCI.getOffsetFlag().equals("-")){%>selected<%}%>>滞后</option>
					   </select>&nbsp;&nbsp;
                        <input type="text" name="offsetTime" id="offsetTime" class="style_int_num"  size=10 value='<%=objIssCI.getOffsetTime() %>'/>秒
					   <br>
                        <font color=red>
                        说明:(以服务器时间是15:30:00为例)<br>
                         (1)提前10秒：表示实际的销售/开奖时间是"15:29:50";<br>
						 (2)滞后10秒：表示实际的销售/开奖时间是“15:30:10”；<br>
						 (3)如果销售/开奖时间需要和服务器时间一致，那么将偏移时间设置为0即可（默认)
                        </font>
			         </td>
               </tr>


		       </form>
	         </table>
         </td>
      </tr>
     
 <%
  //超级一键删除 2013-5-27
%>
      <tr>
        <td>
           <table>
              <tr>
                 <td>
				     <b>超级一键删除</b> <input type="button" name="btndel" value="清除所有记录" onClick="toDelAllRecord()">   <br><br>
				  
                        <font color=red>
                        说明:该操作将删除当前玩法下所有的投注记录和中奖记录，请谨慎操作!
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
                 <td>修改奖金金额
                 </td>
              </tr>
              <tr>
                 <td>
                   <form name="formprmonup" action="/servlet/prmon" method="post" style="margin-bottom:10px;margin-top:10px;">
                      <input type="hidden" name="play" value="pok">
                      <SELECT id="rule" NAME="rule"  onchange="change_val(this.value)">
						<option value="">请选择...</option>
		                 <OPTION VALUE="pokone" >任选一
		                 <OPTION VALUE="poktwo" >任选二
		                 <OPTION VALUE="pokthrthr" >任选三中3
		                 <OPTION VALUE="pokthrtwo" >任选三中2
		                 <OPTION VALUE="pokfourfour" >选四直选中4
		                 <OPTION VALUE="pokfourthr" >选四直选中3
		                 <OPTION VALUE="pokgrfour" >选四组选4
		                 <OPTION VALUE="pokgrsix" >选四组选6
		                 <OPTION VALUE="pokgrtwelve" >选四组选12
		                 <OPTION VALUE="pokgrtwfour" >选四组选24
		              </SELECT>
		              <input type="text" name="money" value="0" size=20>
					  &nbsp;&nbsp;
					  限制倍数：<input type="text" name="limitDegree" id="limitDegree" value="0" size=10>倍
		              <input type="submit" name="subprmon" value="更新">   		
                   </form>
                 </td>
              </tr>   
              <tr>
              <%PrMoney objPM=new PrMoney(); %>
                 <td>当前奖金为: <br>
                 任选一:<%=objPM.getPrMoney("pokprmoney","pokone") %>元
                     任选二:<%=objPM.getPrMoney("pokprmoney","poktwo") %>元
                     任选三中3:<%=objPM.getPrMoney("pokprmoney","pokthrthr") %>元
                     任选三中2:<%=objPM.getPrMoney("pokprmoney","pokthrtwo") %>元
                     选四直选中4:<%=objPM.getPrMoney("pokprmoney","pokfourfour") %>元
                     选四直选中3:<%=objPM.getPrMoney("pokprmoney","pokfourthr") %>元
                     组4:<%=objPM.getPrMoney("pokprmoney","pokgrfour") %>元
                     组6:<%=objPM.getPrMoney("pokprmoney","pokgrsix") %>元
                     组12:<%=objPM.getPrMoney("pokprmoney","pokgrtwelve") %>元
                     组24:<%=objPM.getPrMoney("pokprmoney","pokgrtwfour") %>元
                 </td>
              </tr>
           </table>
        </td>
      </tr>
</table>

</body>
</html>