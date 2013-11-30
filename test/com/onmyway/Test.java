package com.onmyway;

import java.util.Calendar;
import java.util.Date;

import util.JStringToolkit;

/**
 * @Title:
 * @Description: 
 * @Create on: Aug 12, 2010 11:05:56 AM
 * @Author:LJY
 * @Version:1.0
 */
public class Test {
	public Date plusDate(Date date,int days){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(cal.DAY_OF_MONTH, days);

		return cal.getTime();
	}
	public void testFor(){
		boolean flag = true;
		for(int i = 0; i < 5; i++){
			System.out.println("第-1-层:" + i);
			for(int j = 0; j < 10; j++){
				System.out.println("第------2-----层:" + j);
				if(j == 3){
					flag = false;
					break;
				}
			}
			if(!flag){
				break;
			}
		}
	}
	public static int getSubNum(String a,String splitSymbol,String b,String exceptStr){
  	  int num=0;
  	  String[] ary = JStringToolkit.splitString(a,splitSymbol);
  	  for(String s : ary){
  		  if(s.indexOf(exceptStr) != -1){
  			  continue;
  		  }else if(s.indexOf(b) != -1){
  			  num ++;    			  
  		  }
  	  }
  	  return num;
  }

	public  static void main(String[] arg){
//		Set set = new HashSet();
//		set.add("1");
//		set.add("2");
//		set.add("1");
//		set.add("4");
//		set.add("2");
//		set.add("3");
//		
//		Iterator it = set.iterator();
//		while(it.hasNext()){
//			System.out.println("--"+it.next());
//		}
		String a = ",32165,32156,";
		String splitSymbol = ",";
		String b = "321";
		String exceptStr  = "32165";
		Test t = new Test();
		int aaa = t.getSubNum(a,splitSymbol,b,exceptStr);
		System.out.println("aaa="+aaa);
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		//	DateTool dt = new DateTool();
//			Date date = new Date();
//			Date d2 = t.plusDate(date, 5);
//			System.out.println("de=" + sdf.format(d2));
	}
}
