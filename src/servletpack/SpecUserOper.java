//import required classes
package servletpack;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import dbconnpac.DataBaseConn;

//class login
public class SpecUserOper extends HttpServlet
{	 
 public void doPost(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
     HttpSession session=req.getSession(false);
     String strUsername=new String(req.getParameter("hidusername").getBytes("ISO8859_1"),"GB2312").trim();
     ServletContext sc = getServletContext();
	 RequestDispatcher rd = sc.getRequestDispatcher("/servlet/servuserinfo?mark=spuseoper");
	 DataBaseConn userOperDBC=new DataBaseConn();
	 userOperDBC.execute("update lotteryuser set drawstate='请求' where username='"+strUsername+"';");
	 userOperDBC.connCloseUpdate();
	 req.setAttribute("drawreqrem","已经请求提款");
     rd.forward(req, res);
 }
 

 public void doGet(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
	 doPost(req,res);
 }
 
}
