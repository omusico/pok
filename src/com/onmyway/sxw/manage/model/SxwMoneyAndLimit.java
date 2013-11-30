package com.onmyway.sxw.manage.model;

/**
 * SscMoneyAndLimit entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SxwMoneyAndLimit implements java.io.Serializable {

	// Fields

	private String id;
	private String playType;
	private String moduleType;
	private String rule;
	private String money;
	private String limitOneIssue;
	private String isValid;
	private String operTime;

	// Constructors

	public SxwMoneyAndLimit(String id, String playType, String moduleType,
			String rule, String money, String limitOneIssue, String isValid,
			String operTime) {
		super();
		this.id = id;
		this.playType = playType;
		this.moduleType = moduleType;
		this.rule = rule;
		this.money = money;
		this.limitOneIssue = limitOneIssue;
		this.isValid = isValid;
		this.operTime = operTime;
	}

	/** default constructor */
	public SxwMoneyAndLimit() {
	}

	/** full constructor */


	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlayType() {
		return this.playType;
	}

	public void setPlayType(String playType) {
		this.playType = playType;
	}

	public String getModuleType() {
		return this.moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public String getRule() {
		return this.rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getMoney() {
		return this.money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getLimitOneIssue() {
		return this.limitOneIssue;
	}

	public void setLimitOneIssue(String limitOneIssue) {
		this.limitOneIssue = limitOneIssue;
	}

	public String getIsValid() {
		return this.isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getOperTime() {
		return operTime;
	}

	public void setOperTime(String operTime) {
		this.operTime = operTime;
	}

}
