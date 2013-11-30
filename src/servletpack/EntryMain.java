//import required classes
package servletpack;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import beanpac.RegRemindInfo;
import beanpac.RemindInfo;

//class login
public class EntryMain extends HttpServlet
{	 
 public void doPost(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
     HttpSession session=req.getSession();
     int intMaxInterval=2*60*60;
     session.setMaxInactiveInterval(intMaxInterval);
     String type=req.getParameter("type");
     RemindInfo objRemindInfo=new RemindInfo();
     session.setAttribute("loginmes", objRemindInfo);
     
     RegRemindInfo objRegRemInfo=new RegRemindInfo();
     session.setAttribute("reginforemind", objRegRemInfo);
    
     ServletContext sc = getServletContext();
	 RequestDispatcher rd = sc.getRequestDispatcher("/lottpok/indexmain.jsp");
	 
	 if(type==null){
		 rd = sc.getRequestDispatcher("/lottpok/indexmain.jsp");
	 }else{
		 if(type.equals("laugh")){
			 rd= sc.getRequestDispatcher("/lott/laugh/laugh.jsp");
		 }
		 if(type.equals("pth")){
			 rd= sc.getRequestDispatcher("/lott/pth/pth.jsp");
		 }
		 if(type.equals("happy")){
			 rd= sc.getRequestDispatcher("/lott/hap/happy.jsp");
		 }
		 if(type.equals("indexmain")){
			 rd= sc.getRequestDispatcher("/lottpok/indexmain.jsp");
		 }
		 if(type.equals("reg")){
			 rd= sc.getRequestDispatcher("/register.jsp");
		 }
	 }
	 
     rd.forward(req, res);
 }
 

 public void doGet(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
	 doPost(req,res);
 }
 
}
