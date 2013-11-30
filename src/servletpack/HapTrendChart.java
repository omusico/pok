//import required classes
package servletpack;

import java.io.*;
import java.util.Date;
import java.text.*;

import javax.servlet.*;
import javax.servlet.http.*;

import dbconnpac.DataBaseConn;

public class HapTrendChart extends HttpServlet
{	 
 public void doPost(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
	 HttpSession session=req.getSession(false);
	 int intIssSpan=Integer.parseInt(req.getParameter("issspan"));
	 String tableName="hapwinnum";
	 ServletContext sc = getServletContext();
	 RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/pubinfopage/haptrendchart.jsp");
	 DataBaseConn winNumTrendDBC = new DataBaseConn();
	 winNumTrendDBC.execQuery("select count(*) from "+tableName);
	 winNumTrendDBC.rsNext();
	 int intStartIndex=winNumTrendDBC.rsGetInt(1)-intIssSpan;
	 String sqlStr="";
	 if(intStartIndex<0){
		 sqlStr ="select * from "+tableName;
	 }else{
		 sqlStr ="select * from "+tableName+" LIMIT "+intStartIndex+", "+intIssSpan;
	 }
	 winNumTrendDBC.execQuery(sqlStr);
	 session.setAttribute("wntdbc", winNumTrendDBC);
//	 session.setAttribute("whday", intIssSpan);//alter by ljy on 2009-05-25
	 session.setAttribute("whday", Integer.valueOf(intIssSpan));
	 
     rd.forward(req, res);
 }
 

 public void doGet(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
	 doPost(req,res);
 }
 
}
