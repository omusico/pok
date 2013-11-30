//include required classes
package pubmethpac;

import dbconnpac.DataBaseConn;
import pubmethpac.GlobalMeth;

public class PokWagPub{

	//投注公共
	public boolean wager(String tabTemp,String userNameTemp,String markIssTemp,String issTypeTemp,String issNumTemp,String pTypeTemp,String pModeTemp,String wagTotTemp,String wagNumTemp,String selTemp){
		boolean wagSuc=true;
		String markIssSeq=getTimes(tabTemp,markIssTemp,userNameTemp);
		int intTotWag=0;
		if(issTypeTemp.equals("singleissue")){
			wagSuc=newWag(tabTemp,userNameTemp,markIssTemp,markIssSeq,issNumTemp,"n","y",pTypeTemp,pModeTemp,wagTotTemp,wagNumTemp,"1");
			intTotWag=Integer.parseInt(wagNumTemp);
		}else{
			 boolean sinwagSuc=true;
			 String[] arrIssueInfo=issNumTemp.split("\\$");
			 for(int i=0;i<arrIssueInfo.length;i++){
				 String[] arrIssueTimes=arrIssueInfo[i].split("\\|");
				 sinwagSuc=newWag(tabTemp,userNameTemp,markIssTemp,markIssSeq,arrIssueTimes[0],"y","y",pTypeTemp,pModeTemp,wagTotTemp,wagNumTemp,arrIssueTimes[1]);
				 int curWag=Integer.parseInt(wagNumTemp)*Integer.parseInt(arrIssueTimes[1]);
				 intTotWag=intTotWag+curWag;
				 if(sinwagSuc==false){
					 wagSuc=sinwagSuc;
				 }
			 }
		}
		if(wagSuc==true){
			GlobalMeth objGM=new GlobalMeth();
			boolean upSeqSuc=objGM.comExeUpdate("update "+tabTemp+" set wagertotal='"+markIssSeq+"' where username='"+userNameTemp+"' and markiss='"+markIssTemp+"' and markissseq='mark';");
			boolean minMonSuc=objGM.minMoney(userNameTemp,intTotWag);
			if(upSeqSuc==false || minMonSuc==false){
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
			newWag(strTabTemp,strUNTemp,strMarkIssTemp,"mark","","mark","mark","","","0","","");
			strTimes="1";
		}
		dbc.connClose();
		return strTimes;
	}
	
	public boolean newWag(String tableTemp, String usern, String strMarkIssTemp, String strMarkIssSeqTemp, String issuenum, String hasFolTemp, String validTemp, String playty, String playmo, String strwager, String wagenu,String timesnum){
		 boolean isSuc=false;
		 DataBaseConn wagerDBConn = new DataBaseConn();
		 String sql = "insert into "+tableTemp+" (username, markiss, markissseq, issuenum, hasfol, valid, playtype, playmode, wagertotal, wagernum, times) values ('" + usern + "','" + strMarkIssTemp + "','" + strMarkIssSeqTemp + "','"+ issuenum+ "','"  + hasFolTemp+ "','" + validTemp+ "','" + playty + "','" + playmo + "','"+ strwager + "','"+ wagenu + "','"+ timesnum+"');";
		 isSuc=wagerDBConn.execute(sql);
		 wagerDBConn.connCloseUpdate();
		 return isSuc;
	}
	
	
	//中奖判断公共
	
    //valid表示是否有效显示在页面上。如果是多期，无效显示其它跟期，并还原金额
	 public void invalid(String userTabTemp, String tabTemp, String userNameTemp, String markIssTemp, String markIssSeqTemp, String issueNumTemp, String hasFolTemp){

		 if(hasFolTemp.equals("y")){
			 int restTotMon=0;
			 DataBaseConn queMonDBC=new DataBaseConn();
			 queMonDBC.execQuery("select * from "+tabTemp+" where username='"+userNameTemp+"' and markiss='"+markIssTemp+"' and markissseq='"+markIssSeqTemp+"' and issuenum>'"+issueNumTemp+"';");
			 while(queMonDBC.rsNext()){
				 int intWagNum=Integer.parseInt(queMonDBC.rsGetString("wagernum"));
				 int intT=Integer.parseInt(queMonDBC.rsGetString("times"));
				 int intMon=intWagNum*intT*2;
				 restTotMon=restTotMon+intMon;
			 }
			 queMonDBC.connClose();
			 GlobalMeth objGM=new GlobalMeth();
			 int intTotMon=Integer.parseInt(objGM.getMon(userNameTemp))+restTotMon;
			 objGM.comExeUpdate("update "+userTabTemp+" set totalmoney='"+String.valueOf(intTotMon)+"' where username='"+userNameTemp+"';");
			 
			 DataBaseConn invalidDBC=new DataBaseConn();
			 invalidDBC.execute("update "+tabTemp+" set valid='n' where username='"+userNameTemp+"' and markiss='"+markIssTemp+"' and markissseq='"+markIssSeqTemp+"' and issuenum>'"+issueNumTemp+"';");
			 invalidDBC.connCloseUpdate();
		 }
	 }
	 
	 public void invalNoWin(String tabTemp,String userNameTemp,String markIssTemp,String markIssSeqTemp,String issueNumTemp){
		 /*
		 DataBaseConn invalidDBC=new DataBaseConn();
		 String strSql ="update "+tabTemp+" set valid='n' where username='"+userNameTemp+"' and markiss='"+markIssTemp+"' and markissseq='"+markIssSeqTemp+"' and issuenum='"+issueNumTemp+"';";
		 invalidDBC.execute(strSql);
		 invalidDBC.connCloseUpdate();
		 */
	 }
	 
	 //删除投注UseInfWagRem使用
	 //删除后，还要还原钱数
	 public String delIss(String strType, String strUserTabTemp,String strTabTemp,String strWagIDTemp){
		 String strReturn="";
		 DataBaseConn queInfDBC=new DataBaseConn();
		 queInfDBC.execQuery("select * from "+strTabTemp+" where id='"+strWagIDTemp+"';");
		 queInfDBC.rsNext();
		 String strHasFol=queInfDBC.rsGetString("hasfol");
		 String strUserName=queInfDBC.rsGetString("username");
		 String strMarkIss=queInfDBC.rsGetString("markiss");
		 String strMarkIssSeq=queInfDBC.rsGetString("markissseq");
		 int restTotMon=0;
		 GlobalMeth objGM=new GlobalMeth();
		 if(strHasFol.equals("n")){
			 restTotMon=2*Integer.parseInt(queInfDBC.rsGetString("wagernum"))*Integer.parseInt(queInfDBC.rsGetString("times"));
			 objGM.comExeUpdate("delete from "+strTabTemp+" where id='"+strWagIDTemp+"';");
			 strReturn=strType+":单期"+strMarkIss+"第"+strMarkIssSeq+"次投注已经删除，还原金额"+restTotMon+"元。";
		 }else{
			 DataBaseConn queMonDBC=new DataBaseConn();
			 queMonDBC.execQuery("select * from "+strTabTemp+" where username='"+strUserName+"' and markiss='"+strMarkIss+"' and markissseq='"+strMarkIssSeq+"';");
			 while(queMonDBC.rsNext()){
				 int intWagNum=Integer.parseInt(queMonDBC.rsGetString("wagernum"));
				 int intT=Integer.parseInt(queMonDBC.rsGetString("times"));
				 int intMon=intWagNum*intT*2;
				 restTotMon=restTotMon+intMon;
			 }
			 queMonDBC.connClose();
			 objGM.comExeUpdate("delete from "+strTabTemp+" where username='"+strUserName+"' and markiss='"+strMarkIss+"' and markissseq='"+strMarkIssSeq+"';");
			 strReturn=strType+":多期"+strMarkIss+"第"+strMarkIssSeq+"次投注已经删除，还原金额"+restTotMon+"元。";
		 }
		 int intTotMon=Integer.parseInt(objGM.getMon(strUserName))+restTotMon;
		 objGM.comExeUpdate("update "+strUserTabTemp+" set totalmoney='"+String.valueOf(intTotMon)+"' where username='"+strUserName+"';");
		 
		 queInfDBC.connClose();
		 
		 
		 
		 return strReturn;
	 }
	 
	
}
