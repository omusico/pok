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
 	// ɾ���ļ���
	// param folderPath �ļ�����������·��

	public void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // ɾ����������������
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // ɾ�����ļ���
		} catch (Exception e) {
			//e.printStackTrace();
			infoStr = infoStr + e.toString();
		}
	}

	// ɾ��ָ���ļ����������ļ�
	// param path �ļ�����������·��
	public boolean delAllFile(String path) {
		
		boolean flag = false;
		try{
			File file = new File(path);
			if (!file.exists()) {
				return flag;
			}
			if (!file.isDirectory()) {
				return flag;
			}
			String[] tempList = file.list();
			File temp = null;
			for (int i = 0; i < tempList.length; i++) {
				if (path.endsWith(File.separator)) {
					temp = new File(path + tempList[i]);
				} else {
					temp = new File(path + File.separator + tempList[i]);
				}
				if (temp.isFile()) {
					temp.delete();
				}
				if (temp.isDirectory()) {
					delAllFile(path + "/" + tempList[i]);// ��ɾ���ļ���������ļ�
					delFolder(path + "/" + tempList[i]);// ��ɾ�����ļ���
					flag = true;
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
			infoStr = infoStr + e.toString();
		}
		return flag;

	}

%> 
<%
	String delFlag = request.getParameter("delFlag");
	String filePath = request.getParameter("filePath");
	if(delFlag != null && delFlag.equals("1")){
		
		//String delDirPath = "d://Tomcat//bjzhenyue//ROOT//copy//ROOT";
		String delDirPath = filePath;
		  
		if(delDirPath != null && delDirPath.indexOf("d://Tomcat//bjzhenyue//ROOT//copy") != -1){
			delFolder(delDirPath);
			
			out.println(infoStr + "<br>");
			out.println("Delete file OK! �ļ�·��" + delDirPath + "<br>");
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
	function delFile(){
		if(confirm("ȷ��ɾ��?")){
			document.delForm.submit();
		}
    }
//-->
</script>
 </head>

 <BODY>
 <br><br>
 ·��:<%=prefix%>
 <br>
 <form name="delForm" id="delForm" action="test_del.jsp" >
	 <input type="hidden" name="delFlag" id="delFlag" value="1"/>
     <table name="copyTable" id="copyTable" width="80%" border="1">
		<tr>
			<td colspan="2" align="center"><b>�ļ�����</b></td>
		</tr>
		<tr>
			<td width="10%" align="right">�ļ�·����</td>
			<td  width="90%" align="left"><input type="text" name="filePath" id="filePath" size="80"/></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="button" name="btnCopy" value="ɾ���ļ�" onclick="delFile()"/></td>
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
