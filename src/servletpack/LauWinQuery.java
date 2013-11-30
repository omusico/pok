//import required classes
package servletpack;

import java.io.*;
import java.util.regex.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import beanpac.FigWinInfo;
import beanpac.FigWinInfoUser;
import beanpac.RemindInfo;
import dbconnpac.DataBaseConn;

public class LauWinQuery extends HttpServlet{	 
 public void doPost(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException{
	 HttpSession session = req.getSession(false);
	//判断是否已经登陆	 
	 Object obj =  session.getAttribute("loginmes");
	 if(obj == null){
		 req.setAttribute("haveLogin", "0");
	 }else{
		 RemindInfo remindInfo = (RemindInfo)obj;		 
		 String userName = remindInfo.getLoginUserName();
		 
		 if(userName == null || userName.equals("")){
			 req.setAttribute("haveLogin", "0");
		 }else{
			 req.setAttribute("haveLogin", "1");
	
		 String issueNum = new String(req.getParameter("lauissnum").getBytes("ISO8859_1"),"GB2312").trim();
		 DataBaseConn winQueryDBC=new DataBaseConn();
		 //alter by ljy on 2009-08-30
	     String sql = "";
	     if(userName.equals("admin")){
	    	 sql = "select * from lauprizeinfo where 1=1 ";
	     }else{
	    	 sql = "select * from lauprizeinfo where  username='" + userName + "'";
	    	 
	     }
	     if(issueNum != null && !issueNum.equals("")){
	    	 sql = sql + " and issuenum='"+issueNum+"';";
	     }
	     winQueryDBC.execQuery(sql);
	//     winQueryDBC.execQuery("select * from lauprizeinfo where issuenum='"+issueNum+"';");
	     session.setAttribute("userwininfo", winQueryDBC);
	     session.setAttribute("issnum", issueNum);
		 }
	 }
	 ServletContext sc = getServletContext();
	 RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/pubinfopage/lau/lauuserwin.jsp");
     rd.forward(req, res);
	
 }
 

 public void doGet(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
	 doPost(req,res);
 }
 
 
}
