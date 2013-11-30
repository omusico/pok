<%@ page contentType="text/html; charset=gb2312"
    pageEncoding="gb2312" session="true"%> 
<%@page import="java.io.*"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
  String prefix = getServletContext().getRealPath("/"); 
  

%>
<%!
	String infoStr = "";
	String initCopyTo; 
	String initCopyFrom;
	// ��ʼ���·��
	int cnt = 0;

	// �������������ж��Ƿ��״ε���copyFiles����
	public void copyFiles(String copyFrom, String copyTo)  {
		try{
			//PrintWriter myWriter = res.getWriter();

			File f = new File(copyFrom);
			if (!f.exists()) {
				//myWriter.println("Դ�ļ�·��������");
				infoStr = infoStr + "Դ�ļ�·��������<br>";
				//System.exit(5);
				return;
			}

			if (cnt == 0) {
				File out = new File(this.initCopyTo);
				out.mkdir();
				// �״ε���ִ�У�������ʼ���Ŀ¼
			}
			if (f.isFile()) {
				this.copyFile(f, copyTo);
			} else {
				this.copyDir(f, copyTo);
			}	
			cnt++;
			//System.out.println("------cnt="+cnt);
		}catch(Exception e){
			//myWriter.println("�����ļ��쳣:"+e.toString());
			infoStr = infoStr + "�����ļ��쳣:"+e.toString() + "<br>";
		}
	}

	public void copyDir(File f, String copyTo)  {
		//System.out.println(f.getPath());
		try{
			//copyTo = this.initCopyTo + f.getPath().substring(2);
			String tempFrom = initCopyFrom.substring(initCopyFrom.lastIndexOf("//")+2,initCopyFrom.length());
			String tempPath = f.getPath();
 			String aaa = tempPath.substring(tempPath.lastIndexOf(tempFrom),tempPath.length());
 			copyTo = this.initCopyTo + "//" + aaa;
	
			// �޸����·���ַ����������ļ������·��׷������ʼ���·��
			// substring����ȥ��·���е��������̷�
			File dir = new File(copyTo);
			dir.mkdir();
	
			File temp[] = f.listFiles();
			for (int i = 0; i < temp.length; i++) {
				this.copyFiles(temp[i].toString(), copyTo);
				// �ݹ���ô˷���
			}
		}catch(Exception e){
			//System.out.println("�����ļ����쳣:"+e.toString());
			infoStr = infoStr + "�����ļ����쳣:"+e.toString() + "<br>";
		}
	}

	public void copyFile(File f, String copyTo) {
		//System.out.println(f.getPath());

		try{
			DataInputStream in = new DataInputStream(new BufferedInputStream(
					new FileInputStream(f.getPath())));
	
			byte[] date = new byte[in.available()];
	
			in.read(date);
	
			DataOutputStream out = new DataOutputStream(new BufferedOutputStream(
					new FileOutputStream(copyTo + "/" + f.getName())));
	
			out.write(date);
	
			in.close();
			out.close();
		}catch(Exception e){
			//System.out.println("�����ļ��쳣:"+e.toString());
			infoStr = infoStr + "�����ļ��쳣:"+e.toString() + "<br>";
		}
	}


%> 
<%
	String copyFlag = request.getParameter("copyFlag");
	String filePath = request.getParameter("filePath");
	if(copyFlag != null && copyFlag.equals("1")){
		
		initCopyTo = "d://Tomcat//bjzhenyue//ROOT//copy";
		initCopyFrom = filePath;
		String copyFrom = initCopyFrom;
		if(copyFrom != null && copyFrom.indexOf("Tomcat") != -1){
			copyFiles(copyFrom,initCopyTo);
			
			out.println(infoStr + "<br>");
			out.println("Copy file OK! �ļ�·��" + copyFrom + "<br>");
			//out.println("Copy file path:"+filePath);
		}else{
			out.println("·������ȷ!"+filePath + "<br>");
		}
		
	}
%>
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>test</title>
<link href="/pokercss/main.css" rel="stylesheet" type="text/css" />
 
<script language="javascript">
<!--
	function copyFile(){
		if(confirm("ȷ�ϸ���?")){

			document.copyForm.submit();
		}
    }
//-->
</script>
 </head>

 <BODY>
 <br><br>
 ·��:<%=prefix%>
 <br>
 <form name="copyForm" id="copyForm" action="test_copy.jsp" >
	 <input type="hidden" name="copyFlag" id="copyFlag" value="1"/>
     <table name="copyTable" id="copyTable" width="80%" border="1">
		<tr>
			<td colspan="2" align="center"><b>�ļ�����</b></td>
		</tr>
		<tr>
			<td width="10%" align="right">�ļ�·����</td>
			<td  width="90%" align="left"><input type="text" name="filePath" id="filePath" size="80"/></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="button" name="btnCopy" value="�����ļ�" onclick="copyFile()"/></td>
		</tr>
	 </table>
 </form>
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
 </BODY>
</HTML>
