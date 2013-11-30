//import required classes
package servletpack;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import beanpac.RemindInfo;

import dbconnpac.DataBaseConn;

public class UseInfRead extends HttpServlet
{	 
 public void doPost(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
	 HttpSession session=req.getSession(false);
	 String strFromPage=req.getParameter("frompage");
	 session.setAttribute("pagetype",strFromPage);
	 
	 ServletContext sc = getServletContext();
	 RequestDispatcher rd =null;
	 String strUser="";
	 if(strFromPage.equals("com")){
         //普通用户页面用户名
		 RemindInfo loginUser=(RemindInfo)session.getAttribute("loginmes");
		 strUser=loginUser.getLoginUserName();
		 if(strUser.equals("")){
			 session.setAttribute("mes","您还没有登录！");
    		 rd=sc.getRequestDispatcher("/WEB-INF/pubinfopage/error.jsp");
		 }else{
			 String tempId = loginUser.getLoginUserId();
			 rd= sc.getRequestDispatcher("/WEB-INF/specialuser/specuserinfo.jsp?userid="+tempId);
		 }
		// req.setAttribute("userid", loginUser.getLoginUserId());
	 }else{
		 //管理页面
		 String strUserID=req.getParameter("userid");
		 DataBaseConn UserNameGetDBC=new DataBaseConn();
		 String sqlStr="select * from lotteryuser where id='"+strUserID+"';";
		 UserNameGetDBC.execQuery(sqlStr);
		 UserNameGetDBC.rsNext();
		 strUser=UserNameGetDBC.rsGetString("username"); 
		 UserNameGetDBC.connClose();
		 rd= sc.getRequestDispatcher("/WEB-INF/usermanage/user/userdetails.jsp");
	 }
	// session.setAttribute("userid", arg1)
	 session.setAttribute("usernamestr",strUser);
     rd.forward(req, res);
 }


 public void doGet(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
	 doPost(req,res);
 }
 
 public void updateRemit(String quan, String user){
	   DataBaseConn plusRemitConn = new DataBaseConn();
	   plusRemitConn.execQuery("select totalmoney from lotteryuser where username='" +user+ "';");
	   plusRemitConn.rsNext();
	   int intTotalMoney=Integer.parseInt(plusRemitConn.rsGetString("totalmoney"));
	   intTotalMoney=intTotalMoney+Integer.parseInt(quan);
	   plusRemitConn.execute("update lotteryuser set totalmoney='"+String.valueOf(intTotalMoney)+"'where username='"+user+"';");
	   plusRemitConn.connClose();
 }

 public void plusPrize(String strSqlTemp){
   DataBaseConn plusPrizeConn = new DataBaseConn();
   plusPrizeConn.execute(strSqlTemp);
   plusPrizeConn.connCloseUpdate();
 }
 
}
