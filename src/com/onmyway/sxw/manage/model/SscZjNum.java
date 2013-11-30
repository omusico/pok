package com.onmyway.sxw.manage.model;
/**
 * @Title:中奖号码信息，主要用于“多期投注的情况下，如果其中有一期中奖，那么中奖后的其余期数投注号码要自动删除，删除后用户余额与所投金额要相应增加与扣除。”
 * @Description: 
 * @Create on: Feb 10, 2011 6:10:15 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SscZjNum {
	private String userId;
	private String userName;
	private String issueNum;
	private String parentIssueNum;
	
	private String tzId;//投注id
	private String haveZhuitou;//是否有追投
	private String isZhuitou;//是否是追投
	private String parentId;//info表中的投注的父ID
	
	private boolean isFirstZj;//是否是当前期中奖，true：表示
	
	private String id;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIssueNum() {
		return issueNum;
	}

	public void setIssueNum(String issueNum) {
		this.issueNum = issueNum;
	}

	public String getParentIssueNum() {
		return parentIssueNum;
	}

	public void setParentIssueNum(String parentIssueNum) {
		this.parentIssueNum = parentIssueNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isFirstZj() {
		if(this.parentIssueNum.equals(this.issueNum)){
			this.isFirstZj = true;
		}else{
			this.isFirstZj = false;
		}
		return isFirstZj;
	}

	public void setFirstZj(boolean isFirstZj) {
		this.isFirstZj = isFirstZj;		
	}

	public String getTzId() {
		return tzId;
	}

	public void setTzId(String tzId) {
		this.tzId = tzId;
	}

	public String getHaveZhuitou() {
		return haveZhuitou;
	}

	public void setHaveZhuitou(String haveZhuitou) {
		this.haveZhuitou = haveZhuitou;
	}

	public String getIsZhuitou() {
		return isZhuitou;
	}

	public void setIsZhuitou(String isZhuitou) {
		this.isZhuitou = isZhuitou;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
}
