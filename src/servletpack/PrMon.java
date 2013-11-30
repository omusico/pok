//import required classes
package servletpack;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import pubmethpac.GlobalMeth;

//class login
public class PrMon extends HttpServlet
{	 
 public void doPost(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {

     HttpSession session=req.getSession(false);
	 String strPlay=new String(req.getParameter("play").getBytes("ISO8859_1"),"GB2312").trim();
	 String strRule=new String(req.getParameter("rule").getBytes("ISO8859_1"),"GB2312").trim();
	 String strMon=new String(req.getParameter("money").getBytes("ISO8859_1"),"GB2312").trim();
	 //限号倍数
	 String strLimitDegree=new String(req.getParameter("limitDegree").getBytes("ISO8859_1"),"GB2312").trim();
	 
	 GlobalMeth objGM=new GlobalMeth();
	 ServletContext sc = getServletContext();
	 RequestDispatcher rd = null;
	 if(strPlay.equals("pok")){
//		 updatePrMon(objGM,strRule,strMon,"pokprmoney");
		 updatePrMon(objGM,strRule,strMon,strLimitDegree,"pokprmoney");
		 rd = sc.getRequestDispatcher("/servlet/loginmaninit?play=pok");
	 }
	 if(strPlay.equals("lau")){
//		 updatePrMon(objGM,strRule,strMon,"lauprmoney");
		 updatePrMon(objGM,strRule,strMon,strLimitDegree,"lauprmoney");
		 rd = sc.getRequestDispatcher("/servlet/loginmaninit?play=lau");
	 }
	 if(strPlay.equals("hap")){
//		 updatePrMon(objGM,strRule,strMon,"happrmoney");
		 updatePrMon(objGM,strRule,strMon,strLimitDegree,"happrmoney");
		 rd = sc.getRequestDispatcher("/servlet/loginmaninit?play=hap");
	 }
	 if(strPlay.equals("pth")){
//		 updatePrMon(objGM,strRule,strMon,"pthprmoney");
		 updatePrMon(objGM,strRule,strMon,strLimitDegree,"pthprmoney");
		 rd = sc.getRequestDispatcher("/servlet/loginmaninit?play=pth");
	 }
	 rd.forward(req, res);

 }
 
 public void updatePrMon(GlobalMeth gm,String rule,String mon,String table){
	 String strSql="update "+table+" set money='"+mon+"' where rule='"+rule+"';";
	 gm.comExeUpdate(strSql);
 } 
 /**
  * 
  *@Description:保存金额及限号倍数
  *@Author:LJY
  *@E-mail:lijiyongcn@sohu.com
  *@Create on May 25, 2009 4:17:51 PM
  * @param gm
  * @param rule
  * @param mon
  * @param limitDegree
  * @param table
  */
 public void updatePrMon(GlobalMeth gm,String rule,String mon,String limitDegree,String table){
	 String strSql="update "+table+" set money='"+mon+"',limit_degree='"+limitDegree+"' where rule='"+rule+"';";
	 gm.comExeUpdate(strSql);
 } 
  
}
