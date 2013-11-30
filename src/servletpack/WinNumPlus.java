//import required classes
package servletpack;

import java.io.*;
import java.util.Date;
import java.util.Vector;

import javax.servlet.*;
import javax.servlet.http.*;

import beanpac.FigWinInfoUser;
import beanpac.WinUserPok;
import beanpac.FigWinInfo;

import dbconnpac.DataBaseConn;
import pubmethpac.GlobalMeth;
import pubmethpac.PokWagPub;
import beanpac.PrMoney;

public class WinNumPlus extends HttpServlet
{	 
 public void doPost(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
	 HttpSession session = req.getSession(false);
	 String currIssueNum = new String(req.getParameter("pokissnum").getBytes("ISO8859_1"),"GB2312").trim();
	 String spade = new String(req.getParameter("spade").getBytes("ISO8859_1"),"GB2312").trim();
	 String heart = new String(req.getParameter("heart").getBytes("ISO8859_1"),"GB2312").trim();
	 String club = new String(req.getParameter("club").getBytes("ISO8859_1"),"GB2312").trim();
	 String diamond = new String(req.getParameter("diamond").getBytes("ISO8859_1"),"GB2312").trim();
	 GlobalMeth objGM=new GlobalMeth();
	 Date sysDate=new Date();
	 String issueDay = objGM.getStrSysDate("yyyyMMdd",sysDate);
	 DataBaseConn winNumDBC=new DataBaseConn();
	 String sqlStrQue="select * from pokwinnum where issuenum='"+currIssueNum+"';";
	 winNumDBC.execQuery(sqlStrQue);
	 if(winNumDBC.rsNext()==false){
	   String sqlStr="insert into pokwinnum(issueday,issuenum,spade,heart,club,diamond) values('" + issueDay + "','"+ currIssueNum + "','" + spade + "','" + heart + "','" + club + "','" + diamond + "');";
	   if(winNumDBC.execute(sqlStr)==true){
		   winUserToBase(currIssueNum,spade,heart,club,diamond);//如果中奖号码添加成功，查询中奖用户并进入数据库
		   req.setAttribute("remMessage", "添加成功！");
	   }else{
		   req.setAttribute("remMessage", "添加失败！");
	   } 
	 }else{
		 req.setAttribute("remMessage", "已有期号，不能添加！");
	 }
	 winNumDBC.connClose();
	 
	 ServletContext sc = getServletContext();
	 RequestDispatcher rd = sc.getRequestDispatcher("/servlet/loginmaninit?play=pok");
     rd.forward(req, res);
 }
 

 public void doGet(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
	 doPost(req,res);
 }
 
 public void winUserToBase(String strIssueNum,String strSpade,String strHeart,String strClub,String strDiamond){
	 String[] arrWinPrizeFig={strSpade,strHeart,strClub,strDiamond};
     DataBaseConn winQueryDBC=new DataBaseConn();
     winQueryDBC.execQuery("select * from pokwagerinfo where issuenum='"+strIssueNum+"' and valid='y' order by username;");//搜索出所有当期投注用户
     while(winQueryDBC.rsNext()){
    	 String strMarkIss=winQueryDBC.rsGetString("markiss");
    	 String strMarkIssSeq=winQueryDBC.rsGetString("markissseq");
    	 String strHasFol=winQueryDBC.rsGetString("hasfol");
    	 String pType=winQueryDBC.rsGetString("playtype");
    	 String pMode=winQueryDBC.rsGetString("playmode");
    	 String wTotBef=winQueryDBC.rsGetString("wagtotbef");
    	 String wTotal=winQueryDBC.rsGetString("wagertotal");
    	 String strUserName=winQueryDBC.rsGetString("username");
		 String strIssNum=winQueryDBC.rsGetString("issuenum");
		 String strTimes=winQueryDBC.rsGetString("times");
		 
    	 Vector wInfo;
    	 WinUserPok objWUP=new WinUserPok();
    	 if(pType.equals("任选一")||pType.equals("任选二")){
    		 wInfo=objWUP.getVecFreeWin(arrWinPrizeFig,wTotal,pType);//得到wagerTotal中共有几次中奖   		 
    	 }else if(pType.equals("任选三")){
    		 wInfo=objWUP.getWinThr(arrWinPrizeFig,wTotal,pType);
    	 }else if(pType.equals("选四直选")){
    		 wInfo=objWUP.getWinFour(arrWinPrizeFig,wTotal,pType);
    	 }else if(pType.equals("组合投注")){
    		 wInfo=objWUP.getVecFreeCompWin(arrWinPrizeFig,wTotal);
    	 }else if(pType.equals("组选单式") || pType.equals("组选复式") || pType.equals("组选胆拖")){
    		 wInfo=objWUP.getVecGroupWin(arrWinPrizeFig,wTotal,pMode);
    	 }else{
    		 wInfo=null;
    	 }
    	 for(int i=0;i<wInfo.size();i++){
             FigWinInfo objFigWinInfo=(FigWinInfo)wInfo.elementAt(i);
    		 String strPlayType=pType;
    		 String strPlayMode=pMode;
    		 String strOneWager=objFigWinInfo.getStrOneWag();
    		 String strRule=objFigWinInfo.getStrRule();
    		 String strWagerNum=objFigWinInfo.getWinWagerNum();
    		 PrMoney objPM=new PrMoney();
    		 String strPrize=objPM.getPrize(strWagerNum,strTimes,strRule);
    		 DataBaseConn winUserDBC=new DataBaseConn();
    		 String strSql = "insert into pokprizeinfo (username,issuenum,playtype,playmode,onewagbef,onewager,rule,wagernum,times,prize) values('" + strUserName + "','" + strIssNum + "','" + strPlayType + "','" + strPlayMode + "','" + wTotBef + "','"+ strOneWager + "','" +strRule+"','"+ strWagerNum + "','" + strTimes + "','" + strPrize + "');";
    		 winUserDBC.execute(strSql);
    		 winUserDBC.connCloseUpdate();
    	 }
    	 //如果有中奖，如果是多期，删除其它跟期，并还原金额
    	 PokWagPub objPWP=new PokWagPub();
    	 if(wInfo.size()>0){
    		 objPWP.invalid("lotteryuser","pokwagerinfo",strUserName,strMarkIss,strMarkIssSeq,strIssNum,strHasFol); 
    	 }else{
    		 objPWP.invalNoWin("pokwagerinfo",strUserName,strMarkIss,strMarkIssSeq,strIssNum);
    	 }
     }
     
     
     winQueryDBC.connClose();
     
     
     
 }
 
 
}
