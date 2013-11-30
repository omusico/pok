package com.onmyway.chankao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Title:
 * @Description: 
 * @Create on: Aug 10, 2010 9:51:27 PM
 * @Author:LJY
 * @Version:1.0
 */
public class Weave {
	private BufferedReader in;
	 private InputStreamReader is;
	 private char[] chars;
	 private int charLen;
	 int min;
	 int max;
	 int count = 0;
	 
	 public Weave(){
	  open();
	  String str = getStrInput("请输入需要组合的字符穿:");
	  chars = str.toCharArray();
	  charLen = chars.length;
	  min = getIntInput("请输入需要组合的最小长度:");
	  max = getIntInput("请输入需要组合的最大长度:");
	  close();
	 }
	 
	 public static void main(String[] args)   {
	  
	  Weave weave = new Weave();
	  long begin=System.currentTimeMillis();
	  weave.combination();
	  weave.loger("执行时间:"+(System.currentTimeMillis() - begin)+"毫秒");
	   } 
	 
	 public void combination(){
	  count = 0;
	  if (min>max){
	      int t = max;
	      max = min;
	      min = t;
	     }
	     for (int i= min;i<=max;i++){
	      combination(new StringBuffer(""),i);
	  }
	 }
	 
	 
	 public void combination(StringBuffer  str,int length) {
	  
	  if (length < 1){
	   loger("长度小于1");
	  }

	  if (length == 1){
	   for(int i=0;i<charLen;i++){
	    StringBuffer result = new StringBuffer(str);
	    result.append(chars[i]);
	    //根据组合的结果执行某些操作
	                doSome(result);
	   }
	  }

	  if (length > 1) {
	   for(int i=0;i<charLen;i++){
	    StringBuffer temp = new StringBuffer(str);
	    combination(temp.append(chars[i]),length-1);
	   }
	  }
	 }
	 
	 public void doSome(StringBuffer str){
	  count++;
	  loger(count + ".  " +str);
	 }
	 
	 public int getIntInput(String info) {
	        int temp=0;
	        loger("\n"+info);
	        try {
	            temp=Integer.parseInt(in.readLine());
	        }
	        catch(Exception e) {
	         loger("错误!!请从新输入");
	            return getIntInput(info);
	        }
	        if(temp<=0) {
	         loger("\n错误!!必须大于0!");
	            return getIntInput("从新输入 : ");
	        }
	        
	        return temp;        
	    } 
	 
	 public String getStrInput(String info) {
	        loger("\n"+info);
	        String temp = "";
	        try {
	   temp = in.readLine();
	  } catch (IOException e) {
	   loger(e);
	  }
	        if(null == temp || "".equals(temp)) {
	         loger("\n错误!!不能输入空字符串!");
	            return getStrInput("从新输入 : ");
	        }
	        return temp;        
	    } 
	 
	 /**
	  * 反馈信息
	  * @param o
	  */
	 public void loger(Object o){
	  System.out.println(o.toString());
	 }
	 
	 public void close(){
	  try {
	   is.close();
	   in.close();
	  } catch (IOException e) {
	   loger(e);
	  }
	  
	 }
	 
	 public void open(){
	  is = new InputStreamReader(System.in);
	  in=new BufferedReader(is);
	 }


}
