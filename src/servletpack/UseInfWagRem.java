//import required classes
package servletpack;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import dbconnpac.DataBaseConn;
import pubmethpac.GlobalMeth;
import pubmethpac.PokWagPub;

public class UseInfWagRem extends HttpServlet
{	 
 public void doPost(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
	 HttpSession session=req.getSession(false);
	 String strWagerID=req.getParameter("wagerid");
	 String strPlay=req.getParameter("play");
	 String strRemPok="";
	 String strRemLau="";
	 String strRemHap="";
	 String strRemPth="";
	 String strMark="";
	 PokWagPub objPWP=new PokWagPub();
	 if(strPlay.equals("pok")){
		 strRemPok=objPWP.delIss("快乐扑克","lotteryuser", "pokwagerinfo", strWagerID);
		 strMark="pokwag";
	 }
	 if(strPlay.equals("lau")){
		 strRemLau=objPWP.delIss("时时乐","lotteryuser", "lauwagerinfo", strWagerID);
		 strMark="lauwag";
	 }
	 if(strPlay.equals("hap")){
		 strRemHap=objPWP.delIss("福彩3D","lotteryuser", "hapwagerinfo", strWagerID);
		 strMark="hapwag";
	 }
	 if(strPlay.equals("pth")){
		 strRemPth=objPWP.delIss("排列三","lotteryuser", "pthwagerinfo", strWagerID);
		 strMark="pthwag";
	 }

     req.setAttribute("strrempok", strRemPok);
     req.setAttribute("strremlau", strRemLau);
     req.setAttribute("strremhap", strRemHap);
     req.setAttribute("strrempth", strRemPth);
	 ServletContext sc = getServletContext();
	 RequestDispatcher rd = sc.getRequestDispatcher("/servlet/servuserinfo?mark="+strMark);
     rd.forward(req, res);
 }
 

 public void doGet(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
	 doPost(req,res);
 }
 

 
}
