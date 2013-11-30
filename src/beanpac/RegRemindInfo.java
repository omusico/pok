package beanpac;

public class RegRemindInfo{
	
	private String userName="";
	private String passWord="";
	private String confirmPass="";
	private String realName="";
	private String idNum="";
	private String email="";
	private String conPhone="";
	private String cellPhone="";
	private String sucMes="";
	private String valCode="";


	
	public String getUserName(){
		return(userName);
	}
	public void setUserName(String un){
		this.userName=un;
	}
	public String getPassWord(){
		return(passWord);
	}
	public void setPassWord(String pas){
		this.passWord=pas;
	}
	public String getConfirmPass(){
		return(confirmPass);
	}
	public void setConfirmPass(String con){
		this.confirmPass=con;
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
	public String getSucMes() {
		return (sucMes);
	}
	public void setSucMes(String sucMes) {
		this.sucMes = sucMes;
	}
	public String getValCode() {
		return valCode;
	}
	public void setValCode(String valCode) {
		this.valCode = valCode;
	}


}
