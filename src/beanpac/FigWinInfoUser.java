package beanpac;
import java.util.*;

public class FigWinInfoUser{
	
	private String username="";
	private String playType="";
	private String playMode="";
	private String times="";
	private Vector vecWinInfo=new Vector();

	public String getPlayMode() {
		return playMode;
	}
	public void setPlayMode(String playMode) {
		this.playMode = playMode;
	}
	public String getPlayType() {
		return playType;
	}
	public void setPlayType(String playType) {
		this.playType = playType;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Vector getVecWinInfo() {
		return vecWinInfo;
	}
	public void setVecWinInfo(Vector vecWinInfo) {
		this.vecWinInfo = vecWinInfo;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	
		

}
