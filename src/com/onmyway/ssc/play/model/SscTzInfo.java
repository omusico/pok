package com.onmyway.ssc.play.model;

import java.util.ArrayList;
import java.util.List;

/**
 * SscTzInfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SscTzInfo implements java.io.Serializable {

	// Fields

	private String id;
	private String userId;
	private String userName;
	private String issueNum;
	private String playType;
	private String typeName;
	private String playMode;
	private String touzhuNum;
	private Integer touzhuCount;
	private Integer touzhuTimes;
	private Integer touzhuMoney;
	private String isZhuitou;
	private String parentIssueNum;
	private String parentId;
	private String isValid;
	private String touzhuTime;
	private String isZuhe;
	private String haveZtFlag;//数据库中的是否有追投的标志
	private Integer tzSeq;//保存同一个人在一期中多次投注的顺序

	private String issueType;
	private String curTotalTzMoney;// 当前期投注的金额
	private String curTotalTzCount;// 当前期投注的数量
	private String totalTzMoney;// 总共投注的金额
	private String totalTzCount;// 总共投注的数量
	private String haveZhuitou;// 是否有追投
	private String zhuitouInfo;// 追投的信息
	private String isSell;//当期是否销售
	
	private List<SscTzDetail> detailList = new ArrayList<SscTzDetail>();//一对多关联；投注信息表（1...n)投注号码明细表

	// Constructors
 

	public SscTzInfo(String id, String userId, String userName,
			String issueNum, String playType, String typeName, String playMode,
			String touzhuNum, Integer touzhuCount, Integer touzhuTimes,
			Integer touzhuMoney, String isZhuitou, String parentIssueNum,
			String parentId, String isValid, String touzhuTime, String isZuhe,
			String haveZtFlag, Integer tzSeq, String issueType,
			String curTotalTzMoney, String curTotalTzCount,
			String totalTzMoney, String totalTzCount, String haveZhuitou,
			String zhuitouInfo, String isSell, List<SscTzDetail> detailList) {
		super();
		this.id = id;
		this.userId = userId;
		this.userName = userName;
		this.issueNum = issueNum;
		this.playType = playType;
		this.typeName = typeName;
		this.playMode = playMode;
		this.touzhuNum = touzhuNum;
		this.touzhuCount = touzhuCount;
		this.touzhuTimes = touzhuTimes;
		this.touzhuMoney = touzhuMoney;
		this.isZhuitou = isZhuitou;
		this.parentIssueNum = parentIssueNum;
		this.parentId = parentId;
		this.isValid = isValid;
		this.touzhuTime = touzhuTime;
		this.isZuhe = isZuhe;
		this.haveZtFlag = haveZtFlag;
		this.tzSeq = tzSeq;
		this.issueType = issueType;
		this.curTotalTzMoney = curTotalTzMoney;
		this.curTotalTzCount = curTotalTzCount;
		this.totalTzMoney = totalTzMoney;
		this.totalTzCount = totalTzCount;
		this.haveZhuitou = haveZhuitou;
		this.zhuitouInfo = zhuitouInfo;
		this.isSell = isSell;
		this.detailList = detailList;
	}

	public List<SscTzDetail> getDetailList() {
		return detailList;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public void setDetailList(List<SscTzDetail> detailList) {
		this.detailList = detailList;
	}

	public String getIsSell() {
		return isSell;
	}

	public void setIsSell(String isSell) {
		this.isSell = isSell;
	}

	/** default constructor */
	public SscTzInfo() {
	}

	// Property accessors



	

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getIssueNum() {
		return this.issueNum;
	}

	public void setIssueNum(String issueNum) {
		this.issueNum = issueNum;
	}

	public String getPlayType() {
		return this.playType;
	}

	public void setPlayType(String playType) {
		this.playType = playType;
	}

	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	public String getPlayMode() {
		return this.playMode;
	}

	public void setPlayMode(String playMode) {
		this.playMode = playMode;
	}

	public String getTouzhuNum() {
		return this.touzhuNum;
	}

	public void setTouzhuNum(String touzhuNum) {
		this.touzhuNum = touzhuNum;
	}

	public Integer getTouzhuCount() {
		return this.touzhuCount;
	}

	public void setTouzhuCount(Integer touzhuCount) {
		this.touzhuCount = touzhuCount;
	}

	public Integer getTouzhuTimes() {
		return this.touzhuTimes;
	}

	public void setTouzhuTimes(Integer touzhuTimes) {
		this.touzhuTimes = touzhuTimes;
	}

	public Integer getTouzhuMoney() {
		return this.touzhuMoney;
	}

	public void setTouzhuMoney(Integer touzhuMoney) {
		this.touzhuMoney = touzhuMoney;
	}

	public String getIsZhuitou() {
		return this.isZhuitou;
	}

	public void setIsZhuitou(String isZhuijia) {
		this.isZhuitou = isZhuijia;
	}

	public String getParentIssueNum() {
		return this.parentIssueNum;
	}

	public void setParentIssueNum(String parentId) {
		this.parentIssueNum = parentId;
	}

	public String getIsValid() {
		return this.isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getTouzhuTime() {
		return this.touzhuTime;
	}

	public void setTouzhuTime(String touzhuTime) {
		this.touzhuTime = touzhuTime;
	}

	public String getIsZuhe() {
		return this.isZuhe;
	}

	public void setIsZuhe(String isZuhe) {
		this.isZuhe = isZuhe;
	}

	public String getCurTotalTzMoney() {
		return curTotalTzMoney;
	}

	public void setCurTotalTzMoney(String curTotalTzMoney) {
		this.curTotalTzMoney = curTotalTzMoney;
	}

	public String getCurTotalTzCount() {
		return curTotalTzCount;
	}

	public void setCurTotalTzCount(String curTotalTzCount) {
		this.curTotalTzCount = curTotalTzCount;
	}

	public String getTotalTzMoney() {
		return totalTzMoney;
	}

	public void setTotalTzMoney(String totalTzMoney) {
		this.totalTzMoney = totalTzMoney;
	}

	public String getTotalTzCount() {
		return totalTzCount;
	}

	public void setTotalTzCount(String totalTzCount) {
		this.totalTzCount = totalTzCount;
	}

	public String getHaveZhuitou() {
		return haveZhuitou;
	}

	public void setHaveZhuitou(String haveZhuitou) {
		this.haveZhuitou = haveZhuitou;
	}

	public String getZhuitouInfo() {
		return zhuitouInfo;
	}

	public void setZhuitouInfo(String zhuitouInfo) {
		this.zhuitouInfo = zhuitouInfo;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getHaveZtFlag() {
		return haveZtFlag;
	}

	public void setHaveZtFlag(String haveZtFlag) {
		this.haveZtFlag = haveZtFlag;
	}

	public Integer getTzSeq() {
		return tzSeq;
	}

	public void setTzSeq(Integer tzSeq) {
		this.tzSeq = tzSeq;
	}
}
