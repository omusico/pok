package com.onmyway.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

/**
 * @Title:获取服务器的时间，由原来的dwr改成servlet
 * @Description:
 * @Create on: 2013年11月30日 下午7:47:15
 * @Author:ljy
 * @Version:1.0
 */
public class ServerTimeServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        
        //根据相应的参数，返回不同的时间对象 
        String method = req.getParameter("method");
    
        if(method == null || method.equals("")){
            method = "long";
        }
        String msg = "";
        //返回值为数组
        if(method.equals("ary")){
           msg = (String)JSON.toJSONString(ServerTime.getServerTime());
        }
        //返回值为  long型数值  System.currentTimeMillis();
        if(method.equals("long")){
            msg = (String)JSON.toJSONString(ServerTime.getServerMillisTime());
        }
        //System.out.println("服务器时间校准:method=" + method + ",serverTime=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(new Long(msg))));
        PrintWriter pw = res.getWriter();

        pw.print(msg);
        pw.flush();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        doPost(req, res);
    }

}
