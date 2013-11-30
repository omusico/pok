package com.onmyway.common;

import junit.framework.TestCase;

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
    }
}
