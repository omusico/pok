package com.onmyway.time;

import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Title:
 * @Description:
 * @Create on: 2013年11月29日 下午10:15:39
 * @Author:ljy
 * @Version:1.0
 */
public class NetTime {
    public static Date getNowTime() {
        // 取得资源对象
        long time = 0;
        try {
            URL url = new URL("http://www.bjtime.cn");
            // 生成连接对象
            URLConnection uc = url.openConnection();
            // 发出连接
            uc.connect();
            time = uc.getDate();
        } catch (Exception e) {
            e.printStackTrace();
            time = new Date().getTime();

        }
        System.out.println("long time:" + time);
        Date date = new Date(time);
        System.out.println("date:" + date.toString());
        System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                .format(date));
       
        return date;
    }
}
