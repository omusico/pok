package com.onmyway.sxw.manage.model;

/**
 * SscBaseInfoConfig entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SxwBaseInfoConfig implements java.io.Serializable {

	// Fields

	private String id;
	private String beginDate;
	private String beginIssue;
	private String beginHour;
	private String beginMin;
	private String sellMin;
	private String winMin;
	private String dayIssueTimes;
	private String operTime;

	private String offsetFlag;
	private String offsetTime;
	// Constructors

	/** default constructor */
	public SxwBaseInfoConfig() {
	}

	/** full constructor */
	public SxwBaseInfoConfig(String beginDate, String beginIssue,
			String beginHour, String beginMin, String sellMin, String winMin,
			String dayIssueTimes, String operTime) {
		this.beginDate = beginDate;
		this.beginIssue = beginIssue;
		this.beginHour = beginHour;
		this.beginMin = beginMin;
		this.sellMin = sellMin;
		this.winMin = winMin;
		this.dayIssueTimes = dayIssueTimes;
		this.operTime = operTime;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBeginDate() {
		return this.beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getBeginIssue() {
		return this.beginIssue;
	}

	public void setBeginIssue(String beginIssue) {
		this.beginIssue = beginIssue;
	}

	public String getBeginHour() {
		return this.beginHour;
	}

	public void setBeginHour(String beginHour) {
		this.beginHour = beginHour;
	}

	public String getBeginMin() {
		return this.beginMin;
	}

	public void setBeginMin(String beginMin) {
		this.beginMin = beginMin;
	}

	public String getSellMin() {
		return this.sellMin;
	}

	public void setSellMin(String sellMin) {
		this.sellMin = sellMin;
	}

	public String getWinMin() {
		return this.winMin;
	}

	public void setWinMin(String winMin) {
		this.winMin = winMin;
	}

	public String getDayIssueTimes() {
		return this.dayIssueTimes;
	}

	public void setDayIssueTimes(String dayIssueTimes) {
		this.dayIssueTimes = dayIssueTimes;
	}

	public String getOperTime() {
		return this.operTime;
	}

	public void setOperTime(String operTime) {
		this.operTime = operTime;
	}

	public String getOffsetFlag() {
		return offsetFlag;
	}

	public void setOffsetFlag(String offsetFlag) {
		this.offsetFlag = offsetFlag;
	}

	public String getOffsetTime() {
		return offsetTime;
	}

	public void setOffsetTime(String offsetTime) {
		this.offsetTime = offsetTime;
	}

}
