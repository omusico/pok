//import required classes
package servletpack;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import dbconnpac.DataBaseConn;
import pubmethpac.GlobalMeth;
public class UseInfOper extends HttpServlet
{	 
 public void doPost(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
	 HttpSession session=req.getSession(false);
	 String strOper=req.getParameter("oper");
	 String strUser=(String)session.getAttribute("usernamestr");
	 if(strOper.equals("remitoper")){
		 String quantity=req.getParameter("remitquantity");
		 String strDoWhat=req.getParameter("dowhat");
		 updateRemit(quantity,strUser,strDoWhat);	 
	 }
	 if(strOper.equals("freeze")){
		 String strDoWhat=req.getParameter("fredowhat");
		 updateFre(strUser,strDoWhat);
	 }
     if(strOper.equals("plusprize")){
	   String strPlay=req.getParameter("play");
	   String issuenumber=req.getParameter("issuenumber");
	   String prize=req.getParameter("prize");
	   String strSql="";
	   if(strPlay.equals("pok")){
		   String playtype=new String(req.getParameter("playtype").getBytes("ISO8859_1"),"GB2312").trim();
		   strSql="insert into prizeinfo (username,issuenum,playtype,prize)values('"+strUser+"','"+issuenumber+"','"+playtype+"','"+prize+"');";
	   }
	   if(strPlay.equals("lau")){
		   String playtype=new String(req.getParameter("playtype").getBytes("ISO8859_1"),"GB2312").trim();
		   strSql="insert into lauprizeinfo (username,issuenum,playtype,prize)values('"+strUser+"','"+issuenumber+"','"+playtype+"','"+prize+"');";
	   }
	   if(strPlay.equals("pth")){
		   strSql="insert into pthprizeinfo (username,issuenum,prize)values('"+strUser+"','"+issuenumber+"','"+prize+"');";
	   }
	   if(strPlay.equals("hap")){
		   strSql="insert into happrizeinfo (username,issuenum,prize)values('"+strUser+"','"+issuenumber+"','"+prize+"');";
	   }
	   GlobalMeth objGM=new GlobalMeth();
	   objGM.comExeUpdate(strSql);
     }
     
     if(strOper.equals("resrequ")){
    	 GlobalMeth objGM=new GlobalMeth();
    	 objGM.comExeUpdate("update lotteryuser set drawstate='0' where username='"+strUser+"';");
	 }

	 ServletContext sc = getServletContext();
	 RequestDispatcher rd = sc.getRequestDispatcher("/servlet/servuserinfo?mark=useroperman");
     rd.forward(req, res);
     
 }


 public void doGet(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
	 doPost(req,res);
 }
 
 public void updateRemit(String quan, String user,String strDoWhatTemp){
	   DataBaseConn plusRemitConn = new DataBaseConn();
	   plusRemitConn.execQuery("select totalmoney from lotteryuser where username='" +user+ "';");
	   plusRemitConn.rsNext();
	   int intTotalMoney=Integer.parseInt(plusRemitConn.rsGetString("totalmoney"));
	   if(strDoWhatTemp.equals("plus")){
		   intTotalMoney=intTotalMoney+Integer.parseInt(quan);
	   }else{
		   intTotalMoney=intTotalMoney-Integer.parseInt(quan);
	   }
	   plusRemitConn.execute("update lotteryuser set totalmoney='"+String.valueOf(intTotalMoney)+"'where username='"+user+"';");
	   plusRemitConn.connClose();
 }
 public void updateFre(String user,String strDoWhatTemp){
	 GlobalMeth objGM=new GlobalMeth();
	 if(strDoWhatTemp.equals("fre")){
		 objGM.comExeUpdate("update lotteryuser set freeze='冻结' where username='"+user+"';");
	 }else{
		 objGM.comExeUpdate("update lotteryuser set freeze='0' where username='"+user+"';");
	 }
}
 
}
