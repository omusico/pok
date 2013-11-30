package com.onmyway.exb.manage.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.onmyway.common.dao.GenericEntityDAO;
import com.onmyway.common.exception.DAOException;
import com.onmyway.ssc.manage.model.SscZjInfo;
import com.onmyway.exb.manage.model.ExbZjInfo;

import dbconnpac.ConstantSymbol;

/**
 * @Title:快乐十分，中奖号码保存
 * @Description: 
 * @Create on: Aug 14, 2010 5:37:30 PM
 * @Author:LJY
 * @Version:1.0
 */
public class ExbZjInfoDaoImpl extends GenericEntityDAO<ExbZjInfo,String> implements IExbZjInfoDao{
	
	private Log logger = LogFactory.getLog(ExbZjInfoDaoImpl.class);

	/**
	 * 得到指定用户的中奖信息
	 * admin可以查询所有的信息，普通用户只可以查询本人的中奖信息
	 * @return
	 * @throws DAOException
	 */
	public List<ExbZjInfo> getZjInfo(String issueNum,String userName) throws DAOException{
		String sql = "from ExbZjInfo t where 1=1";
		if(!userName.equals("admin") && !userName.equals("") ){
			sql = sql + " and user_name='" + userName + "'";
		}
		if(issueNum != null && !issueNum.equals("")){
			sql = sql + " and issue_num='" + issueNum + "'";
		}
		List<ExbZjInfo> list = getList(sql);
		return list;
	}
	
	/**
	 * 得到指定用户的中奖信息
	 * admin可以查询所有的信息，普通用户只可以查询本人的中奖信息
	 * @return
	 * @throws DAOException
	 */
	public List<ExbZjInfo> getZjInfo(String issueNum,String userName,String userId) throws DAOException{
		String sql = "from ExbZjInfo t where 1=1";
		if( userName != null &&  !userName.equals("") ){
			sql = sql + " and t.userName='" + userName + "'";
		}
		if( userId != null &&  !userId.equals("") ){
			sql = sql + " and t.userId='" + userId + "'";
		}
		if(issueNum != null && !issueNum.equals("")){
			sql = sql + " and t.issueNum='" + issueNum + "'";
		}
		sql = sql + " order by t.opTime desc";
		List<ExbZjInfo> list = getList(sql);
		return list;
	} 
	
	/**
	 * 删除指定用户某一个中奖号码信息，同时删除数据库中已存在的用户的投注信息
	 * 同时将用户的账户中扣除相应的奖金（目前先不做)
	 * @param issueNum
	 * @return
	 */
	public boolean delUserZjInfo(String tzId,String userId) throws DAOException {
		//ExbZjInfo info = get(zjId);
		
		//得到相应的中奖金额
		String tzMoney = "0";
		String sql5 = "select sum(t.zjMoney) as zjMoney from ExbZjInfo t where t.tzId='" + tzId + "' and userId='" + userId + "'";
		List<ExbZjInfo> list = getList(sql5);
		if(!list.isEmpty()){
//			ExbZjInfo info = list.get(0);
//			tzMoney = info.getZjMoney();
			Object obj = list.get(0);
			tzMoney = obj.toString();
		}
		
		String sql1 = "delete from ExbTzInfo t where t.id='" + tzId + "'";
		String sql2 = "delete from ExbZjInfo t where t.tzId='" + tzId + "'";
		String sql3 = "update UserInfo t set t.totalmoney=(t.totalmoney-" + tzMoney + ") where t.id='" + userId + "'";
		
		int k = update(sql1);
		int m = update(sql2);
		/***先不做扣除金额操作，以后如果需要再进行扣除 ljy 2011-01-08***
		int n = update(sql3);
		**/
		
		logger.info("删除用户" + userId + "的ID为" + tzId + "中奖信息！删除中奖信息：结果=" + k + ";删除中奖信息：结果=" + m + ";更新用户资金信息：结果=+ n"  );
		if(k == 0 ){
			return false;
		}else{
			return true;
		}
	}
 
	/**
	 * 查询指定用户总的中奖注数及中奖金额
	 * @param issueNum:期号，可以为空
	 * @param userId:用户ID
	 * @return
	 * @throws DAOException
	 */
	public ExbZjInfo getUserTotalZjInfo(String issueNum,String userId) {
		ExbZjInfo info = new ExbZjInfo();
		//String sql = " from SscTzInfo t where 1=1 ";
		String sql = " select sum(t.totalZjZhushu) as zjZhushu,sum(t.zjMoney) as  zjMoney "
				   + " from ExbZjInfo t "
				   + " where 1=1 ";// and t.userId='7'
		
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
				
				String zjZhushu = info2[0] == null ? "0" : info2[0].toString();
				String zjMoney = info2[1]  == null ? "0" : info2[1].toString();
				info.setZjZhushu(zjZhushu);
				info.setZjMoney(zjMoney);
				
			}
		}catch(Exception e){
			logger.error("查询指定用户总的中奖注数及中奖金额错误:"+e.toString());
		}
		return info;
	}
	

	/**
	 * 重置中奖号码时，删除对应期号已经存在的中奖信息
	 * @param issueNum
	 * @throws DAOException
	 */
	public void deleteHistoryZjInfo(String issueNum) throws DAOException{
		String sql = "delete from ExbZjInfo t where t.issueNum='" + issueNum + "'";
		update(sql);
		
	}
	
}
