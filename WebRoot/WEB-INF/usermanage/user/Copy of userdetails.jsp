<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="dbconnpac.DataBaseConn, pubmethpac.GlobalMeth" %>
<%
GlobalMeth objGM=new GlobalMeth();
DataBaseConn userDC = (DataBaseConn) session.getAttribute("specuserdc");
DataBaseConn userWagerC = (DataBaseConn) session.getAttribute("specuserwagerc");
DataBaseConn userPrizeC = (DataBaseConn) session.getAttribute("specuserprizec");
%>
<html>
<head>
<title>用户详细信息</title>
<script Language="JavaScript">
function subRemit(act){
  document.all("dowhat").value=act;
  document.formremit.submit();
}
function subFre(act){
  document.all("fredowhat").value=act;
  document.formfre.submit();
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
.image2 li { float:left; color:#FFF; margin:0 20px}
.image2 a:link,.image2 a:visited { color:#FFF; font-weight:bold; margin:0 2px 0 5px; text-decoration:none;}
.image2 a:active,.image2 a:hover { color:#FFFF00;}

.pad1 { padding-left:20px}
</style>
</head>

<body>

<table align=center width=910 height=400 border=1 cellpadding=0 cellspacing=0 bordercolor=red>
 <tr>
  <td>
   <table align=center width=910 height=170 border=1 cellpadding=0 cellspacing=0 bordercolor=pink>
    <tr><td colspan=6 height=30 align=center>用户详细信息</td></tr>
    <tr height=20 class=pad1>
     <td  align=left>用户名:<%=userDC.rsGetString("username")%><br></td>
     <td  align=left>身份证号:<%=userDC.rsGetString("idcardnumber")%><br></td>
     <td  align=left>手机:<%=userDC.rsGetString("mobilephone")%><br></td>
    </tr>
    <tr height=20 class=pad1>
     <td  align=left>性别:<%=userDC.rsGetString("sex")%><br></td>
     <td  align=left>E-mail:<%=userDC.rsGetString("email")%><br></td>
     <td  align=left>联系电话:<%=userDC.rsGetString("contactphone")%><br></td>
    </tr>
    <tr height=20 class=pad1>
     <td  align=left>真实姓名:<%=userDC.rsGetString("realname")%><br></td>
     <td  align=left>联系地址:<%=userDC.rsGetString("address")%><br></td>
     <td  align=left>MSN:<%=userDC.rsGetString("msnnum")%><br></td>
    </tr>
    <tr height=20 class=pad1>
     <td  align=left>QQ:<%=userDC.rsGetString("qqnum")%><br></td>
     <td  align=left>汇款总额:<%=userDC.rsGetString("totalmoney")%>元<br></td>
     <td  align=left>提款状态:<%=userDC.rsGetString("drawstate")%><br></td>
    </tr>
    <tr height=20 class=pad1>
     <td  align=left>冻结状态:<%=userDC.rsGetString("freeze")%><br></td>
     <td  align=left> <br></td>
     <td  align=left> <br></td>
    </tr>
   </table>
  </td>
 </tr>
  <tr>
  <td>
   <table align=center width=910 height=30 border=1 cellpadding=0 cellspacing=0 bordercolor=pink>
	    <tr>
	     <td colspan=2 height=30 align=center>信息操作</td>
	    </tr>
	    <tr height=30 valign=top>
		     <td width=300 colspan=2>
		      <form name="formremit" action="/servlet/useinfoper?oper=remitoper" method="post">
		       汇款额：
		      <input name="remitquantity" type="text"size="12">元
		      <input type="hidden" name="dowhat">
		      <input type="button" name="plusremit" value="添加" onClick="subRemit('plus')">
		      <input type="button" name="delremit" value="减少" onClick="subRemit('del')">
		      </form>
		     </td>
	    </tr>
	    <tr height=30 valign=top>
		     <td width=300 colspan=2>
		      <form name="formfre" action="/servlet/useinfoper?oper=freeze" method="post">
		      用户投注限制:
		      <input type="hidden" name="fredowhat">
		      <input type="button" name="fre" value="冻结" onClick="subFre('fre')">
		      <input type="button" name="unfre" value="解冻" onClick="subFre('unfre')">
		      </form>
		     </td>
	    </tr>
	    <tr>
	        <tr height=30 valign=top>
		     <td width=300 colspan=2>
		      <form name="formresrequ" action="/servlet/useinfoper?oper=resrequ" method="post">
		      还原请求状态:
		      <input type="submit" name="restore" value="还原">
		      </form>
		     </td>
	    </tr>
        
        <tr>
          <td align=left style="font-size:13px;font-weight:bold;color:red">
          提示：对多期跟号进行删除时，与"标识期号"连带的所有"同次"期号将一并删除，用户金额将被还原。<br>
          标识期号:是指用户在这一期销售时进行投注。<br>
          同次：是指用户在同一期，同一次数序号(因为用户可以在同一期投注多次。)
          </td>
        </tr>
   <%
     int intWagNumPok=0;
     int intWagNumLau=0;
     int intWagNumHap=0;
     int intWagNumPth=0;
   %>
  <tr>
     <td>
	         <table>
	          <tr>
	           <td align=center style="font-size:13px;font-weight:bold;color:red">快乐扑克</td>
	          </tr>
			  <tr>
			  <td>
			   <table align=center width=910 height=90 border=1 cellpadding=0 cellspacing=0 bordercolor=pink>
			    <tr><td colspan=11 height=30 align=center>投注记录
			    <font color=red>
			    <%=objGM.removeNull((String)request.getAttribute("strrempok"))%></font></td></tr>
			    <tr height=20 align=center>
			     <td width=60>标识期号</td>
			     <td width=60>投注期号</td>
			     <td width=30>次数序号</td>
			     <td width=30>多期跟号</td>
			     <td width=70>投注类型<br></td>
			     <td width=70>投注方式</td>
			     <td width=430>投注号码</td>
			     <td width=60>注数</td>
			     <td width=40>倍数</td>
			     <td width=40>操作</td>
			    </tr>
			    <%while(userWagerC.rsNext())
			    {
			    	int intMarkIss=Integer.parseInt(userWagerC.rsGetString("markiss").trim());
			    	int intIssueNum=Integer.parseInt(userWagerC.rsGetString("issuenum").trim());
			    	String strHasFolPok=userWagerC.rsGetString("hasfol");
			        String hasFolShowPok="";
			        if(strHasFolPok.equals("n")){
			    	    hasFolShowPok="无";
			        }else{
			    	    hasFolShowPok="有";
			        }
			     %>
			    <tr height=20 >
			     <td width=60 align=center><%=intMarkIss%><BR></td>
			     <td width=60 align=center><%=intIssueNum%><BR></td>
			     <td width=30 align=center><%=userWagerC.rsGetString("markissseq")%><BR></td>
			     <td width=30 align=center><%=hasFolShowPok%><BR></td>
			     <td width=70 align=center><%=userWagerC.rsGetString("playtype")%><BR></td>
			     <td width=70 align=center>
			     <%
			     String strPokMode=userWagerC.rsGetString("playmode");
			     out.println(strPokMode);
			     %><BR></td>
			     <td style= "WIDTH: 430; WORD-WRAP: break-word " align=left>
			     <%
			     String strPokWagTot=userWagerC.rsGetString("wagertotal");
			     if(strPokMode.equals("和值")){
			    	 String[] arrDolSegStr=strPokWagTot.split("\\$");
			    	 int dolLen=arrDolSegStr.length;
			    	 int[] dolSegTotNum=new int[dolLen];
			    	 String objStr="";
			    	 for(int i=0;i<dolLen;i++){
			    		 String[] arrVerLineSegStr=arrDolSegStr[i].split("\\|");
			    		 for(int j=0;j<arrVerLineSegStr.length;j++){
			    			 String onePok=arrVerLineSegStr[j];
			    			 String onePokNew=onePok.replace("A","1").replace("J","11").replace("Q","12").replace("K","13").replace("=","0");
			    			 dolSegTotNum[i]=dolSegTotNum[i]+Integer.parseInt(onePokNew);
			    		 }
			    		 if(i!=0 && dolSegTotNum[i]==dolSegTotNum[i-1]){
			    			 objStr=objStr+arrDolSegStr[i]+"$";
			    		 }else{
			    			 objStr=objStr+"<br><font color=red>[和值"+String.valueOf(dolSegTotNum[i])+"]：</font>"+arrDolSegStr[i]+"$";
			    		 }
			    	 }
			    	 out.println(objStr);
			     }else if(strPokMode.equals("跨度")){
			    	 String[] arrDolSegStr=strPokWagTot.split("\\$");
			    	 int dolLen=arrDolSegStr.length;
			    	 int[] dolSegDifNum=new int[dolLen];
			    	 String objStr="";
			    	 for(int i=0;i<dolLen;i++){
			    		 String[] arrVerLineSegStr=arrDolSegStr[i].split("\\|");
			    		 int[] intDifNum=new int[2];
			    		 int index=0;
			    		 for(int j=0;j<arrVerLineSegStr.length;j++){
			    			 String onePok=arrVerLineSegStr[j];
			    			 String onePokNew=onePok.replace("A","1").replace("J","11").replace("Q","12").replace("K","13").replace("=","0");			    			 
			    			 if(!onePokNew.equals("0")){
			    				 intDifNum[index]=Integer.parseInt(onePokNew);
			    				 index++;
			    			 }
			    		 }
			    		 dolSegDifNum[i]=Math.abs(intDifNum[0]-intDifNum[1]);
			    		 if(i!=0 && dolSegDifNum[i]==dolSegDifNum[i-1]){
			    			 objStr=objStr+arrDolSegStr[i]+"$";
			    		 }else{
			    			 objStr=objStr+"<br><font color=red>[跨度"+String.valueOf(dolSegDifNum[i])+"]：</font>"+arrDolSegStr[i]+"$";
			    		 }
			    	 }
			    	 out.println(objStr);
			     }else{
			    	 out.println(strPokWagTot);
			     }
			     %><BR></td>
			     <td style= "WIDTH: 60" align=center>
			     <%int intEachWagNum=Integer.parseInt(userWagerC.rsGetString("wagernum"));
			       out.println(intEachWagNum);%><BR></td>
			     <td style= "WIDTH: 40" align=center>
			     <%int intEachTimes=Integer.parseInt(userWagerC.rsGetString("times"));
			       out.println(intEachTimes);%><BR></td>
			     <td width=40 align=center>
			     <%
			        if(intMarkIss==intIssueNum){
			        	out.print("<a href=servlet/useinfwagrem?play=pok&wagerid="+userWagerC.rsGetString("id")+">删除</a>");
			        }
			     %><BR></td>
			    </tr>
			   <%intWagNumPok=intWagNumPok+intEachWagNum*intEachTimes;}%>
			   </table>
			  </td>
			 </tr>
			 <tr align=center>
			     <td>
			        <table width=300 border=0 cellpadding=0 cellspacing=0 bordercolor=yellow>
			          <tr>
			            <td>所投总注数:</td>
			            <td style="color:red"><%out.println(intWagNumPok); %></td>
			            <td width=30%><br><td>
			            <td>所投总金额:</td>
			            <td style="color:red"><%out.println(intWagNumPok*2); %>元</td>
			          </tr>
			        </table>
			     </td>
			 </tr>
			 <tr>
			  <td>
			   <table align=center width=910 height=90 border=1 cellpadding=0 cellspacing=0 bordercolor=pink>
			    <tr><td colspan=11 height=30 align=center>中奖记录</td></tr>
			    <tr>
		            <td width="7%" height="20" align="center">用户名</td>
		            <td width="7%" align="center">中奖期号</td>
		            <td width="7%" align="center">玩法类型</td>
		            <td width="7%" align="center">玩法模式</td>
		            <td width="7%" align="center">投注号码</td>
		            <td width="7%" align="center">判断规则</td>
		            <td width="7%" align="center">中奖注数</td>
		            <td width="7%" align="center">倍数</td>
		            <td width="7%" align="center">总注数</td>
		            <td width="7%" align="center">奖金</td>
		            <td width="7%" align="center">操作</td>
		          </tr>
		          <%while(userPrizeC.rsNext()){%>
		          <tr>
		            <td align="center"><%=userPrizeC.rsGetString("username") %></td>
		            <td align="center"><%=userPrizeC.rsGetString("issuenum") %></td>
		            <td align="center"><%=userPrizeC.rsGetString("playtype") %></td>
		            <td align="center"><%=userPrizeC.rsGetString("playmode")%><br></td>
		            <td align="center"><%=userPrizeC.rsGetString("onewager") %></td>
		            <td align="center"><%=userPrizeC.rsGetString("rule")%></td>
		            <td align="center"><%Integer IntWagNum=Integer.valueOf(userPrizeC.rsGetString("wagernum"));
		                                 out.println(IntWagNum);%></td>
		            <td align="center"><%Integer IntTimes=Integer.valueOf(userPrizeC.rsGetString("times"));
		                                 out.println(IntTimes);%></td>
		            <td align="center"><%out.println(IntWagNum*IntTimes); %></td>
		            <td align="center"><%=userPrizeC.rsGetString("prize") %></td>
		            <td align="center"><%out.print("<a href=servlet/useinfprdel?play=pok&prid="+userPrizeC.rsGetString("id")+">删除</a>");
			        %></td>
		          </tr>
		          <%  
		          }%>
			   </table>
			  </td>
			 </tr>
			</table>
	 </td>
  </tr>
  
  <tr>
  <%
   DataBaseConn userLauWagC = (DataBaseConn) session.getAttribute("ulauwdbc");
   DataBaseConn userLauPrizeC = (DataBaseConn) session.getAttribute("ulaupdbc");
  %>
        <td>
	        <table>
	          <tr>
	            <td align=center style="font-size:13px;font-weight:bold;color:red">时时乐
	            </td>
	          </tr>
			  <tr>
			  <td>
			   <table align=center width=910 height=90 border=1 cellpadding=0 cellspacing=0 bordercolor=pink>
			    <tr><td colspan=11 height=30 align=center>投注记录
			    <font color=red>
			    <%=objGM.removeNull((String)request.getAttribute("strremlau"))%></font></td></tr>
			    <tr height=20 align=center>
			     <td width=60>标识期号</td>
			     <td width=60>投注期号</td>
			     <td width=30>次数序号</td>
			     <td width=30>多期跟号</td>
			     <td width=70>投注类型<br></td>
			     <td width=70>投注方式</td>
			     <td width=430>投注号码</td>
			     <td width=60>注数</td>
			     <td width=40>倍数</td>
			     <td width=40>操作</td>
			    </tr>
			    <%while(userLauWagC.rsNext()){
			    	
			    	int intMarkIss=Integer.parseInt(userLauWagC.rsGetString("markiss").trim());
			    	int intIssueNum=Integer.parseInt(userLauWagC.rsGetString("issuenum").trim());
			    	String strHasFolPok=userLauWagC.rsGetString("hasfol");
			        String hasFolShowPok="";
			        if(strHasFolPok.equals("n")){
			    	    hasFolShowPok="无";
			        }else{
			    	    hasFolShowPok="有";
			        }
			      %>
			    <tr height=20 align=center>
			     <td width=60 ><%=intMarkIss%><BR></td>
			     <td width=60 ><%=intIssueNum%><BR></td>
			     <td width=30 ><%=userLauWagC.rsGetString("markissseq")%><BR></td>
			     <td width=30 ><%=hasFolShowPok%><BR></td>
			     <td width=70><%=userLauWagC.rsGetString("playtype")%><BR></td>
			     <td width=70><%=userLauWagC.rsGetString("playmode")%><BR></td>
			     <td style= "WIDTH: 430; WORD-WRAP: break-word ">
			     <%=userLauWagC.rsGetString("wagertotal")%><BR></td>
			     <td style= "WIDTH: 60; WORD-WRAP: break-word ">
			     <%int intEachWagNum=Integer.parseInt(userLauWagC.rsGetString("wagernum"));
			       out.println(intEachWagNum);%><BR></td>
			     <td width=40>
			     <%int intEachTimes=Integer.parseInt(userLauWagC.rsGetString("times"));
			       out.println(intEachTimes);%></td>
			     <td width=40><%
			     if(intMarkIss==intIssueNum){
			    	 out.print("<a href=servlet/useinfwagrem?play=lau&wagerid="+userLauWagC.rsGetString("id")+">删除</a>");
			     }
			     %><BR></td>
			    </tr>
			   <%intWagNumLau=intWagNumLau+intEachWagNum*intEachTimes;}%>
			   </table>
			  </td>
			 </tr>
			 <tr align=center>
			     <td>
			        <table width=300 border=0 cellpadding=0 cellspacing=0 bordercolor=yellow>
			          <tr>
			            <td>所投总注数:</td>
			            <td style="color:red"><%out.println(intWagNumLau); %></td>
			            <td width=30%><br><td>
			            <td>所投总金额:</td>
			            <td style="color:red"><%out.println(intWagNumLau*2); %>元</td>
			          </tr>
			        </table>
			     </td>
			 </tr>
			 <tr>
			  <td>
			   <table align=center width=910 height=90 border=1 cellpadding=0 cellspacing=0 bordercolor=pink>
			    <tr><td colspan=11 height=30 align=center>中奖记录</td></tr>
			    <tr height=20>
			        <td width="7%" height="20" align="center">用户名</td>
			        <td width="7%" align="center">中奖期号</td>
		            <td width="7%" align="center">玩法类型</td>
		            <td width="7%" align="center">玩法模式</td>
		            <td width="7%" align="center">投注号码</td>
		            <td width="7%" align="center">判断规则</td>
		            <td width="7%" align="center">中奖注数</td>
		            <td width="7%" align="center">倍数</td>
		            <td width="7%" align="center">总注数</td>
		            <td width="7%" align="center">奖金</td>
		            <td width="7%" align="center">操作</td>
		          </tr>
		          <%while(userLauPrizeC.rsNext()){%>
		          <tr>
		            <td align="center"><%=userLauPrizeC.rsGetString("username") %></td>
		            <td align="center"><%=userLauPrizeC.rsGetString("issuenum") %></td>
		            <td align="center"><%=userLauPrizeC.rsGetString("playtype") %></td>
		            <td align="center"><%=userLauPrizeC.rsGetString("playmode")%><br></td>
		            <td align="center"><%=userLauPrizeC.rsGetString("onewager") %></td>
		            <td align="center"><%=userLauPrizeC.rsGetString("rule")%></td>
		            <td align="center"><%Integer IntWagNum=Integer.valueOf(userLauPrizeC.rsGetString("wagernum"));
		                                 out.println(IntWagNum);%></td>
		            <td align="center"><%Integer IntTimes=Integer.valueOf(userLauPrizeC.rsGetString("times"));
		                                 out.println(IntTimes);%></td>
		            <td align="center"><%out.println(IntWagNum*IntTimes); %></td>
		            <td align="center"><%=userLauPrizeC.rsGetString("prize") %></td>
		            <td align="center"><%out.print("<a href=servlet/useinfprdel?play=lau&prid="+userLauPrizeC.rsGetString("id")+">删除</a>");
			        %></td>
		          </tr>
		          <%  
		          }%>
			   </table>
			  </td>
			 </tr>
	       </table>
        </td>
 </tr>
 <tr>
  <%
   DataBaseConn userHapWagC = (DataBaseConn) session.getAttribute("uhapwdbc");
   DataBaseConn userHapPrizeC = (DataBaseConn) session.getAttribute("uhappdbc");
  %>
        <td>
	        <table>
	          <tr>
	            <td align=center style="font-size:13px;font-weight:bold;color:red">福彩3D
	            </td>
	          </tr>
			  <tr>
			  <td>
			   <table align=center width=910 height=90 border=1 cellpadding=0 cellspacing=0 bordercolor=pink>
			    <tr><td colspan=11 height=30 align=center>投注记录
			    <font color=red>
			    <%=objGM.removeNull((String)request.getAttribute("strremhap")) %></font></td></tr>
			    <tr height=20 align=center>
			     <td width=60>标识期号</td>
			     <td width=60>投注期号</td>
			     <td width=30>次数序号</td>
			     <td width=30>多期跟号</td>
			     <td width=70>投注类型<br></td>
			     <td width=70>投注方式</td>
			     <td width=430>投注号码</td>
			     <td width=60>注数</td>
			     <td width=40>倍数</td>
			     <td width=40>操作</td>
			    </tr>
			    <%while(userHapWagC.rsNext()){
			    	int intMarkIss=Integer.parseInt(userHapWagC.rsGetString("markiss").trim());
			    	int intIssueNum=Integer.parseInt(userHapWagC.rsGetString("issuenum").trim());
			    	String strHasFolPok=userHapWagC.rsGetString("hasfol");
			        String hasFolShowPok="";
			        if(strHasFolPok.equals("n")){
			    	    hasFolShowPok="无";
			        }else{
			    	    hasFolShowPok="有";
			        }
			    %>
			    <tr height=20 align=center>
			     <td width=60 ><%=intMarkIss%><BR></td>
			     <td width=60 ><%=intIssueNum%><BR></td>
			     <td width=30 ><%=userHapWagC.rsGetString("markissseq")%><BR></td>
			     <td width=30 ><%=hasFolShowPok%><BR></td>
			     <td width=70><%=userHapWagC.rsGetString("playtype")%><BR></td>
			     <td width=70><%=userHapWagC.rsGetString("playmode")%><BR></td>
			     <td style= "WIDTH: 430; WORD-WRAP: break-word " >
			     <%=userHapWagC.rsGetString("wagertotal")%><BR></td>
			     <td style= "WIDTH: 60">
			     <%int intEachWagNum=Integer.parseInt(userHapWagC.rsGetString("wagernum"));
			       out.println(intEachWagNum);%><BR></td>
			     <td style= "WIDTH: 40">
			     <%=userHapWagC.rsGetString("times")%><BR></td>
			     <td width=40>
			     <%
			     if(intMarkIss==intIssueNum){
			    	 out.print("<a href=servlet/useinfwagrem?play=hap&wagerid="+userHapWagC.rsGetString("id")+">删除</a>");
			     }
			     %><BR></td>
			    </tr>
			   <%intWagNumHap=intWagNumHap+intEachWagNum;}%>
			   </table>
			  </td>
			 </tr>
			 <tr align=center>
			     <td>
			        <table width=300 border=0 cellpadding=0 cellspacing=0 bordercolor=yellow>
			          <tr>
			            <td>所投总注数:</td>
			            <td style="color:red"><%out.println(intWagNumHap); %></td>
			            <td width=30%><br><td>
			            <td>所投总金额:</td>
			            <td style="color:red"><%out.println(intWagNumHap*2); %>元</td>
			          </tr>
			        </table>
			     </td>
			 </tr>
			 <tr>
			  <td>
			   <table align=center width=910 height=90 border=1 cellpadding=0 cellspacing=0 bordercolor=pink>
			    <tr><td colspan=11 height=30 align=center>中奖记录</td></tr>
			    <tr height=20>
			        <td width="7%" height="20" align="center">用户名</td>
			        <td width="7%" align="center">中奖期号</td>
		            <td width="7%" align="center">玩法类型</td>
		            <td width="7%" align="center">玩法模式</td>
		            <td width="7%" align="center">投注号码</td>
		            <td width="7%" align="center">判断规则</td>
		            <td width="7%" align="center">中奖注数</td>
		            <td width="7%" align="center">倍数</td>
		            <td width="7%" align="center">总注数</td>
		            <td width="7%" align="center">奖金</td>
		            <td width="7%" align="center">操作</td>
		          </tr>
		          <%while(userHapPrizeC.rsNext()){%>
		          <tr>
		            <td align="center"><%=userHapPrizeC.rsGetString("username") %></td>
		            <td align="center"><%=userHapPrizeC.rsGetString("issuenum") %></td>
		            <td align="center"><%=userHapPrizeC.rsGetString("playtype") %></td>
		            <td align="center"><%=userHapPrizeC.rsGetString("playmode")%><br></td>
		            <td align="center"><%=userHapPrizeC.rsGetString("onewager") %></td>
		            <td align="center"><%=userHapPrizeC.rsGetString("rule")%></td>
		            <td align="center"><%Integer IntWagNum=Integer.valueOf(userHapPrizeC.rsGetString("wagernum"));
		                                 out.println(IntWagNum);%></td>
		            <td align="center"><%Integer IntTimes=Integer.valueOf(userHapPrizeC.rsGetString("times"));
		                                 out.println(IntTimes);%></td>
		            <td align="center"><%out.println(IntWagNum*IntTimes); %></td>
		            <td align="center"><%=userHapPrizeC.rsGetString("prize") %></td>
		            <td align="center"><%out.print("<a href=servlet/useinfprdel?play=hap&prid="+userHapPrizeC.rsGetString("id")+">删除</a>");
			        %></td>
		          </tr>
		          <%  
		          }%>
			   </table>
			  </td>
			 </tr>
	       </table>
        </td>
 </tr>
 <tr>
  <%
   DataBaseConn userPthWagC = (DataBaseConn) session.getAttribute("upthwdbc");
   DataBaseConn userPthPrizeC = (DataBaseConn) session.getAttribute("upthpdbc");
  %>
        <td>
	        <table>
	          <tr>
	            <td align=center style="font-size:13px;font-weight:bold;color:red">排列三
	            </td>
	          </tr>
			  <tr>
			  <td>
			   <table align=center width=910 height=90 border=1 cellpadding=0 cellspacing=0 bordercolor=pink>
			    <tr><td colspan=11 height=30 align=center>投注记录
			    <font color=red>
			    <%=objGM.removeNull((String)request.getAttribute("strrempth")) %></font></td></tr>
			    <tr height=20 align=center>
			     <td width=60>标识期号</td>
			     <td width=60>投注期号</td>
			     <td width=30>次数序号</td>
			     <td width=30>多期跟号</td>
			     <td width=70>投注类型<br></td>
			     <td width=70>投注方式</td>
			     <td width=430>投注号码</td>
			     <td width=60>注数</td>
			     <td width=40>倍数</td>
			     <td width=40>操作</td>
			    </tr>
			    <%while(userPthWagC.rsNext()){
			    	int intMarkIss=Integer.parseInt(userPthWagC.rsGetString("markiss").trim());
			    	int intIssueNum=Integer.parseInt(userPthWagC.rsGetString("issuenum").trim());
			    	String strHasFolPok=userPthWagC.rsGetString("hasfol");
			        String hasFolShowPok="";
			        if(strHasFolPok.equals("n")){
			    	    hasFolShowPok="无";
			        }else{
			    	    hasFolShowPok="有";
			        }
			    %>
			    <tr height=20 align=center>
			     <td width=60 ><%=intMarkIss%><BR></td>
			     <td width=60 ><%=intIssueNum%><BR></td>
			     <td width=30 ><%=userPthWagC.rsGetString("markissseq")%><BR></td>
			     <td width=30 ><%=hasFolShowPok%><BR></td>
			     <td width=70><%=userPthWagC.rsGetString("playtype")%><BR></td>
			     <td width=70><%=userPthWagC.rsGetString("playmode")%><BR></td>
			     <td style= "WIDTH: 430; WORD-WRAP: break-word " >
			     <%=userPthWagC.rsGetString("wagertotal")%><BR></td>
			     <td style= "WIDTH: 60">
			     <%int intEachWagNum=Integer.parseInt(userPthWagC.rsGetString("wagernum"));
			       out.println(intEachWagNum);%><BR></td>
			     <td style= "WIDTH: 40">
			     <%=userPthWagC.rsGetString("times")%><BR></td>
			     <td width=40>
			     <%
			     if(intMarkIss==intIssueNum){
			    	 out.print("<a href=servlet/useinfwagrem?play=pth&wagerid="+userPthWagC.rsGetString("id")+">删除</a>");
			     }
			    %><BR></td>
			    </tr>
			   <%intWagNumPth=intWagNumPth+intEachWagNum;}%>
			   </table>
			  </td>
			 </tr>
			 <tr align=center>
			     <td>
			        <table width=300 border=0 cellpadding=0 cellspacing=0 bordercolor=yellow>
			          <tr>
			            <td>所投总注数:</td>
			            <td style="color:red"><%out.println(intWagNumPth); %></td>
			            <td width=30%><br><td>
			            <td>所投总金额:</td>
			            <td style="color:red"><%out.println(intWagNumPth*2); %>元</td>
			          </tr>
			        </table>
			     </td>
			 </tr>
			 <tr>
			  <td>
			   <table align=center width=910 height=90 border=1 cellpadding=0 cellspacing=0 bordercolor=pink>
			    <tr><td colspan=11 height=30 align=center>中奖记录</td></tr>
			    <tr height=20>
			        <td width="7%" height="20" align="center">用户名</td>
			        <td width="7%" align="center">中奖期号</td>
		            <td width="7%" align="center">玩法类型</td>
		            <td width="7%" align="center">玩法模式</td>
		            <td width="7%" align="center">投注号码</td>
		            <td width="7%" align="center">判断规则</td>
		            <td width="7%" align="center">中奖注数</td>
		            <td width="7%" align="center">倍数</td>
		            <td width="7%" align="center">总注数</td>
		            <td width="7%" align="center">奖金</td>
		            <td width="7%" align="center">操作</td>
		          </tr>
		          <%while(userPthPrizeC.rsNext()){%>
		          <tr>
		            <td align="center"><%=userPthPrizeC.rsGetString("username") %></td>
		            <td align="center"><%=userPthPrizeC.rsGetString("issuenum") %></td>
		            <td align="center"><%=userPthPrizeC.rsGetString("playtype") %></td>
		            <td align="center"><%=userPthPrizeC.rsGetString("playmode")%><br></td>
		            <td align="center"><%=userPthPrizeC.rsGetString("onewager") %></td>
		            <td align="center"><%=userPthPrizeC.rsGetString("rule")%></td>
		            <td align="center"><%Integer IntWagNum=Integer.valueOf(userPthPrizeC.rsGetString("wagernum"));
		                                 out.println(IntWagNum);%></td>
		            <td align="center"><%Integer IntTimes=Integer.valueOf(userPthPrizeC.rsGetString("times"));
		                                 out.println(IntTimes);%></td>
		            <td align="center"><%out.println(IntWagNum*IntTimes); %></td>
		            <td align="center"><%=userPthPrizeC.rsGetString("prize") %></td>
		            <td align="center"><%out.print("<a href=servlet/useinfprdel?play=pth&prid="+userPthPrizeC.rsGetString("id")+">删除</a>");
			        %></td>
		          </tr>
		          <%  
		          }%>
			   </table>
			  </td>
			 </tr>
	       </table>
        </td>
 </tr>
</table>
<% userDC.connClose();
   userWagerC.connClose();
   userPrizeC.connClose();
   
   userLauWagC.connClose();
   userLauPrizeC.connClose();
   
   userHapWagC.connClose();
   userHapPrizeC.connClose();
   
   userPthWagC.connClose();
   userPthPrizeC.connClose();%>
</body>
</html>