package beanpac;

import java.util.Vector;

import sun.jdbc.rowset.CachedRowSet;
import util.EncryptUtil;
import dbconnpac.ConstantSymbol;
import dbconnpac.DBAccess;
import dbconnpac.DBTool;

/**
 * @Title:对数据库中已有的密码进行加密，初始化用，只用一次
 * @Description: 
 * @Create on: Sep 23, 2009 9:14:52 AM
 * @Author:LJY
 * @Version:1.0
 */
public class InitEncryptPassword {
	public String encryptPassword(){
		String str = "";
		DBTool dbTool = DBAccess.getDBTool();
		String dbSource = ConstantSymbol.dbSource;
		String sql = "select * from lotteryuser";
		CachedRowSet crs = dbTool.querySql(dbSource,sql);
		Vector vec = new Vector();
		try{
			if(crs != null )
				while(crs.next()){
					String oldPassword = crs.getString("password");
					int id = crs.getInt("id");
					String newPassword = EncryptUtil.generatePassword(oldPassword);
					String sql_new = "update lotteryuser set password='" + newPassword + "' where id='" + id + "'";
					vec.add(sql_new);
				}
				boolean flag = dbTool.executeSql(dbSource, vec);
				if(flag){
					str = "更新成功";
				}else{
					str = "更新失败";
				}
		}catch(Exception e){
			System.out.println("-----初始化密码异常:" + e.toString());
			str = "更新异常!";
		}
		return str;
	}
}
