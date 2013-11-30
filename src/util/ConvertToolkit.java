package util;

import com.onmyway.factory.ExbConstant;

/**
 * @Title:
 * @Description: 
 * @Create on: Nov 4, 2010 10:16:39 AM
 * @Author:LJY
 * @Version:1.0
 */
public class ConvertToolkit {

	/**
	 * 将时时彩的玩法模式由英文变为相应的英文
	 * @param playType
	 * @return
	 */
	public static String sscPlayTypeConvert(String playType,String typeName){
		if(playType == null){
			return null;
		}
		String chineseStr = "";
		if(playType.equals("yixing")){
			if(typeName.equals("zhixuan")){
				chineseStr = "一星直选";
			}else if(typeName.equals("yixingzhixuan")){
				chineseStr = "一星直选";
			}
		}else if(playType.equals("erxing")){
			if(typeName.equals("zhixuan")){
				chineseStr = "二星直选";
			}else if(typeName.equals("zuxuan")){
				chineseStr = "二星组选";
			}else if(typeName.equals("zuhe")){
				chineseStr = "二星组合";
			}else if(typeName.equals("erxingzuhe")){
				chineseStr = "二星组合";
			}else if(typeName.equals("zhixuandanshi")){
				chineseStr = "二星直选单式";
			}else if(typeName.equals("zhixuanfushi")){
				chineseStr = "二星直选复式";
			}else if(typeName.equals("zuxuanfushi")){
				chineseStr = "二星组选复式";
			}else if(typeName.equals("zuxuandanshi")){
				chineseStr = "二星组选单式";
			}else if(typeName.equals("baodan")){
				chineseStr = "二星组选包胆";
			}else if(typeName.equals("hezhi")){
				chineseStr = "二星组选和值";
			}
		}else if(playType.equals("sanxing")){
			if(typeName.equals("zhixuan")){
				chineseStr = "三星直选";
			}else if(typeName.equals("zuxuan")){
				chineseStr = "三星组选";
			}else if(typeName.equals("zuhe")){
				chineseStr = "三星组合";
			}else if(typeName.equals("sanxingzuhe")){
				chineseStr = "三星组合";
			}else if(typeName.equals("zhixuandanshi")){
				chineseStr = "三星直选单式";
			}else if(typeName.equals("zhixuanfushi")){
				chineseStr = "三星直选复式";
			}else if(typeName.equals("bao1dan")){
				chineseStr = "三星组选包1胆";
			}else if(typeName.equals("bao2dan")){
				chineseStr = "三星组选包2胆";
			}else if(typeName.equals("hezhi")){
				chineseStr = "三星组选和值";
			}
		}else if(playType.equals("sixing")){
			if(typeName.equals("zhixuan")){
				chineseStr = "四星直选";
			}else if(typeName.equals("zuxuan")){
				chineseStr = "四星组选";
			}else if(typeName.equals("zuhe")){
				chineseStr = "四星组合";
			}else if(typeName.equals("sixingzuhe")){
				chineseStr = "四星组合";
			}else if(typeName.equals("zhixuanfushi")){
				chineseStr = "四星直选复式";
			}
		}else if(playType.equals("wuxing")){
			if(typeName.equals("zhixuan")){
				chineseStr = "五星直选";
			}else if(typeName.equals("tx")){
				chineseStr = "五星通选";
			}else if(typeName.equals("zuhe")){
				chineseStr = "五星组合";
			}else if(typeName.equals("wuxingzuhe")){
				chineseStr = "五星组合";
			}else if(typeName.equals("zhixuanfushi")){
				chineseStr = "五星直选复式";
			}else if(typeName.equals("zuxuan")){
				chineseStr = "五星组选";
			}
			
		}else if(playType.equals("dxds")){
			chineseStr = "大小单双";
		}else if(playType.equals("renxuan1")){
			chineseStr = "任选1";
		}else if(playType.equals("renxuan2")){
			chineseStr = "任选2";
		}else if(playType.equals("renxuan3")){
			chineseStr = "任选3";
		}
		//如果为空，返回英文
		if(chineseStr.equals("")){
			chineseStr = playType + typeName;
		}
		return chineseStr;
	}
	
	/**
	 * 将时时彩的模式由英文变为相应的英文
	 * @param playType
	 * @return
	 */
	public static String sscPlayModeConvert(String playMode){
		if(playMode == null){
			return null;
		}
		String chineseStr = "";
		if(playMode.equals("fushi")){
			chineseStr = "复式";
		}else if(playMode.equals("danshi")){
			chineseStr = "单式";
		}else if(playMode.equals("zu3")){
			chineseStr = "组选3";
		}else if(playMode.equals("zu6")){
			chineseStr = "组选6";
		}else if(playMode.equals("zuhe")){
			chineseStr = "组合";
		}else if(playMode.equals("baodan")){
			chineseStr = "包胆";
		}else if(playMode.equals("hezhi")){
			chineseStr = "和值";
		}else if(playMode.equals("dxds")){
			chineseStr = "大小单双";
		}else if(playMode.equals("zhixuanfushi")){
			chineseStr = "直选复式";
		}else if(playMode.equals("zuxuanfushi")){
			chineseStr = "组选复式";
		}else if(playMode.equals("zhixuandanshi")){
			chineseStr = "直选单式";
		}else if(playMode.equals("zuxuandanshi")){
			chineseStr = "组选单式";
		}else if(playMode.equals("zuxuan")){
			chineseStr = "组选";
		}else if(playMode.equals("bao1dan")){
			chineseStr = "包一胆";
		}else if(playMode.equals("bao2dan")){
			chineseStr = "包二胆";
		}else if(playMode.equals("tx")){
			chineseStr = "通选";
		}else if(playMode.equals("zu120")){
			chineseStr = "组选一百二十";
		}else if(playMode.equals("zu60")){
			chineseStr = "组选六十";
		}else if(playMode.equals("zu30")){
			chineseStr = "组选三十";
		}else if(playMode.equals("zu20")){
			chineseStr = "组选二十";
		}else if(playMode.equals("zu10")){
			chineseStr = "组选十";
		}else if(playMode.equals("zu5")){
			chineseStr = "组选五";
		}else if(playMode.equals("zuchong2")){
			chineseStr = "组选二重号全包";
		}else if(playMode.equals("zuchong3")){
			chineseStr = "组选三重号全包";
		}else if(playMode.equals("zuchong4")){
			chineseStr = "组选四重号全包";
		}else if(playMode.equals("zu24")){
			chineseStr = "组选二十四";
		}else if(playMode.equals("zu12")){
			chineseStr = "组选十二";
		}else if(playMode.equals("zu6")){
			chineseStr = "组选六";
		}else if(playMode.equals("zu4")){
			chineseStr = "组选四";
		}else if(playMode.equals("renxuan1")){
			chineseStr = "任选1";
		}else if(playMode.equals("renxuan2")){
			chineseStr = "任选2";
		}else if(playMode.equals("renxuan3")){
			chineseStr = "任选3";
		}
		
		if(chineseStr.equals("")){
			chineseStr = playMode;
		}
		return chineseStr;
	}
	
	/**
	 * 将时时彩的玩法模式由英文变为相应的英文
	 * @param playType
	 * @return
	 */
	public static String sxwPlayTypeConvert(String playType ){
		if(playType == null){
			return null;
		}
		String chineseStr = "";
		if(playType.equals("renxuan1")){
			chineseStr = "任选一";
		}else if(playType.equals("renxuan2")){
			chineseStr = "任选二";
		}else if(playType.equals("renxuan3")){
			chineseStr = "任选三";
		}else if(playType.equals("renxuan4")){
			chineseStr = "任选四";
		}else if(playType.equals("renxuan5")){
			chineseStr = "任选五";
		}else if(playType.equals("renxuan6")){
			chineseStr = "任选六";
		}else if(playType.equals("renxuan7")){
			chineseStr = "任选七";
		}else if(playType.equals("renxuan8")){
			chineseStr = "任选八";
		}
		if(chineseStr.equals("")){
			chineseStr = playType;
		}
		return chineseStr;
	}
	/**
	 * 将时时彩的模式由英文变为相应的英文
	 * @param playType
	 * @return
	 */
	public static String sscZjRuleConvert(String rule){
		if(rule == null){
			return null;
		}
		String chineseStr = "";
		if(rule.equals("wuxingzuhe")){	chineseStr = "五星组合";		}
		if(rule.equals("sixingzuhe")){	chineseStr = "四星组合";	}
		if(rule.equals("sanxingzuhe")){	chineseStr = "三星组合";}
		if(rule.equals("erxingzuhe")){	chineseStr = "二星组合";}
		if(rule.equals("wuxingzuhe")){	chineseStr = "五星组合";	}
		if(rule.equals("danshi")){	chineseStr = "单式";	}
		 if(rule.equals("zu3")){chineseStr = "组选3";}
		 if(rule.equals("zu6")){chineseStr = "组选6";}
		 if(rule.equals("zuhe")){chineseStr = "组合";}
		 if(rule.equals("baodan")){	chineseStr = "包胆";	}
		 if(rule.equals("hezhi")){chineseStr = "和值";	}
		 if(rule.equals("dxds")){chineseStr = "大小单双";	}
		 if(rule.equals("zhixuanfushi")){chineseStr = "直选复式";}
		 if(rule.equals("zhixuandanshi")){chineseStr = "直选单式";}
		 if(rule.equals("zuxuanfushi")){chineseStr = "组选复式";}
		 if(rule.equals("zuxuandanshi")){chineseStr = "组选单式";}
		 if(rule.equals("zuxuan")){chineseStr = "组选";}
		 if(rule.equals("bao1dan")){chineseStr = "包一胆";}
		 if(rule.equals("bao2dan")){chineseStr = "包二胆";}
		 if(rule.equals("tx")){chineseStr = "通选";	}
		 if(rule.equals("yixingzhixuan")){chineseStr = "一星直选";}
		 if(rule.equals("erxingzhixuan")){chineseStr = "二星直选";}
		 if(rule.equals("sanxingzhixuan")){	chineseStr = "三星直选";}
		 if(rule.equals("sixingzhixuan")){chineseStr = "四星直选";}
		 if(rule.equals("wuxingzhixuan")){chineseStr = "五星直选";}
		
 
		 if(chineseStr.equals("")){
			chineseStr = rule;
		 }
		
		return chineseStr;
	}
	/**
	 * 将12x5的中奖类型由英文变为相应的英文
	 * @param zjType
	 * @return
	 */
	public static String sxwZjTypeConvert(String zjType){
		if(zjType == null){
			return null;
		}
		String chineseStr = "";
		if(zjType.equals("renxuan1")){
			chineseStr = "任选一";
		}else if(zjType.equals("renxuan2")){
			chineseStr = "任选二";
		}else if(zjType.equals("renxuan3")){
			chineseStr = "任选三";
		}else if(zjType.equals("renxuan4")){
			chineseStr = "任选四";
		}else if(zjType.equals("renxuan5")){
			chineseStr = "任选五";
		}else if(zjType.equals("renxuan5zhong4")){
			chineseStr = "任选五中四";
		}else if(zjType.equals("renxuan5zhong3")){
			chineseStr = "任选五中三";
		}else if(zjType.equals("renxuan5zhong2")){
			chineseStr = "任选五中二";
		}else if(zjType.equals("renxuan5zhong1")){
			chineseStr = "任选五中一";
		}else if(zjType.equals("renxuan2zhiqian2")){
			chineseStr = "直选前二";
		}else if(zjType.equals("renxuan2zuqian2")){
			chineseStr = "组选前二";
		}else if(zjType.equals("zuqian2dantuo")){
			chineseStr = "组选前二胆拖";
		}else if(zjType.equals("renxuan3zuqian3")){
			chineseStr = "组选前三";
		}else if(zjType.equals("zuqian3dantuo")){
			chineseStr = "组选前三胆拖";
		}else if(zjType.equals("renxuan3zhiqian3")){
			chineseStr = "直选前三";
		} else if(zjType.equals("renxuan6")){
			chineseStr = "任选六";
		}else if(zjType.equals("renxuan7")){
			chineseStr = "任选七";
		}else if(zjType.equals("renxuan8")){
			chineseStr = "任选八";
		}
		
 
		return chineseStr;
	}
	/**
	 * 将12x5的由英文变为相应的英文
	 * @param playType
	 * @return
	 */
	public static String sxwPlayModeConvert(String playMode){
		if(playMode == null){
			return null;
		}
		String chineseStr = "";
		if(playMode.equals("fushi")){
			chineseStr = "复式";
		}else if(playMode.equals("dantuo")){
			chineseStr = "胆拖";
		}else if(playMode.equals("zhiqian2")){
			chineseStr = "直选前二";
		}else if(playMode.equals("zuqian2")){
			chineseStr = "组选前二";
		}else if(playMode.equals("zuqian2dantuo")){
			chineseStr = "组选前二胆拖";
		}else if(playMode.equals("zuqian3")){
			chineseStr = "组选前三";
		}else if(playMode.equals("zuqian3dantuo")){
			chineseStr = "组选前三胆拖";
		}else if(playMode.equals("zhiqian3")){
			chineseStr = "直选前三";
		} else if(playMode.equals("renxuan6")){
			chineseStr = "任选六";
		}else if(playMode.equals("renxuan7")){
			chineseStr = "任选七";
		}else if(playMode.equals("renxuan8")){
			chineseStr = "任选八";
		}
		
		return chineseStr;
	}
	
	/**
	 * 将快乐十分的玩法类型由英文变为相应的英文
	 * @param playType
	 * @return
	 */
	public static String exbPlayTypeConvert(String playType){
		if(playType == null){
			return null;
		}
		String chineseStr = "";
		if(playType.equals(ExbConstant.PLAY_TYPE_X1)){
			chineseStr = "选一";
		}else if(playType.equals(ExbConstant.PLAY_TYPE_X2)){
			chineseStr = "选二";
		}else if(playType.equals(ExbConstant.PLAY_TYPE_X3)){
			chineseStr = "选三";
		}else if(playType.equals(ExbConstant.PLAY_TYPE_X4)){
			chineseStr = "选四";
		}else if(playType.equals(ExbConstant.PLAY_TYPE_X5)){
			chineseStr = "选五";
		}
		return chineseStr;
	}
	/**
	 * 将快乐十分的玩法模式由英文变为相应的英文
	 * @param playType
	 * @return
	 */
	public static String exbPlayModeConvert_odl(String playMode){
		if(playMode == null){
			return null;
		}
		String chineseStr = "";
		if(playMode.equals(ExbConstant.X1_MODE_SHUTOU)){
			chineseStr = "选一数投";
		}else if(playMode.equals(ExbConstant.X1_MODE_HONGTOU)){
			chineseStr = "选一红投";
		}else if(playMode.equals(ExbConstant.X2_MODE_RENXUAN)){
			chineseStr = "选二任选";
		}else if(playMode.equals(ExbConstant.X2_MODE_RENXUAN_DANTUO)){
			chineseStr = "选二任选胆拖";
		}else if(playMode.equals(ExbConstant.X2_MODE_LIANZHI)){
			chineseStr = "选二连直";
		}else if(playMode.equals(ExbConstant.X2_MODE_LIANZU)){
			chineseStr = "选二连组";
		}else if(playMode.equals(ExbConstant.X2_MODE_LIANZU_DANTUO)){
			chineseStr = "选二连组胆拖";
		}else if(playMode.equals(ExbConstant.X3_MODE_RENXUAN)){
			chineseStr = "选三任选";
		}else if(playMode.equals(ExbConstant.X3_MODE_RENXUAN_DANTUO)){
			chineseStr = "选三任选胆拖";
		}else if(playMode.equals(ExbConstant.X3_MODE_QIANZHI)){
			chineseStr = "选三前直";
		}else if(playMode.equals(ExbConstant.X3_MODE_QIANZU)){
			chineseStr = "选三前组";
		}else if(playMode.equals(ExbConstant.X3_MODE_QIANZU_DANTUO)){
			chineseStr = "选三前组胆拖";
		}else if(playMode.equals(ExbConstant.X4_MODE_RENXUAN)){
			chineseStr = "选四任选";
		}else if(playMode.equals(ExbConstant.X4_MODE_RENXUAN_DANTUO)){
			chineseStr = "选四任选胆拖";
		}else if(playMode.equals(ExbConstant.X5_MODE_RENXUAN)){
			chineseStr = "选五任选";
		}else if(playMode.equals(ExbConstant.X5_MODE_RENXUAN_DANTUO)){
			chineseStr = "选五任选胆拖";
		}

		return chineseStr;
	}
	/**
	 * 将快乐十分的玩法模式由英文变为相应的英文
	 * @param playType
	 * @return
	 */
	public static String exbPlayModeConvert(String playType,String playMode){
		if(playMode == null){
			return null;
		}
		String chineseStr = "";
		if(playType.equals(ExbConstant.PLAY_TYPE_X1)){
			if(playMode.equals(ExbConstant.X1_MODE_SHUTOU)){
				chineseStr = "选一数投";
			}else if(playMode.equals(ExbConstant.X1_MODE_HONGTOU)){
				chineseStr = "选一红投";
			}
		}else if(playType.equals(ExbConstant.PLAY_TYPE_X2)){
			if(playMode.equals(ExbConstant.X2_MODE_RENXUAN)){
				chineseStr = "选二任选";
			}else if(playMode.equals(ExbConstant.X2_MODE_RENXUAN_DANTUO)){
				chineseStr = "选二任选胆拖";
			}else if(playMode.equals(ExbConstant.X2_MODE_LIANZHI)){
				chineseStr = "选二连直";
			}else if(playMode.equals(ExbConstant.X2_MODE_LIANZU)){
				chineseStr = "选二连组";
			}else if(playMode.equals(ExbConstant.X2_MODE_LIANZU_DANTUO)){
				chineseStr = "选二连组胆拖";
			}
		}else if(playType.equals(ExbConstant.PLAY_TYPE_X3)){
			if(playMode.equals(ExbConstant.X3_MODE_RENXUAN)){
				chineseStr = "选三任选";
			}else if(playMode.equals(ExbConstant.X3_MODE_RENXUAN_DANTUO)){
				chineseStr = "选三任选胆拖";
			}else if(playMode.equals(ExbConstant.X3_MODE_QIANZHI)){
				chineseStr = "选三前直";
			}else if(playMode.equals(ExbConstant.X3_MODE_QIANZU)){
				chineseStr = "选三前组";
			}else if(playMode.equals(ExbConstant.X3_MODE_QIANZU_DANTUO)){
				chineseStr = "选三前组胆拖";
			}
		}else if(playType.equals(ExbConstant.PLAY_TYPE_X4)){
		
			if(playMode.equals(ExbConstant.X4_MODE_RENXUAN)){
				chineseStr = "选四任选";
			}else if(playMode.equals(ExbConstant.X4_MODE_RENXUAN_DANTUO)){
				chineseStr = "选四任选胆拖";
			}
		}else if(playType.equals(ExbConstant.PLAY_TYPE_X5)){
			 if(playMode.equals(ExbConstant.X5_MODE_RENXUAN)){
				chineseStr = "选五任选";
			}else if(playMode.equals(ExbConstant.X5_MODE_RENXUAN_DANTUO)){
				chineseStr = "选五任选胆拖";
			}
		}

		return chineseStr;
	}
	/**
	 * 将快乐十分的中奖模式由英文变为相应的英文
	 * @param playTypeexbZjTypeConvert
	 * @return
	 */
	public static String exbZjTypeConvert(String zjType){
		if(zjType == null){
			return null;
		}
		String chineseStr = "";
		if(zjType.equals(ExbConstant.X1_PRIZE_LIMIT_SHUTOU)){
			chineseStr = "选一数投";
		}else if(zjType.equals(ExbConstant.X1_PRIZE_LIMIT_HONGTOU)){
			chineseStr = "选一红投";
		}else if(zjType.equals(ExbConstant.X2_PRIZE_LIMIT_RENXUAN)){
			chineseStr = "选二任选";
		}else if(zjType.equals(ExbConstant.X2_PRIZE_LIMIT_LIANZHI)){
			chineseStr = "选二连直";
		}else if(zjType.equals(ExbConstant.X2_PRIZE_LIMIT_LIANZU)){
			chineseStr = "选二连组";
		}else if(zjType.equals(ExbConstant.X3_PRIZE_LIMIT_RENXUAN)){
			chineseStr = "选三任选";
		}else if(zjType.equals(ExbConstant.X3_PRIZE_LIMIT_QIANZHI)){
			chineseStr = "选三前直";
		}else if(zjType.equals(ExbConstant.X3_PRIZE_LIMIT_QIANZU)){
			chineseStr = "选三前组";
		}else if(zjType.equals(ExbConstant.X4_PRIZE_LIMIT_RENXUAN)){
			chineseStr = "选四任选";
		}else if(zjType.equals(ExbConstant.X5_PRIZE_LIMIT_RENXUAN)){
			chineseStr = "选五任选";
		}
		return chineseStr;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
