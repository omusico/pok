package com.onmyway.exb.utils;

import java.util.Map;

import com.onmyway.factory.ExbConstant;
import com.onmyway.init.ExbLimitInfoInit;

/**
 * @Title:
 * @Description: 
 * @Create on: Jan 8, 2012 6:15:20 PM
 * @Author:LJY
 * @Version:1.0
 */
public class ExbTools {
	private static Map<String,Integer> limitMap = ExbLimitInfoInit.getExbLimitMap();
	private static Map<String,Integer> prizeMap = ExbLimitInfoInit.getExbPrizeMap();
	/**
	 * 得到相应玩法及类型的开奖号码
	 * @param playType
	 * @param playMode
	 * @return
	 */
	public static int getExbConfigTime(String playType,String playMode){
		int times = 0;
		//任选1
		if(playType.equals(ExbConstant.PLAY_TYPE_X1)){//选一数投
			if(playMode.equals(ExbConstant.X1_MODE_SHUTOU)){
				times = limitMap.get(ExbConstant.X1_PRIZE_LIMIT_SHUTOU);
			}else if(playMode.equals(ExbConstant.X1_MODE_HONGTOU)){
				times = limitMap.get(ExbConstant.X1_PRIZE_LIMIT_HONGTOU);
			}
		}
		//任选2
		if(playType.equals(ExbConstant.PLAY_TYPE_X2)){
			if(playMode.equals(ExbConstant.X2_MODE_RENXUAN) || playMode.equals(ExbConstant.X2_MODE_RENXUAN_DANTUO)){
				times = limitMap.get(ExbConstant.X2_PRIZE_LIMIT_RENXUAN);
			}else if(playMode.equals(ExbConstant.X2_MODE_LIANZHI)){
				times = limitMap.get(ExbConstant.X2_PRIZE_LIMIT_LIANZHI);
			}else if(playMode.equals(ExbConstant.X2_MODE_LIANZU) || playMode.equals(ExbConstant.X2_MODE_LIANZU_DANTUO)){
				times = limitMap.get(ExbConstant.X2_PRIZE_LIMIT_LIANZU);
			}
		}
		//任选3
		if(playType.equals(ExbConstant.PLAY_TYPE_X3)){
			if(playMode.equals(ExbConstant.X3_MODE_RENXUAN) || playMode.equals(ExbConstant.X3_MODE_RENXUAN_DANTUO)){
				times = limitMap.get(ExbConstant.X3_PRIZE_LIMIT_RENXUAN);
			}else if(playMode.equals(ExbConstant.X3_MODE_QIANZHI)){
				times = limitMap.get(ExbConstant.X3_PRIZE_LIMIT_QIANZHI);
			}else if(playMode.equals(ExbConstant.X3_MODE_QIANZU) || playMode.equals(ExbConstant.X3_MODE_QIANZU_DANTUO)){
				times = limitMap.get(ExbConstant.X3_PRIZE_LIMIT_QIANZU);
			}
		}

		//任选4
		if(playType.equals(ExbConstant.PLAY_TYPE_X4)){
			if(playMode.equals(ExbConstant.X4_MODE_RENXUAN) || playMode.equals(ExbConstant.X4_MODE_RENXUAN_DANTUO)){
				times = limitMap.get(ExbConstant.X4_PRIZE_LIMIT_RENXUAN);
			}
		}
		//任选5
		if(playType.equals(ExbConstant.PLAY_TYPE_X5)){
			if(playMode.equals(ExbConstant.X5_MODE_RENXUAN) || playMode.equals(ExbConstant.X5_MODE_RENXUAN_DANTUO)){
				times = limitMap.get(ExbConstant.X5_PRIZE_LIMIT_RENXUAN);
			}
		}
		return times;
	}
	
	/**
	 * 得到相应玩法及类型的开奖号码
	 * @param playType
	 * @param playMode
	 * @return
	 */
	public static int getExbConfigPrize(String playType,String playMode){
		int prizeMoney = 0;
		//任选1
		if(playType.equals(ExbConstant.PLAY_TYPE_X1)){//选一数投
			if(playMode.equals(ExbConstant.X1_MODE_SHUTOU)){
				prizeMoney = prizeMap.get(ExbConstant.X1_PRIZE_LIMIT_SHUTOU);
			}else if(playMode.equals(ExbConstant.X1_MODE_HONGTOU)){
				prizeMoney = prizeMap.get(ExbConstant.X1_PRIZE_LIMIT_HONGTOU);
			}
		}
		//任选2
		if(playType.equals(ExbConstant.PLAY_TYPE_X2)){
			if(playMode.equals(ExbConstant.X2_MODE_RENXUAN) || playMode.equals(ExbConstant.X2_MODE_RENXUAN_DANTUO)){
				prizeMoney = prizeMap.get(ExbConstant.X2_PRIZE_LIMIT_RENXUAN);
			}else if(playMode.equals(ExbConstant.X2_MODE_LIANZHI)){
				prizeMoney = prizeMap.get(ExbConstant.X2_PRIZE_LIMIT_LIANZHI);
			}else if(playMode.equals(ExbConstant.X2_MODE_LIANZU) || playMode.equals(ExbConstant.X2_MODE_LIANZU_DANTUO)){
				prizeMoney = prizeMap.get(ExbConstant.X2_PRIZE_LIMIT_LIANZU);
			}
		}
		//任选3
		if(playType.equals(ExbConstant.PLAY_TYPE_X3)){
			if(playMode.equals(ExbConstant.X3_MODE_RENXUAN) || playMode.equals(ExbConstant.X3_MODE_RENXUAN_DANTUO)){
				prizeMoney = prizeMap.get(ExbConstant.X3_PRIZE_LIMIT_RENXUAN);
			}else if(playMode.equals(ExbConstant.X3_MODE_QIANZHI)){
				prizeMoney = prizeMap.get(ExbConstant.X3_PRIZE_LIMIT_QIANZHI);
			}else if(playMode.equals(ExbConstant.X3_MODE_QIANZU) || playMode.equals(ExbConstant.X3_MODE_QIANZU_DANTUO)){
				prizeMoney = prizeMap.get(ExbConstant.X3_PRIZE_LIMIT_QIANZU);
			}
		}

		//任选4
		if(playType.equals(ExbConstant.PLAY_TYPE_X4)){
			if(playMode.equals(ExbConstant.X4_MODE_RENXUAN) || playMode.equals(ExbConstant.X4_MODE_RENXUAN_DANTUO)){
				prizeMoney = prizeMap.get(ExbConstant.X4_PRIZE_LIMIT_RENXUAN);
			}
		}
		//任选5
		if(playType.equals(ExbConstant.PLAY_TYPE_X5)){
			if(playMode.equals(ExbConstant.X5_MODE_RENXUAN) || playMode.equals(ExbConstant.X5_MODE_RENXUAN_DANTUO)){
				prizeMoney = prizeMap.get(ExbConstant.X5_PRIZE_LIMIT_RENXUAN);
			}
		}
		return prizeMoney;
	}
}
