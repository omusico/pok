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

import beanpac.RemindInfo;
import dbconnpac.DataBaseConn;

public class WinQuery extends HttpServlet{	 
 public void doPost(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException{
	 HttpSession session = req.getSession(false);
	 //判断是否已经登陆	 
	 Object obj =  session.getAttribute("loginmes");
//	 Object obj2 =   session.getAttribute("fromlogman");
//	 if(obj != null){
//		 String fromAdmin = (String)obj2;
//		 //req.setAttribute("haveLogin", "0");
//	 }else{
//		 req.setAttribute("haveLogin", "0");
//	 }
	 if(obj == null){
		 req.setAttribute("haveLogin", "0");
	 }else{
		 RemindInfo remindInfo = (RemindInfo)obj;		 
		 String userName = remindInfo.getLoginUserName();
		 
		 if(userName == null || userName.equals("")){
			 req.setAttribute("haveLogin", "0");
		 }else{
			 req.setAttribute("haveLogin", "1");
			 String issueNum = new String(req.getParameter("pokissnum").getBytes("ISO8859_1"),"GB2312").trim();
		     DataBaseConn winQueryDBC=new DataBaseConn();
		     //alter by ljy on 2009-08-30
		     String sql = "";
		     if(userName.equals("admin")){
		    	 sql = "select * from pokprizeinfo where 1=1 ";
		     }else{
		    	 sql = "select * from pokprizeinfo where  username='" + userName + "'";
		    	 
		     }
		     if(issueNum != null && !issueNum.equals("")){
		    	 sql = sql + " and issuenum='"+issueNum+"';";
		     }
		     winQueryDBC.execQuery(sql);
		     session.setAttribute("userwininfo", winQueryDBC);
		     session.setAttribute("issnum", issueNum);
		 }
	 }
	 	 ServletContext sc = getServletContext();
	 RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/pubinfopage/pok/userwin.jsp");
     rd.forward(req, res);
 }
 

 public void doGet(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
	 doPost(req,res);
 }
 
}
