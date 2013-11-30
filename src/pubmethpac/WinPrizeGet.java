//include required classes
package pubmethpac;
import dbconnpac.DataBaseConn;

public class WinPrizeGet{
  private String strSpade;
  private String strHeart;
  private String strClub;
  private String strDiamond;
  private String maxIssNum;
  private String picLinkSpade;
  private String picLinkHeart;
  private String picLinkClub;
  private String picLinkDiamond;
  
  public void getPrizeWinInfo(){
    DataBaseConn winPrizeDBC= new DataBaseConn();
    winPrizeDBC.execQuery("select max(issuenum) from pokwinnum;");
    winPrizeDBC.rsNext();
    maxIssNum=String.valueOf(winPrizeDBC.rsGetInt(1));
    if(!maxIssNum.equals("0")){
    	winPrizeDBC.execQuery("select * from pokwinnum where issuenum='"+maxIssNum+"';");
        winPrizeDBC.rsNext();
        strSpade=winPrizeDBC.rsGetString("spade");
        strHeart=winPrizeDBC.rsGetString("heart");
        strClub=winPrizeDBC.rsGetString("club");
        strDiamond=winPrizeDBC.rsGetString("diamond");
    }else{
    	strSpade="10";
        strHeart="10";
        strClub="10";
        strDiamond="10";
    }
    winPrizeDBC.connClose();
    
    String[] arrFigNumGet={"A","1","2","3","4","5","6","7","8","9","10","J","Q","K"};
    String[] arrFigNumLink={"a","1","2","3","4","5","6","7","8","9","10","j","q","k"};
    
    for (int i=0;i<arrFigNumGet.length;i++){
      if(strSpade.equals(arrFigNumGet[i])){
    	  picLinkSpade="/pkimages/pokerimage/pokpic/spade"+arrFigNumLink[i]+".bmp";
      }
      if(strHeart.equals(arrFigNumGet[i])){
    	  picLinkHeart="/pkimages/pokerimage/pokpic/heart"+arrFigNumLink[i]+".bmp";
      }
      if(strClub.equals(arrFigNumGet[i])){
    	  picLinkClub="/pkimages/pokerimage/pokpic/club"+arrFigNumLink[i]+".bmp";
      }
      if(strDiamond.equals(arrFigNumGet[i])){
    	  picLinkDiamond="/pkimages/pokerimage/pokpic/diamond"+arrFigNumLink[i]+".bmp";
      }
    }
    
 }
public String getStrClub() {
	return strClub;
}
public String getStrDiamond() {
	return strDiamond;
}
public String getStrHeart() {
	return strHeart;
}
public String getStrSpade() {
	return strSpade;
}
public String getMaxIssNum() {
	return maxIssNum;
}
public String getPicLinkClub() {
	return picLinkClub;
}
public String getPicLinkDiamond() {
	return picLinkDiamond;
}
public String getPicLinkHeart() {
	return picLinkHeart;
}
public String getPicLinkSpade() {
	return picLinkSpade;
}


}
