package com.onmyway.ssc.play.dao;

import java.sql.Connection;
import java.util.List;

import com.onmyway.common.dao.GenericEntityDAO;
import com.onmyway.common.exception.DAOException;
import com.onmyway.ssc.play.model.SscTzDetail;

import dbconnpac.DBAccess;
import dbconnpac.DBTool;
import dbconnpac.ProxoolPoolManager;

/**
 * @Title:时时彩-投注信息管理
 * @Description: 保存-判断-验证等
 * @Create on: Aug 20, 2010 5:42:43 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SscTzDetailDaoImpl extends GenericEntityDAO<SscTzDetail, String>
		implements ISscTzDetailDao {
	DBTool dbTool = DBAccess.getDBTool();
	ProxoolPoolManager poolManager =  new ProxoolPoolManager();


	/**
	 * 判断是否超出限号
	 * @param playType
	 * @param playMode
	 * @param issueNum
	 * @param times
	 * @return
	 */
//	public boolean isInLimit(String playType,String playMode,String issueNum,String times){
//		
//	}
	/**
	 * 查询某种玩法已经投注的情况
	 * @param playType
	 * @param playMode
	 * @param issueNum
	 * @return
	 */
	public int getCountOfNum(String playType,String typeName,String playMode,String issueNum,String playNum) throws DAOException{
		//String sql = "select count(*) from ssc_money_and_limit t where t.play_type='" + playType + "' and t.issue_num='" + issueNum + "'";
		String hsql = "from SscTzDetail t where t.playType='" + playType + "' and t.issueNum='" + issueNum + "' and t.numDetail like '%" + playNum + ",%'";
		List<SscTzDetail> list = getList(hsql);
		int count = 0;
		int totalTimes = 0;
		if(list != null && !list.isEmpty()){
			int listSize = list.size();
			for(SscTzDetail info : list ){
				String times = info.getNumTimes();
				try{
					totalTimes += Integer.parseInt(times);
				}catch(NumberFormatException e){
					logger.error("查询已投注倍数错误：" + e.toString());
					totalTimes += 1;
				}
			}
			//count = ((Integer)list.get(0)).intValue();
		}
		return totalTimes;
	}
	
	
	
	/**
	 * 从连接池中得到连接
	 * 
	 * @param dbSource
	 * @return
	 */
	private Connection getConnection(String dbSource) {
		try {
			return poolManager.getConnection(dbSource);
		} catch (Exception ex) {
			logger.error("获取数据库连接异常:" + ex.toString());
			ex.printStackTrace();
			return null;
		}
	}

	public void close(Connection conn) {
		try {
			if (!(conn == null || conn.isClosed())) {
				conn.close();
			}
		} catch (Exception ex) {
			logger.error("连接关闭异常: "  + ex.toString());
			ex.printStackTrace();
		}
	}
}
