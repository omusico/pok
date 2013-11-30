package pubmethpac;

import java.text.SimpleDateFormat;
import java.util.Date;

import pubmethpac.GlobalMeth;
import beanpac.ServerTime;

public class IssInfoCal{
	
	public int getCurIss(int begTimSec,int durSec,int HMIss){
		ServerTime objST=new ServerTime();
		int intH=Integer.parseInt(objST.getHour());
		int intM=Integer.parseInt(objST.getMinute());
		int intS=Integer.parseInt(objST.getSecond());
		int intCurTimSec=intH*60*60+intM*60+intS;
		int endTimSec=durSec*HMIss+begTimSec;
		int intCurIss=0;
		if(intCurTimSec>begTimSec && intCurTimSec<endTimSec){
			int elapFromBeg=intCurTimSec-begTimSec;//服务器时间与9:00的差
			intCurIss = (int)Math.floor(elapFromBeg/durSec);//服务器时间与9:00的差，除以15分钟，加开始期，得到期号
		}else{
			intCurIss=HMIss;
		}
		return intCurIss;
	}
	
	public String getStrTime(int intSec,Date objDateTemp){	
		int secPerHour=60*60;
		int intHour=(int)Math.floor(intSec/secPerHour);
		int intMin=(intSec%secPerHour)/60;
		GlobalMeth objGM=new GlobalMeth();
		String strTime=getStrSysDate("yyyy-MM-dd",objDateTemp)+" "+objGM.formatNumTwo(intHour)+":"+objGM.formatNumTwo(intMin)+":00";
		return strTime;
	}
	
	public String getStrSysDate(String format,Date objDate){
		SimpleDateFormat sysDateFormat=new SimpleDateFormat(format);
		String strSysDate=sysDateFormat.format(objDate);
		return strSysDate;
	}
	

}
