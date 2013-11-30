//import required classes
package servletpack;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import dbconnpac.DataBaseConn;
import beanpac.FigWinInfo;
//class login
public class WinNumShow extends HttpServlet
{	 
 public void doPost(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
     HttpSession session=req.getSession(false);
     String strPlay=req.getParameter("play");
     Vector vecWinNumShow=new Vector(); 
     DataBaseConn winNumShowDBC=new DataBaseConn();
     String tableName="";
     if(strPlay.equals("poker")){
    	 tableName="pokwinnum";
     }
     if(strPlay.equals("laugh")){
    	 tableName="lauwinnum";
     }
     if(strPlay.equals("happy")){
    	 tableName="hapwinnum";
     }
     if(strPlay.equals("pth")){
    	 tableName="pthwinnum";
     }
     winNumShowDBC.execQuery("select * from "+tableName+";"); 
     boolean hasWinNum=winNumShowDBC.rsLast();
     if(hasWinNum==true){
    	 vecWinNumShow.addElement(getBeanFWInfo(winNumShowDBC,strPlay));
    	 int count=0;
    	 while(winNumShowDBC.rsPrevious()){
    		 vecWinNumShow.addElement(getBeanFWInfo(winNumShowDBC,strPlay));
    		 count++;
    		 if(count==9){
    			 break;
    		 }
    	 }
    	 
     }
     winNumShowDBC.connClose();
     session.setAttribute("winnumshowinfo", vecWinNumShow);
     ServletContext sc = getServletContext();
	 RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/pubinfopage/winnumshow.jsp");
     rd.forward(req, res);
 }
 

 public void doGet(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
	 doPost(req,res);
 }
 
 public FigWinInfo getBeanFWInfo(DataBaseConn dbcTemp, String strPlayTemp){
	 FigWinInfo beanFWInfo=new FigWinInfo();    	 
	 String strIssNum=dbcTemp.rsGetString("issuenum"); 
	 beanFWInfo.setStrIssueNum(strIssNum);
	 String strWinWagerNum="";
	 if(strPlayTemp.equals("poker")){
		 strWinWagerNum=dbcTemp.rsGetString("spade")+","+dbcTemp.rsGetString("heart")+","+dbcTemp.rsGetString("club")+","+dbcTemp.rsGetString("diamond");
	 }
	 if(strPlayTemp.equals("laugh")||strPlayTemp.equals("happy")||strPlayTemp.equals("pth")){
		 strWinWagerNum=dbcTemp.rsGetString("hundred")+","+dbcTemp.rsGetString("ten")+","+dbcTemp.rsGetString("one");
	 }
	 beanFWInfo.setWinWagerNum(strWinWagerNum);
	 return beanFWInfo;
 }
 
}
