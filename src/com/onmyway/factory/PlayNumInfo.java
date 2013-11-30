package com.onmyway.factory;
/**
 * @Title:对每注算法进包装
 * @Description: 
 * @Create on: Sep 15, 2010 10:13:26 PM
 * @Author:LJY
 * @Version:1.0
 */
public class PlayNumInfo {

	private String gameType;//游戏类型
	private String playType;//必填
	private String typeName;//玩法小项 必填
	private String playMode;//毕填
	private String playNum;//必填
	private String baodanType;
	private String isHezhi;
	private String isZuhe;
	
	 
	
	public PlayNumInfo(String gameType, String playType, String typeName,
			String playMode, String playNum, String baodanType, String isHezhi,
			String isZuhe) {
		super();
		this.gameType = gameType;
		this.playType = playType;
		this.typeName = typeName;
		this.playMode = playMode;
		this.playNum = playNum;
		this.baodanType = baodanType;
		this.isHezhi = isHezhi;
		this.isZuhe = isZuhe;
	}
	public PlayNumInfo(String gameType, String playType, 
			String playMode, String playNum) {
		super();
		this.gameType = gameType;
		this.playType = playType;
		this.playMode = playMode;
		this.playNum = playNum;
	}
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getPlayNum() {
		return playNum;
	}

	public void setPlayNum(String playNum) {
		this.playNum = playNum;
	}

	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	public String getPlayType() {
		return playType;
	}
	public void setPlayType(String playType) {
		this.playType = playType;
	}
	public String getPlayMode() {
		return playMode;
	}
	public void setPlayMode(String playMode) {
		this.playMode = playMode;
	}
	public String getBaodanType() {
		return baodanType;
	}
	public void setBaodanType(String baodanType) {
		this.baodanType = baodanType;
	}
	public String getIsHezhi() {
		return isHezhi;
	}
	public void setIsHezhi(String isHezhi) {
		this.isHezhi = isHezhi;
	}
	public String getIsZuhe() {
		return isZuhe;
	}
	public void setIsZuhe(String isZuhe) {
		this.isZuhe = isZuhe;
	}
	
}
