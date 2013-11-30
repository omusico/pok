//用于普通用户查询，和管理员查询每个普通用户。
package servletpack;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import pubmethpac.PageInfoGet;
import dbconnpac.DataBaseConn;
import beanpac.RemindInfo;

//class login
public class ServUserInfo extends HttpServlet
{	 
 public void doPost(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
	 HttpSession session=req.getSession(false);
	 ServletContext sc = getServletContext();
	 RequestDispatcher rd=null;
	 String userNameLogin=(String)session.getAttribute("usernamestr");
	 String strMark = req.getParameter("mark");
	 
	 int intWag=0;
	 int intMon=0;
	 PageInfoGet objPageInfoGet=new PageInfoGet();
	 String strChSql="";
	 if(strMark.equals("spuseinf")){
		 strChSql="select id from lotteryuser where username='" + userNameLogin + "'";
		 objPageInfoGet.generInfo(req,"lotteryuser",strChSql);
	 }
	 if(strMark.equals("pokwag")){
		 strChSql="SELECT id FROM pokwagerinfo where username='"+userNameLogin+"' and valid='y' and markissseq!='mark'";
		 objPageInfoGet.generInfo(req,"pokwagerinfo",strChSql);
		 intWag=getWag("SELECT * FROM pokwagerinfo where username='"+userNameLogin+"' and valid='y' and markissseq!='mark'");
		 intMon=intWag*2;
	 }
	 if(strMark.equals("pokpri")){
		 strChSql="SELECT id FROM pokprizeinfo where username='"+userNameLogin+"'";
		 objPageInfoGet.generInfo(req,"pokprizeinfo",strChSql);		
		 intWag=getWag("SELECT * FROM pokprizeinfo where username='"+userNameLogin+"'");
		 intMon=getWinPri("SELECT * FROM pokprizeinfo where username='"+userNameLogin+"'");
	 }
	 if(strMark.equals("lauwag")){
		 strChSql="SELECT id FROM lauwagerinfo where username='"+userNameLogin+"' and valid='y' and markissseq!='mark'";
		 objPageInfoGet.generInfo(req,"lauwagerinfo",strChSql);
		 intWag=getWag("SELECT * FROM lauwagerinfo where username='"+userNameLogin+"' and valid='y' and markissseq!='mark'");
		 intMon=intWag*2;
	 }
	 
	 if(strMark.equals("laupri")){
		 strChSql="SELECT id FROM lauprizeinfo where username='"+userNameLogin+"'";
		 objPageInfoGet.generInfo(req,"lauprizeinfo",strChSql);
		 intWag=getWag("SELECT * FROM lauprizeinfo where username='"+userNameLogin+"'");
		 intMon=getWinPri("SELECT * FROM lauprizeinfo where username='"+userNameLogin+"'");
	 }
	 if(strMark.equals("hapwag")){
		 strChSql="SELECT id FROM hapwagerinfo where username='"+userNameLogin+"' and markissseq!='mark'";
		 objPageInfoGet.generInfo(req,"hapwagerinfo",strChSql);		
		 intWag=getWag("SELECT * FROM hapwagerinfo where username='"+userNameLogin+"' and markissseq!='mark'");
		 intMon=intWag*2;
	 }
	 
	 if(strMark.equals("happri")){
		 strChSql="SELECT id FROM happrizeinfo where username='"+userNameLogin+"'";	 
		 objPageInfoGet.generInfo(req,"happrizeinfo",strChSql);	
		 intWag=getWag("SELECT * FROM happrizeinfo where username='"+userNameLogin+"'");
		 intMon=getWinPri("SELECT * FROM happrizeinfo where username='"+userNameLogin+"'");
	 }
	 //排列3 投注记录
	 if(strMark.equals("pthwag")){
		 strChSql="SELECT id FROM pthwagerinfo where username='"+userNameLogin+"' and markissseq!='mark'";
		 objPageInfoGet.generInfo(req,"pthwagerinfo",strChSql);		
		 intWag=getWag("SELECT * FROM pthwagerinfo where username='"+userNameLogin+"' and markissseq!='mark'");
		 intMon=intWag*2;
	 }
	 if(strMark.equals("pthpri")){
		 strChSql="SELECT id FROM pthprizeinfo where username='"+userNameLogin+"'";
		 objPageInfoGet.generInfo(req,"pthprizeinfo",strChSql);	
		 intWag=getWag("SELECT * FROM pthprizeinfo where username='"+userNameLogin+"'");
		 intMon=getWinPri("SELECT * FROM pthprizeinfo where username='"+userNameLogin+"'");
	 }
	 
	 req.setAttribute("infodbc", objPageInfoGet.getUserPageConn());
	 req.setAttribute("infopage", objPageInfoGet.getBeanPageShow());
	 req.setAttribute("wag", new Integer(intWag));
	 req.setAttribute("mon", new Integer(intMon));
	 rd = sc.getRequestDispatcher("/WEB-INF/specialuser/info/"+strMark+".jsp");
	 
	 rd.forward(req, res);	
 }
 

 public void doGet(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
	 doPost(req,res);
 }
 
 public int getWag(String strSql){
	 DataBaseConn objDBC = new DataBaseConn();
	 int intWag=0;
	 objDBC.execQuery(strSql);
	 while(objDBC.rsNext()){
		 int intEachWinWag=Integer.parseInt(objDBC.rsGetString("wagernum"))*Integer.parseInt(objDBC.rsGetString("times"));
		 intWag=intWag+intEachWinWag;
	 }
	 objDBC.connClose();
	 return intWag;
 }
 public int getWinPri(String strSql){
	 DataBaseConn objDBC = new DataBaseConn();
	 int intMon=0;
	 objDBC.execQuery(strSql);
	 while(objDBC.rsNext()){
		 int intEachWinPri=Integer.parseInt(objDBC.rsGetString("prize"));
		 intMon=intMon+intEachWinPri;
	 }
	 objDBC.connClose();
	 return intMon;
 }
 
}
