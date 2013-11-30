package beanpac;

public class UserInfo{
	
	private String userName="";
	private String realName="";
	private String idNum="";
	private String email="";
	private String conPhone="";
	private String cellPhone="";
	private String address="";
	private String msn="";
	private String qq="";
	
	public String getUserName(){
		return(userName);
	}
	public void setUserName(String un){
		this.userName=un;
	}
	
	public String getRealName(){
		return(realName);
	}
	public void setRealName(String rn){
		this.realName=rn;
	}
	public String getIdNum(){
		return(idNum);
	}
	public void setIdNum(String id){
		this.idNum=id;
	}
	public String getEmail(){
		return(email);
	}
	public void setEmail(String em){
		this.email=em;
	}
	public String getConPhone(){
		return(conPhone);
	}
	public void setConPhone(String cp){
		this.conPhone=cp;
	}
	public String getCellPhone(){
		return(cellPhone);
	}
	public void setCellPhone(String cellp){
		this.cellPhone=cellp;
	}
	public String getAddress(){
		return(address);
	}
	public void setAddress(String add){
		this.address=add;
	}
	public String getMsn(){
		return(msn);
	}
	public void setMsn(String ms){
		this.msn=ms;
	}
	
	public String getQq(){
		return(qq);
	}
	public void setQq(String qqnum){
		this.qq=qqnum;
	}

}
