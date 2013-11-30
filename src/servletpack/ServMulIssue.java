//import required classes
package servletpack;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import pubmethpac.IssInfoCal;
import beanpac.ServerTime;
import beanpac.SingleIssueInfo;
import beanpac.IssConInfo;

public class ServMulIssue extends HttpServlet{	
 public void doPost(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
	 HttpSession session = req.getSession(false);
	 String strPlay=req.getParameter("play");
	 String strTab="";
	 if(strPlay.equals("poker")){
		 strTab="pokisscon";
	 }
	 if(strPlay.equals("laugh")){
		 strTab="lauisscon";
	 }
	 session.setAttribute("wholeissnumvec", getCurIssueNum(strTab));
	 ServletContext sc = getServletContext();
	 RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/pubinfopage/mulissue.jsp");
     rd.forward(req, res);
     
 }
 

 public void doGet(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
	 doPost(req,res);
 }

	public Vector getCurIssueNum(String strTable){
		IssConInfo objICI=new IssConInfo();
		objICI.getIssCon(strTable);
		 String strBD=objICI.getStrBegDate();
		 int intBegIss=Integer.parseInt(objICI.getStrBegIss());
		 int intBegH=Integer.parseInt(objICI.getStrBegHour());
		 int intBegM=Integer.parseInt(objICI.getStrBegMin());
		 int intDurS=Integer.parseInt(objICI.getStrDurSell());
		 int intDurW=Integer.parseInt(objICI.getStrDurWin());
		 int intHMI=Integer.parseInt(objICI.getStrHMIss());
		 
		 int intBegTimSec=intBegH*60*60+intBegM*60;
		 int intDurSSec=intDurS*60;
		 int intDurWSec=intDurW*60;
		 int intDurSec=intDurSSec+intDurWSec;
		 
		 Date sysDate=new Date();
		 Vector vecWholeIssueNum=new Vector();
		 SimpleDateFormat sysDateFormat=new SimpleDateFormat("yyyyMMdd");
		 String strSysDate=sysDateFormat.format(sysDate);

		 if(strBD.equals(strSysDate)){
			 IssInfoCal objIssInfCal=new IssInfoCal();
			 int intCurIss=objIssInfCal.getCurIss(intBegTimSec,intDurSec,intHMI);
	
			 int issueLength=intHMI-intCurIss;//当前期以0开始，0为第一期。如果当前期大于总期数，为负，不执行循环，vector.size为0
			 for(int i=0;i<issueLength;i++){
				 int issNumTemp=intCurIss+i;
				 String strIssNum=String.valueOf(intBegIss+issNumTemp);
				 int wagEndTimSec=intBegTimSec+(issNumTemp+1)*intDurSec-intDurWSec;
				 int winEndTimeSec=wagEndTimSec+intDurWSec;
				 
				 SingleIssueInfo objSingleIssueInfo=new SingleIssueInfo();
				 objSingleIssueInfo.setIssueNumber(strIssNum);
				 objSingleIssueInfo.setWagerEndTime(objIssInfCal.getStrTime(wagEndTimSec, sysDate));
				 objSingleIssueInfo.setWinPrizeTime(objIssInfCal.getStrTime(winEndTimeSec, sysDate));
				 
				 vecWholeIssueNum.addElement(objSingleIssueInfo);
			 }
		 }
		 return vecWholeIssueNum;
	 }

}
