package beanpac;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ServerTime{
	
	private String year="";
	private String month="";
	private String day="";
	private String hour="";
	private String minute="";
	private String second="";
	
	public ServerTime(){
		
		GregorianCalendar calendar = new GregorianCalendar();
		Integer intYear = new Integer(calendar.get(Calendar.YEAR));
		Integer intMonth = new Integer(calendar.get(Calendar.MONTH));
		Integer intDay = new Integer(calendar.get(Calendar.DAY_OF_MONTH));
		Integer intHour = new Integer(calendar.get(Calendar.HOUR_OF_DAY));
		Integer intMinute = new Integer(calendar.get(Calendar.MINUTE));
		Integer intSecond = new Integer(calendar.get(Calendar.SECOND));
		year = intYear.toString();
		month = intMonth.toString();
		day = intDay.toString();
		hour = intHour.toString();
		minute = intMinute.toString();
		second = intSecond.toString();

		
	}
	public String getYear(){
		return(year);
	}
	public void setYear(String ye){
		this.year=ye;
	}
	
	public String getMonth(){
		return(month);
	}
	public void setMonth(String mo){
		this.month=mo;
	}
	public String getDay(){
		return(day);
	}
	public void setDay(String da){
		this.day=da;
	}
	public String getHour(){
		return(hour);
	}
	public void setHour(String ho){
		this.hour=ho;
	}
	public String getMinute(){
		return(minute);
	}
	public void setMinute(String mi){
		this.minute=mi;
	}
	public String getSecond(){
		return(second);
	}
	public void setSecond(String se){
		this.second=se;
	}
}
