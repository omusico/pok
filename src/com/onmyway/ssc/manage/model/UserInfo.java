package com.onmyway.ssc.manage.model;

/**
 * Lotteryuser entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class UserInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String username;
	private String password;
	private String confirm;
	private String sex;
	private String realname;
	private String idcardnumber;
	private String email;
	private String contactphone;
	private String mobilephone;
	private String address;
	private String msnnum;
	private String qqnum;
	private String totalmoney;
	private String drawstate;
	private String freeze;

	// Constructors

	/** default constructor */
	public UserInfo() {
	}

	/** full constructor */
	public UserInfo(String username, String password, String confirm,
			String sex, String realname, String idcardnumber, String email,
			String contactphone, String mobilephone, String address,
			String msnnum, String qqnum, String totalmoney, String drawstate,
			String freeze) {
		this.username = username;
		this.password = password;
		this.confirm = confirm;
		this.sex = sex;
		this.realname = realname;
		this.idcardnumber = idcardnumber;
		this.email = email;
		this.contactphone = contactphone;
		this.mobilephone = mobilephone;
		this.address = address;
		this.msnnum = msnnum;
		this.qqnum = qqnum;
		this.totalmoney = totalmoney;
		this.drawstate = drawstate;
		this.freeze = freeze;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return this.confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getIdcardnumber() {
		return this.idcardnumber;
	}

	public void setIdcardnumber(String idcardnumber) {
		this.idcardnumber = idcardnumber;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactphone() {
		return this.contactphone;
	}

	public void setContactphone(String contactphone) {
		this.contactphone = contactphone;
	}

	public String getMobilephone() {
		return this.mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMsnnum() {
		return this.msnnum;
	}

	public void setMsnnum(String msnnum) {
		this.msnnum = msnnum;
	}

	public String getQqnum() {
		return this.qqnum;
	}

	public void setQqnum(String qqnum) {
		this.qqnum = qqnum;
	}

	public String getTotalmoney() {
		return this.totalmoney;
	}

	public void setTotalmoney(String totalmoney) {
		this.totalmoney = totalmoney;
	}

	public String getDrawstate() {
		return this.drawstate;
	}

	public void setDrawstate(String drawstate) {
		this.drawstate = drawstate;
	}

	public String getFreeze() {
		return this.freeze;
	}

	public void setFreeze(String freeze) {
		this.freeze = freeze;
	}

}
