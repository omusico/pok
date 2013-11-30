package com.onmyway.sxw.manage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.onmyway.common.dao.GenericEntityDAO;
import com.onmyway.common.exception.AppException;
import com.onmyway.common.exception.DAOException;
import com.onmyway.sxw.manage.model.SxwZjInfo;
import com.onmyway.sxw.manage.model.SxwZjNumConfig;

import dbconnpac.ConstantSymbol;
import dbconnpac.ProxoolPoolManager;

/**
 * @Title:12x5，中奖号码保存
 * @Description: 
 * @Create on: Aug 14, 2010 5:37:30 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SxwZjNumConfigDaoImpl extends GenericEntityDAO<SxwZjNumConfig,String> implements ISxwZjNumConfigDao{
	private Log logger = LogFactory.getLog(SxwZjNumConfigDaoImpl.class);

	ProxoolPoolManager poolManager =  new ProxoolPoolManager();

	/**
	 * 删除给定期号的的中奖信息
	 * @return
	 * @throws DAOException
	 */
	public void deleteZjNum(String issueNum) throws DAOException{
		String sql = "delete from SxwZjNumConfig t where t.issueNum='" + issueNum + "'";
		int k = update(sql);
		
	}
	/**
	 * 得到所有的中奖信息
	 * @return
	 * @throws DAOException
	 */
	public List<SxwZjNumConfig> getAllZjNum() throws DAOException{
		String sql = "from SxwZjNumConfig t order by t.operTime desc";
		List<SxwZjNumConfig> list = getList(sql);
		return list;
	}
	/**
	 * 得到最新的中奖信息
	 * @return
	 * @throws DAOException
	 */
	public SxwZjNumConfig getCurrentZjNum() throws DAOException{
		SxwZjNumConfig info = new SxwZjNumConfig();
		String sql = "from SxwZjNumConfig s order by s.operTime desc";
		List<SxwZjNumConfig> list = getList(sql,1);//按照操作时间倒序排列，取最新的一条
		if(list != null && !list.isEmpty()){
			info = list.get(0);
		}else{
			info.setWan("0");
			info.setQian("0");
			info.setBai("0");
			info.setShi("0");
			info.setGe("0");
		}
		return info;
	}
	
	/**
	 * 得到给定期号的的中奖信息
	 * @return
	 * @throws DAOException
	 */
	public SxwZjNumConfig getZjNum(String issueNum) throws DAOException{
		SxwZjNumConfig config = new SxwZjNumConfig();
		String sql = "from SxwZjNumConfig t where t.issueNum='" + issueNum + "'";
		List<SxwZjNumConfig> list = getList(sql);
		if(list != null && !list.isEmpty()){
			config = list.get(0);
		}
		return config;
	}
	 
	/**
	 * 得到所有的中奖信息,除了当前正在发行的期别
	 * @return
	 * @throws DAOException
	 */
	public List<SxwZjNumConfig> getAllZjNum(String currentIssue) throws DAOException{
		String sql = "from SxwZjNumConfig t where t.issueNum not in ('"+currentIssue+"') order by t.operTime desc";
		List<SxwZjNumConfig> list = getList(sql);
		return list;
	}
	/**
	 * 查询相应时间段内的的中奖信息,除了当前正在发行的期别
	 * @param issueNum
	 * @return
	 * @throws AppException
	 */
	public List<SxwZjNumConfig> getZjNumBetweenDate(String beginDate,String endDate,String currentIssue) throws DAOException{
		String sql = "from SxwZjNumConfig t where t.issueDate between '"+beginDate+"' and '" + endDate + "' and t.issueNum not in ('"+currentIssue+"') order by t.issueNum asc";
		List<SxwZjNumConfig> list = getList(sql);
		return list;
	}
	
	 

	/**
	 * 得到最新的中奖信息,除了当前期
	 * @return
	 * @throws DAOException
	 */
	public SxwZjNumConfig getCurrentZjNum(String currentIssue) throws DAOException{
		SxwZjNumConfig info = new SxwZjNumConfig();
		String sql = "from SxwZjNumConfig s where s.issueNum not in ('"+currentIssue+"')  order by s.operTime desc";
		List<SxwZjNumConfig> list = getList(sql,1);//按照操作时间倒序排列，取最新的一条
		if(list != null && !list.isEmpty()){
			info = list.get(0);
		}else{
			info.setWan("-");
			info.setQian("-");
			info.setBai("-");
			info.setShi("-");
			info.setGe("-");
		}
		return info;
	}
	/**
	 * 中奖信息保存
	 * 
	 * @param list
	 * @return
	 * @throws DAOException
	 */
	public boolean jdbcSaveListInfo(List<SxwZjInfo> list,List<String> zjIdList,Map<String,Integer> zjMap) throws DAOException {
		boolean flag = true;
		Connection conn = null;
		PreparedStatement ps = null;
		
		int batchCount = ConstantSymbol.batchSize;
		String insertSql = "insert into sxw_zj_info(id,issue_num,tz_id,detail_id,user_id,user_name,play_type,play_mode,tz_num,tz_times,is_zhuitou,op_time,zj_money,zj_type,money_of_zj_type,zj_zhushu,total_zj_zhushu)"
            + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			if (conn == null || conn.isClosed()) {
				conn = getConnection(ConstantSymbol.dbSource);
				conn.setAutoCommit(false);
			} else {
				conn.setAutoCommit(false);
			}
			if (ps == null) {
				ps = conn.prepareStatement(insertSql);
	 		}

			int j = 0;
			for (int i = 0; i < list.size(); i++) {
				SxwZjInfo info = list.get(i);
				ps.setString(1, info.getId());
				ps.setString(2, info.getIssueNum());
				ps.setString(3, info.getTzId());
				ps.setString(4, info.getDetailId());
				ps.setString(5, info.getUserId());
				ps.setString(6, info.getUserName());
				ps.setString(7, info.getPlayType());
				ps.setString(8, info.getPlayMode());
				ps.setString(9, info.getTzNum());
				ps.setString(10, info.getTzTimes());
				ps.setString(11, info.getIsZhuitou());
				ps.setString(12, info.getOpTime());
				ps.setString(13, info.getZjMoney());
				ps.setString(14, info.getZjType());
				ps.setString(15, info.getMoneyOfZjType());
				ps.setString(16, info.getZjZhushu());
				ps.setString(17, info.getTotalZjZhushu());
				
				ps.addBatch();
				if ((i + 1) % batchCount == 0) {
					ps.executeBatch();
					conn.commit();
					ps.clearBatch();
				}
				j++;
			}
			if (j % batchCount > 0) {
				ps.executeBatch();
				conn.commit();
				ps.clearBatch();

			}
			//多期投注的情况下，如果其中有一期中奖，那么中奖后的其余期数投注号码要自动删除，删除后用户余额与所投金额要相应增加与扣除。
			//中奖用户Id
			String updateSql = "update sxw_tz_info set is_valid='0'  where 1=1 and id=? ";
			ps = conn.prepareStatement(updateSql);
			int k = 0;
			for(String str:zjIdList){
				ps.setString(1,str);
				logger.info("删除中奖信息::" + updateSql + ", id="+str);
				
				ps.addBatch();
				if ((k + 1) % batchCount == 0) {
					ps.executeBatch();
					conn.commit();
					ps.clearBatch();
				}
				k++;
			}
			if (k % batchCount > 0) {
				ps.executeBatch();
				conn.commit();
				ps.clearBatch();
			}
			//更新用户账户余额totalmoney,id from lotteryuser
			String updateMoneySql = "update lotteryuser set totalmoney=(totalmoney + ?) where id=? ";
			ps = conn.prepareStatement(updateMoneySql);
			int x = 0;
			Iterator<String> zjIt = zjMap.keySet().iterator();
			while(zjIt.hasNext()){
				String userId= zjIt.next();
				Integer money = zjMap.get(userId);
				ps.setInt(1,money);
				ps.setString(2,userId);
				logger.info("更新用户账户:" + updateMoneySql + ",totalmoney="+money+",id="+userId);
				ps.addBatch();
				if ((x + 1) % batchCount == 0) {
					ps.executeBatch();
					conn.commit();
					ps.clearBatch();
				}
				x++;
			}
			if (x % batchCount > 0) {
				ps.executeBatch();
				conn.commit();
				ps.clearBatch();

			}
			
			ps.close();
		
			conn.setAutoCommit(true);
			this.close(conn);
		} catch (Exception ex) {
			flag = false;
			ex.printStackTrace();
			
			try {
				conn.rollback();
				conn.setAutoCommit(true);
				this.close(conn);
			} catch (Exception e) {
				logger.error("回滚异常!" + e.toString());
				e.printStackTrace();
			}
			throw new DAOException("保存11选5中奖信息异常，请处理!" +  ex.toString());
		}
		  
		return flag;
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
	/**
	 * 根据发行期号，删除中奖号码信息，同时删除数据库中已存在的用户的中奖信息
	 * @param issueNum
	 * @return
	 */
	public boolean delZhongjianghaoma(String issueNum) throws DAOException{
		String sql = "delete from SxwZjNumConfig t where t.issueNum='" + issueNum + "'";

		String sql2 = "delete from SxwZjInfo t where t.issueNum='" + issueNum + "'";
		int k = update(sql);
		int m = update(sql2);
		if(k == 0 || m == 0){
			return false;
		}else{
			return true;
		}
	}
}
