//import required classes
package servletpack;

import java.io.*;
import java.util.Date;
import java.util.Vector;

import javax.servlet.*;
import javax.servlet.http.*;

import beanpac.FigWinInfo;
import beanpac.WinUserLau;

import pubmethpac.GlobalMeth;
import pubmethpac.PokWagPub;

import dbconnpac.DataBaseConn;

public class HapWinNumPlus extends HttpServlet
{	 
 public void doPost(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
	 HttpSession session = req.getSession(false);
	 String currIssueNum = new String(req.getParameter("hapissnum").getBytes("ISO8859_1"),"GB2312").trim();
	 String hundred = new String(req.getParameter("hundred").getBytes("ISO8859_1"),"GB2312").trim();
	 String ten = new String(req.getParameter("ten").getBytes("ISO8859_1"),"GB2312").trim();
	 String one = new String(req.getParameter("one").getBytes("ISO8859_1"),"GB2312").trim();
	 GlobalMeth objGM=new GlobalMeth();
	 Date sysDate=new Date();
	 String issueDay = objGM.getStrSysDate("yyyyMMdd",sysDate);
	 DataBaseConn winNumDBC=new DataBaseConn();
	 String sqlStrQue="select * from hapwinnum where issuenum='"+currIssueNum+"';";
	 winNumDBC.execQuery(sqlStrQue);
	 if(winNumDBC.rsNext()==false){
	   String sqlStr="insert into hapwinnum(issueday,issuenum,hundred,ten,one) values('" + issueDay + "','"+ currIssueNum + "','" + hundred + "','" + ten + "','" + one + "');";
	   if(winNumDBC.execute(sqlStr)==true){
		   req.setAttribute("remMessage", "添加成功！");
		   winUserToBase(currIssueNum,hundred,ten,one);//如果中奖号码添加成功，查询中奖用户并进入数据库
	   }else{
		   req.setAttribute("remMessage", "添加失败！");
	   } 
	 }else{
	   req.setAttribute("remMessage", "已有期号，不能添加！");
	 }
	 winNumDBC.connClose();
	 ServletContext sc = getServletContext();
	 RequestDispatcher rd = sc.getRequestDispatcher("/servlet/loginmaninit?play=hap");
     rd.forward(req, res);
 }
 
 public void doGet(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
	 doPost(req,res);
 }
 
 public void winUserToBase(String strIssueNum,String strHundred,String strTen,String strOne){
	 String[] arrWinPrizeFig={strHundred,strTen,strOne};
     DataBaseConn winQueryDBC=new DataBaseConn();
     winQueryDBC.execQuery("select * from hapwagerinfo where issuenum='"+strIssueNum+"' and valid='y' order by username;");//搜索出所有当期投注用户
     while(winQueryDBC.rsNext()){
    	 String strMarkIss=winQueryDBC.rsGetString("markiss");
    	 String strMarkIssSeq=winQueryDBC.rsGetString("markissseq");
    	 String strHasFol=winQueryDBC.rsGetString("hasfol");
    	 String pType=winQueryDBC.rsGetString("playtype");
    	 String pMode=winQueryDBC.rsGetString("playmode");
    	 String wTotal=winQueryDBC.rsGetString("wagertotal");
    	 String strUserName=winQueryDBC.rsGetString("username");
		 String strIssNum=winQueryDBC.rsGetString("issuenum");
		 String strTimes=winQueryDBC.rsGetString("times");
    	 Vector wInfo=new Vector();
    	 WinUserLau objWUL=new WinUserLau();
    	 if(pType.equals("直选")){
    		 wInfo=objWUL.getVecComWin(arrWinPrizeFig,wTotal,pType,pMode);	 
    	 }
    	 if(pType.equals("组选")){
    		 wInfo=objWUL.getVecGrWinPth(arrWinPrizeFig,wTotal,pType,pMode);		 
    	 }
    	 for(int i=0;i<wInfo.size();i++){
             FigWinInfo objFigWinInfo=(FigWinInfo)wInfo.elementAt(i);
    		 String strPlayType=pType;
    		 String strPlayMode=pMode;
    		 String strOneWager=objFigWinInfo.getStrOneWag();
    		 String strRule=objFigWinInfo.getStrRule();
    		 String strWagerNum=objFigWinInfo.getWinWagerNum();
    		 String strPrize=getPrize(strWagerNum,strTimes,strRule,arrWinPrizeFig);    		 
    		 DataBaseConn winUserDBC=new DataBaseConn();
    		 String strSql = "insert into happrizeinfo (username,issuenum,playtype,playmode,onewager,rule,wagernum,times,prize) values('" + strUserName + "','" + strIssNum + "','" + strPlayType + "','" + strPlayMode + "','" + strOneWager + "','" +strRule+"','"+ strWagerNum + "','" + strTimes + "','" + strPrize + "');";
    		 winUserDBC.execute(strSql);
    		 winUserDBC.connCloseUpdate();
    	 }
    	 
    	 //如果有中奖，如果是多期，删除其它跟期，并还原金额
    	 PokWagPub objPWP=new PokWagPub();
    	 if(wInfo.size()>0){
    		 objPWP.invalid("lotteryuser","hapwagerinfo",strUserName,strMarkIss,strMarkIssSeq,strIssNum,strHasFol); 
    	 }else{
    		 objPWP.invalNoWin("hapwagerinfo",strUserName,strMarkIss,strMarkIssSeq,strIssNum);
    	 }
     }
     
     
     winQueryDBC.connClose();
     
     
     
 }

 public String getPrize(String wagNum, String times,String rule,String[] arrWinFig){
	 String strPr="";
	 int intPM=0;
	 String strRuleEng="";
	 DataBaseConn pmDBC=new DataBaseConn();
	 if(rule.equals("和值")){
		 strRuleEng=whTypeGr(arrWinFig);
	 }else{
	     String[] arrRuleChin={"直选","组3","组6"};
	     String[] arrRuleEng={"hapsingthr","hapgrthr","hapgrsix"};
	     GlobalMeth objGM=new GlobalMeth();
	     strRuleEng=objGM.tranCoorStr(arrRuleChin, arrRuleEng, rule);
	 }

	 pmDBC.execQuery("select * from happrmoney where rule='"+strRuleEng+"';");
	 pmDBC.rsNext();
	 intPM=Integer.parseInt(pmDBC.rsGetString("money"));
	 int intPr=Integer.parseInt(wagNum)*intPM*Integer.parseInt(times);
	 strPr=String.valueOf(intPr);
	 pmDBC.connClose();
	 return strPr;
	 
 }
 
 public String whTypeGr(String[] arrWinFigTemp){
	 String strRet="";
	 if(!arrWinFigTemp[0].equals(arrWinFigTemp[1])){
		 if(arrWinFigTemp[0].equals(arrWinFigTemp[2]) || arrWinFigTemp[1].equals(arrWinFigTemp[2])){
			 strRet="hapgrthr"; 
		 }
		 if(!arrWinFigTemp[1].equals(arrWinFigTemp[2]) && !arrWinFigTemp[0].equals(arrWinFigTemp[2])){
			 strRet="hapgrsix";
		 }
	 }else{
		 if(!arrWinFigTemp[0].equals(arrWinFigTemp[2])){
			 strRet="hapgrthr";
		 }
	 }
	 return strRet;
 }
}
