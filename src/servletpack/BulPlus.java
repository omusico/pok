//import required classes
package servletpack;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import beanpac.RegRemindInfo;
import beanpac.RemindInfo;
import beanpac.Bulletin;
//class login
public class BulPlus extends HttpServlet
{	 
 public void doPost(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
     HttpSession session=req.getSession(false);
     ServletContext sc = getServletContext();
	 RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/usermanage/bul.jsp");
	 String strMes=new String(req.getParameter("mes").getBytes("ISO8859_1"),"GB2312").trim();
	 Bulletin objBul=new Bulletin();
	 objBul.setMes(strMes); 
     rd.forward(req, res);
 }
 
}
