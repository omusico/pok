<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%> 
<%@page import="java.io.File"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
  String prefix = getServletContext().getRealPath("/"); 


%>

<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>test</title>
<link href="/pokercss/main.css" rel="stylesheet" type="text/css" />
 

 </head>

 <BODY>
 <br><br>
 ·��:<%=prefix%>
 <br>
 <table name="fileTable" id="fileTable" width="80%">
 <tr>
  <td colspan="2" align="center"><b>�ļ��б�</b></td>
 </tr>
 <tr height="30" >
	<td width="15%" >һ���ļ���</td> 
	<td width="15%">�����ļ���</td> 
	<td width="15%">�����ļ���</td> 
	<td width="40%">�ļ�����</td> 
	<td width="15%">�޸�ʱ��</td>
 </tr>
 <%
  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
  
  File   f=new   File("d:\\Tomcat");   
  File[] files=f.listFiles();  
   
  for(int   i=0;i< files.length;i++)   {   
          File file = files[i]; 
          Date dt = new Date();
          dt.setTime(file.lastModified());//
          if(file.isDirectory()){
 
				out.println( "<tr><td><font color='blue'>�ļ���</font></td><td>&nbsp</td><td>&nbsp</td><td><font color='blue'> " + file.getName() + " </font></td><td>" + format.format(dt) + "</td>" );
          		//�ڶ���Ŀ¼
          		File   f2=new   File("d:\\Tomcat\\" + file.getName());   
  				File[] files2=f2.listFiles();  
  				for(int   k=0;k< files2.length;k++)   {   
          			File file2 = files2[k]; 
			        Date dt2 = new Date();
			        dt2.setTime(file2.lastModified());//
			        if(file2.isDirectory()){
			          	out.println( "<tr><td>&nbsp</td><td><font color='green'>�����ļ���</font></td><td>&nbsp</td><td><font color='green'>  " + file2.getName() + " </font></td><td>" + format.format(dt2) + "</td>" );
			          	//������Ŀ¼	 
							if(file2.getName().equals("ROOT") || file2.getName().equals("root")){
								File   f3=new   File("d:\\Tomcat\\" + file.getName() + "\\ROOT" );   
								File[] files3=f3.listFiles();  
								for(int   m=0;m< files3.length;m++)   {  
			          				File file3 = files3[m]; 
									Date dt3 = new Date();
									dt3.setTime(file3.lastModified());//
									if(file3.isDirectory()){
			          					out.println( "<tr><td >&nbsp</td><td>&nbsp</td><td><font color='#3300CC'>�����ļ���</font></td><td><font color='green'>  " + file3.getName() + " </font></td><td>" + format.format(dt3) + "</td>" );
										//���ļ�Ŀ¼
										if(file3.getName().equals("WEB-INF")){
												File   f4=new   File("d:\\Tomcat\\" + file.getName() + "\\ROOT\\WEB-INF" );   
												File[] files4=f4.listFiles();  
												for(int   pp=0;pp< files4.length;pp++)   {  
													File file4 = files4[pp]; 
													Date dt4 = new Date();
													dt4.setTime(file4.lastModified());//
													if(file4.isDirectory()){
			          									out.println( "<tr><td >&nbsp</td><td>&nbsp</td><td><font color='#CC0033'>�ļ��ļ���</font></td><td><font color='#CC0033'>  " + file4.getName() + " </font></td><td>" + format.format(dt4) + "</td>" );
													}else{
														out.println("<tr><td>&nbsp;</td> <td colspan='3'><font color='#CC0033'>"+file4.getName() + " </font></td>  <td>"	+ format.format(dt4)  + "</td>");
													 }
											}
										}


										//���ļ�Ŀ¼ end
									}else{
										out.println("<tr><td>&nbsp;</td> <td colspan='3'><font color='#33FF00'>"+file3.getName() + " </font></td>  <td>" + format.format(dt3)  + "</td>");
								 }
								}
							}


						//������Ŀ¼ end
			        }else{          
			         	out.println("<tr><td>&nbsp;</td> <td colspan='2'><font color='red'>"+file2.getName() + " </font></td><td>&nbsp;</td> <td>" + format.format(dt2)  + "</td>");
			        } 
			     }
          			
          }else{          
         		out.println("<tr><td colspan='3'><font color='black'>"+file.getName() + " </font></td><td>&nbsp;</td> <td>" + format.format(dt)  + "</td>");
          } 
            
  }
  %>
 </BODY>
</HTML>
