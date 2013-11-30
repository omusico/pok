package beanpac;

public class RemindInfo{
	
	private String loginUserMes="";
	private String loginManageMes="";
	private String wagerMes="";
	private String loginUserName="";
	private String userInfoMes="";
	private String loginUserId="";
	
	public String getLoginUserMes(){
		return(this.loginUserMes);
	}
	public void setLoginUserMes(String lum){
		this.loginUserMes=lum;
	}
	public String getLoginManageMes(){
		return(this.loginManageMes);
	}
	public void setLoginManageMes(String lmm){
		this.loginManageMes=lmm;
	}
	public String getWagerMes(){
		return(this.wagerMes);
	}
	public void setWagerMes(String wm){
		this.wagerMes=wm;
	}
	public String getLoginUserName(){
		return(this.loginUserName);
	}
	public void setLoginUserName(String lun){
		this.loginUserName=lun;
	}
	public String getUserInfoMes(){
		return(this.userInfoMes);
	}
	public void setUserInfoMes(String use){
		this.userInfoMes=use;
	}
	public String getLoginUserId() {
		return loginUserId;
	}
	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}
}
