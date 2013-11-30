//import required classes
package servletpack;

import java.io.*;
import java.util.Date;
import java.util.regex.*;

import javax.servlet.*;
import javax.servlet.http.*;

import pubmethpac.GlobalMeth;

//class login
public class PokIssCon extends HttpServlet{	 
 public void doPost(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException{
	 HttpSession session = req.getSession(false);
	 String strPlay=req.getParameter("play");
	 String strTab="";
	 String strBegH=req.getParameter("beghour");
	 String strBegM=req.getParameter("begmin");
	 String strDurSell=req.getParameter("dursell");
	 String strDurWin=req.getParameter("durwin");
	 String strHMIss=req.getParameter("howmanyiss");
	 String strBegIss=req.getParameter("begiss");
	 String offsetFlag=req.getParameter("offsetFlag");
	 String offsetTime=req.getParameter("offsetTime");
	  
	 
	 GlobalMeth objGM=new GlobalMeth();
	 Date sysDate=new Date();
	 String strBegDate = objGM.getStrSysDate("yyyyMMdd",sysDate);
	 ServletContext sc = getServletContext();
	 RequestDispatcher rd = sc.getRequestDispatcher("/servlet/loginmaninit?play=pok");
	 if(strPlay.equals("pok")){//快 乐 扑 克[pokisscon]
	     strTab="pokisscon";
	     rd = sc.getRequestDispatcher("/servlet/loginmaninit?play=pok");
	 }
	 if(strPlay.equals("lau")){
		 strTab="lauisscon";
		 rd = sc.getRequestDispatcher("/servlet/loginmaninit?play=lau");
	 }
	 if(strPlay.equals("hap")){// 福 彩 3D[hapisscon]
		 strTab="hapisscon";
		 rd = sc.getRequestDispatcher("/servlet/loginmaninit?play=hap");
	 }
	 if(strPlay.equals("pth")){
		 strTab="pthisscon";
		 rd = sc.getRequestDispatcher("/servlet/loginmaninit?play=pth");
	 }
	 String strSql="update "+strTab+" set begdate='"+strBegDate+"', begiss='"+strBegIss+"', beghour='"+strBegH+"', begmin='"+strBegM+"', dursell='"+strDurSell+"', durwin='"+strDurWin+"', howmanyiss='"+strHMIss+"',offset_flag='"+offsetFlag+"',offset_time='"+offsetTime+"' where id='1';";
	 objGM.comExeUpdate(strSql);	 
     rd.forward(req, res);
 }
 public void doGet(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException{
	 doPost(req,res);
 }
 
}
