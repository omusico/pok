package beanpac;

import java.util.Date;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.jdbc.rowset.CachedRowSet;
import util.JDateToolkit;

import com.onmyway.common.exception.AppException;
import com.onmyway.exb.manage.model.ExbZjNumConfig;
import com.onmyway.exb.manage.service.IExbManageService;
import com.onmyway.ssc.manage.model.SscZjNumConfig;
import com.onmyway.ssc.manage.service.ISscManageService;
import com.onmyway.sxw.manage.model.SxwZjNumConfig;
import com.onmyway.sxw.manage.service.ISxwManageService;

import dbconnpac.ConstantSymbol;
import dbconnpac.DBAccess;
import dbconnpac.DBTool;
import dbconnpac.DataBaseConn;

/**
 *@Description:
 *@Author:LJY
 *@E-mail:lijiyongcn@sohu.com
 *@Create on Jun 5, 2009 12:05:11 AM
 *@Ver:
 */
public class DwrBean {
	private Log logger = LogFactory.getLog(DwrBean.class);

	private DBTool dbTool = DBAccess.getDBTool();
	private ISscManageService sscManageService;
	private ISxwManageService sxwManageService;
	private IExbManageService exbManageService;
	
	public ISscManageService getSscManageService() {
		return sscManageService;
	}
	public void setSscManageService(ISscManageService sscManageService) {
		this.sscManageService = sscManageService;
	}
	
	public ISxwManageService getSxwManageService() {
		return sxwManageService;
	}
	public void setSxwManageService(ISxwManageService sxwManageService) {
		this.sxwManageService = sxwManageService;
	}
	public void setExbManageService(IExbManageService exbManageService) {
		this.exbManageService = exbManageService;
	}
	/**
	 * 
	 *@Description:
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Jun 5, 2009 12:17:21 AM
	 * @param gameType  gameType　poker:扑克,laugh：时时乐,hap:福 彩 3D ,pth:排列3
	 * @param strRule
	 * @return
	 */
	public String getMoneyInfo(String gameType,String strRule){

		String strTable = getPrMoneyTable(gameType);
		String sql = "select money,limit_degree from " + strTable + " where 1=1 and rule='" + strRule + "'" ;
		DataBaseConn conn=new DataBaseConn();
		conn.execQuery(sql);
		conn.rsNext();
		String strLimit = conn.rsGetString("limit_degree");
		String strMoney = conn.rsGetString("money");
		
		if(strLimit == null || strLimit.equals("")){
			strLimit = "";
		}
		
		return strMoney+","+strLimit;
	}
	public String getPrMoneyTable(String gameType){
		String strTable = "";
		if(gameType.equals("poker")){  strTable       = "pokprmoney";}   
		if(gameType.equals("laugh")){  strTable       = "lauprmoney";}     
		if(gameType.equals("hap")){  strTable       = "happrmoney";}    
		if(gameType.equals("pth")){  strTable       = "pthprmoney";}    
		if(gameType.equals("ssc")){  strTable       = "ssc_money_and_limit";}    
		if(gameType.equals("sxw")){  strTable       = "sxw_money_and_limit";}    
		if(gameType.equals("exb")){  strTable       = "exb_money_and_limit";}    
		return strTable;
	}
	
	/**
	 * 时时彩信息
	 * @param gameType
	 * @param strRule
	 * @return
	 */
	public String getMoneyLimitInfo(String gameType,String strRule){
		String str = "";
		String strTable = getPrMoneyTable(gameType);
		String sql = "select rule,money,limit_one_issue from " + strTable + " where 1=1 and rule='" + strRule + "' and is_valid='1'" ;
		CachedRowSet crs = dbTool.querySql(ConstantSymbol.dbSource, sql);
		if(crs != null){
			try{
			if(crs.next()){
				String rule = crs.getString("rule");
				String money = crs.getString("money");
				String limit = crs.getString("limit_one_issue");
				str = money + "," + limit;
			}
			}catch(Exception e){
				logger.error("查询奖金及限号信息错误：" + e.toString());
			}
		}	
		
		return str;
	}
	/**
	 * 查询当期开奖号码
	 * @return
	 */
	public SscZjNumConfig getCurrentZjNum(){
		SscZjNumConfig info = new SscZjNumConfig();
		try{
			info = sscManageService.getCurrentZjNum();
		}catch(AppException e){
			logger.error("dwr 查询ssc当期开奖号码异常:"+e.toString());
		}
		return info;
	}
	/**
	 * 查询当期开奖号码 12x5
	 * @return
	 */
	public SxwZjNumConfig getSxwCurrentZjNum(){
		SxwZjNumConfig info = new SxwZjNumConfig();
		try{
			info = sxwManageService.getCurrentZjNum();
		}catch(AppException e){
			logger.error("dwr 查询12选5当期开奖号码异常:"+e.toString());
		}
		return info;
	}
	/**
	 * 查询当期开奖号码 快乐十分
	 * @return
	 */
	public ExbZjNumConfig getExbCurrentZjNum(){
		ExbZjNumConfig info = new ExbZjNumConfig();
		try{
			info = exbManageService.getCurrentZjNum();
		}catch(AppException e){
			logger.error("dwr 快乐十分:查询当期开奖号码异常:"+e.toString());
		}
		return info;
	}
	/**
	 * 查询给定期号开奖号码
	 * @return
	 */
	public SscZjNumConfig getZjConfig(String issueNum){
		SscZjNumConfig info = new SscZjNumConfig();
		try{
			info = sscManageService.getZjNumConfig(issueNum);
		}catch(AppException e){
			logger.error("dwr 查询开奖号码异常:"+e.toString());
		}
		return info;
	}
	/**
	 * 查询12选5给定期号开奖号码
	 * @return
	 */
	public SxwZjNumConfig getSxwZjConfig(String issueNum){
		SxwZjNumConfig info = new SxwZjNumConfig();
		try{
			info = sxwManageService.getZjNumConfig(issueNum);
		}catch(AppException e){
			logger.error("dwr 查询开奖号码异常:"+e.toString());
		}
		return info;
	}
	
	/**
	 * 查询12选5给定期号开奖号码
	 * @return
	 */
	public ExbZjNumConfig getExbZjConfig(String issueNum){
		ExbZjNumConfig info = new ExbZjNumConfig();
		try{
			if(exbManageService == null){
				logger.warn("exbManageService is null!!!!!!!!!");
			}
			info = exbManageService.getZjNumConfig(issueNum);
		}catch(AppException e){
			logger.error("dwr 查询快乐十分开奖号码异常:"+e.toString());
		}catch(Exception e){
			
			logger.error("dwr 查询快乐十分开奖号码异常22:"+e.toString());
		}
		return info;
	}
	/**
	 * 删除时时彩的所有<投注记录，中奖记录>信息 20130527需求
	 * @Description:
	 * @Author:ljy
	 * @Version:1.0
	 * @Create on:May 27, 2013 8:12:21 PM
	 *
	 * @return
	 * @see
	 */
	public boolean delAllRecordOfSsc(){			
		String sqlTz = "delete from ssc_tz_info";
		String sqlTzDetail = "delete from ssc_tz_detail";
		String sqlZj = "delete from ssc_zj_info";
		Vector vecSql = new Vector();
		vecSql.add(sqlTz);
		vecSql.add(sqlTzDetail);
		vecSql.add(sqlZj);
		String nowTime = JDateToolkit.getNowDate4();
		logger.warn("准备【删除】时时彩的所有记录:"+nowTime);
		logger.info("删除_tz:" + sqlTz);
		logger.info("删除_tz_detail:" + sqlTzDetail);
		logger.info("删除_zj:" + sqlZj);
		boolean flag = dbTool.executeVecSql(ConstantSymbol.dbSource,vecSql);
		logger.warn("【删除】时时彩的所有记录完毕，结果为"+flag);
		return flag;
	}
	
	/**
	 * 删除快乐十分的所有<投注记录，中奖记录>信息 20130527需求
	 * @Description:
	 * @Author:ljy
	 * @Version:1.0
	 * @Create on:May 27, 2013 8:12:21 PM
	 *
	 * @return
	 * @see
	 */
	public boolean delAllRecordOfExb(){			
		String sqlTz = "delete from exb_tz_info";
		String sqlTzDetail = "delete from exb_tz_detail";
		String sqlZj = "delete from exb_zj_info";
		Vector vecSql = new Vector();
		vecSql.add(sqlTz);
		vecSql.add(sqlTzDetail);
		vecSql.add(sqlZj);
		String nowTime = JDateToolkit.getNowDate4();
		logger.warn("准备【删除】快乐十分的所有记录:"+nowTime);
		logger.info("删除_tz:" + sqlTz);
		logger.info("删除_tz_detail:" + sqlTzDetail);
		logger.info("删除_zj:" + sqlZj);
		boolean flag = dbTool.executeVecSql(ConstantSymbol.dbSource,vecSql);
		logger.warn("【删除】快乐十分的所有记录完毕，结果为"+flag);
		return flag;
	}
	
	/**
	 * 删除11选5的所有<投注记录，中奖记录>信息 20130527需求
	 * @Description:
	 * @Author:ljy
	 * @Version:1.0
	 * @Create on:May 27, 2013 8:12:21 PM
	 *
	 * @return
	 * @see
	 */
	public boolean delAllRecordOfSxw(){			
		String sqlTz = "delete from sxw_tz_info";
		String sqlTzDetail = "delete from sxw_tz_detail";
		String sqlZj = "delete from sxw_zj_info";
		Vector vecSql = new Vector();
		vecSql.add(sqlTz);
		vecSql.add(sqlTzDetail);
		vecSql.add(sqlZj);
		String nowTime = JDateToolkit.getNowDate4();
		logger.warn("准备【删除】11选5的所有记录:"+nowTime);
		logger.info("删除_tz:" + sqlTz);
		logger.info("删除_tz_detail:" + sqlTzDetail);
		logger.info("删除_zj:" + sqlZj);
		boolean flag = dbTool.executeVecSql(ConstantSymbol.dbSource,vecSql);
		logger.warn("【删除】11选5的所有记录完毕，结果为"+flag);
		return flag;
	}
	/**
	 * 删除快乐扑克的所有<投注记录，中奖记录>信息 20130527需求
	 * @Description:
	 * @Author:ljy
	 * @Version:1.0
	 * @Create on:May 27, 2013 8:12:21 PM
	 *
	 * @return
	 * @see
	 */
	public boolean delAllRecordOfPoker(){		

		String sqlTz = "delete from pokwagerinfo"; 
		String sqlZj = "delete from pokprizeinfo";
		Vector vecSql = new Vector();
		vecSql.add(sqlTz); 
		vecSql.add(sqlZj);
		String nowTime = JDateToolkit.getNowDate4();
		logger.warn("准备【删除】快乐扑克的所有记录:"+nowTime);
		logger.info("删除_tz:" + sqlTz); 
		logger.info("删除_zj:" + sqlZj);
		boolean flag = dbTool.executeVecSql(ConstantSymbol.dbSource,vecSql);
		logger.warn("【删除】快乐扑克的所有记录完毕，结果为"+flag);
		return flag;
	}
	/**
	 * 删除排列三的所有<投注记录，中奖记录>信息 20130527需求
	 * @Description:
	 * @Author:ljy
	 * @Version:1.0
	 * @Create on:May 27, 2013 8:12:21 PM
	 *
	 * @return
	 * @see
	 */
	public boolean delAllRecordOfPth(){		

		String sqlTz = "delete from pthwagerinfo"; 
		String sqlZj = "delete from pthprizeinfo";
		Vector vecSql = new Vector();
		vecSql.add(sqlTz); 
		vecSql.add(sqlZj);
		String nowTime = JDateToolkit.getNowDate4();
		logger.warn("准备【删除】排列三的所有记录:"+nowTime);
		logger.info("删除_tz:" + sqlTz); 
		logger.info("删除_zj:" + sqlZj);
		boolean flag = dbTool.executeVecSql(ConstantSymbol.dbSource,vecSql);
		logger.warn("【删除】排列三的所有记录完毕，结果为"+flag);
		return flag;
	}
	
	/**
	 * 删除时时乐的所有<投注记录，中奖记录>信息 20130527需求
	 * @Description:
	 * @Author:ljy
	 * @Version:1.0
	 * @Create on:May 27, 2013 8:12:21 PM
	 *
	 * @return
	 * @see
	 */
	public boolean delAllRecordOfLaugh(){		

		String sqlTz = "delete from lauwagerinfo"; 
		String sqlZj = "delete from lauprizeinfo";
		Vector vecSql = new Vector();
		vecSql.add(sqlTz); 
		vecSql.add(sqlZj);
		
		String nowTime = JDateToolkit.getNowDate4();
		logger.warn("准备【删除】时时乐的所有记录:"+nowTime);
		logger.info("删除_tz:" + sqlTz); 
		logger.info("删除_zj:" + sqlZj);
		boolean flag = dbTool.executeVecSql(ConstantSymbol.dbSource,vecSql);
		logger.warn("【删除】时时乐的所有记录完毕，结果为"+flag);
		return flag;
	}
	
	/**
	 * 删除福彩3d的所有<投注记录，中奖记录>信息 20130527需求
	 * @Description:
	 * @Author:ljy
	 * @Version:1.0
	 * @Create on:May 27, 2013 8:12:21 PM
	 *
	 * @return
	 * @see
	 */
	public boolean delAllRecordOfHappy(){		

		String sqlTz = "delete from hapwagerinfo"; 
		String sqlZj = "delete from happrizeinfo";
		Vector vecSql = new Vector();
		vecSql.add(sqlTz); 
		vecSql.add(sqlZj);
		String nowTime = JDateToolkit.getNowDate4();
		logger.warn("准备【删除】福彩3d的所有记录:"+nowTime);
		logger.info("删除_tz:" + sqlTz); 
		logger.info("删除_zj:" + sqlZj);
		boolean flag = dbTool.executeVecSql(ConstantSymbol.dbSource,vecSql);
		logger.warn("【删除】福彩3d的所有记录完毕，结果为"+flag);
		return flag;
	}
}
