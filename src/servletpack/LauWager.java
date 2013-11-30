package servletpack;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.http.*;

import pubmethpac.GlobalMeth;
import pubmethpac.PokWagPub;

import beanpac.CompareBuyAndServerTime;
import beanpac.RemindInfo;
import beanpac.useWagEn;
import dbconnpac.DataBaseConn;

//class login
public class LauWager extends HttpServlet
{
 public void doPost(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException{
	 HttpSession session = req.getSession(false);
	 RemindInfo remInfo=(RemindInfo)session.getAttribute("loginmes");
	 String strMarkIss = new String(req.getParameter("markiss").getBytes("ISO8859_1"),"GB2312").trim();
	 String strIssueType = new String(req.getParameter("issuetype").getBytes("ISO8859_1"),"GB2312").trim();
	 String strIssueNumber = req.getParameter("getissuenumber");
	 String strPlayType = new String(req.getParameter("playtype").getBytes("ISO8859_1"),"GB2312").trim();
	 String strPlayMode = new String(req.getParameter("playmode").getBytes("ISO8859_1"),"GB2312").trim();
	 String strWagerTotal=new String(req.getParameter("wagertotal").getBytes("ISO8859_1")).trim();
	 String strWagerNum=new String(req.getParameter("wagernum").getBytes("ISO8859_1")).trim();
	 String strSelling=new String(req.getParameter("selling").getBytes("ISO8859_1"),"GB2312").trim();
	 
	 String typePageUrl="/lott/laugh/play/"+req.getParameter("typepagemark")+".jsp";
	 
	 String wagerInfoTable="lauwagerinfo";
	 
	 if(remInfo.getLoginUserName().equals("")){
		 req.setAttribute("wagrem","您还没有登录，请先登录!");
	 }else{
		//在购买彩票时，判断是否已经超过销售时间
		 CompareBuyAndServerTime compare = new CompareBuyAndServerTime("laugh");
		 String issStat = compare.caculTime(strMarkIss);
		 if(issStat.equals("0")){
			 req.setAttribute("wagrem","今天停止销售!");//今天停止销售 1:正在销售 2:正在开奖
		 }else if(issStat.equals("3")){
			 req.setAttribute("wagrem", "投注期号已经过期，请刷新页面后重新投注!");// 当前投注期号和服务器期号不同，可能客户端与服务器端发生延迟，不允许投注
		 }else if(issStat.equals("2")){
			 req.setAttribute("wagrem","正在开奖,暂停销售!");//1:正在销售 2:正在开奖
		 }else{
			 String strUserName=remInfo.getLoginUserName();
			
	
			 useWagEn objUWE=new useWagEn();
			 String strWagerNumTemp=objUWE.getWagNumTot(strIssueType,strWagerNum, strIssueNumber);
	//		 String strUWE=objUWE.useWagEnable(strUserName,strWagerNumTemp,strWagerTotal,strSelling);	
			 String strUWE=objUWE.useWagEnable(strMarkIss,strIssueNumber,strUserName,strWagerNumTemp,strWagerTotal,strSelling,"laugh",strPlayType,strPlayMode);//判断是否可以进行购卖		 
	
			 if(!strUWE.equals("true")){
				 req.setAttribute("wagrem",strUWE);
			 }else{			 
				 PokWagPub objPWP=new PokWagPub();
				 boolean wagerSuccess=objPWP.wager("lauwagerinfo", strUserName, strMarkIss, strIssueType, strIssueNumber, strPlayType, strPlayMode, strWagerTotal, strWagerNum, strSelling);
				 if(wagerSuccess==true){
					 GlobalMeth objGM=new GlobalMeth();
					 req.setAttribute("wagrem","投注成功,您的当前余额为"+objGM.getMon(strUserName)+"元");
				 }else{
					 req.setAttribute("wagrem","投注失败!");
				 }
			    }
			 }
	 }

	 ServletContext sc = getServletContext();
	 RequestDispatcher rd=sc.getRequestDispatcher(typePageUrl);
	 rd.forward(req, res);
 }
 
}
