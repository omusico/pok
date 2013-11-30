package beanpac;
import java.io.Serializable;

public class FigWinInfo implements Serializable{

	private String strIssueNum="";
	private String strOneWag="";
	private String strRule="";
	private String[] arrWinFigure={"","","",""};
	private String winWagerNum="";
	private String[] arrWinWagerNum={"0","0","0","0"};
	public String[] getArrWinFigure() {
		return arrWinFigure;
	}
	public void setArrWinFigure(String[] arrWinFigure) {
		this.arrWinFigure = arrWinFigure;
	}
	public String[] getArrWinWagerNum() {
		return arrWinWagerNum;
	}
	public void setArrWinWagerNum(String[] arrWinWagerNum) {
		this.arrWinWagerNum = arrWinWagerNum;
	}
	public String getWinWagerNum() {
		return winWagerNum;
	}
	public void setWinWagerNum(String winWagerNum) {
		this.winWagerNum = winWagerNum;
	}
	public String getStrIssueNum() {
		return strIssueNum;
	}
	public void setStrIssueNum(String strIssueNum) {
		this.strIssueNum = strIssueNum;
	}
	public String getStrOneWag() {
		return strOneWag;
	}
	public void setStrOneWag(String strOneWag) {
		this.strOneWag = strOneWag;
	}
	public String getStrRule() {
		return strRule;
	}
	public void setStrRule(String strRule) {
		this.strRule = strRule;
	}

}
