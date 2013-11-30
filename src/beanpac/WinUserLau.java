package beanpac;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dbconnpac.DataBaseConn;

//此类包括大气排列三，福彩，时时乐算法
public class WinUserLau{
	
	public boolean hasWinNum(String strWinNum, String strWager){
		 Pattern p=Pattern.compile(strWinNum);//spade为正则，即要匹配的字串如：“3”
		 Matcher m=p.matcher(strWager);//从字串"3,4,7"中找
		 boolean hasWin=m.find();
	     return hasWin;
	 }
	 
	 
	 //前一后一
	 public Vector getVecFrOnWin(String[] arrFig,String wholeStr,String playMode){
		 Vector vecWinStr=new Vector();	 
	     String[] arrDolSegStr=wholeStr.split("\\$");
	     for(int j=0;j<arrDolSegStr.length;j++){
	    	 boolean hasWin=false;
	    	 if(playMode.equals("前一")){
	    		 hasWin=hasFrOnFronWin(arrFig,arrDolSegStr[j]);
	    	 }
	    	 if(playMode.equals("后一")){
	    		 hasWin=hasFrOnBaOnWin(arrFig,arrDolSegStr[j]);
	    	 }
	    	 
	    	 if(hasWin==true){
	    		 FigWinInfo beanFigWinInfo=new FigWinInfo();
	    		 beanFigWinInfo.setWinWagerNum("1");
	    		 beanFigWinInfo.setStrOneWag(arrDolSegStr[j]);
	    		 beanFigWinInfo.setStrRule(playMode);
	    		 vecWinStr.addElement(beanFigWinInfo);//数据库中一条投注字串中的几次中奖
	    	 }
			 
	     }
	     return vecWinStr;
	 } 
	 public boolean hasFrOnFronWin(String[] arrWinNum,String dolSegStr){
		 boolean hasWin=false;
		 String[] arrVerLineSegStr=dolSegStr.split("\\|");//按|截取，开始判断
	     hasWin=hasWinNum(arrWinNum[0],arrVerLineSegStr[0]);
		 return hasWin;
	 }
	 public boolean hasFrOnBaOnWin(String[] arrWinNum,String dolSegStr){
		 boolean hasWin=false;
		 String[] arrVerLineSegStr=dolSegStr.split("\\|");//按|截取，开始判断
	     hasWin=hasWinNum(arrWinNum[2],arrVerLineSegStr[2]);
		 return hasWin;
	 }
	 
//	前二后二
	 public Vector getVecFrTwWin(String[] arrFig,String wholeStr,String playMode){
		 Vector vecWinStr=new Vector();	 
	     String[] arrDolSegStr=wholeStr.split("\\$");
	     for(int j=0;j<arrDolSegStr.length;j++){
	    	 boolean hasWin=false;
	    	 if(playMode.equals("前二")){
	    		 hasWin=hasFrTwFrTwWin(arrFig,arrDolSegStr[j]);
	    	 }
	    	 if(playMode.equals("后二")){
	    		 hasWin=hasFrTwBaTwWin(arrFig,arrDolSegStr[j]);
	    	 }
	    	 FigWinInfo beanFigWinInfo=new FigWinInfo();
	    	 if(hasWin==true){
	    		 beanFigWinInfo.setWinWagerNum("1");
	    		 beanFigWinInfo.setStrOneWag(arrDolSegStr[j]);
	    		 beanFigWinInfo.setStrRule(playMode);
	    		 vecWinStr.addElement(beanFigWinInfo);//数据库中一条投注字串中的几次中奖
	    	 }
			 
	     }
	     return vecWinStr;
	 } 
	 
	 public boolean hasFrTwFrTwWin(String[] arrWinNum,String dolSegStr){
		 boolean hasWin=false;
		 String[] arrVerLineSegStr=dolSegStr.split("\\|");//按|截取，开始判断
	     if(hasWinNum(arrWinNum[0],arrVerLineSegStr[0])==true && hasWinNum(arrWinNum[1],arrVerLineSegStr[1])==true){
	    	 hasWin=true;
	     }
		 return hasWin;
	 }
	 public boolean hasFrTwBaTwWin(String[] arrWinNum,String dolSegStr){
		 boolean hasWin=false;
		 String[] arrVerLineSegStr=dolSegStr.split("\\|");//按|截取，开始判断
	     if(hasWinNum(arrWinNum[2],arrVerLineSegStr[2])==true && hasWinNum(arrWinNum[1],arrVerLineSegStr[1])==true){
	    	 hasWin=true;
	     }
		 return hasWin;
	 }
	 //直选
	 public Vector getVecComWin(String[] arrFig,String wholeStr,String playType,String playMode){
		 Vector vecWinStr=new Vector();	 
	     String[] arrDolSegStr=wholeStr.split("\\$");
	     for(int j=0;j<arrDolSegStr.length;j++){
	    	 boolean hasWin=false;
	    	 if(playMode.equals("复式")||playMode.equals("单式")){
	    		 hasWin=hasComMulWin(arrFig,arrDolSegStr[j]);
	    	 }
	    	 if(playMode.equals("和值")){
	    		 hasWin=hasComTotWin(arrFig,arrDolSegStr[j]);
	    	 }
	    	 if(playMode.equals("包号")){
	    		 hasWin=hasComSamWin(arrFig,arrDolSegStr[j]);
	    	 }
    	 
	    	 if(hasWin==true){
	    		 FigWinInfo beanFigWinInfo=new FigWinInfo();
	    		 beanFigWinInfo.setWinWagerNum("1");//中奖的注数
	    		 beanFigWinInfo.setStrOneWag(arrDolSegStr[j]);
	    		 beanFigWinInfo.setStrRule(playType);
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
	 public boolean hasComTotWin(String[] arrWinNum,String dolSegStr){
		 boolean hasWin=false;
		 int intTotal=0;
		 for(int i=0;i<arrWinNum.length;i++){
			 intTotal=intTotal+Integer.parseInt(arrWinNum[i]);
		 }
		 hasWin=hasWinNum(String.valueOf(intTotal), dolSegStr);
		 return hasWin;
	 }
	 public boolean hasComSamWin(String[] arrWinNum,String dolSegStr){
		 boolean hasWin=true;
		 for(int i=0;i<arrWinNum.length;i++){
			 if(hasWinNum(arrWinNum[i],dolSegStr)==false){
				 hasWin=false;
			 }
		 }	 
		 return hasWin;
	 }
	 //组选时时乐
	 public Vector getVecGrWin(String[] arrFig,String wholeStr,String playType,String playMode){
		 Vector vecWinStr=new Vector();
		 String[] arrDolSegStr=wholeStr.split("\\$");
	     for(int j=0;j<arrDolSegStr.length;j++){
	    	 boolean hasWin=false;
	    	 if(playType.equals("组6")){    		 
	    		 if(playMode.equals("复式")){
	        		 hasWin=hasGrSixMulWin(arrFig,arrDolSegStr[j]);
	        	 }
	        	 if(playMode.equals("和值")){
	        		 hasWin=hasGrSixTotWin(arrFig,arrDolSegStr[j]);
	        	 } 
	    	 }
	    	 if(playType.equals("组3")){    		 
	    		 if(playMode.equals("复式")){
	        		 hasWin=hasGrThrMulWin(arrFig,arrDolSegStr[j]);
	        	 }
	        	 if(playMode.equals("和值")){
	        		 hasWin=hasGrThrTotWin(arrFig,arrDolSegStr[j]);
	        	 } 
	    	 }
//	    	 if(playType.equals("组单")){
//	    		 hasWin=hasGrSingWin(arrFig,arrDolSegStr[j]);
//已经删除   	 }
	    	 
	    	 if(hasWin==true){
	    		 FigWinInfo beanFigWinInfo=new FigWinInfo();
	    		 beanFigWinInfo.setWinWagerNum("1");
	    		 beanFigWinInfo.setStrOneWag(arrDolSegStr[j]);
	    		 beanFigWinInfo.setStrRule(playType);
	    		 vecWinStr.addElement(beanFigWinInfo);//数据库中一条投注字串中的几次中奖
	    	 }
			 
	     }
	     return vecWinStr;
	 }
	 public boolean hasGrSixMulWin(String[] arrWinNum,String dolSegStr){
		 boolean hasWin=true;
		 String strHasSame="";
		 for(int i=0;i<arrWinNum.length;i++){		 
			 if(hasWinNum(arrWinNum[i],strHasSame)==false){//判断是否有重复的数字
				 strHasSame=strHasSame+","+arrWinNum[i];
			 }else{
				 hasWin=false;//有，肯定为假，没有中奖号
				 break;
			 }
		 }
		 if(hasWin==true){//如果没有重复的数字
			 hasWin=hasComSamWin(arrWinNum,dolSegStr);
		 }
		 return hasWin;
	 }
	 public boolean hasGrSixTotWin(String[] arrWinNum,String dolSegStr){
		 boolean hasWin=false;
		 hasWin=hasComTotWin(arrWinNum,dolSegStr);
		 return hasWin;
	 }
	 
	 public boolean hasGrThrMulWin(String[] arrWinNum,String dolSegStr){
		 boolean hasWin=false;
		 if(!arrWinNum[0].equals(arrWinNum[1])){		 
			 if(arrWinNum[0].equals(arrWinNum[2]) || arrWinNum[1].equals(arrWinNum[2])){
				 hasWin=hasComSamWin(arrWinNum,dolSegStr);
			 }//否则全不相等，为假
		 }else{
			 if(!arrWinNum[0].equals(arrWinNum[2])){
				 hasWin=hasComSamWin(arrWinNum,dolSegStr);
			 }//否则全相等，为假
		 }
		 
		 return hasWin;
	 }
	 public boolean hasGrThrTotWin(String[] arrWinNum,String dolSegStr){
		 boolean hasWin=false;
		 if(!arrWinNum[0].equals(arrWinNum[1])){		 
			 if(arrWinNum[0].equals(arrWinNum[2]) || arrWinNum[1].equals(arrWinNum[2])){
				 hasWin=hasComTotWin(arrWinNum,dolSegStr);
			 }//否则全不相等，为假
		 }else{
			 if(!arrWinNum[0].equals(arrWinNum[2])){
				 hasWin=hasComTotWin(arrWinNum,dolSegStr);
			 }//否则全相等，为假
		 }
		 
		 return hasWin;
	 }
	 public boolean hasGrSingWin(String[] arrWinNum,String dolSegStr){
		 boolean hasWin=false;
		 if(arrWinNum[0].equals(arrWinNum[1]) && arrWinNum[1].equals(arrWinNum[2])){		 
			 hasWin=false;
		 }else{
			 hasWin=hasWinNum(letterTranOrder(arrWinNum),letterTranOrder(dolSegStr.split(",")));
		 }
		 
		 return hasWin;
	 }
	 
	 
	 public String[] orderStrSeq(String[] arrStrWin){
	 	for(int i=0;i<arrStrWin.length;i++){
	 		String chg;
	 		boolean exchange = false;
	 		int startIndex=arrStrWin.length-1;
	 		for(int j=startIndex;j>i;j--){
	 			if(Integer.parseInt(arrStrWin[j])<Integer.parseInt(arrStrWin[j-1])){
	 				chg = arrStrWin[j];
	 				arrStrWin[j] = arrStrWin[j-1];
	 				arrStrWin[j-1] = chg;
	 				exchange = true;
	 			}
	 		}
	 		if(!exchange){
	 			return arrStrWin;
	 		}
	 	}
	 	return arrStrWin;
	 }
	 
	 public String letterTranOrder(String[] arrLetStrWin){
		 String tempStr=arrLetStrWin[0]+","+arrLetStrWin[1]+","+arrLetStrWin[2];
		 String[] arrTempStr=orderStrSeq(tempStr.split(","));
		 tempStr=arrTempStr[0]+","+arrTempStr[1]+","+arrTempStr[2];
		 return tempStr;
	 }
	 
	 //排列三组选算法
	 public Vector getVecGrWinPth(String[] arrFig,String wholeStr,String playType,String playMode){
		 Vector vecWinStr=new Vector();
		 String[] arrDolSegStr=wholeStr.split("\\$");
	     for(int j=0;j<arrDolSegStr.length;j++){
	    	 boolean hasWin=false;
	    	 if(playMode.equals("组6")){    		 
	        	hasWin=hasGrSixMulWin(arrFig,arrDolSegStr[j]); 
	    	 }
	    	 if(playMode.equals("组3")){	 
	        	hasWin=hasGrThrMulWin(arrFig,arrDolSegStr[j]); 
	    	 }
	    	 if(playMode.equals("和值")){    		 
		        	hasWin=hasGrTotWinPth(arrFig,arrDolSegStr[j]); 
		     }
	    	 if(hasWin==true){
	    		 FigWinInfo beanFigWinInfo=new FigWinInfo();
	    		 beanFigWinInfo.setWinWagerNum("1");
	    		 beanFigWinInfo.setStrOneWag(arrDolSegStr[j]);
	    		 beanFigWinInfo.setStrRule(playMode);
	    		 vecWinStr.addElement(beanFigWinInfo);//数据库中一条投注字串中的几次中奖
	    	 }
			 
	     }
	     return vecWinStr;
	 }
	 
	 public boolean hasGrTotWinPth(String[] arrWinNum,String dolSegStr){
		 boolean hasWin=false;
		 if(arrWinNum[0].equals(arrWinNum[1]) && arrWinNum[1].equals(arrWinNum[2])){
			 hasWin=false;
		 }else{
			 hasWin=hasComTotWin(arrWinNum,dolSegStr);
		 }
		 return hasWin;
	 }
	
}
