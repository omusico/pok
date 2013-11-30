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
	// 初始输出路径
	int cnt = 0;

	// 计数器，用于判定是否首次调用copyFiles方法
	public void copyFiles(String copyFrom, String copyTo)  {
		try{
			//PrintWriter myWriter = res.getWriter();

			File f = new File(copyFrom);
			if (!f.exists()) {
				//myWriter.println("源文件路径不存在");
				infoStr = infoStr + "源文件路径不存在<br>";
				//System.exit(5);
				return;
			}

			if (cnt == 0) {
				File out = new File(this.initCopyTo);
				out.mkdir();
				// 首次调用执行，创建初始输出目录
			}
			if (f.isFile()) {
				this.copyFile(f, copyTo);
			} else {
				this.copyDir(f, copyTo);
			}	
			cnt++;
			//System.out.println("------cnt="+cnt);
		}catch(Exception e){
			//myWriter.println("复制文件异常:"+e.toString());
			infoStr = infoStr + "复制文件异常:"+e.toString() + "<br>";
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
	
			// 修改输出路径字符串，将子文件夹相对路径追加至初始输出路径
			// substring用于去掉路径中的驱动器盘符
			File dir = new File(copyTo);
			dir.mkdir();
	
			File temp[] = f.listFiles();
			for (int i = 0; i < temp.length; i++) {
				this.copyFiles(temp[i].toString(), copyTo);
				// 递归调用此方法
			}
		}catch(Exception e){
			//System.out.println("复制文件夹异常:"+e.toString());
			infoStr = infoStr + "复制文件夹异常:"+e.toString() + "<br>";
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
			//System.out.println("复制文件异常:"+e.toString());
			infoStr = infoStr + "复制文件异常:"+e.toString() + "<br>";
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
			out.println("Copy file OK! 文件路径" + copyFrom + "<br>");
			//out.println("Copy file path:"+filePath);
		}else{
			out.println("路径不正确!"+filePath + "<br>");
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
		if(confirm("确认复制?")){

			document.copyForm.submit();
		}
    }
//-->
</script>
 </head>

 <BODY>
 <br><br>
 路径:<%=prefix%>
 <br>
 <form name="copyForm" id="copyForm" action="test_copy.jsp" >
	 <input type="hidden" name="copyFlag" id="copyFlag" value="1"/>
     <table name="copyTable" id="copyTable" width="80%" border="1">
		<tr>
			<td colspan="2" align="center"><b>文件复制</b></td>
		</tr>
		<tr>
			<td width="10%" align="right">文件路径：</td>
			<td  width="90%" align="left"><input type="text" name="filePath" id="filePath" size="80"/></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="button" name="btnCopy" value="复制文件" onclick="copyFile()"/></td>
		</tr>
	 </table>
 </form>
 <table name="fileTable" id="fileTable" width="80%">
 <tr>
  <td colspan="2" align="center"><b>文件列表</b></td>
 </tr>
 <tr height="30" >
	<td width="15%" >一级文件夹</td> 
	<td width="15%">二级文件夹</td> 
	<td width="15%">三级文件夹</td> 
	<td width="40%">文件名称</td> 
	<td width="15%">修改时间</td>
 </tr>
 </BODY>
</HTML>
