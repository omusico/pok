package com.onmyway.ssc.play.model;

/**
 * SscTzDetail entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SscTzDetail implements java.io.Serializable {

	// Fields

	private String id;
	private String PId;
	private String issueNum;
	private String playType;
	private String typeName;
	private String playMode;
	private String numDetail;
	private String numTimes;
	private String isZuhe;
	private String isZhuitou;
	private String infoIndex;//一对多时的关联
	
	
	private SscTzInfo tzInfo;

	// Constructors

	/** default constructor */
	public SscTzDetail() {
	}

 

	// Property accessors

	public SscTzDetail(String id, String id2, String issueNum, String playType,
			String typeName, String playMode, String numDetail,
			String numTimes, String isZuhe, String isZhuitou, String infoIndex) {
		super();
		this.id = id;
		PId = id2;
		this.issueNum = issueNum;
		this.playType = playType;
		this.typeName = typeName;
		this.playMode = playMode;
		this.numDetail = numDetail;
		this.numTimes = numTimes;
		this.isZuhe = isZuhe;
		this.isZhuitou = isZhuitou;
		this.infoIndex = infoIndex;
	}



	public String getId() {
		return this.id;
	}


	public void setId(String id) {
		this.id = id;
	}
	public String getInfoIndex() {
		return infoIndex;
	}

	public void setInfoIndex(String infoIndex) {
		this.infoIndex = infoIndex;
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

	public String getIsZuhe() {
		return this.isZuhe;
	}

	public void setIsZuhe(String isZuhe) {
		this.isZuhe = isZuhe;
	}

	public String getIsZhuitou() {
		return this.isZhuitou;
	}

	public void setIsZhuitou(String isZhuitou) {
		this.isZhuitou = isZhuitou;
	}

	public SscTzInfo getTzInfo() {
		return tzInfo;
	}

	public void setTzInfo(SscTzInfo tzInfo) {
		this.tzInfo = tzInfo;
	}



	public String getTypeName() {
		return typeName;
	}



	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
