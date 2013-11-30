package com.onmyway.exb.play.model;

import java.util.ArrayList;
import java.util.List;

import com.onmyway.ssc.play.model.SscTzDetail;

/**
 * ExbTzInfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ExbTzInfo implements java.io.Serializable {

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
	private Integer tzSeq;
	private String haveZtFlag;

	private String issueType;
	private String curTotalTzMoney;// 当前期投注的金额
	private String curTotalTzCount;// 当前期投注的数量
	private String totalTzMoney;// 总共投注的金额
	private String totalTzCount;// 总共投注的数量
	private String haveZhuitou;// 是否有追投
	private String zhuitouInfo;// 追投的信息
	private String isSell;//当期是否销售
	
	private List<ExbTzDetail> detailList = new ArrayList<ExbTzDetail>();//一对多关联；投注信息表（1...n)投注号码明细表

	// Constructors

	/** default constructor */
	public ExbTzInfo() {
	}

	/** full constructor */
	public ExbTzInfo(String userId, String userName, String issueNum,
			String playType, String typeName, String playMode,
			String touzhuNum, Integer touzhuCount, Integer touzhuTimes,
			Integer touzhuMoney, String isZhuitou, String parentIssueNum,
			String parentId, String isValid, String touzhuTime, Integer tzSeq,
			String haveZtFlag) {
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
		this.tzSeq = tzSeq;
		this.haveZtFlag = haveZtFlag;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
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

	public String getIsSell() {
		return isSell;
	}

	public void setIsSell(String isSell) {
		this.isSell = isSell;
	}

	public List<ExbTzDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<ExbTzDetail> detailList) {
		this.detailList = detailList;
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

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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

	public void setIsZhuitou(String isZhuitou) {
		this.isZhuitou = isZhuitou;
	}

	public String getParentIssueNum() {
		return this.parentIssueNum;
	}

	public void setParentIssueNum(String parentIssueNum) {
		this.parentIssueNum = parentIssueNum;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
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

	public Integer getTzSeq() {
		return this.tzSeq;
	}

	public void setTzSeq(Integer tzSeq) {
		this.tzSeq = tzSeq;
	}

	public String getHaveZtFlag() {
		return this.haveZtFlag;
	}

	public void setHaveZtFlag(String haveZtFlag) {
		this.haveZtFlag = haveZtFlag;
	}

}
