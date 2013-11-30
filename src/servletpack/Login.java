//import required classes
package servletpack;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import util.EncryptUtil;
import beanpac.RemindInfo;

import com.onmyway.ssc.manage.model.UserInfo;

import dbconnpac.DataBaseConn;
import dbconnpac.PoolManager;

//class login
public class Login extends HttpServlet{

	private Logger log = Logger.getLogger(PoolManager.class);
	
 public void doPost(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
  HttpSession session = req.getSession();
  String username = new String(req.getParameter("username").getBytes("ISO8859_1"),"GB2312").trim();
  String password = req.getParameter("password");
  String valCode = req.getParameter("valcode");
  String strRand=(String)session.getAttribute("rand");
  
  //RemindInfo remindInfoObj=(RemindInfo)session.getAttribute("loginmes"); alter by ljy on 2009-05-25
  res.setContentType("text/xml; charset=gb2312");
  PrintWriter out = res.getWriter();
  String strHasUser=hasUsername(username);
 
  if (strHasUser.equals("false")){
	  out.println("用户名不正确！");
  }else if(strHasUser.equals("admin")){
	  out.println("禁用此用户名！");
  }else if(checklogin(username,password)!=true){
	  out.println("密码不正确！");
  }
  /***测试期间，不用验证码 
  else if(!strRand.equals(valCode)){
	  out.println("验证失败!\n友情提示:\n注意字母大写。或点击验证码刷新重试。");
  }
 */
  else{
	  
	  RemindInfo remindInfoObj= new RemindInfo();
	  remindInfoObj.setLoginUserName(username);
	  String userid = getUserid(username);
	  remindInfoObj.setLoginUserId(userid);
	  session.setAttribute("loginmes",remindInfoObj);
	  //RemindInfo remindInfoObj=(RemindInfo)session.getAttribute("loginmes");
	  out.println(remindInfoObj.getLoginUserName()+",   您好!祝贺您登录成功!");
	  
	  //日志信息
	  String addr = req.getRemoteAddr();//获取的IP实际上是代理服务器的地址;
	  log.warn(remindInfoObj.getLoginUserName() + " 登陆成功! 客户登陆地址:" + addr);
  }
  out.close();
 }
 
 public void doGet(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
	 doPost(req,res);
 }
 
 public String hasUsername(String usern){
	 String strReturn="false";
	 DataBaseConn loginConn = new DataBaseConn();
	 loginConn.execQuery("select * from lotteryuser where username='" + usern + "'");
	 if(loginConn.rsNext()){
		 strReturn=loginConn.rsGetString("username");
	 }
	 loginConn.connClose();
	 return strReturn;
 }
 public String getUserid(String usern){
	 String strReturn="false";
	 DataBaseConn loginConn = new DataBaseConn();
	 loginConn.execQuery("select * from lotteryuser where username='" + usern + "'");
	 if(loginConn.rsNext()){
		 strReturn=loginConn.rsGetString("id");
	 }
	 loginConn.connClose();
	 return strReturn;
 }
 /**
  * 返回user对象
  * @param usern
  * @return
  */
 public UserInfo hasUserInfo(String userName){
	 UserInfo userInfo = null;
	 String strReturn="false";
	 DataBaseConn loginConn = new DataBaseConn();
	 loginConn.execQuery("select * from lotteryuser where username='" + userName + "'");
	 if(loginConn.rsNext()){
		 userInfo.setId(loginConn.rsGetInt("id"));
		 userInfo.setUsername(loginConn.rsGetString("username"));
		 userInfo.setPassword(loginConn.rsGetString("password"));		 
	 }
	 loginConn.connClose();
	 
	 return userInfo;
 }
 
 public boolean checklogin(String userna,String passw){
	 boolean isSuc=false;
	 DataBaseConn loginConn = new DataBaseConn();
	 loginConn.execQuery("select * from lotteryuser where username='" + userna + "'");
	 loginConn.rsNext();
	 //将密码串加密后与数据库对比
	  passw = EncryptUtil.generatePassword(passw);
	  
	 if(loginConn.rsGetString("password").trim().equals(passw)){
		 isSuc=true;
	 }
	 loginConn.connClose();
	 return isSuc;
 }

}
