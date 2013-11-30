package beanpac;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dbconnpac.DataBaseConn;

public class WinUserPok{
	
	public String[] getArrFigWin(String[] arrFigure,String dolSegStr){
		 String[] arrFigWin={"-","-","-","-"};
		 String[] arrVerLineSegStr=dolSegStr.split("\\|");//按|截取，开始判断
		 for (int i=0;i<arrVerLineSegStr.length;i++){
			 Pattern p=Pattern.compile(arrFigure[i]);//spade为正则，即要匹配的字串如：“3”
			 Matcher m=p.matcher(arrVerLineSegStr[i]);//从字串"3,4,7"中找
			 boolean isWin=m.find();
	         if(isWin==true){
	        	 arrFigWin[i]=arrFigure[i];//如果有把3给中奖的字串中。
	         }
		 }
		 return arrFigWin;
	 }
	 public int getQuan(String type,int Q1,int Q2,int Q3,int Q4){
		 int quan=0;
		 /*删除
		 if(type.equals("选四直选")){
			 quan= Q1*Q2*Q3*Q4;
		 }
		 if(type.equals("任选三")){
			 quan= Q1*Q2*Q3 + Q1*Q2*Q4 + Q1*Q3*Q4 + Q2*Q3*Q4;
		 }
		 */
		 if(type.equals("任选二")){
			 quan= Q1*Q2 + Q1*Q3 + Q1*Q4 + Q2*Q3 + Q2*Q4 + Q3*Q4;
		 }
		 if(type.equals("任选一")){
			 quan= Q1+Q2+Q3+Q4;
		 }
	     return quan;
	 }
	 public int[] getFreeArrNum(String[] arrTempStr){
    	 int[] arrN={0,0,0,0};
    	 for(int i=0;i<arrTempStr.length;i++){
    		 if(!arrTempStr[i].equals("-")){
    			 arrN[i]=1;
    		 }
    	 }
    	 return arrN;
	 }
	 
/*组合计算*/
	 public Vector getVecFreeCompWin(String[] arrFig,String totStr){
		 Vector vecWinStr=new Vector();
		 String[] arrTotStr=totStr.split(("\\|"));
		 int index=0;
		 for(int i=0;i<arrTotStr.length;i++){
			 if(arrTotStr[i].equals("=")){
				 index++;
			 }
		 }
		 if(index<4){
			 Vector vecWinOne=getVecFreeWin(arrFig,totStr,"任选一");
			 for(int i=0;i<vecWinOne.size();i++){
				 FigWinInfo beanFigWinInfo=(FigWinInfo)vecWinOne.elementAt(i);
	    		 vecWinStr.addElement(beanFigWinInfo);
			 }
			 if(index<3){
				 Vector vecWinTwo=getVecFreeWin(arrFig,totStr,"任选二");
				 for(int i=0;i<vecWinTwo.size();i++){
					 FigWinInfo beanFigWinInfo=(FigWinInfo)vecWinTwo.elementAt(i);
		    		 vecWinStr.addElement(beanFigWinInfo);
				 }
				 if(index<2){
					 Vector vecWinThr=getWinThr(arrFig,totStr,"任选三");
					 for(int i=0;i<vecWinThr.size();i++){
						 FigWinInfo beanFigWinInfo=(FigWinInfo)vecWinThr.elementAt(i);
			    		 vecWinStr.addElement(beanFigWinInfo);
					 }
					 if(index<1){
						 Vector vecWinFour=getWinFour(arrFig,totStr,"选四直选");
						 for(int i=0;i<vecWinFour.size();i++){
							 FigWinInfo beanFigWinInfo=(FigWinInfo)vecWinFour.elementAt(i);
				    		 vecWinStr.addElement(beanFigWinInfo);
						 }
					 }
				 }
			 }
		 }
		 return vecWinStr;
	 }
	

/*任选一，二计算*/
	 public Vector getVecFreeWin(String[] arrFig,String wholeStr,String playType){
		 Vector vecWinStr=new Vector();	 
	     String[] arrDolSegStr=wholeStr.split("\\$");
	     for(int j=0;j<arrDolSegStr.length;j++){
	    	 String strOneWager=arrDolSegStr[j];
	    	 String[] arrStr=getArrFigWin(arrFig,strOneWager);//截取$前的第一个字串，看是否有中奖
	    	 int[] arrN=getFreeArrNum(arrStr);
	    	 int num=0;
	    	 num=getQuan(playType,arrN[0],arrN[1],arrN[2],arrN[3]);
	    	 if(num!=0){
	    		 FigWinInfo beanFigWinInfo=new FigWinInfo();
    			 beanFigWinInfo.setWinWagerNum(String.valueOf(num));//中奖的注数
    			 beanFigWinInfo.setStrOneWag(strOneWager);//得到一个$片段（如：3,4|3,7|3|9,K）的中奖值
    			 beanFigWinInfo.setStrRule(playType);
    			 vecWinStr.addElement(beanFigWinInfo);//得到总的中奖信息
    		 }
	     }
	     return vecWinStr;
	 } 

/*任选三计算*/
	 public Vector getWinThr(String[] arrFig,String wholeStr,String playType){
		 Vector vecWinStr=new Vector();
	     String[] arrDolSegStr=wholeStr.split("\\$");
	     for(int i=0;i<arrDolSegStr.length;i++){
	    	 String strOneWager=arrDolSegStr[i];//得到一段如：1,2|3,4|6,7|9,10
	    	 String[] arrOneWag=strOneWager.split("\\|");
	    	 String[] arrWagSpade=arrOneWag[0].split(",");//得到spade所选的数组如：1,2
	    	 String[] arrWagHeart=arrOneWag[1].split(",");
	    	 String[] arrWagClub=arrOneWag[2].split(",");
	    	 String[] arrWagDiamond=arrOneWag[3].split(",");
	    	 Vector vecWinSeg=getVecWinSegThr(arrFig,arrWagSpade,arrWagHeart,arrWagClub,arrWagDiamond);
	    	 for(int j=0;j<vecWinSeg.size();j++){
	    		 FigWinInfo beanFigWinInfo=(FigWinInfo)vecWinSeg.elementAt(j);
	    		 vecWinStr.addElement(beanFigWinInfo);
	    	 }
	     }
	     return vecWinStr;
	 }
	 public Vector getVecWinSegThr(String[] arrFig, String[] arrSpade, String[] arrHeart, String[] arrClub, String[] arrDiamond){
		 Vector vecWinStr=new Vector();
		 for(int i=0;i<arrSpade.length;i++){
			 for(int j=0;j<arrHeart.length;j++){
				 for(int k=0;k<arrClub.length;k++){
					 String strWag=arrSpade[i]+"|"+arrHeart[j]+"|"+arrClub[k]+"|"+"-";					 
					 FigWinInfo beanFigWinInfo=getBeanThr(arrFig,strWag);
					 if(beanFigWinInfo.getWinWagerNum().equals("1")){
						 vecWinStr.addElement(beanFigWinInfo);
					 }
				 }
			 } 
		 }
		 for(int i=0;i<arrSpade.length;i++){
			 for(int j=0;j<arrHeart.length;j++){
				 for(int k=0;k<arrDiamond.length;k++){
					 String strWag=arrSpade[i]+"|"+arrHeart[j]+"|"+"-"+"|"+arrDiamond[k];
					 FigWinInfo beanFigWinInfo=getBeanThr(arrFig,strWag);
					 if(beanFigWinInfo.getWinWagerNum().equals("1")){
						 vecWinStr.addElement(beanFigWinInfo);
					 }
				 }
			 } 
		 }
		 for(int i=0;i<arrSpade.length;i++){
			 for(int j=0;j<arrClub.length;j++){
				 for(int k=0;k<arrDiamond.length;k++){
					 String strWag=arrSpade[i]+"|"+"-"+"|"+arrClub[j]+"|"+arrDiamond[k];
					 FigWinInfo beanFigWinInfo=getBeanThr(arrFig,strWag);
					 if(beanFigWinInfo.getWinWagerNum().equals("1")){
						 vecWinStr.addElement(beanFigWinInfo);
					 }
				 }
			 } 
		 }
		 for(int i=0;i<arrHeart.length;i++){
			 for(int j=0;j<arrClub.length;j++){
				 for(int k=0;k<arrDiamond.length;k++){
					 String strWag="-"+"|"+arrHeart[i]+"|"+arrClub[j]+"|"+arrDiamond[k];
					 FigWinInfo beanFigWinInfo=getBeanThr(arrFig,strWag);
					 if(beanFigWinInfo.getWinWagerNum().equals("1")){
						 vecWinStr.addElement(beanFigWinInfo);
					 }
				 }
			 }
		 }
		 return vecWinStr;
	 }

	 public FigWinInfo getBeanThr(String[] arrFig, String strSingWag){
		 String[] arrStrSingWag=strSingWag.split("\\|");
		 int index=0;
		 FigWinInfo beanFigWinInfo=new FigWinInfo();
		 for(int i=0;i<arrStrSingWag.length;i++){
			 if(arrStrSingWag[i].equals("=")){
				 index++;
			 }
		 }
		 if(index<1){			 
			 String[] arrStr=getArrFigWin(arrFig,strSingWag);//看是否有中奖
	    	 int[] arrN=getFreeArrNum(arrStr);
	    	 int intWinNum=arrN[0]+arrN[1]+arrN[2]+arrN[3];
	    	 if(intWinNum==3){
				 beanFigWinInfo.setWinWagerNum("1");//中奖的注数
				 beanFigWinInfo.setStrOneWag(strSingWag);//得到一个单注（如：3|7|3|-）的中奖值
				 beanFigWinInfo.setStrRule("任选三中3");
	    	 }else if(intWinNum==2){
				 beanFigWinInfo.setWinWagerNum("1");//中奖的注数
				 beanFigWinInfo.setStrOneWag(strSingWag);
				 beanFigWinInfo.setStrRule("任选三中2");
	    	 }
		 }
    	 return beanFigWinInfo;
	 }
	 
	 
/*选四直选四计算*/	 
	 public Vector getWinFour(String[] arrFig,String wholeStr,String playType){
		 Vector vecWinStr=new Vector();
	     String[] arrDolSegStr=wholeStr.split("\\$");
	     for(int i=0;i<arrDolSegStr.length;i++){
	    	 String strOneWager=arrDolSegStr[i];//得到一段如：1,2|3,4|6,7|9,10
	    	 String[] arrOneWag=strOneWager.split("\\|");
	    	 String[] arrWagSpade=arrOneWag[0].split(",");//得到spade所选的数组如：1,2
	    	 String[] arrWagHeart=arrOneWag[1].split(",");
	    	 String[] arrWagClub=arrOneWag[2].split(",");
	    	 String[] arrWagDiamond=arrOneWag[3].split(",");
	    	 Vector vecWinSeg=getVecWinSegFour(arrFig,arrWagSpade,arrWagHeart,arrWagClub,arrWagDiamond);
	    	 for(int j=0;j<vecWinSeg.size();j++){
	    		 FigWinInfo beanFigWinInfo=(FigWinInfo)vecWinSeg.elementAt(j);
	    		 vecWinStr.addElement(beanFigWinInfo);
	    	 }
	     }
	     return vecWinStr;
	 }
	 public Vector getVecWinSegFour(String[] arrFig, String[] arrSpade, String[] arrHeart, String[] arrClub, String[] arrDiamond){
		 Vector vecWinStr=new Vector();
		 for(int i=0;i<arrSpade.length;i++){
			 for(int j=0;j<arrHeart.length;j++){
				 for(int k=0;k<arrClub.length;k++){
					 for(int l=0;l<arrDiamond.length;l++){
						 String strWag=arrSpade[i]+"|"+arrHeart[j]+"|"+arrClub[k]+"|"+arrDiamond[l];
						 FigWinInfo beanFigWinInfo=getBeanFour(arrFig,strWag);
						 if(beanFigWinInfo.getWinWagerNum().equals("1")){
							 vecWinStr.addElement(beanFigWinInfo);
						 }
					 }
				 }
			 }
		 }
		 return vecWinStr;
	 }
	 public FigWinInfo getBeanFour(String[] arrFig, String strSingWag){
		 String[] arrStr=getArrFigWin(arrFig,strSingWag);//看是否有中奖
    	 int[] arrN=getFreeArrNum(arrStr);
    	 int intWinNum=arrN[0]+arrN[1]+arrN[2]+arrN[3];
    	 FigWinInfo beanFigWinInfo=new FigWinInfo();
    	 if(intWinNum==4){//如果中四
			 beanFigWinInfo.setWinWagerNum("1");//中奖的注数
			 beanFigWinInfo.setStrOneWag(strSingWag);//得到一个单注（如：3|7|3|-）的中奖值
			 beanFigWinInfo.setStrRule("选四直选中4");
    	 }else if(intWinNum==3){//如果不中四判断是否中三
			 beanFigWinInfo.setWinWagerNum("1");//中奖的注数
			 beanFigWinInfo.setStrOneWag(strSingWag);
			 beanFigWinInfo.setStrRule("选四直选中3");
    	 }
    	 return beanFigWinInfo;
	 }
	 
	 
/*组选计算*/		 
	 public Vector getVecGroupWin(String[] arrFig,String wholeStr,String playMode){
		 Vector vecWinStr=new Vector();
		 String orderWinFig=letterTranOrder(arrFig);
		 String[] arrDolSegStr=wholeStr.split("\\$");
	     for(int j=0;j<arrDolSegStr.length;j++){
			 boolean isWin=false;
			 String strOneWager=arrDolSegStr[j];
			 String strOneWagOrder=letterTranOrder(strOneWager.split(","));
			 Pattern p=Pattern.compile(orderWinFig);//spade为正则，即要匹配的字串如：“3”
			 Matcher m=p.matcher(strOneWagOrder);//从字串"3,4,7"中找
			 if(m.find()==true){
				 isWin=true;
			 }
			 /*
			 if(playMode.equals("组24")){
				 boolean[] isWinEachFig={false,false,false,false};
				 if(arrFig[0].equals(arrFig[1]) || arrFig[0].equals(arrFig[2]) || arrFig[0].equals(arrFig[3]) || arrFig[1].equals(arrFig[2]) || arrFig[1].equals(arrFig[3]) || arrFig[2].equals(arrFig[3])){
					 isWin=false;
				 }else{
					 for(int i=0;i<arrFig.length;i++){
						 Pattern p=Pattern.compile(arrFig[i]);
						 Matcher m=p.matcher(strOneWagOrder);
						 if(m.find()==true){
							 isWinEachFig[i]=true;
						 }
					 }
					 if(isWinEachFig[0]==true && isWinEachFig[1]==true && isWinEachFig[2]==true && isWinEachFig[3]==true){
						 isWin=true;
					 }else{
						 isWin=false;
					 }
				 }
			 }
			 */
	
		     if(isWin==true){
		    	 FigWinInfo beanFigWinInfo=new FigWinInfo();
		    	 beanFigWinInfo.setStrOneWag(strOneWager);
		    	 beanFigWinInfo.setStrRule(playMode);
		    	 beanFigWinInfo.setWinWagerNum("1");
		    	 vecWinStr.addElement(beanFigWinInfo);
		     }
	     }
	     
	     return vecWinStr;
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
		 String tempStr=arrLetStrWin[0]+","+arrLetStrWin[1]+","+arrLetStrWin[2]+","+arrLetStrWin[3];
		 tempStr=tempStr.replace("A", "1").replace("J", "11").replace("Q", "12").replace("K", "13");
		 String[] arrTempStr=orderStrSeq(tempStr.split(","));
		 tempStr=arrTempStr[0]+","+arrTempStr[1]+","+arrTempStr[2]+","+arrTempStr[3];
		 tempStr=tempStr.replace("11","J").replace("12","Q").replace("13","K").replace("1","A").replace("A0","10");
		 return tempStr;
	 }
	
}
