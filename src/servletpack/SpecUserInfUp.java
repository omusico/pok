//import required classes
package servletpack;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pubmethpac.GlobalMeth;
import util.EncryptUtil;
import beanpac.RemindInfo;
import dbconnpac.DataBaseConn;

public class SpecUserInfUp extends HttpServlet
{	 
 public void doPost(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
	 HttpSession session=req.getSession(false);
	 RemindInfo loginUser=(RemindInfo)session.getAttribute("loginmes");
	 String userNameLogin=loginUser.getLoginUserName();
	 ServletContext sc = getServletContext();
	 RequestDispatcher rd;
	 String strUserInfo=req.getParameter("userinfo");
	 String strUserPass=req.getParameter("userpass");
	 if(strUserInfo!=null){
		 GlobalMeth objGM=new GlobalMeth(); 
		 String strEmail=new String(req.getParameter("email").getBytes("ISO8859_1"),"GB2312").trim();
		 String strConPho=new String(req.getParameter("contactphone").getBytes("ISO8859_1"),"GB2312").trim();
		 String strMobPho=new String(req.getParameter("mobilephone").getBytes("ISO8859_1"),"GB2312").trim();
		 String strAddr=new String(req.getParameter("address").getBytes("ISO8859_1"),"GB2312").trim();
		 String strMsnNum=new String(req.getParameter("msnnum").getBytes("ISO8859_1"),"GB2312").trim();
		 String strQqNum=new String(req.getParameter("qqnum").getBytes("ISO8859_1"),"GB2312").trim();
    	 boolean isSuc=objGM.comExeUpdate("update lotteryuser set email='"+strEmail+"', contactphone='"+strConPho+"', mobilephone='"+strMobPho+"', address='"+strAddr+"', msnnum='"+strMsnNum+"', qqnum='"+strQqNum+"' where username='"+userNameLogin+"';");
    	 if(isSuc==true){
    		 req.setAttribute("infoupmes", "更新成功");
    	 }else{
    		 req.setAttribute("infoupmes", "没有更新");
    	 }
	 }
	 if(strUserPass!=null){
		 String strPassOld=new String(req.getParameter("passold").getBytes("ISO8859_1"),"GB2312").trim();
		 String strPassNew=new String(req.getParameter("passnew").getBytes("ISO8859_1"),"GB2312").trim();
		 String strPassConf=new String(req.getParameter("passconf").getBytes("ISO8859_1"),"GB2312").trim();
		 if(strPassOld.equals("") || strPassNew.equals("") || strPassConf.equals("")){
			 req.setAttribute("warn", "三个文本框都需要填写！");
		 }else{
			 if(chPassOld(strPassOld,userNameLogin)==true){
				 if(strPassNew.equals(strPassConf)){
					 DataBaseConn dbConn = new DataBaseConn();
					 //对密码加密
					 strPassNew = EncryptUtil.generatePassword(strPassNew);
					 dbConn.execute("update lotteryuser set password='"+strPassNew+"' where username='"+userNameLogin+"';");
					 dbConn.connCloseUpdate();
					 req.setAttribute("warn", "修改成功!");
				 }else{
					 req.setAttribute("warn", "新密码与确认密码不一致，请重新输入!");
				 }
			 }else{
				 req.setAttribute("warn", "旧密码不正确，请重新输入!");
			 }
		 }		 
	 }
	 DataBaseConn specUserDBC=new DataBaseConn();
	 specUserDBC.execQuery("select * from lotteryuser where username='" + userNameLogin + "'");
	 session.setAttribute("specuserdc",specUserDBC);
	 rd = sc.getRequestDispatcher("/WEB-INF/specialuser/infoupdate.jsp");	 
	 rd.forward(req, res);
     
 }


 public void doGet(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
	 doPost(req,res);
 }
 
 public boolean chPassOld(String passw,String strUserName){
	 boolean isOK=false;
	 DataBaseConn dbConn = new DataBaseConn();
	 dbConn.execQuery("select * from lotteryuser where username='"+strUserName+"';");
	 dbConn.rsNext();

	 //将密码串加密后与数据库对比2010-12-03
	  passw = EncryptUtil.generatePassword(passw);
	  
	 if(dbConn.rsGetString("password").trim().equals(passw)){
		 isOK= true;
	 }
	 dbConn.connClose();
	 return isOK;
 }
 
}
