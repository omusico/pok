package com.onmyway.sxw.manage.model;



/**
 * SscZjNumConfig entity. @author MyEclipse Persistence Tools
 */

public class SxwZjNumConfig  implements java.io.Serializable {


    // Fields    

     private String id;
     private String issueDate;
     private String issueNum;
     private String wan;
     private String qian;
     private String bai;
     private String shi;
     private String ge;
     private String operTime;
     private String isResetIssueInfo;////是否重新设置中奖号码 1：重新设置；0：不重新设置 只在页面上有用

    // Constructors

   


	public SxwZjNumConfig(String id, String issueDate, String issueNum,
			String wan, String qian, String bai, String shi, String ge,
			String operTime, String isResetIssueInfo) {
		super();
		this.id = id;
		this.issueDate = issueDate;
		this.issueNum = issueNum;
		this.wan = wan;
		this.qian = qian;
		this.bai = bai;
		this.shi = shi;
		this.ge = ge;
		this.operTime = operTime;
		this.isResetIssueInfo = isResetIssueInfo;
	}


	/** default constructor */
    public SxwZjNumConfig() {
    }

    
    /** full constructor */
     

   
    // Property accessors
    public String getIsResetIssueInfo() {
		return isResetIssueInfo;
	}


	public void setIsResetIssueInfo(String isResetIssueInfo) {
		this.isResetIssueInfo = isResetIssueInfo;
	}
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
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

    public String getWan() {
        return this.wan;
    }
    
    public void setWan(String wan) {
        this.wan = wan;
    }

    public String getQian() {
        return this.qian;
    }
    
    public void setQian(String qian) {
        this.qian = qian;
    }

    public String getBai() {
        return this.bai;
    }
    
    public void setBai(String bai) {
        this.bai = bai;
    }

    public String getShi() {
        return this.shi;
    }
    
    public void setShi(String shi) {
        this.shi = shi;
    }

    public String getGe() {
        return this.ge;
    }
    
    public void setGe(String ge) {
        this.ge = ge;
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
    	if(textName.equals("wan")){
    		str = getWan();
    	}else if(textName.equals("qian")){
    		str = getQian();
    	}else if(textName.equals("bai")){
    		str = getBai();
    	}else if(textName.equals("shi")){
    		str = getShi();
    	}else if(textName.equals("ge")){
    		str = getGe();
    	}
    	return str;
    }







}
