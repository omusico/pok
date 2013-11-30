//import required classes
package servletpack;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import util.EncryptUtil;

import dbconnpac.DataBaseConn;

//class login
public class UpdatPass extends HttpServlet
{	 
 public void doPost(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
     HttpSession session=req.getSession(false);
     ServletContext sc = getServletContext();
	 RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/usermanage/bul.jsp");
	 String strPassOld=new String(req.getParameter("passold").getBytes("ISO8859_1"),"GB2312").trim();
	 String strPassNew=new String(req.getParameter("passnew").getBytes("ISO8859_1"),"GB2312").trim();
	 String strPassConf=new String(req.getParameter("passconf").getBytes("ISO8859_1"),"GB2312").trim();
	 if(strPassOld.equals("") || strPassNew.equals("") || strPassConf.equals("")){
		 req.setAttribute("warn", "三个文本框都需要填写！");
	 }else{
		 if(chPassOld(strPassOld)==true){
			 if(strPassNew.equals(strPassConf)){
				//将密码串加密后与数据库对比
				 strPassNew = EncryptUtil.generatePassword(strPassNew);
				  
				 DataBaseConn dbConn = new DataBaseConn();
				 dbConn.execute("update lotteryuser set password='"+strPassNew+"' where username='admin';");
				 dbConn.connCloseUpdate();
				 req.setAttribute("warn", "修改成功!");
			 }else{
				 req.setAttribute("warn", "新密码与确认密码不一致，请重新输入!");
			 }
		 }else{
			 req.setAttribute("warn", "旧密码不正确，请重新输入!");
		 }
	 }
     rd.forward(req, res);
 }
 public boolean chPassOld(String passw){
	 boolean isOK=false;
	 

	//将密码串加密后与数据库对比
	 passw = EncryptUtil.generatePassword(passw);
	 
	 DataBaseConn dbConn = new DataBaseConn();
	 dbConn.execQuery("select * from lotteryuser where username='admin';");
	 dbConn.rsNext();
	 if(dbConn.rsGetString("password").trim().equals(passw)){
		 isOK= true;
	 }
	 dbConn.connClose();
	 return isOK;
 }
}
