package com.onmyway.ssc.manage.dao;

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
import com.onmyway.ssc.manage.model.SscZjInfo;
import com.onmyway.ssc.manage.model.SscZjNumConfig;

import dbconnpac.ConstantSymbol;
import dbconnpac.ProxoolPoolManager;

/**
 * @Title:时时彩，中奖号码保存
 * @Description: 
 * @Create on: Aug 14, 2010 5:37:30 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SscZjNumConfigDaoImpl extends GenericEntityDAO<SscZjNumConfig,String> implements ISscZjNumConfigDao{
	
	private Log logger = LogFactory.getLog(SscZjNumConfigDaoImpl.class);

	ProxoolPoolManager poolManager =  new ProxoolPoolManager();
	
	/**
	 * 删除给定期号的的中奖信息
	 * @return
	 * @throws DAOException
	 */
	public boolean deleteZjNum(String issueNum) throws DAOException{
		String sql = "delete from SscZjNumConfig t where t.issueNum='" + issueNum + "'";
		int k = update(sql);
		if(k == 0){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * 得到给定期号的的中奖信息
	 * @return
	 * @throws DAOException
	 */
	public SscZjNumConfig getZjNum(String issueNum) throws DAOException{
		SscZjNumConfig config = new SscZjNumConfig();
		String sql = "from SscZjNumConfig t where t.issueNum='" + issueNum + "'";
		List<SscZjNumConfig> list = getList(sql);
		if(list != null && !list.isEmpty()){
			config = list.get(0);
		}
		return config;
	}
	/**
	 * 得到所有的中奖信息
	 * @return
	 * @throws DAOException
	 */
	public List<SscZjNumConfig> getAllZjNum() throws DAOException{
		String sql = "from SscZjNumConfig t order by t.operTime desc";
		List<SscZjNumConfig> list = getList(sql);
		return list;
	}
	/**
	 * 得到所有的中奖信息,除了当前正在发行的期别
	 * @return
	 * @throws DAOException
	 */
	public List<SscZjNumConfig> getAllZjNum(String currentIssue) throws DAOException{
		String sql = "from SscZjNumConfig t where t.issueNum not in ('"+currentIssue+"') order by t.operTime desc";
		List<SscZjNumConfig> list = getList(sql);
		return list;
	}
	/**
	 * 查询相应时间段内的的中奖信息,除了当前正在发行的期别
	 * @param issueNum
	 * @return
	 * @throws AppException
	 */
	public List<SscZjNumConfig> getZjNumBetweenDate(String beginDate,String endDate,String currentIssue) throws DAOException{
		String sql = "from SscZjNumConfig t where t.issueDate between '"+beginDate+"' and '" + endDate + "' and t.issueNum not in ('"+currentIssue+"') order by t.issueNum asc";
		List<SscZjNumConfig> list = getList(sql);
		return list;
	}
	
	/**
	 * 得到最新的中奖信息
	 * @return
	 * @throws DAOException
	 */
	public SscZjNumConfig getCurrentZjNum() throws DAOException{
		SscZjNumConfig info = new SscZjNumConfig();
		String sql = "from SscZjNumConfig s order by s.operTime desc";
		List<SscZjNumConfig> list = getList(sql,1);//按照操作时间倒序排列，取最新的一条
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
	 * 得到最新的中奖信息,除了当前期
	 * @return
	 * @throws DAOException
	 */
	public SscZjNumConfig getCurrentZjNum(String currentIssue) throws DAOException{
		SscZjNumConfig info = new SscZjNumConfig();
		String sql = "from SscZjNumConfig s where s.issueNum not in ('"+currentIssue+"')  order by s.operTime desc";
		List<SscZjNumConfig> list = getList(sql,1);//按照操作时间倒序排列，取最新的一条
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
	public void jdbcSaveListInfo(List<SscZjInfo> list,List<String> zjIdList,Map<String,Integer> zjMap) throws DAOException {
		boolean flag = true;
		Connection conn = null;
		PreparedStatement ps = null;
		
		int batchCount = ConstantSymbol.batchSize;
		String insertSql = "insert into ssc_zj_info(id,issue_num,tz_id,detail_id,user_id,user_name,play_type,play_mode,tz_num,tz_times,is_zhuitou,op_time,zj_money,zj_type,money_of_zj_type,zj_zhushu,total_zj_zhushu)"
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
				SscZjInfo info = list.get(i);
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
			String updateSql = "update ssc_tz_info set is_valid='0'  where 1=1 and id=? ";
			ps = conn.prepareStatement(updateSql);
			int k = 0;
			for(String str:zjIdList){
				ps.setString(1,str);
				
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
				logger.error("保存中奖号码时,回滚异常!" + e.toString());
				e.printStackTrace();
			}
			throw new DAOException("保存中奖号码时异常"+ex.toString());
		}
		   
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
		String sql = "delete from SscZjNumConfig t where t.issueNum='" + issueNum + "'";

		String sql2 = "delete from SscZjInfo t where t.issueNum='" + issueNum + "'";
		int k = update(sql);
		int m = update(sql2);
		if(k == 0 || m == 0){
			return false;
		}else{
			return true;
		}
	}
}
