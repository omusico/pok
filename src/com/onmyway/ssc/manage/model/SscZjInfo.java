package com.onmyway.ssc.manage.model;

/**
 * SscZjInfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SscZjInfo implements java.io.Serializable {

	// Fields

	private String id;
	private String issueNum;
	private String tzId;
	private String detailId;
	private String userId;
	private String userName;
	private String playType;
	private String playMode;
	private String tzNum;
	private String zjZhushu;//单注中奖注数
	private String totalZjZhushu;//总的
	private String tzTimes;
	private String isZhuitou;
	private String opTime;
	private String zjMoney;
	private String zjType;
	private String moneyOfZjType;

	// Constructors

	public SscZjInfo(String id, String issueNum, String tzId, String detailId,
			String userId, String userName, String playType, String playMode,
			String tzNum, String zjZhushu, String totalZjZhushu,
			String tzTimes, String isZhuitou, String opTime, String zjMoney,
			String zjType, String moneyOfZjType) {
		super();
		this.id = id;
		this.issueNum = issueNum;
		this.tzId = tzId;
		this.detailId = detailId;
		this.userId = userId;
		this.userName = userName;
		this.playType = playType;
		this.playMode = playMode;
		this.tzNum = tzNum;
		this.zjZhushu = zjZhushu;
		this.totalZjZhushu = totalZjZhushu;
		this.tzTimes = tzTimes;
		this.isZhuitou = isZhuitou;
		this.opTime = opTime;
		this.zjMoney = zjMoney;
		this.zjType = zjType;
		this.moneyOfZjType = moneyOfZjType;
	}

	public SscZjInfo(){
		
	}

	// Property accessors

	 

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIssueNum() {
		return this.issueNum;
	}

	public void setIssueNum(String issueNum) {
		this.issueNum = issueNum;
	}

	public String getTzId() {
		return this.tzId;
	}

	public void setTzId(String tzId) {
		this.tzId = tzId;
	}

	public String getDetailId() {
		return this.detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPlayType() {
		return this.playType;
	}

	public void setPlayType(String playType) {
		this.playType = playType;
	}

	public String getPlayMode() {
		return this.playMode;
	}

	public void setPlayMode(String playMode) {
		this.playMode = playMode;
	}

	public String getTzNum() {
		return this.tzNum;
	}

	public void setTzNum(String tzNum) {
		this.tzNum = tzNum;
	}

	public String getTzTimes() {
		return this.tzTimes;
	}

	public void setTzTimes(String tzTimes) {
		this.tzTimes = tzTimes;
	}

	public String getIsZhuitou() {
		return this.isZhuitou;
	}

	public void setIsZhuitou(String isZhuitou) {
		this.isZhuitou = isZhuitou;
	}

	public String getOpTime() {
		return this.opTime;
	}

	public void setOpTime(String opTime) {
		this.opTime = opTime;
	}

	public String getZjMoney() {
		return this.zjMoney;
	}

	public void setZjMoney(String zjMoney) {
		this.zjMoney = zjMoney;
	}

	public String getZjType() {
		return this.zjType;
	}

	public void setZjType(String zjType) {
		this.zjType = zjType;
	}

	public String getMoneyOfZjType() {
		return this.moneyOfZjType;
	}

	public void setMoneyOfZjType(String moneyOfZjType) {
		this.moneyOfZjType = moneyOfZjType;
	}

	public String getZjZhushu() {
		return zjZhushu;
	}

	public void setZjZhushu(String zjZhushu) {
		this.zjZhushu = zjZhushu;
	}

	public String getTotalZjZhushu() {
		return totalZjZhushu;
	}

	public void setTotalZjZhushu(String totalZjZhushu) {
		this.totalZjZhushu = totalZjZhushu;
	}

}
