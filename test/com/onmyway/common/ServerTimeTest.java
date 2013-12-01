package com.onmyway.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.TestCase;

import org.apache.commons.lang.time.DateUtils;

/**
 * @Title:
 * @Description: 
 * @Create on: 2013年11月29日 下午10:41:56
 * @Author:ljy
 * @Version:1.0
 */
public class ServerTimeTest extends TestCase{

    public void testGetServerTime(){
        ServerTime st = new ServerTime();
        int[] t = st.getServerTime();
        System.out.println("year="+t[0] + "," + t[1] + "," + t[2] + "," + t[3] + "," + t[4] + "," + t[5]);
        
        Date date = new Date(1385806179000L);
        
        System.out.println(System.currentTimeMillis());
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
    }
    public void testDate(){
        DateUtils du = new DateUtils();
        long ms = 1385800822954L;
        
    }
}
