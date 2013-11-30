package com.onmyway.time;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import junit.framework.TestCase;

/**
 * @Title:
 * @Description: 
 * @Create on: 2013年11月29日 下午10:24:59
 * @Author:ljy
 * @Version:1.0
 */

public class NetTimeTest extends TestCase {
 
    public void testTime(){
        Date date = NetTime.getNowTime();
        System.out.println(date.getYear()+"," + date.getMonth() + "," + date.getDay()+ "," + date.getHours()+ "," + date.getMinutes()+ "," + date.getSeconds());
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
    }

}
