package util;

 
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.TimeZone;

public class JDateToolkit {
  private static Calendar staticCal;
  public JDateToolkit() {
  }

  
  /**
   * 判断是否闰年
   * @param cyear     年
   * @return          是true，否false
   */
  public static boolean isLoopYear(int cyear) {
    return ( (cyear % 4 == 0) && (cyear % 100 != 0)) || (cyear % 400 == 0);
  }

  /**
   * 求得每月最大天数
   * @param cyear      年
   * @param cmonth     月
   * @return           最大天数
   */
  public static int maxDayOfMonth(int cyear, int cmonth) {
    int[] iDay = new int[] {
        31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    if (cmonth == 2 && isLoopYear(cyear)) {
      iDay[1] = 29;
    }
    return iDay[cmonth - 1];
  }

  private static String datePartToString(int value, char c0) {
    String s0 = String.valueOf(c0);
    if (value < 10) {
      s0 += "0";
    }
    return s0 + String.valueOf(value);
  }

  /**
   * 得到一个日期对象的字符串表示(yyyy-mm-dd hh:mm:ss).
   * 当时、分、秒都为0时，表示的时间字符串中只含年、月、日形式(yyyy-mm-dd)
   * @param  date       日期对象
   * @param  bHaveHour  是否需要时分秒
   * @return            date的字符串表示
   */
  static public final String dateToString(java.util.Date date,
                                          boolean bHaveHour) {
    Calendar cal = getStaticCalendars(date); //(java.sql.Timestamp)value;
    int year = cal.get(Calendar.YEAR); //((java.sql.Timestamp)value).getYear() + 1900;
    int month = cal.get(Calendar.MONTH) + 1; //((java.sql.Timestamp)value).getMonth() + 1;
    int day = cal.get(Calendar.DAY_OF_MONTH); //((java.sql.Timestamp)value).getDate();
    String dateStr="" + year + (month < 10 ? "-0" : "-") + month +
          (day < 10 ? "-0" : "-") + day;
    if (!bHaveHour ||
        (cal.get(Calendar.HOUR_OF_DAY) == 0 && cal.get(Calendar.MINUTE) == 0 &&
         cal.get(Calendar.SECOND) == 0)) {
    }else{
      int hour=cal.get(Calendar.HOUR_OF_DAY);
      int minute=cal.get(Calendar.MINUTE);
      int second=cal.get(Calendar.SECOND);
      dateStr=dateStr+" "+(hour<10?"0":"")+hour+":"+(minute<10?"0":"")+minute+":"+(second<10?"0":"")+second;
    }
    return dateStr;
  }


  /**
   *  求两个日期之间相差的天数(日期相减)
   *   @param  dateEnd    结束日期
   *   @param  dateStart  开始日期
   *   @return dateEnd 与 dateStart 之间相差的天数
   */
  public static int diffDate(Calendar date1, Calendar date2) {
    return (int) ( (toLongTime(date1.get(Calendar.YEAR),
                               date1.get(Calendar.MONTH) + 1,
                               date1.get(Calendar.DATE))
                    -
                    toLongTime(date2.get(Calendar.YEAR),
                               date2.get(Calendar.MONTH) + 1,
                               date2.get(Calendar.DATE))
                    ) / (24 * 60 * 60 * 1000));
  }
  /**
   *  求两个日期之间相差的天数(日期相减)
   *   @param  dateEnd    结束日期
   *   @param  dateStart  开始日期
   *   @return dateEnd 与 dateStart 之间相差的天数
   */
  public static int diffDate(String date1, String date2) {

      Calendar calendar1=Calendar.getInstance();
      Calendar calendar2=Calendar.getInstance();
      String temp1[]=date1.split("-");
      String temp2[]=date2.split("-");
      calendar1.set(Integer.parseInt(temp1[0]),Integer.parseInt(temp1[1]),Integer.parseInt(temp1[2]));
      calendar2.set(Integer.parseInt(temp2[0]),Integer.parseInt(temp2[1]),Integer.parseInt(temp2[2]));
      return diffDate(calendar1,calendar2);
  }
  /**
   * 加若干天后的日期
   * @param date Date
   * @param day int
   * @return Date
   */
  public static java.util.Date getAddDayDate(java.util.Date date, int day) {
    return new java.util.Date(date.getTime() + (day * (24 * 60 * 60 * 1000)));
  }
  /**
   * 加若干月后的日期
   * @param date Date
   * @param cmonth int
   * @return Date
   */
  public static java.util.Date getAddMonthDate(java.util.Date date, int cmonth) {
    return null;
  }
  /**
   * 加若干年后的日期
   * @param date Date
   * @param year int
   * @return Date
   */
  public static java.util.Date getAddYearDate(java.util.Date date, int year) {
    return null;
  }

  /**
   * 日期-->>字符串
   * @param date     日期
   * @return         字符串
   */
  static public final String dateToString(java.util.Date date) {
    return dateToString(date, false);
  }

  public static long toLongTime(int year, int month, int day, int hour, int min,
                                int sec) {
    if (staticCal == null) {
      staticCal = new GregorianCalendar();
    }
    staticCal.clear();
    staticCal.set(Calendar.YEAR, year);
    staticCal.set(Calendar.MONTH, month - 1);
    staticCal.set(Calendar.DAY_OF_MONTH, day); // day-1??
    staticCal.set(Calendar.HOUR_OF_DAY, hour);
    staticCal.set(Calendar.MINUTE, min);
    staticCal.set(Calendar.SECOND, sec);
    return staticCal.getTime().getTime();
  }

  /**
   *  从给定的 year,mongth,day 得到时间的long值表示(a point in time that is
   *  <tt>time</tt> milliseconds after January 1, 1970 00:00:00 GMT).
   * @param  year  年
   * @param  month  月
   * @param  day   日
   * @return   给定的 year,mongth,day 得到时间的long值表示
   */
  public static long toLongTime(int year, int month, int day) {
    return toDate(year, month, day).getTime();
  }

  /**
   * 从年月日得到一个Date对象
   */
  public static java.util.Date toDate(int year, int month, int day) {
    if (staticCal == null) {
      staticCal = new GregorianCalendar();
    }
    staticCal.clear();
    staticCal.set(Calendar.YEAR, year);
    staticCal.set(Calendar.MONTH, month - 1);
    staticCal.set(Calendar.DAY_OF_MONTH, day); // day-1??
    return staticCal.getTime(); //.getTime();
  }
  
  /**
   * 将一个日期String对象转换为一个指定格式的日期对象(2006-06-23)
   */
  public static java.util.Date toDate(String date ) {
	  java.util.Date mydate = new Date();
	  SimpleDateFormat  bartdateformat = 
	        //new SimpleDateFormat("yyyy-MM-dd"); 
	  new SimpleDateFormat("yyyy-MM-dd"); 
    try{
      bartdateformat.setTimeZone(TimeZone.getDefault());    
	  mydate = bartdateformat.parse(date);  
    }
    catch (Exception ex){
    	 System.out.println(ex.getMessage()); 
    	
    }
      // System.out.println("转化后的格式日期××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××"+mydate);
	    return mydate; //.getTime();
  }
  

  /**
   * 日期差计算，一天位单位
   * @param dateEnd       结束日期
   * @param dateStart     开始日期
   * @return              天数
   */
  static public int diffDate(java.util.Date dateEnd, java.util.Date dateStart) {
    return (int) ( (dateEnd.getTime() - dateStart.getTime()) /
                  (24 * 60 * 60 * 1000));
  }

  /**
   * 从一个<code>java.util.Date</code>对象得到一个表示该日期的日
   *  @param  date <code>java.util.Date</code>对象,从中取日
   *  @return  日期 date 表示的日
   */
  public static int getDateDay(java.util.Date date) {
    if (date == null) {
      return 0;
    }
    return getStaticCalendars(date).get(Calendar.DATE);
  }
  /**
   * 返回输入日期格式的天如 30
   * @param date 字符串如2005-08-30的格式
   * @return
   */
  public static String getDateDay(String date) {
	  if ("".equals(date)) {
	      return "0";
	    }
	    String[] temp=date.split("-");
	    if(temp.length<2)return "日期格式有误";
	    return temp[2];
	  }
  public static int getDateYear(java.util.Date date) {
    if (date == null) {
      return 0;
    }
    return getStaticCalendars(date).get(Calendar.YEAR);
  }
  /**
   * 返回输入日期格式的年 如 2005
   * @param date 字符串如2005-08-30的格式
   * @return
   */
  public static String getDateYear(String date) {
	  if ("".equals(date)) {
	      return "0";
	    }
	    String[] temp=date.split("-");
	    if(temp.length<0)return "日期格式有误";
	    return temp[0];
	  }
  /**
   * 从一个<code>java.util.Date</code>对象得到一个表示该日期的月份
   *  @param  date <code>java.util.Date</code>对象,从中取月份
   *  @return  日期 date 表示的月份
   */
  public static int getDateMonth(java.util.Date date) {
    if (date == null) {
      return 0;
    }
    return getStaticCalendars(date).get(Calendar.MONTH) + 1;
  }
  /**
   * 返回输入日期格式的月份 如 08
   * @param date 字符串如2005-08-30的格式
   * @return
   */
  public static String getDateMonth(String  date) {
	    if ("".equals(date)) {
	      return "0";
	    }
	    String[] temp=date.split("-");
	    if(temp.length<1)return "日期格式有误";
	    return temp[1];
	  }
  /**
   * 获得某年某月的双休日
   */
  public static int getDefaultHolidays(int year, int month) {
    GregorianCalendar cal = new GregorianCalendar(year, month - 1, 1);
    int x = 0;
    for (int d = 0; ; d++) {
      int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
      if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY) {
        x |= (1 << d);
      }
      cal.add(Calendar.DAY_OF_YEAR, 1);
      if (cal.get(Calendar.MONTH) + 1 != month) {
        break;
      }
    }
    return x;
  }

  /**
   *  得到一个静态的 Calendar 临时对象
   *  @return  一个静态的 Calendar 临时对象
   */
  public static Calendar getStaticCalendars() {
    return getStaticCalendars(null);
  }

  /**
   *  得到一个静态的 给定日期和时间的 Calendar 临时对象,
   *  @param   time  给定Calendar 临时对象表示的日期和时间
   *  @return  一个静态的给定日期和时间(long time) Calendar 临时对象
   */
  public static Calendar getStaticCalendars(long time) {
    Calendar cal = getStaticCalendars(null);
    if (cal != null) {
      cal.setTime(new java.util.Date(time));
    }
    return cal;
  }

  public static Calendar getStaticCalendars(java.util.Date date) {
    if (staticCal == null) {
      staticCal = new GregorianCalendar();
    }
    if (date != null) {
      staticCal.setTime(date);
    }
    return staticCal;
    //utcCal = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
    //defaultCenturyStart = staticCal.get(Calendar.YEAR) - 80;
  }

  /**
   * 得到一个日期的年月日时分秒
   */
  public static int[] getDateElements(java.util.Date date) {
    if (date == null) {
      return null;
    }
    Calendar cal = getStaticCalendars(date);
    int ymd[] = new int[7];
    ymd[0] = cal.get(Calendar.YEAR);
    ymd[1] = cal.get(Calendar.MONTH) + 1;
    ymd[2] = cal.get(Calendar.DATE);
    ymd[3] = cal.get(Calendar.HOUR_OF_DAY);
    ymd[4] = cal.get(Calendar.MINUTE);
    ymd[5] = cal.get(Calendar.SECOND);
    return ymd;
  }

  /**
   * 获得当前年
   * @return
   */
  public static String getNowYear() {
    Calendar calendar = Calendar.getInstance();
    String year = Integer.toString(calendar.get(Calendar.YEAR));
    if (year.length() == 1) {
      year = "0" + year;
    }
    return year;
  }
  
  /**
   * 获得前一年
   * @return
   */
  public static String getPastYear() {
    Calendar calendar = Calendar.getInstance();
    String year = Integer.toString(calendar.get(Calendar.YEAR)-1);
    if (year.length() == 1) {
      year = "0" + year;
    }
    return year;
  }
  
  /**
   * 获得给定日期的年(不处理长度）
   * @param date  格式：2009-09-09
   * @return
   */
  public static String getYear(String date) { 
	  String year = "";
		try{
			year = date.substring(0, 4);
		}catch(Exception e) {
			System.out.println("日期格式不正确！");
			e.printStackTrace();
		}
		return year;
  }
  /**
   * 获得当前月
   * @return
   */
  public static String getNowMonth() {
    Calendar calendar = Calendar.getInstance();
    String month = Integer.toString( (calendar.get(Calendar.MONTH) + 1));
    if (month.length() == 1) {
      month = "0" + month;
    }
    return month;
  }
  /**
   * 获得当前月(不处理长度）
   * @return
   */
  public static String getNowMonth1() {
    Calendar calendar = Calendar.getInstance();
    String month = Integer.toString( (calendar.get(Calendar.MONTH) + 1));

    return month;
  }
  /**
   * 获得给定日期的月份(不处理长度）
   * @param date  格式：2009-09-09
   * @return
   */
  public static String getMonth(String date) { 
	  String month = "";
		try{
			System.out.println(date);
			month = date.substring(5, 7);
			System.out.println(month);
			if(month != null&& month.charAt(0) == '0') {
				month = month.substring(1);
			}
		}catch(Exception e) {
			System.out.println("日期格式不正确！");
			e.printStackTrace();
		}
		return month;
  }
  /**
   * 获得前一个月(不处理长度）
   * @return
   */
  public static String getPastMonth1() {
    Calendar calendar = Calendar.getInstance();
    calendar.add(calendar.MONTH, -1);
    String month = Integer.toString( (calendar.get(Calendar.MONTH) + 1));

    return month;
  }
  /**
   * 获得当前年
   * @return
   */
  public static String getYearOfPastMonth() {
    Calendar calendar = Calendar.getInstance();
    calendar.add(calendar.MONTH, -1);
    String year = Integer.toString(calendar.get(Calendar.YEAR));
    if (year.length() == 1) {
      year = "0" + year;
    }
    return year;
  }
  /**
   * 获得当前日
   * @return
   */
  public static String getNowDay() {
    Calendar calendar = Calendar.getInstance();
    String day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
    if (day.length() == 1) {
      day = "0" + day;
    }
    return day;
  }
  /**
   * 获得当前日(不处理长度）
   * @return
   */
  public static String getNowDay1() {
      Calendar calendar = Calendar.getInstance();
      String day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
      return day;
    }
  /**
   * 得到当前日期的字符串，包含时：分
   * @return
   */
  public static String getNowDate1() {
    Calendar calendar = Calendar.getInstance();
    String NowYear = Integer.toString(calendar.get(Calendar.YEAR));
    String NowMonth = Integer.toString( (calendar.get(Calendar.MONTH) + 1));
    String NowDay = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
    String NowHour = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
    String NowMinute = Integer.toString(calendar.get(Calendar.MINUTE));
    String NowDate = NowYear + "-" +
        (NowMonth.length() == 1 ? "0" + NowMonth : NowMonth) + "-" +
        (NowDay.length() == 1 ? "0" + NowDay : NowDay) + " " +
        (NowHour.length() == 1 ? "0" + NowHour : NowHour) + ":" +
        (NowMinute.length() == 1 ? "0" + NowMinute : NowMinute);
    return NowDate;
  }

  /**
   * 得到当前日期的字符串，包含时：分
   * @return
   */
  public static String getNowDate3() {
    Calendar calendar = Calendar.getInstance();
    String NowYear = Integer.toString(calendar.get(Calendar.YEAR));
    String NowMonth = Integer.toString( (calendar.get(Calendar.MONTH) + 1));
    String NowDay = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
    String NowHour = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
    String NowMinute = Integer.toString(calendar.get(Calendar.MINUTE));
    String NowSecond = Integer.toString(calendar.get(Calendar.SECOND));
    String NowDate = NowYear + "_" +
        (NowMonth.length() == 1 ? "0" + NowMonth : NowMonth) + "_" +
        (NowDay.length() == 1 ? "0" + NowDay : NowDay) + "_" +
        (NowHour.length() == 1 ? "0" + NowHour : NowHour) + "_" +
        (NowMinute.length() == 1 ? "0" + NowMinute : NowMinute) + "_" +
        (NowSecond.length() == 1 ? "0" + NowSecond : NowSecond);
    return NowDate;
  }

  public static String getNowDate4() {
    Calendar calendar = Calendar.getInstance();
    String NowYear = Integer.toString(calendar.get(Calendar.YEAR));
    String NowMonth = Integer.toString( (calendar.get(Calendar.MONTH) + 1));
    String NowDay = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
    String NowHour = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
    String NowMinute = Integer.toString(calendar.get(Calendar.MINUTE));
    String NowSecond = Integer.toString(calendar.get(Calendar.SECOND));
    String NowDate = NowYear + "-" +
        (NowMonth.length() == 1 ? "0" + NowMonth : NowMonth) + "-" +
        (NowDay.length() == 1 ? "0" + NowDay : NowDay) + " " +
        (NowHour.length() == 1 ? "0" + NowHour : NowHour) + ":" +
        (NowMinute.length() == 1 ? "0" + NowMinute : NowMinute) + ":" +
        (NowSecond.length() == 1 ? "0" + NowSecond : NowSecond);
    return NowDate;
  }

  public static String getTime() {
    Calendar calendar = Calendar.getInstance();
    String NowHour = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
    String NowMinute = Integer.toString(calendar.get(Calendar.MINUTE));
    String NowSecond = Integer.toString(calendar.get(Calendar.SECOND));
    String NowDate =
        (NowHour.length() == 1 ? "0" + NowHour : NowHour) + ":" +
        (NowMinute.length() == 1 ? "0" + NowMinute : NowMinute);
    return NowDate;
  }

  /**
   * 得到当前日期的字符串，不包含时：分,如：2010-09-18
   * @return
   */
  public static String getNowDate2() {
    Calendar calendar = Calendar.getInstance();
    String NowYear = Integer.toString(calendar.get(Calendar.YEAR));
    String NowMonth = Integer.toString( (calendar.get(Calendar.MONTH) + 1));
    String NowDay = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
    String NowDate = NowYear + "-" +
        (NowMonth.length() == 1 ? "0" + NowMonth : NowMonth) + "-" +
        (NowDay.length() == 1 ? "0" + NowDay : NowDay);
    return NowDate;
  }
  /**
   * 得到当前日期的字符串，不包含时：分,返回值如：20100918
   * @return 
   */
  public static String getNowDate5(){
	  String nowDate = getNowDate2();
	  String[] ary = nowDate.split("-");
	  String str = "";
	  for(String s : ary){
		  str = str + s;
	  }
	  return str;
  }
  
  public static String getDate3(String date) {
    java.util.StringTokenizer strIt = new StringTokenizer(date, "-");
    String retDate = "";
    int i = 0;
    while (strIt.hasMoreElements()) {
      if (i == 0) {
        retDate = retDate + strIt.nextElement().toString() + "年";
      }
      if (i == 1) {
        retDate = retDate + strIt.nextElement().toString() + "月";
      }
      if (i == 2) {
        retDate = retDate + strIt.nextElement().toString() + "日";
      }
      i++;
    }
    return retDate;
  }
  /**
   * 日期格式化：2007-03-06 转换为  2007 年 3 月 6 日
   * @param date
   * @return
   */
  public static String getDate4(String date) {
	    java.util.StringTokenizer strIt = new StringTokenizer(date, "-");
	    String retDate = "";
	    int i = 0;
	    while (strIt.hasMoreElements()) {
	      if (i == 0) {
	        retDate = retDate + strIt.nextElement().toString() + "年";
	      }
	      if (i == 1) {
	        retDate = retDate + Integer.parseInt(strIt.nextElement().toString()) + "月";
	      }
	      if (i == 2) {
	        retDate = retDate + Integer.parseInt(strIt.nextElement().toString()) + "日";
	      }
	      i++;
	    }
	    return retDate;
	  }
  /**
   * 日期格式化：2007-03-06 10:10 转换为  2007 年 3 月 6 日 10时 10分
   * @param date 2007-03-06 10:10:00
   * @return
   */
	public static String getDate5(String date) {
	  	String retDate = "";
	  	if (date.length() > 10) {
			String dates[] = date.split(" ");
			StringTokenizer strIt = new StringTokenizer(dates[0], "-");
			int i = 0;
		    while (strIt.hasMoreElements()) {
		      if (i == 0) {
		        retDate = retDate + strIt.nextElement().toString() + "年";
		      }
		      if (i == 1) {
		        retDate = retDate + Integer.parseInt(strIt.nextElement().toString()) + "月";
		      }
		      if (i >= 2) {
		        retDate = retDate + Integer.parseInt(strIt.nextElement().toString()) + "日";
		      }
		      i++;
		    }
		    StringTokenizer strIt1 = new StringTokenizer(dates[1], ":");
		    int j = 0;
		    while (strIt1.hasMoreElements()) {
		      if (j == 0) {
		        retDate = retDate + strIt1.nextElement().toString() + "时";
		      }
		      if (j == 1) {
		        retDate = retDate + Integer.parseInt(strIt1.nextElement().toString()) + "分";
		      }
		      if (j >= 2) {
		    	 strIt1.nextElement();
		      }
		      j++;
		    }
		}
	    return retDate;
	}
  
  //日期转换为中文（简体）
  //开始
	private static String getChinese(String digital) {
		switch (Integer.parseInt(digital)) {
		case 0:
			return "〇";
		case 1:
			return "一";
		case 2:
			return "二";
		case 3:
			return "三";
		case 4:
			return "四";
		case 5:
			return "五";
		case 6:
			return "六";
		case 7:
			return "七";
		case 8:
			return "八";
		case 9:
			return "九";
		case 10:
			return "十";
		}
		return "〇";
	}

	private static String getYearChinese(String digital) {
		JDateToolkit d = new JDateToolkit();
		String retDate = "";
		for (int i=0;i<digital.length();i++){
			retDate = retDate + d.getChinese(String.valueOf(digital.charAt(i)));
		}
		return retDate;
	}

	private static String getMonthDayChinese(String digital) {
		JDateToolkit d = new JDateToolkit();
		String retDate = "";
		if (digital.length()==1){
			retDate = retDate + d.getChinese(String.valueOf(digital.charAt(0)));
		}else{
			if(String.valueOf(digital.charAt(0)).equals("0")){
				retDate = retDate + d.getChinese(String.valueOf(digital.charAt(1)));
			}else
			if(String.valueOf(digital.charAt(0)).equals("1")){
				retDate = retDate + d.getChinese("10");
				if (String.valueOf(digital.charAt(1)).equals("0")){}else{
					retDate = retDate + d.getChinese(String.valueOf(digital.charAt(1)));
				}
			}else{
				retDate = retDate + d.getChinese(String.valueOf(digital.charAt(0)));
				retDate = retDate + d.getChinese("10");
				if (String.valueOf(digital.charAt(1)).equals("0")){}else{
					retDate = retDate + d.getChinese(String.valueOf(digital.charAt(1)));
				}
			}
		}
		return retDate;
	}

	public static String dateToCH(String date) {
		JDateToolkit d = new JDateToolkit();
		StringTokenizer strIt = new StringTokenizer(date, "-");
		String retDate = "";
		int i = 0;
		while (strIt.hasMoreElements()) {
			if (i == 0) {
				retDate = retDate + d.getYearChinese(strIt.nextElement().toString()) + "年";
			}
			if (i == 1) {
				retDate = retDate + d.getMonthDayChinese(strIt.nextElement().toString()) + "月";
			}
			if (i == 2) {
				retDate = retDate + d.getMonthDayChinese(strIt.nextElement().toString()) + "日";
			}
			i++;
		}
		return retDate;
	}
  //结束

  public static String getDateChinese(String date) {
    java.util.StringTokenizer strIt = new StringTokenizer(date, "-");
    String retDate = "";
    int i = 0;
    while (strIt.hasMoreElements()) {
      if (i == 0) {
        retDate = retDate +
            JUtilities.getNumChiness(strIt.nextElement().toString()) + "年";
      }
      if (i == 1) {
        retDate = retDate +
            JUtilities.getNumChinesTwo(strIt.nextElement().toString()) + "月";
      }
      if (i == 2) {
        retDate = retDate +
            JUtilities.getNumChinesTwo(strIt.nextElement().toString()) + "日";
      }
      i++;
    }
    return retDate;
  }

  public static String getDateChinese1(String date) {
    String retDate = "";
    String year = date.substring(0, date.indexOf("年"));
    String month = date.substring(date.indexOf("年") + 1, date.indexOf("月"));
    String day = date.substring(date.indexOf("月") + 1, date.indexOf("日"));
    retDate = retDate + JUtilities.getNumChiness(year) + "年";
    retDate = retDate + JUtilities.getNumChinesTwo(month) + "月";
    retDate = retDate + JUtilities.getNumChinesTwo(day) + "日";
    return retDate;
  }

   
  /**
     * 得到与当前日期相差day天的日期，不包含时：分
     * @return
     */
    public static String getBeforeAfterDate(int day) {
      Calendar calendar = Calendar.getInstance();
      calendar.add(calendar.DATE,day);
      String NowYear = Integer.toString(calendar.get(Calendar.YEAR));
      String NowMonth = Integer.toString( (calendar.get(Calendar.MONTH) + 1));
      String NowDay = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
      String NowDate = NowYear + "-" + getFullStr(NowMonth) + "-" + getFullStr(NowDay);
      return NowDate;
    }
    /**
     * 得到与当前日期相差month月的日期，不包含时：分
     * @return
     */
    public static String getBeforeAfterMonth(int month) {
      Calendar calendar = Calendar.getInstance();
      calendar.add(calendar.MONTH,month);
      String NowYear = Integer.toString(calendar.get(Calendar.YEAR));
      String NowMonth = Integer.toString( (calendar.get(Calendar.MONTH) + 1));
      String NowDay = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
      String NowDate = NowYear + "-" + getFullStr(NowMonth) + "-" + getFullStr(NowDay);
      return NowDate;
    }
    /**
     * 获得下一天；
     * @param date
     * @return
     */
    public static String getNextDate(String date)
    {
    	String[] str = date.split("-");

    	String	year = str[0];
    	String	month = str[1];
    	String	day = str[2];

    	java.util.Calendar calendar = new java.util.GregorianCalendar(Integer.parseInt(year),Integer.parseInt(month)-1,Integer.parseInt(day));

        calendar.add(Calendar.DATE,+1) ;
        int yearpre_d = calendar.get(Calendar.YEAR);
        int monthpre_d = calendar.get(Calendar.MONTH)+1;
        int daypre_d = calendar.get(Calendar.DATE);
        month=monthpre_d+"";
        day  =daypre_d+"";
        if(monthpre_d<10)
    		month = "0" + monthpre_d;

    	if(daypre_d<10)
    		day = "0" + daypre_d;
    	return yearpre_d + "-" + month + "-" +day;

    }

    /**
     * 得到与当前日期相差year的日期，不包含时：分
     * @return
     */
    public static String getBeforeAfterYear(int year) {
      Calendar calendar = Calendar.getInstance();
      calendar.add(calendar.YEAR,year);
      String NowYear = Integer.toString(calendar.get(Calendar.YEAR));
      String NowMonth = Integer.toString( (calendar.get(Calendar.MONTH) + 1));
      String NowDay = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
      String NowDate = NowYear + "-" + getFullStr(NowMonth) + "-" + getFullStr(NowDay);
      return NowDate;
    }
    /**
   *如果日期和月小于两位数,前面加"0".
   */
  public static String getFullStr(String str){
     if(Integer.parseInt(str)<10)
       return (new StringBuffer("0").append(str).toString());
     else return (new StringBuffer("").append(str).toString());
  }


  /**
   * 取得传入年月日的星期
   * @param year String年
   * @param month String月
   * @param day String日
   * @return String 返回中文字符串
   */
  public static String getDateWeek(String year,String month,String day)
{
        String week = "";
        java.util.Calendar calendar = new java.util.GregorianCalendar(Integer.parseInt(year),Integer.parseInt(month)-1,Integer.parseInt(day));
        int dWeek = calendar.get(Calendar.DAY_OF_WEEK);

        switch(dWeek)
        {
                case 1:
                week="星期日";
                 break;

                case 2:
                week="星期一";
                 break;

                case 3:
                week="星期二";
                 break;

                case 4:
                week="星期三";
                 break;

        case 5:
                week="星期四";
                 break;

                case 6:
                week="星期五";
                 break;

                case 7:
                week="星期六";
                 break;

        }
        return week;
  }
    /**
   * 取得传入年月日这一天的的星期 返回整型值
   * @param year String 年
   * @param month String月
   * @param day String日
   * @return int  星期的整型值(1,2,3,4,5,6,7)
   */
  public static int getIntDateWeek(String year,String month,String day)
{
       java.util.Calendar calendar = new java.util.GregorianCalendar(Integer.parseInt(year),Integer.parseInt(month)-1,Integer.parseInt(day));
       return calendar.get(Calendar.DAY_OF_WEEK);
 }
 /**
  * 得到两个日期之间相差的时间(时分)
  * @param dateEnd String 结束日期
  * @param dateStart String 开始日期 (2006-2-12 09:12:34)
  * @return String X小时Y分
  */
 public static String diffTime(String dateEnd,String dateStart){
   String restr="";
   String[] endArr=JStringToolkit.splitString(dateEnd," ");
   String[] startArr=JStringToolkit.splitString(dateStart," ");
   if(endArr!=null&&startArr!=null&&endArr.length==2){
     int diffDate=diffDate(endArr[0].trim(),startArr[0].trim());
     String[] endTime=JStringToolkit.splitString(endArr[1].trim(),":");
     String[] startTime=JStringToolkit.splitString(startArr[1],":");
     if(endTime!=null&&startTime!=null&&endTime.length==3){
       int diffTime=diffDate*24*3600+Integer.parseInt(endTime[0])*3600+Integer.parseInt(endTime[1])*60+Integer.parseInt(endTime[2])-Integer.parseInt(startTime[0])*3600-Integer.parseInt(startTime[1])*60-Integer.parseInt(startTime[2]);
       int hour=diffTime/3600;
       //System.out.println("hour=="+hour);
       //int day=hour/24;
       //System.out.println("day=="+day);
       //hour=hour%24;
       //System.out.println("hour=="+hour);
       int minute=(diffTime%3600)/60;
       //int miao=diffTime%3600%60;
       restr=(hour==0?"":hour+"小时")+(minute==0?"":minute+"分");
     }
   }
   return restr;
 }
 	

 public static String getOnlineTime(String dateEnd,String dateStart){
   String restr="";
   String[] endArr=JStringToolkit.splitString(dateEnd," ");
   String[] startArr=JStringToolkit.splitString(dateStart," ");
   if(endArr!=null&&startArr!=null&&endArr.length==2){
     int diffDate=diffDate(endArr[0].trim(),startArr[0].trim());
     String[] endTime=JStringToolkit.splitString(endArr[1].trim(),":");
     String[] startTime=JStringToolkit.splitString(startArr[1],":");
     if(endTime!=null&&startTime!=null&&endTime.length==3){
       int diffTime = diffDate * 24 * 3600
						+ Integer.parseInt(endTime[0]) * 3600
						+ Integer.parseInt(endTime[1]) * 60
						+ Integer.parseInt(endTime[2])
						- Integer.parseInt(startTime[0]) * 3600
						- Integer.parseInt(startTime[1]) * 60
						- Integer.parseInt(startTime[2]);
				int hour = diffTime / 3600;
				int minute = (diffTime % 3600) / 60;
				restr = minute + "";//(hour==0?"":hour+"小时")+(minute==0?"":minute+"分");
     }
   }
   return restr;
 }
 
 public static int diffHour(String dateEnd, String dateStart)
 {
	 int hour = 0;
	 String[] endArr=JStringToolkit.splitString(dateEnd," ");
	 String[] startArr=JStringToolkit.splitString(dateStart," ");
	 if(endArr!=null&&startArr!=null&&endArr.length==2){
	   int diffDate=diffDate(endArr[0].trim(),startArr[0].trim());
	   String[] endTime=JStringToolkit.splitString(endArr[1].trim(),":");
	   String[] startTime=JStringToolkit.splitString(startArr[1],":");
	   if(endTime!=null&&endTime!=null&&endTime.length==3){
	     int diffTime=diffDate*24*3600+Integer.parseInt(endTime[0])*3600+Integer.parseInt(endTime[1])*60+Integer.parseInt(endTime[2])-Integer.parseInt(startTime[0])*3600-Integer.parseInt(startTime[1])*60-Integer.parseInt(startTime[2]);
	     hour=diffTime/3600;
	   }
	 }
	 return hour;
 }

 public static String getAddDate(String date,String day){

   if(day!=null&&!day.trim().equals("")&&!day.equals("null")){
      String[] arr=JStringToolkit.splitString(date," ");
     if(arr!=null&&arr.length==2){
       if(!day.equals("0")){
         long day_int=(long)(Double.parseDouble(day)*60*60*1000);
         String[] dateArr = JStringToolkit.splitString(arr[0], "-");
         String[] timeArr = JStringToolkit.splitString(arr[1], ":");
         long dateTime = 0l;
         if (dateArr != null && dateArr.length == 3) {
           if (timeArr != null && timeArr.length == 3) {
             dateTime = toLongTime(Integer.parseInt(dateArr[0].trim()),
                                   Integer.parseInt(dateArr[1].trim()),
                                   Integer.parseInt(dateArr[2]),
                                   Integer.parseInt(timeArr[0]),
                                   Integer.parseInt(timeArr[1]),
                                   Integer.parseInt(timeArr[2]));
           }
           else {
             dateTime = toLongTime(Integer.parseInt(dateArr[0].trim()),
                                   Integer.parseInt(dateArr[1].trim()),
                                   Integer.parseInt(dateArr[2]), 0, 0, 0);
           }
           date = dateToString(new java.util.Date(dateTime +
               day_int), true);
         }
       }else{
         date=arr[0]+" "+"23:59:59";
       }
     }
   }
   return date;
 }
 
  
 /**
	 * 返回指定日期所在月份的旬
	 * add by wolf
	 * @param day
	 * @return 0 错误；1 上旬；2 中旬；3 下旬
	 */
	public static int compDay(String day){
		try{
			int iday = Integer.parseInt(day);
			if (iday >=0 && iday <=10)
				return 1;
			else if (iday >10 && iday <=20)
				return 2;
			else if (iday >20 && iday <=31)
				return 3;
			else
				return 0;
		} catch (Exception e){
			return 0;
		}
	}

	/**
 	 * 根据年份和周次得到这周的第一天
 	 * @param year	年份
 	 * @param week	周次
 	 * @param flag	如果为0，返回的第一天是周日；如果为1，则是周一
 	 * @return
 	 */
 	public static String getFirstOfWeek(int year, int week,int flag){
 		Calendar c = new GregorianCalendar();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.WEEK_OF_YEAR, week);

		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()+flag);
		//System.out.println(c.getTime());
 		return toDate(c.getTime());
 	}
 	
 	/**
 	   * 返回"yyyy-MM-dd"格式的字符串
 	   * @param myDate 日期型参数
 	   * @return  
 	   */

 	  public static String toDate(Date myDate) {
 	    if (myDate == null) {
 	      return "";
 	    }
 	    else {
 	      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
 	      String dateStr = formatter.format(myDate);
 	      return dateStr;
 	    }
 	  }
	
 
 public static void main(String[] args){
	 //System.out.print(toDate(2006,12,23).getDate()); 
//   String date1="2006-10-20 09:33:36";
//   String date2="1.5";
//   System.out.println(getAddDate(date1,date2));
	 System.out.println(getYear("2009-10-01"));
 }

}
