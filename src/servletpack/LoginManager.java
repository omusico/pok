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

import util.EncryptUtil;
import beanpac.RemindInfo;
import dbconnpac.DataBaseConn;


public class LoginManager extends HttpServlet
{
 public void doPost(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException{
	 HttpSession session=req.getSession();
	 ServletContext sc = getServletContext();
	 RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/usermanage/loginmanage.jsp");;
	 String username = req.getParameter("username");
	 String password = req.getParameter("password");
	 if(username==null || password==null){
		 session.setAttribute("mesislogin","");
	 }else if(!username.equals("admin")){
	     session.setAttribute("mesislogin", "管理员名不正确！");
	 }else if(checklogin(username,password)!=true){
		 session.setAttribute("mesislogin", "密码不正确，请重新输入！");
	 }else{
		 session.setAttribute("fromlogman", "true");
		 //add by ljy on 2009-08-30
		 RemindInfo remindInfoObj= new RemindInfo();
		  remindInfoObj.setLoginUserName(username);
		  session.setAttribute("loginmes",remindInfoObj);
		  
		 rd = sc.getRequestDispatcher("/servlet/loginmaninit?play=manplat");
	 }
	 rd.forward(req, res);
 }
 
 public void doGet(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException{
	 doPost(req,res);
 }
 
 public boolean checklogin(String userna,String passw){
	 DataBaseConn loginConn = new DataBaseConn();
  try{
	  loginConn.execQuery("select * from lotteryuser where username='" + userna + "'");
	  loginConn.rsNext();
	  //将密码串加密后与数据库对比
	  passw = EncryptUtil.generatePassword(passw);
	  if(loginConn.rsGetString("password").trim().equals(passw)){
		  loginConn.connClose();
		  return true;
	  }else{
		  loginConn.connClose();
		  return false;
	  }
  }catch(Exception e){
	  loginConn.connClose(); 
	  return false;
  }
 }
}
