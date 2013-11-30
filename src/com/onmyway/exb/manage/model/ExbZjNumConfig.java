package com.onmyway.exb.manage.model;

/**
 * ExbZjNumConfig entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ExbZjNumConfig implements java.io.Serializable {

	// Fields

	private String id;
	private String issueDate;
	private String issueNum;
	private String position1;
	private String position2;
	private String position3;
	private String position4;
	private String position5;
	private String position6;
	private String position7;
	private String position8;
	private String operTime;

    private String isResetIssueInfo;////是否重新设置中奖号码 1：重新设置；0：不重新设置 只在页面上有用

	// Constructors

	/** default constructor */
	public ExbZjNumConfig() {
	}

	/** full constructor */
	public ExbZjNumConfig(String issueDate, String issueNum, String position1,
			String position2, String position3, String position4,
			String position5, String position6, String position7,
			String position8, String operTime) {
		this.issueDate = issueDate;
		this.issueNum = issueNum;
		this.position1 = position1;
		this.position2 = position2;
		this.position3 = position3;
		this.position4 = position4;
		this.position5 = position5;
		this.position6 = position6;
		this.position7 = position7;
		this.position8 = position8;
		this.operTime = operTime;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIsResetIssueInfo() {
		return isResetIssueInfo;
	}

	public void setIsResetIssueInfo(String isResetIssueInfo) {
		this.isResetIssueInfo = isResetIssueInfo;
	}

	public String getIssueDate() {
		return this.issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getIssueNum() {
		return this.issueNum;
	}

	public void setIssueNum(String issueNum) {
		this.issueNum = issueNum;
	}

	public String getPosition1() {
		return this.position1;
	}

	public void setPosition1(String position1) {
		this.position1 = position1;
	}

	public String getPosition2() {
		return this.position2;
	}

	public void setPosition2(String position2) {
		this.position2 = position2;
	}

	public String getPosition3() {
		return this.position3;
	}

	public void setPosition3(String position3) {
		this.position3 = position3;
	}

	public String getPosition4() {
		return this.position4;
	}

	public void setPosition4(String position4) {
		this.position4 = position4;
	}

	public String getPosition5() {
		return this.position5;
	}

	public void setPosition5(String position5) {
		this.position5 = position5;
	}

	public String getPosition6() {
		return this.position6;
	}

	public void setPosition6(String position6) {
		this.position6 = position6;
	}

	public String getPosition7() {
		return this.position7;
	}

	public void setPosition7(String position7) {
		this.position7 = position7;
	}

	public String getPosition8() {
		return this.position8;
	}

	public void setPosition8(String position8) {
		this.position8 = position8;
	}

	public String getOperTime() {
		return this.operTime;
	}

	public void setOperTime(String operTime) {
		this.operTime = operTime;
	}
	  /**
     * 通过名称得到相应的值
     * @param textName
     * @return
     */
    public String getValueByText(String textName){
    	String str = "";
    	if(textName.equals("position1")){
    		str = getPosition1();
    	}else if(textName.equals("position2")){
    		str = getPosition2();
    	}else if(textName.equals("position3")){
    		str = getPosition3();
    	}else if(textName.equals("position4")){
    		str = getPosition4();
    	}else if(textName.equals("position5")){
    		str = getPosition5();
    	}else if(textName.equals("position6")){
    		str = getPosition6();
    	}else if(textName.equals("position7")){
    		str = getPosition7();
    	}else if(textName.equals("position8")){
    		str = getPosition8();
    	}
    	return str;
    }
}
