//import required classes
package servletpack;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import pubmethpac.PageInfoGet;

public class UserPageShow extends HttpServlet
{	 
 public void doPost(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
	 HttpSession session=req.getSession(false);
	 ServletContext sc = getServletContext();
	 RequestDispatcher rd;
	 PageInfoGet objPageInfoGet=new PageInfoGet();
	 String strChSql="select id from lotteryuser";
	 objPageInfoGet.generInfo(req, "lotteryuser",strChSql);
	 rd = sc.getRequestDispatcher("/WEB-INF/usermanage/user/userpage.jsp");
     session.setAttribute("userpc", objPageInfoGet.getUserPageConn());
     session.setAttribute("bepagshow", objPageInfoGet.getBeanPageShow());
     
     rd.forward(req, res);
 }
 

 public void doGet(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
	 doPost(req,res);
 }
 
}
