package com.onmyway.common;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.onmyway.time.NetTime;

/**
 * @Title:
 * @Description: 
 * @Create on: Apr 13, 2011 10:05:52 PM
 * @Author:LJY
 * @Version:1.0
 */
public class ServerTime {

	public String[] getServerTime_old(){
		
		Calendar calendar = Calendar.getInstance();
	    String NowYear = Integer.toString(calendar.get(Calendar.YEAR));
	    String NowMonth = Integer.toString( (calendar.get(Calendar.MONTH) + 1));
	    String NowDay = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
	    String NowHour = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
	    String NowMinute = Integer.toString(calendar.get(Calendar.MINUTE));
	    String NowSecond = Integer.toString(calendar.get(Calendar.SECOND));
//	    String NowDate = NowYear + "-" +
//	        (NowMonth.length() == 1 ? "0" + NowMonth : NowMonth) + "-" +
//	        (NowDay.length() == 1 ? "0" + NowDay : NowDay) + " " +
//	        (NowHour.length() == 1 ? "0" + NowHour : NowHour) + ":" +
//	        (NowMinute.length() == 1 ? "0" + NowMinute : NowMinute) + ":" +
//	        (NowSecond.length() == 1 ? "0" + NowSecond : NowSecond);
	    String[] dateAry = {NowYear,NowMonth,NowDay,NowHour,NowMinute,NowSecond};
	    return dateAry;
		
		
	}
	public int[] getServerTime(){
		

	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(NetTime.getNowTime());
	    
		int NowYear = calendar.get(Calendar.YEAR);
		int NowMonth = calendar.get(Calendar.MONTH) + 1;
		int NowDay = calendar.get(Calendar.DAY_OF_MONTH);
		int NowHour = calendar.get(Calendar.HOUR_OF_DAY);
		int NowMinute = calendar.get(Calendar.MINUTE);
		int NowSecond = calendar.get(Calendar.SECOND);
//	    String NowDate = NowYear + "-" +
//	        (NowMonth.length() == 1 ? "0" + NowMonth : NowMonth) + "-" +
//	        (NowDay.length() == 1 ? "0" + NowDay : NowDay) + " " +
//	        (NowHour.length() == 1 ? "0" + NowHour : NowHour) + ":" +
//	        (NowMinute.length() == 1 ? "0" + NowMinute : NowMinute) + ":" +
//	        (NowSecond.length() == 1 ? "0" + NowSecond : NowSecond);
		int[] dateAry = {NowYear,NowMonth,NowDay,NowHour,NowMinute,NowSecond};
	    return dateAry;
		
		
	}
}
