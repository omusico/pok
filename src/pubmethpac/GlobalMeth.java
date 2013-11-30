//include required classes
package pubmethpac;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import dbconnpac.DataBaseConn;

public class GlobalMeth{
	private Logger log = Logger.getLogger("huacaizx");
	public String removeNull(String strObj){
		String strRet=strObj;
		if(strRet==null){
			strRet="";
		}
		return strRet;
	}
	public String getMon(String user){
		DataBaseConn dbc=new DataBaseConn();
		dbc.execQuery("select * from lotteryuser where username='"+user+"';");
		dbc.rsNext();
		String strMon=dbc.rsGetString("totalmoney");
		dbc.connClose();
		return strMon;
	}
	public boolean minMoney(String user,int wagNumTot){
		int intMonSpent=wagNumTot*2;
		DataBaseConn dbc=new DataBaseConn();
		dbc.execQuery("select * from lotteryuser where username='"+user+"';");
		dbc.rsNext();
//		int intMonTotal=Integer.valueOf(dbc.rsGetString("totalmoney"));//alter by ljy on 2009-05-26 
		int intMonTotal=Integer.valueOf(dbc.rsGetString("totalmoney")).intValue(); 
		int intMonLeft=intMonTotal-intMonSpent;
		boolean isSuc=dbc.execute("update lotteryuser set totalmoney='"+intMonLeft+"' where username='"+user+"';");
		dbc.connClose();
		return isSuc;
	}
	public String getStrSysDate(String format,Date objDate){
		SimpleDateFormat sysDateFormat=new SimpleDateFormat(format);
		String strSysDate=sysDateFormat.format(objDate);
		return strSysDate;
	}
	
	//从中文转英文，从英文转中文
	public String tranCoorStr(String[] arrStrMark, String[] arrStrReal, String strMark){
		String strReal="";
		for(int i=0;i<arrStrMark.length;i++){
			if(strMark.equals(arrStrMark[i])){
				strReal=arrStrReal[i];
				break;
			}
		}
		return strReal;
	}
	
	public String formatNum(int intValue){
		 String strFormated="";
		 if(intValue>=0 && intValue<10){
			 strFormated="00"+String.valueOf(intValue);
		 }else if(intValue>=10 && intValue<100){
			 strFormated="0"+String.valueOf(intValue);
		 }else{
			 strFormated=String.valueOf(intValue);
		 }
		 return strFormated;	 
	 }
	public String formatNumTwo(int intValue){
		 String strFormated="";
		 if(intValue>=0 && intValue<10){
			 strFormated="0"+String.valueOf(intValue);
		 }else{
			 strFormated=String.valueOf(intValue);
		 }
		 return strFormated;	 
	 }
	
	public long formatToParseTime(String strFormat,String strTime){
		SimpleDateFormat objFormat = new SimpleDateFormat(strFormat);
	    Date objDate=null;
        try{
        	objDate=objFormat.parse(strTime);
    	}catch(ParseException e){
    		System.out.println(e.toString());
    	}
//	    Long longParseTime=objDate.getTime();//alter by ljy on 2009-05-26
	    long longParseTime=objDate.getTime();
	    return longParseTime;
	}
	
	 public boolean newwager(String wagerInfoTableTemp, String usern, String issnum, String strwager, String wagenu){
		  try{
			  DataBaseConn wagerDBConn = new DataBaseConn();
			  String sql = "insert into "+ wagerInfoTableTemp+" (username, issuenum, wagertotal, wagernum) values ('" + usern + "','" + issnum + "','"+ strwager + "','"+ wagenu +"');";
			  boolean isSuc=wagerDBConn.execute(sql);
			  wagerDBConn.connCloseUpdate();
			  if(isSuc==true){
				  return true;
			  }else{
				  return false;
			  }		  
			 }catch(Exception e){
			  System.out.println(e.toString());
			  return false;
			 }
	 }
	 
	 public boolean comExeUpdate(String strSqlTemp){
		 DataBaseConn comExeUpDBC = new DataBaseConn();
		 boolean isSuc=comExeUpDBC.execute(strSqlTemp);
		 comExeUpDBC.connCloseUpdate();
		 return isSuc;
	 }
	
}
