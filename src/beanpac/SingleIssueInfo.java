package beanpac;

import java.io.*;

public class SingleIssueInfo implements Serializable{

	private String issueNumber="";//期号
	private String wagerEndTime="";//销售截止时间
	private String winPrizeTime="";//开奖截止时间
	public String getIssueNumber() {
		return issueNumber;
	}
	public void setIssueNumber(String issueNumber) {
		this.issueNumber = issueNumber;
	}
	public String getWagerEndTime() {
		return wagerEndTime;
	}
	public void setWagerEndTime(String wagerEndTime) {
		this.wagerEndTime = wagerEndTime;
	}
	public String getWinPrizeTime() {
		return winPrizeTime;
	}
	public void setWinPrizeTime(String winPrizeTime) {
		this.winPrizeTime = winPrizeTime;
	}
	
}
