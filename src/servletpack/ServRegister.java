//import required classes
package servletpack;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import util.EncryptUtil;
import beanpac.RegRemindInfo;
import dbconnpac.DataBaseConn;
//class login
public class ServRegister extends HttpServlet
{	 
	private Logger log = Logger.getLogger("huacaizx");
 public void doPost(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
	 String username = new String(req.getParameter("username").getBytes("ISO8859_1"),"GB2312").trim();
	 String password = new String(req.getParameter("password").getBytes("ISO8859_1"),"GB2312").trim();
	 String confirm = new String(req.getParameter("confirm").getBytes("ISO8859_1"),"GB2312").trim();
	 String sex = new String(req.getParameter("sex").getBytes("ISO8859_1"),"GB2312").trim();
	 String realname = new String(req.getParameter("realname").getBytes("ISO8859_1"),"GB2312").trim();
	 String idcardnumber = new String(req.getParameter("idcardnumber").getBytes("ISO8859_1"),"GB2312").trim();
	 String email = new String(req.getParameter("email").getBytes("ISO8859_1"),"GB2312").trim();
	 String contactphone = new String(req.getParameter("contactphone").getBytes("ISO8859_1"),"GB2312").trim();
	 String mobilephone = new String(req.getParameter("mobilephone").getBytes("ISO8859_1"),"GB2312").trim();
	 String address = new String(req.getParameter("address").getBytes("ISO8859_1"),"GB2312").trim();
	 String msnnum = new String(req.getParameter("msnnum").getBytes("ISO8859_1"),"GB2312").trim();
	 String qqnum = new String(req.getParameter("qqnum").getBytes("ISO8859_1"),"GB2312").trim();
	 String valCode = req.getParameter("valcode");

	 HttpSession session = req.getSession(false);
	 String strRand = (String)(String)session.getAttribute("rand");
	 RegRemindInfo objRegRem=(RegRemindInfo)session.getAttribute("reginforemind");
	 boolean regSuccess = false;
	 boolean passTotal=false;
	 if(username.equals("")){
		 clearmessage(objRegRem);
		 objRegRem.setUserName("用户名不能为空！");
	 }else 
		 /*if(isuserexist(username)==true){
		 clearmessage(objRegRem);
		 objRegRem.setUserName("用户名已存在，请选择其它的用户名！");
	 }else*/ 
		 if(password.equals("")){
		 clearmessage(objRegRem);
		 objRegRem.setPassWord("投注密码不能为空！");
	 }else if(confirm.equals("")){
		 clearmessage(objRegRem);
		 objRegRem.setConfirmPass("确认密码不能为空！");
	 }else if(!confirm.equals(password)){
		 clearmessage(objRegRem);
		 objRegRem.setConfirmPass("投注密码与确认密码不一致！");
	 }else if(!strRand.equals(valCode)){
		 clearmessage(objRegRem);
		 objRegRem.setValCode("验证码输入错误！");
	 }else if(realname.equals("")){
		 clearmessage(objRegRem);
		 objRegRem.setRealName("真实姓名不能为空！");
	 }else if(ischinese(realname)==false){
		 clearmessage(objRegRem);
		 objRegRem.setRealName("请填写中文姓名!");
	 }else if(idcardnumber.equals("")){
		 clearmessage(objRegRem);
		 objRegRem.setIdNum("身份证号码不能为空！");
	 }else if(email.equals("")){
		 clearmessage(objRegRem);
		 objRegRem.setEmail("E-mail不能为空!");
	 }else if(contactphone.equals("")){
		 clearmessage(objRegRem);
		 objRegRem.setConPhone("联系电话不能为空!");
	 }else if(mobilephone.equals("")){
		 clearmessage(objRegRem);
		 objRegRem.setCellPhone("手机号码不能为空");
	 }else{
		 clearmessage(objRegRem);
		 //对密码进行加密 add by ljy on 2009-09-23
		 password = EncryptUtil.generatePassword(password);
		 passTotal=true;
		 regSuccess= newUser(username,password,confirm,sex,realname,idcardnumber,email,contactphone,mobilephone,address,msnnum,qqnum);
	 }
	 
	 if (regSuccess==true){
		 objRegRem.setSucMes("恭喜您，注册成功！");
	 }else if(passTotal==true){
		 objRegRem.setSucMes("此用户名已经存在！");
	 }else{
		 objRegRem.setSucMes("");
	 }
	 
	 ServletContext sc = getServletContext();
	 RequestDispatcher rd= sc.getRequestDispatcher("/register.jsp");
	 rd.forward(req, res);
 }
 
 public boolean ischinese(String uname){  
     String   testchin;  
     String   testeng; 
     testchin="[\u4E00-\u9FA5]";  
     testeng="[a-zA-Z]";
     //test="[\\u4E00-\\u9FA5]+";
     Pattern pchin= Pattern.compile(testchin);  
     Matcher mchin= pchin.matcher(uname);  
     boolean resultchin = mchin.find();  
     
     Pattern peng=Pattern.compile(testeng);
     Matcher meng=peng.matcher(uname);
     boolean resulteng=meng.find();
     
     if(resultchin==true && resulteng==false){
    	 return true; 
     }
     else {return false;}
 }
 
 public void clearmessage(RegRemindInfo regrem){

	 regrem.setCellPhone("");
	 regrem.setConfirmPass("");
	 regrem.setConPhone("");
	 regrem.setEmail("");
	 regrem.setIdNum("");
	 regrem.setPassWord("");
	 regrem.setRealName("");
	 regrem.setUserName("");
	 regrem.setValCode("");

	 }
 
 public boolean isuserexist(String user){
	  try{
		  DataBaseConn userDBConn = new DataBaseConn();
		  String sql1="select * from lotteryuser where username='" + user+ "';";
		  userDBConn.execQuery(sql1);
		  if(userDBConn.rsNext())
		   {
			  userDBConn.connClose();
			  return true;
		   }else
		    return false;
		  }catch(Exception e){
		   //System.out.println(e.toString());
		   return false;
		  }
	}
 public boolean newUser(String username2,String password2,String confirm2,String sex2, 
         String realname2,String idcardnumber2, String email2, String contactphone2, 
         String mobilephone2, String address2, String msnnum2, String qqnum2){

		 DataBaseConn regDBConn = new DataBaseConn();
		 String sql2 = "insert into lotteryuser(username,password,confirm,sex,realname,idcardnumber,email,contactphone,mobilephone,address,msnnum,qqnum) values('" + username2 + "','" + password2 + "','" + confirm2 + "','" + sex2 + "','" + realname2 + "','" + idcardnumber2 + "','" + email2 + "','" + contactphone2 + "','" + mobilephone2 + "','" + address2 + "','" + msnnum2 + "','"+ qqnum2 + "');";
		 if(regDBConn.execute(sql2)==true){
			 regDBConn.connCloseUpdate();
			 return true;
		 }else{
			 return false;
		 }
		 

 }
}
