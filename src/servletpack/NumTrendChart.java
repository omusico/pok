//import required classes
package servletpack;

import java.io.*;
import java.util.Date;
import java.text.*;

import javax.servlet.*;
import javax.servlet.http.*;

import dbconnpac.DataBaseConn;

public class NumTrendChart extends HttpServlet
{	 
 public void doPost(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
	 HttpSession session=req.getSession(false);
	 String strPlay=req.getParameter("play");
	 String tableName="";
	 ServletContext sc = getServletContext();
	 RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/pubinfopage/error.jsp");
//	 Integer objDayNum = Integer.parseInt(req.getParameter("daynum"));//alter by ljy on 2009-05-25 
	 Integer objDayNum = new Integer(req.getParameter("daynum"));
	 int intDayNum=	 objDayNum.intValue();
	 Date sysDate=new Date();	 
	 long dayBefore=sysDate.getTime()-intDayNum*24*60*60*1000;
	 Date dayBeforeDate=new Date(dayBefore);	
	 SimpleDateFormat sysDateFormat=new SimpleDateFormat("yyyyMMdd");
	 String beforeIssueDay=sysDateFormat.format(dayBeforeDate);
	 String currIssueDay=sysDateFormat.format(sysDate);
	 if(strPlay.equals("poker")){
		 tableName="pokwinnum";
		 rd = sc.getRequestDispatcher("/WEB-INF/pubinfopage/trendchart.jsp");
	 }
	 if(strPlay.equals("laugh")){
		 tableName="lauwinnum";
		 rd = sc.getRequestDispatcher("/WEB-INF/pubinfopage/lautrendchart.jsp");
	 }
	 if(strPlay.equals("happy")){
		 tableName="hapwinnum";
		 rd = sc.getRequestDispatcher("/WEB-INF/pubinfopage/haptrendchart.jsp");
	 }
	 if(strPlay.equals("pth")){
		 tableName="pthwinnum";
		 rd = sc.getRequestDispatcher("/WEB-INF/pubinfopage/pthtrendchart.jsp");
	 }
	 DataBaseConn winNumTrendDBC = new DataBaseConn();
	 String sqlStr ="select * from "+tableName+" where issueday between '"+beforeIssueDay+"' and '"+currIssueDay+"';";
	 winNumTrendDBC.execQuery(sqlStr);
	 session.setAttribute("wntdbc", winNumTrendDBC);
	 session.setAttribute("whday", objDayNum);
	 
     rd.forward(req, res);
 }
 

 public void doGet(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
	 doPost(req,res);
 }
 
}
