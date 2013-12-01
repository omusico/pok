package com.onmyway.time;

import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * @Title:
 * @Description:
 * @Create on: 2013年11月29日 下午10:15:39
 * @Author:ljy
 * @Version:1.0
 */
public class NetTime {
    private static final Logger log = Logger.getLogger(NetTime.class);
    public static Date getNowTime() {
        // 取得资源对象
        long time = 0;
        try {
//            URL url = new URL("http://www.bjtime.cn");
//            // 生成连接对象
//            URLConnection uc = url.openConnection();
//            // 发出连接
//            uc.connect();
//            time = uc.getDate();
            
            time = new Date().getTime();
        } catch (Exception e) {
            e.printStackTrace();
            time = new Date().getTime();

        }
//       log.info("long time:" + time);
        Date date = new Date(time);
//        log.info("date:" + date.toString());
//        log.info(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date));
//       
        return date;
    }
}
