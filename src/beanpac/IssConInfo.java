package beanpac;

import dbconnpac.DataBaseConn;

public class IssConInfo{
	private String strBegDate="0";
	private String strBegIss="0";
	private String strBegHour="0";
	private String strBegMin="0";
	private String strDurSell="0";
	private String strDurWin="0";
	private String strHMIss="0";
	

	private String offsetFlag="+";
	private String offsetTime="0";
	
	
	public void getIssCon(String strTable){
		DataBaseConn picDBC=new DataBaseConn();
		picDBC.execQuery("select * from "+strTable+" where id='1';");
		picDBC.rsNext();
		strBegDate=picDBC.rsGetString("begdate");
		strBegIss=picDBC.rsGetString("begiss");
		strBegHour=picDBC.rsGetString("beghour");
		strBegMin=picDBC.rsGetString("begmin");
		strDurSell=picDBC.rsGetString("dursell");
		strDurWin=picDBC.rsGetString("durwin");
		strHMIss=picDBC.rsGetString("howmanyiss");
		offsetFlag=picDBC.rsGetString("offset_flag");
		offsetTime=picDBC.rsGetString("offset_time");
		
		picDBC.connClose();
		
	}

	public String getStrBegHour() {
		return strBegHour;
	}

	public String getStrBegIss() {
		return strBegIss;
	}

	public String getStrBegMin() {
		return strBegMin;
	}

	public String getStrDurSell() {
		return strDurSell;
	}

	public String getStrDurWin() {
		return strDurWin;
	}

	public String getStrHMIss() {
		return strHMIss;
	}

	public String getStrBegDate() {
		return strBegDate;
	}

	public String getOffsetFlag() {
		return offsetFlag;
	}

	public void setOffsetFlag(String offsetFlag) {
		this.offsetFlag = offsetFlag;
	}

	public String getOffsetTime() {
		return offsetTime;
	}

	public void setOffsetTime(String offsetTime) {
		this.offsetTime = offsetTime;
	}

	public void setStrBegDate(String strBegDate) {
		this.strBegDate = strBegDate;
	}

	public void setStrBegIss(String strBegIss) {
		this.strBegIss = strBegIss;
	}

	public void setStrBegHour(String strBegHour) {
		this.strBegHour = strBegHour;
	}

	public void setStrBegMin(String strBegMin) {
		this.strBegMin = strBegMin;
	}

	public void setStrDurSell(String strDurSell) {
		this.strDurSell = strDurSell;
	}

	public void setStrDurWin(String strDurWin) {
		this.strDurWin = strDurWin;
	}

	public void setStrHMIss(String strHMIss) {
		this.strHMIss = strHMIss;
	}
	
}
