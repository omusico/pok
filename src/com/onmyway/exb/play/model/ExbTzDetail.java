package com.onmyway.exb.play.model;

/**
 * ExbTzDetail entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ExbTzDetail implements java.io.Serializable {

	// Fields

	private String id;
	private String PId;
	private String issueNum;
	private String playType;
	private String typeName;
	private String playMode;
	private String numDetail;
	private String numTimes;
	private String isZhuitou;

	// Constructors

	/** default constructor */
	public ExbTzDetail() {
	}

	/** full constructor */
	public ExbTzDetail(String PId, String issueNum, String playType,
			String typeName, String playMode, String numDetail,
			String numTimes, String isZhuitou) {
		this.PId = PId;
		this.issueNum = issueNum;
		this.playType = playType;
		this.typeName = typeName;
		this.playMode = playMode;
		this.numDetail = numDetail;
		this.numTimes = numTimes;
		this.isZhuitou = isZhuitou;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPId() {
		return this.PId;
	}

	public void setPId(String PId) {
		this.PId = PId;
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

	public String getNumDetail() {
		return this.numDetail;
	}

	public void setNumDetail(String numDetail) {
		this.numDetail = numDetail;
	}

	public String getNumTimes() {
		return this.numTimes;
	}

	public void setNumTimes(String numTimes) {
		this.numTimes = numTimes;
	}

	public String getIsZhuitou() {
		return this.isZhuitou;
	}

	public void setIsZhuitou(String isZhuitou) {
		this.isZhuitou = isZhuitou;
	}

}
