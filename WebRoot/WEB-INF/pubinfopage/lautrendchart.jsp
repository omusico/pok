<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="dbconnpac.DataBaseConn" %>
<%
DataBaseConn wntDBC = (DataBaseConn) session.getAttribute("wntdbc");
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>��������-��ƱͶע��</title>
<link href="/pokercss/main.css" rel="stylesheet" type="text/css" />

<style type="text/css">
* { margin:0;}
body,td,th { font-size: 12px;}
td { font-family:Verdana, Arial, Helvetica, sans-serif;color: #999999;}
.line_top_1 { border-top:#000000 solid 1px;}
.line_right_1 {
	border-right:#000000 solid 1px;
}
.line_bottom_1 { border-bottom:#000000 solid 1px;}
.line_left_1 { border-left:#000000 solid 2px;}
.ball_1 {
	background-image: url(/pkimages/pokerimage/ball_1.gif);
	background-repeat: no-repeat;
	background-position: center center;
	color:#FFFFFF;
}
.ball_2 {
	background-image: url(/pkimages/pokerimage/ball_2.gif);
	background-repeat: no-repeat;
	background-position: center center;
	color:#FFFFFF;
}
.font_1 { color:#DE040A;}
.font_2 { color:#015C01;}
.fenxi_a { color:#fff}
.fenxi_a a:link,.fenxi_a a:visited { color:#fff;}
.fenxi_a a:hover { color:#FFFF00; text-decoration:none; position:relative; top:1px; left:1px;}
.image1 a:link,.image1 a:visited { color:#00F; text-decoration:none}
.image1 a:hover,.image1 a:active { color:#FF0000; text-decoration:underline;}
.image1 { background: url(/pkimages/mainimages/layout.gif) repeat-x top;}
.image2 { padding-top:2px; padding-left:20px;}
.image2 li { float:left; color:#FFF;margin:0 20px}
.image2 a:link,.image2 a:visited { color:#FFF; font-weight:bold; margin:0px; text-decoration:none;}
.image2 a:active,.image2 a:hover { color:#FFFF00;} 
</style>
</head>
<body>

<table width="1300" border="1" cellspacing="3" cellpadding="3" bordercolorlight=blue bordercolordark=yellow>
  <tr>
    <td bgcolor="#C4B7F4" align=center style="font-size:36px; color:red">���������н�����ֲ�ͼ</td>
  </tr>
</table>


<table width="1300" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#000000">
    <tr>
      <td width=10% align=center bgcolor="#f5f5f5"><a href="servlet/entrymain?type=laugh">����ʱʱ��</a>
      </td>
      <td height="25" align="center" bgcolor="#f5f5f5">
	       <form name="frm" action="/servlet/numtrendchart" method="post" style="margin:0px; padding:0px">
	             <input type="hidden" name="play" value="laugh">
	              ��ѡ��:&nbsp;&nbsp;
	  	        <select name="daynum" id="daynum" onChange="frm.submit()" >
	  	          <%
	  	            int[] dayValue={1,2,3,5,10};
	  	            String[] dayText={"���һ��","�������","�������","�������","���ʮ��"};
	  	            Integer whichDay=(Integer)session.getAttribute("whday");
	  	            for(int i=0;i<dayValue.length;i++){
	  	            	if(dayValue[i]==whichDay.intValue()){
	  	            		out.println("<option value='"+dayValue[i]+"' selected>"+dayText[i]+"</option>");
	  	            	}else{
	  	            		out.println("<option value='"+dayValue[i]+"'>"+dayText[i]+"</option>");
	  	            	}
	  	            	
	  	            }%>
	            </select>
	        </form>
      </td>
    </tr>
</table>

<table width="1300" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#000000">
      <tr>			
      <td bgcolor="#FFFFFF"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
        <tbody>
          <tr>
            <td width="88" rowspan="2" align="center" bgcolor="#e5e5e5" class="line_bottom_1">
			<strong><font color="#000000">�ں�</font></strong>			</td>
            <td width="50" rowspan="2" align="center" bgcolor="#e5e5e5" class="line_left_1 line_bottom_1"><strong><font color="#000000">��������</font></strong></td>
            <td height="25" colspan="10" align="center" bgcolor="#e5e5e5" class="line_bottom_1 line_left_1"><strong><font color="#000000">��λ</font></strong></td>
            <td colspan="10" align="center" bgcolor="#e5e5e5" class="line_bottom_1 line_left_1"><strong><font color="#000000">ʮλ </font></strong></td>
            <td colspan="10" align="center" bgcolor="#e5e5e5" class="line_bottom_1 line_left_1"><strong><font color="#000000">��λ </font></strong></td>
            
          </tr>
          <tr>
            <td width="23" align="center" bgcolor="#f5f5f5" class="line_left_1">
            <strong><font color="#000000">0</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">1</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">2</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">3</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">4</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">5</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">6</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">7</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">8</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">9</font></strong></td>

            <td width="23" align="center" bgcolor="#f5f5f5" class="line_left_1">
			<strong><font color="#000000">0</font></strong></td>			
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">1</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">2</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">3</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">4</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">5</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">6</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">7</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">8</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">9</font></strong></td>
			
            <td width="23" align="center" bgcolor="#f5f5f5" class="line_left_1">
            <strong><font color="#000000">0</font></strong></td>            
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">1</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">2</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">3</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">4</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">5</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">6</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">7</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">8</font></strong></td>
            <td width="23" align="center" bgcolor="#f5f5f5"><strong><font color="#000000">9</font></strong></td>

          </tr>
          
          <%
          String[] arrPoker={"0","1","2","3","4","5","6","7","8","9"};
          int[][] arrIntPoker=new int[3][10];
          String[] arrPokerFig={"hundred","ten","one"};
          
          while (wntDBC.rsNext()){ //ѭ������RS�е���%>
           <tr class='td10'>
           <td align='center' bgcolor='#FFFFFF' style='color:#666666' height=24 >
            <%=wntDBC.rsGetString("issuenum")%>
           </td>
           <td align='center' bgcolor='#FFFFFF' style='color:red' height=24  class='line_left_1' >
            <%=wntDBC.rsGetString("hundred")%>,<%=wntDBC.rsGetString("ten")%>,<%=wntDBC.rsGetString("one")%>
           </td>
             <%
              for(int i=0;i<arrPokerFig.length;i++){
               for(int j=0;j<arrPoker.length;j++){//ѭ��13����
                 if(arrPoker[j].equals(wntDBC.rsGetString(arrPokerFig[i]))){//�ж����������н�����
                     arrIntPoker[i][j]=0;%>
                     <td align='center' bgcolor='#FFFFFF' style='color:#666666' class="ball_1 <%if(j==0){out.println("line_left_1");} %>">
                      <font color=white><%=arrPoker[j].toString()%></font>
                     </td>
                 <%}else{
                     arrIntPoker[i][j]=arrIntPoker[i][j]+1;%>
                     <td align='center' bgcolor='#FFFFFF' style='color:#666666' class="<%if(j==0){out.println("line_left_1");} %>">
                      <%=String.valueOf(arrIntPoker[i][j])%>
                     </td>
                 <%} 
               }
              }%>
           </tr>
          <%} %>
       </tbody>
      </table></td>
    </tr>
</table>
<%wntDBC.connClose(); %>
</body>
</html>
