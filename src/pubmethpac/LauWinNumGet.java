package pubmethpac;

import dbconnpac.DataBaseConn;

/**
 * Description:“时时乐”中奖号码查询 
 * Author:LJY 
 * E-mail:lijiyongcn@sohu.com 
 * Create on May
 * 25, 2009 11:49:15 AM Ver:
 */
public class LauWinNumGet {
	private String strHundred;// 百位
	private String strTen;// 十位
	private String strOne;// 个位

	private String maxLauIssNum;// 最新的开奖期号

	private String picHundred;// 百位图片
	private String picTen;// 十位图片
	private String picOne;// 个位图片
	/**
	 * 
	 *@Description:查询最新开奖号码信息
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on May 25, 2009 12:28:36 PM
	 */
	public String[] getLauNumWinInfo(){
	    DataBaseConn winPrizeDBC= new DataBaseConn();
	    winPrizeDBC.execQuery("select max(issuenum) from lauwinnum;");
	    winPrizeDBC.rsNext();
	    maxLauIssNum=String.valueOf(winPrizeDBC.rsGetInt(1));
	    if(!maxLauIssNum.equals("0")){
	    	winPrizeDBC.execQuery("select * from lauwinnum where issuenum='"+maxLauIssNum+"';");
	        winPrizeDBC.rsNext();
	        strHundred=winPrizeDBC.rsGetString("hundred");
	        strTen=winPrizeDBC.rsGetString("ten");
	        strOne=winPrizeDBC.rsGetString("one");
	    }else{
	    	strHundred="9";
	    	strTen="9";
	    	strOne="9";
	    }
	    winPrizeDBC.connClose();
	    
	    String[] arrFigNumGet={"0","1","2","3","4","5","6","7","8","9"};
	    String[] arrFigNumLink={"0","1","2","3","4","5","6","7","8","9"};
	    
	    for (int i=0;i<arrFigNumGet.length;i++){
	      if(strHundred.equals(arrFigNumGet[i])){
	    	  picHundred="/pkimages/pokerimage/pokpic/spade"+arrFigNumLink[i]+".bmp";
	      }
	      if(strTen.equals(arrFigNumGet[i])){
	    	  picTen="/pkimages/pokerimage/pokpic/heart"+arrFigNumLink[i]+".bmp";
	      }
	      if(strOne.equals(arrFigNumGet[i])){
	    	  picOne="/pkimages/pokerimage/pokpic/club"+arrFigNumLink[i]+".bmp";
	      }
	     
	    }
	    return new String[]{maxLauIssNum,strHundred,strTen,strOne,picHundred,picTen,picOne};
	 }
	/**
	 * 
	 *@Description:
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Jun 5, 2009 5:02:54 PM
	 * @return
	 */
	public String[] getLastLauNumWinInfo(){
		 String hundred = "";// 百位
		 String ten = "";// 十位
		 String one = "";// 个位
		 String strMaxLau =  "";
		DataBaseConn winPrizeDBC= new DataBaseConn();
	    winPrizeDBC.execQuery("select max(issuenum) from lauwinnum;");
	    winPrizeDBC.rsNext();
	    strMaxLau=String.valueOf(winPrizeDBC.rsGetInt(1));
	    if(!strMaxLau.equals("0")){
	    	String sql = "select * from lauwinnum where issuenum='"+strMaxLau+"'";
	    	winPrizeDBC.execQuery(sql);
	        winPrizeDBC.rsNext();
	        hundred=winPrizeDBC.rsGetString("hundred");
	        ten=winPrizeDBC.rsGetString("ten");
	        one=winPrizeDBC.rsGetString("one");
	    }
	    winPrizeDBC.connClose();
	    return new String[]{strMaxLau,hundred,ten,one};
	}
}
