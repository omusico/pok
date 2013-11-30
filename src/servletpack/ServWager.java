package servletpack;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pubmethpac.GlobalMeth;
import beanpac.CompareBuyAndServerTime;
import beanpac.RemindInfo;
import beanpac.useWagEn;
import dbconnpac.DataBaseConn;

public class ServWager extends HttpServlet
{
 public void doPost(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
	 HttpSession session = req.getSession();
	 String typePageUrl="/lottpok/play/"+req.getParameter("typepagemark")+".jsp";
	 String strMarkIss = new String(req.getParameter("markiss").getBytes("ISO8859_1"),"GB2312").trim();//期号
	
	 String strIssueType = new String(req.getParameter("issuetype").getBytes("ISO8859_1"),"GB2312").trim();//类型singleissue
	 String strIssueNumber = new String(req.getParameter("getissuenumber").getBytes("ISO8859_1"),"GB2312").trim();//期号,多期投注及各期的数量"9009310 |1$9009311 |1$9009312 |1"
	 String strPlayType = new String(req.getParameter("playtype").getBytes("ISO8859_1"),"GB2312").trim();//类型，中文strPlayType= "任选一"
	 String strPlayMode = new String(req.getParameter("playmode").getBytes("ISO8859_1"),"GB2312").trim();//模 式,复式
	 //String strWagTotBef = new String(req.getParameter("wagtotbef").getBytes("ISO8859_1"),"GB2312").trim();
	 String strWagerTotal=new String(req.getParameter("wagertotal").getBytes("ISO8859_1"),"GB2312").trim();//投注的号码
	 String strWagerNum=new String(req.getParameter("wagernum").getBytes("ISO8859_1"),"GB2312").trim();//总注数
	 String strSelling=new String(req.getParameter("selling").getBytes("ISO8859_1"),"GB2312").trim();//是否销售
	 
	 
     String strTabName="pokwagerinfo";
	 RemindInfo remInfo=(RemindInfo)session.getAttribute("loginmes");
	 if(remInfo.getLoginUserName() == null || remInfo.getLoginUserName().equals("")){
		 req.setAttribute("wagrem","您还没有登录，请先登录!");
	 }else{
		 //在购买彩票时，判断是否已经超过销售时间
		 CompareBuyAndServerTime compare = new CompareBuyAndServerTime("poker");
		 String issStat = compare.caculTime(strMarkIss);
		 if(issStat.equals("0")){
			 req.setAttribute("wagrem","今天停止销售!");//今天停止销售 1:正在销售 2:正在开奖
		 }else if(issStat.equals("3")){
			 req.setAttribute("wagrem", "投注期号已经过期，请刷新页面后重新投注!");// 当前投注期号和服务器期号不同，可能客户端与服务器端发生延迟，不允许投注
		 }else if(issStat.equals("2")){
			 req.setAttribute("wagrem","正在开奖,暂停销售!");//1:正在销售 2:正在开奖
		 }else{
			 //正在销售，可以购买
			 String strUserName=remInfo.getLoginUserName();
		     
			 useWagEn objUWE=new useWagEn();
			 String strWagerNumTemp=objUWE.getWagNumTot(strIssueType,strWagerNum, strIssueNumber);//下注总金额
	//		 String strUWE=objUWE.useWagEnable(strUserName,strWagerNumTemp,strWagerTotal,strSelling);//判断是否可以进行购卖		 
			 String strUWE=objUWE.useWagEnable(strMarkIss,strIssueNumber,strUserName,strWagerNumTemp,strWagerTotal,strSelling,"poker",strPlayType,strPlayMode);//判断是否可以进行购卖		 
			 if(!strUWE.equals("true")){
				 req.setAttribute("wagrem",strUWE);
			 }else{
				 boolean wagerSuccess=false;
				 String strMarkIssSeq=getTimes(strTabName,strMarkIss,strUserName);//无论下面如何循环，标识期次数序号，在一次投注中，始终一样。
				 if(strPlayMode.equals("和值") || strPlayMode.equals("跨度") || strPlayType.equals("组选胆拖")||strPlayType.equals("组选复式")){
					 String[] arrWagTot=strWagerTotal.split("f");
					 for(int i=0;i<arrWagTot.length;i++){
						 String[] totBefLat=arrWagTot[i].split("g");
						 String strBef=totBefLat[0];
						 String strLat=totBefLat[1];
						 String strEachWagNum=String.valueOf(strLat.split("\\$").length);
						 wagerSuccess=wager(strTabName, strUserName, strMarkIss, strMarkIssSeq, strIssueType, strIssueNumber, strPlayType, strPlayMode, strBef,strLat, strEachWagNum, strSelling);
					 }
				 }else{
					 wagerSuccess=wager(strTabName, strUserName, strMarkIss, strMarkIssSeq, strIssueType, strIssueNumber, strPlayType, strPlayMode, "", strWagerTotal, strWagerNum, strSelling);
				 }
				 GlobalMeth objGM=new GlobalMeth();
				 boolean upSeqSuc=objGM.comExeUpdate("update "+strTabName+" set wagertotal='"+strMarkIssSeq+"' where username='"+strUserName+"' and markiss='"+strMarkIss+"' and markissseq='mark';");
				 if(wagerSuccess==true && upSeqSuc==true){
					 req.setAttribute("wagrem","投注成功,您的当前余额为"+objGM.getMon(strUserName)+"元");
				 }else{
					 req.setAttribute("wagrem","投注失败!");
				 }
		    }
		 }
	 }
	 
	 ServletContext sc = getServletContext();
	 RequestDispatcher rd;
	 rd = sc.getRequestDispatcher(typePageUrl);
	 rd.forward(req, res);
 }
 
 public boolean wager(String tabTemp,String userNameTemp,String markIssTemp,String markIssSeqTemp,String issTypeTemp,String issNumTemp,String pTypeTemp,String pModeTemp,String wagTotBefTemp,String wagTotTemp,String wagNumTemp,String selTemp){
		boolean wagSuc=true;
		int intTotWag=0;
		if(issTypeTemp.equals("singleissue")){
			wagSuc=newWag(tabTemp,userNameTemp,markIssTemp,markIssSeqTemp,issNumTemp,"n","y",pTypeTemp,pModeTemp,wagTotBefTemp,wagTotTemp,wagNumTemp,"1");
			intTotWag=Integer.parseInt(wagNumTemp);
		}else{
			 boolean sinwagSuc=true;
			 String[] arrIssueInfo=issNumTemp.split("\\$");
			 for(int i=0;i<arrIssueInfo.length;i++){
				 String[] arrIssueTimes=arrIssueInfo[i].split("\\|");
				 sinwagSuc=newWag(tabTemp,userNameTemp,markIssTemp,markIssSeqTemp,arrIssueTimes[0],"y","y",pTypeTemp,pModeTemp,wagTotBefTemp,wagTotTemp,wagNumTemp,arrIssueTimes[1]);
				 int curWag=Integer.parseInt(wagNumTemp)*Integer.parseInt(arrIssueTimes[1]);
				 intTotWag=intTotWag+curWag;
				 if(sinwagSuc==false){
					 wagSuc=sinwagSuc;
				 }
			 }
		}
		if(wagSuc==true){
			GlobalMeth objGM=new GlobalMeth();
			boolean minMonSuc=objGM.minMoney(userNameTemp,intTotWag);//每输入一行，减少相应的投注数。这个函数中是intTotWag(wagNumTemp),在调用函数中,和跨胆复中使用strEachWagNum,其它使用strWagerNum
			if(minMonSuc==false){
				wagSuc=false;
			}
		}
		return wagSuc;
	}
	//得到第几次在同一期添加，如果没有新建并且赋值为0
	public String getTimes(String strTabTemp,String strMarkIssTemp,String strUNTemp){
		String strTotTimes="";
		String strTimes="";
		DataBaseConn dbc = new DataBaseConn();
		String queMISql="select * from "+strTabTemp+" where username='"+strUNTemp+"' and markiss='"+strMarkIssTemp+"';";
		dbc.execQuery(queMISql);
		boolean hasMarkIss=dbc.rsNext();
		if(hasMarkIss==true){
			DataBaseConn dbcTotTimes = new DataBaseConn();
			dbcTotTimes.execQuery("select * from "+strTabTemp+" where username='"+strUNTemp+"' and markiss='"+strMarkIssTemp+"' and markissseq='mark';");
			dbcTotTimes.rsNext();
			strTotTimes=dbcTotTimes.rsGetString("wagertotal");
			dbcTotTimes.connClose();
			int intTimes=Integer.parseInt(strTotTimes)+1;
			strTimes=String.valueOf(intTimes);
		}else{//为假时
			newWag(strTabTemp,strUNTemp,strMarkIssTemp,"mark","","mark","mark","","","","0","","");
			strTimes="1";
		}
		dbc.connClose();
		return strTimes;
	}
	
	public boolean newWag(String tableTemp, String usern, String strMarkIssTemp, String strMarkIssSeqTemp, String issuenum, String hasFolTemp, String validTemp, String playty, String playmo, String wagBef, String strwager, String wagenu,String timesnum){
		 boolean isSuc=false;
		 DataBaseConn wagerDBConn = new DataBaseConn();
		 String sql = "insert into "+tableTemp+" (username, markiss, markissseq, issuenum, hasfol, valid, playtype, playmode, wagtotbef, wagertotal, wagernum, times) values ('" + usern + "','" + strMarkIssTemp + "','" + strMarkIssSeqTemp + "','"+ issuenum+ "','"  + hasFolTemp+ "','" + validTemp+ "','" + playty + "','" + playmo + "','"+ wagBef + "','"+ strwager + "','"+ wagenu + "','"+ timesnum+"');";
		 isSuc=wagerDBConn.execute(sql);
		 wagerDBConn.connCloseUpdate();
		 return isSuc;
	}
}
