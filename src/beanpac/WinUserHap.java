package beanpac;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dbconnpac.DataBaseConn;

public class WinUserHap{
	public boolean hasWinNum(String strWinNum, String strWager){
		 Pattern p=Pattern.compile(strWinNum);//spade为正则，即要匹配的字串如：“3”
		 Matcher m=p.matcher(strWager);//从字串"3,4,7"中找
		 boolean hasWin=m.find();
	     return hasWin;
	 }
	 
	 
	 //直选
	 public Vector getVecComWin(String[] arrFig,String wholeStr){
		 Vector vecWinStr=new Vector();	 
	     String[] arrDolSegStr=wholeStr.split("\\$");
	     for(int j=0;j<arrDolSegStr.length;j++){
	    	 boolean hasWin=false;
	    	 hasWin=hasComMulWin(arrFig,arrDolSegStr[j]);
	    	 if(hasWin==true){
	    		 FigWinInfo beanFigWinInfo=new FigWinInfo();
	    		 beanFigWinInfo.setWinWagerNum("1");
	    		 beanFigWinInfo.setStrOneWag(arrDolSegStr[j]);//得到一个$片段（如：3,4|3,7|3|9,K）的中奖值
	    		 vecWinStr.addElement(beanFigWinInfo);//数据库中一条投注字串中的几次中奖
	    	 }
	     }
	     return vecWinStr;
	 } 
	 public boolean hasComMulWin(String[] arrWinNum,String dolSegStr){
		 boolean hasWin=true;
		 String[] arrVerLineSegStr=dolSegStr.split("\\|");//按|截取，开始判断
		 for (int i=0;i<arrVerLineSegStr.length;i++){
	         if(hasWinNum(arrWinNum[i],arrVerLineSegStr[i])==false){
	        	 hasWin=false;
	         }
		 }
		 return hasWin;
	 }
	
}
