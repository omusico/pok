package com.onmyway.sxw.play.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.onmyway.common.dao.GenericEntityDAO;
import com.onmyway.common.exception.DAOException;
import com.onmyway.ssc.play.model.SscTzDetail;
import com.onmyway.ssc.play.model.SscTzInfo;
import com.onmyway.sxw.play.model.SxwTzDetail;
import com.onmyway.sxw.play.model.SxwTzInfo;

import dbconnpac.ConstantSymbol;
import dbconnpac.DBAccess;
import dbconnpac.DBTool;
import dbconnpac.ProxoolPoolManager;

/**
 * @Title:12选5-投注信息管理
 * @Description: 保存-判断-验证等
 * @Create on: Aug 20, 2010 5:42:43 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SxwTzInfoDaoImpl extends GenericEntityDAO<SxwTzInfo, String>
		implements ISxwTzInfoDao {
	DBTool dbTool = DBAccess.getDBTool();
	ProxoolPoolManager poolManager =  new ProxoolPoolManager();

	/**
	 * 批量保存投注信息
	 * 
	 * @param list
	 * @return
	 * @throws DAOException
	 */
	public boolean jdbcSaveListInfo(List<SxwTzInfo> list) throws DAOException {
		boolean flag = true;
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		
		int batchCount = ConstantSymbol.batchSize;
		String insertSql = "insert into sxw_tz_info (user_id, user_name, issue_num, play_type, type_name,play_mode, touzhu_num, touzhu_count, touzhu_times, touzhu_money, is_zhuitou, parent_issue_num, parent_id,is_valid, touzhu_time, ID,have_zt_flag,tz_seq)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?)";
		String insertSql2 = "insert into sxw_tz_detail (id,p_id,issue_num,play_type,type_name,play_mode,num_detail,num_times,is_zhuitou)"
			+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			if (conn == null || conn.isClosed()) {
				conn = getConnection(ConstantSymbol.dbSource);
				conn.setAutoCommit(false);
			} else {
				conn.setAutoCommit(false);
			}
			if (ps == null) {
				ps = conn.prepareStatement(insertSql);
				ps2 = conn.prepareStatement(insertSql2);
			}

			int j = 0;
			for (int i = 0; i < list.size(); i++) {
				SxwTzInfo info = list.get(i);
				ps.setString(1, info.getUserId());
				ps.setString(2, info.getUserName());
				ps.setString(3, info.getIssueNum());
				ps.setString(4, info.getPlayType());
				ps.setString(5, info.getTypeName());
				ps.setString(6, info.getPlayMode());
				ps.setString(7, info.getTouzhuNum());
				ps.setInt(8, info.getTouzhuCount());
				if(info.getTouzhuTimes() == null){
					ps.setInt(9, 0);
				}else{
					ps.setInt(9, info.getTouzhuTimes());
				}
				if(info.getTouzhuMoney() == null){
					ps.setInt(10, 0); 
				}else{
					ps.setInt(10, info.getTouzhuMoney()); 
				}
				
				ps.setString(11, info.getIsZhuitou());
				ps.setString(12, info.getParentIssueNum());
				ps.setString(13, info.getParentId());
				ps.setString(14, info.getIsValid());
				ps.setString(15, info.getTouzhuTime());
				ps.setString(16, info.getId());
				ps.setString(17,info.getHaveZtFlag());
				ps.setInt(18,info.getTzSeq());
				
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

			ps.close();
			
			//投注信息分解
			int k = 0;
			for (int i = 0; i < list.size(); i++) {
				List<SxwTzDetail> list2 = list.get(i).getDetailList();
				for (int m = 0; m < list2.size(); m++) {
					//id,p_id,issue_num,play_type,play_mode,num_detail,num_times,is_zuhe,is_zhuitou)"
					SxwTzDetail info2 = list2.get(m);
					ps2.setString(1, info2.getId());
					ps2.setString(2, info2.getPId());
					ps2.setString(3, info2.getIssueNum());
					ps2.setString(4, info2.getPlayType());
					ps2.setString(5, info2.getTypeName());
					ps2.setString(6, info2.getPlayMode());
					ps2.setString(7, info2.getNumDetail());
					ps2.setString(8, info2.getNumTimes());
					
					ps2.setString(9, info2.getIsZhuitou());
				
//					if(i==1){
//						throw new Exception("test test test ");
//					}
			 
					ps2.addBatch();
					if ((i + 1) % batchCount == 0) {
						ps2.executeBatch();
						conn.commit();
						ps2.clearBatch();
					}
					k++;
				}
			}
			if (k % batchCount > 0) {
				ps2.executeBatch();
				conn.commit();
				ps2.clearBatch();

			}
			ps2.close();
			conn.setAutoCommit(true);
			// poolManager.freeConnection(dbSource, conn);
			this.close(conn);
		} catch (Exception ex) {
			flag = false;
			try {
				conn.rollback();
				conn.setAutoCommit(true);
				// poolManager.freeConnection(dbSource, conn);
				this.close(conn);
			} catch (Exception e) {
				logger.error("回滚异常!");
				e.printStackTrace();
			}

			ex.printStackTrace();
		}
		  
		return flag;
	}

	/**
	 * 得到指定用户的投注信息
	 * @param issueNum:期号，可以为空
	 * @param userId:用户ID
	 * @return
	 * @throws DAOException
	 */
	public List<SxwTzInfo> getUserTzInfo(String issueNum,String userId) {
		
		String sql = " from SxwTzInfo t where 1=1 and t.isValid='1' ";
		if(issueNum != null  && !issueNum.equals("")){
			sql = sql + " and t.issueNum = '" + issueNum + "' ";
		}
		if(userId != null  && !userId.equals("")){
			sql = sql + " and t.userId = '" + userId + "' ";
		}

		sql = sql + " order by t.touzhuTime desc,tz_seq desc";
		List<SxwTzInfo> list =  null;
		try{
			list = getList(sql);
		}catch(Exception e){
			logger.error("查询12x5用户投注信息错误:"+e.toString());
		}
		return list;
	}
	/**
	 * 得到指定期号中指定用户的投注的最大序号
	 * @param issueNum:期号，可以为空
	 * @param userId:用户ID
	 * @return
	 * @throws DAOException
	 */
	public String getUserTzMaxSeq(String issueNum,String userId) {
		
		String seq = "0";
		String sql = " select max(t.tzSeq) from SxwTzInfo t where 1=1 ";
		if(issueNum != null  && !issueNum.equals("")){
			sql = sql + " and t.issueNum = '" + issueNum + "' ";
		}
		if(userId != null  && !userId.equals("")){
			sql = sql + " and t.userId = '" + userId + "' ";
		}
		try{
			List<SscTzInfo> list  = getList(sql);
			if(list != null && !list.isEmpty()){
				Object obj = list.get(0);
				seq = obj.toString();
				
			}
		}catch(Exception e){
			logger.error("查询12x5用户投注序号信息错误:"+e.toString());
		}
		return seq;
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
	 * 删除指定用户的投注信息
	 * @param tzId
	 * @param userId
	 * @return
	 * @throws DAOException
	 */
	public boolean delUserTzInfo(String tzId,String userId)throws DAOException{
		SxwTzInfo info = get(tzId);
		Integer tzMoney = info.getTouzhuMoney();
		String sql1 = "delete from SxwTzInfo t where t.id='" + tzId + "'";
		String sql2 = "delete from SxwZjInfo t where t.tzId='" + tzId + "'";
		String sql3 = "update UserInfo t set t.totalmoney=(t.totalmoney+" + tzMoney.intValue() + ") where t.id='" + userId + "'";
		
		int k = update(sql1);
		int m = update(sql2);
		int n = update(sql3);
		
		logger.info("删除用户" + userId + "的ID为" + tzId + "投注信息！删除投注信息：结果=" + k + ";删除中奖信息：结果=" + m + ";更新用户资金信息：结果=" + n );
		if(k == 0 ){
			return false;
		}else{
			return true;
		}
	}
 
	/**
	 * 查询指定用户总的投注注数及投注金额
	 * @param issueNum:期号，可以为空
	 * @param userId:用户ID
	 * @return
	 * @throws DAOException
	 */
	public SxwTzInfo getUserTotalTzInfo(String issueNum,String userId) {
		SxwTzInfo info = new SxwTzInfo();
		//String sql = " from SscTzInfo t where 1=1 ";
		String sql = " select sum(touzhuCount*touzhuTimes) as touzhuCount,sum(t.touzhuMoney) as  touzhuMoney "
				   + " from SxwTzInfo t "
				   + " where t.isValid='1' ";// and t.userId='7'
			
		if(issueNum != null  && !issueNum.equals("")){
			sql = sql + " and t.issueNum = '" + issueNum + "' ";
		}
		if(userId != null  && !userId.equals("")){
			sql = sql + " and t.userId = '" + userId + "' ";
		}
		List<Object[]>  list =  null;
		try{
			list = getList(sql);
			int size = list.size();
			if(list != null && !list.isEmpty()){
				Object[] info2 = list.get(0);
				
				Long touzhuCount = (Long)(info2[0] == null ? 0L : info2[0]);
				Long touzhuMoney = (Long)(info2[1] == null ? 0L : info2[1]);
				info.setTouzhuCount(Integer.parseInt(touzhuCount.toString()));
				info.setTouzhuMoney(Integer.parseInt(touzhuMoney.toString()));
				
			}
		}catch(Exception e){
			logger.error("查询12x5指定用户总的投注信息错误:"+e.toString());
		}
		return info;
	}
}
