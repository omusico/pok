//import required classes
package servletpack;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import dbconnpac.DataBaseConn;

import pubmethpac.PageInfoGet;

public class LauWinPageShow extends HttpServlet
{
 public void doPost(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
	 HttpSession session=req.getSession(false);
	 ServletContext sc = getServletContext();
	 RequestDispatcher rd;
	 String strPrizeID=req.getParameter("prizeid");
	 if(strPrizeID!=null){
		 DataBaseConn DelPrizeDBC=new DataBaseConn();
		 String sqlStr="delete from lauwinnum where id='"+Integer.parseInt(strPrizeID)+"';";
		 DelPrizeDBC.execute(sqlStr);
		 DelPrizeDBC.connCloseUpdate();
	 }
	 PageInfoGet objPageInfoGet=new PageInfoGet();
	 String strChSql="select id from lauwinnum";
	 objPageInfoGet.generInfo(req, "lauwinnum",strChSql); 
     session.setAttribute("userpc", objPageInfoGet.getUserPageConn());
     session.setAttribute("bepagshow", objPageInfoGet.getBeanPageShow());
     rd = sc.getRequestDispatcher("/WEB-INF/usermanage/lau/lauprizepage.jsp");
     rd.forward(req, res);
 }
 

 public void doGet(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
	 doPost(req,res);
 }
 
}
