//import required classes
package servletpack;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import dbconnpac.DataBaseConn;
import pubmethpac.GlobalMeth;
import pubmethpac.PokWagPub;

public class UseInfPrDel extends HttpServlet
{	 
 public void doPost(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
	 HttpSession session=req.getSession(false);
	 String strPrID=req.getParameter("prid");
	 String strPlay=req.getParameter("play");
	 String strMark="";

	 if(strPlay.equals("pok")){
		 delPr("pokprizeinfo", strPrID);
		 strMark="pokpri";
	 }
	 if(strPlay.equals("lau")){
		 delPr("lauprizeinfo", strPrID);
		 strMark="laupri";
	 }
	 if(strPlay.equals("hap")){
		 delPr("happrizeinfo", strPrID);
		 strMark="happri";
	 }
	 if(strPlay.equals("pth")){
		 delPr("pthprizeinfo", strPrID);
		 strMark="pthpri";
	 }
	 ServletContext sc = getServletContext();
	 RequestDispatcher rd = sc.getRequestDispatcher("/servlet/servuserinfo?mark="+strMark);
     rd.forward(req, res);
 }
 

 public void doGet(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
	 doPost(req,res);
 }
 
 
 public boolean delPr(String strTabTemp,String strPrIDTemp){
	 GlobalMeth objGM=new GlobalMeth();
	 boolean isSuc=objGM.comExeUpdate("delete from "+strTabTemp+" where id='"+strPrIDTemp+"';");
	 return isSuc;
 }
 

 
}
