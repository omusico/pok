package beanpac;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.onmyway.ssc.manage.service.SscManageServiceImpl;

import pubmethpac.GlobalMeth;
import sun.jdbc.rowset.CachedRowSet;
import dbconnpac.ConstantSymbol;
import dbconnpac.DBAccess;
import dbconnpac.DBTool;
import dbconnpac.DataBaseConn;

public class PrMoney{
	private Log logger = LogFactory.getLog(PrMoney.class);

	private DBTool dbTool = DBAccess.getDBTool();
	
	public String getPrMoney(String strTable,String strRule){
		DataBaseConn dbc=new DataBaseConn();
		dbc.execQuery("select * from "+strTable+" where rule='"+strRule+"';");
		dbc.rsNext();
		String strMoney=dbc.rsGetString("money");
		dbc.connClose();
		return strMoney;
	}
	
	public String getPokRuleMon(String ruleTemp){
		String[] arrRuleChin={"任选一","任选二","任选三中3","任选三中2","选四直选中4","选四直选中3","组4","组6","组12","组24"};
		String[] arrRuleEng={"pokone","poktwo","pokthrthr","pokthrtwo","pokfourfour","pokfourthr","pokgrfour","pokgrsix","pokgrtwelve","pokgrtwfour"};
		GlobalMeth objGM=new GlobalMeth();
		String strRuleEng=objGM.tranCoorStr(arrRuleChin, arrRuleEng, ruleTemp);
		String mon=getPrMoney("pokprmoney",strRuleEng);
		return mon;
	}
	
	public String getPrize(String wagNum,String times,String rule){
		 String strPr="";
		 int intPM=Integer.parseInt(getPokRuleMon(rule));
		 int intPr=Integer.parseInt(wagNum)*intPM*Integer.parseInt(times);
		 strPr=String.valueOf(intPr);
		 return strPr;
		 
	}

	public String getSscMoneyAndLimit(String gameType){
		String str = "";
		String strTable = getTable(gameType);
		String sql = "select * from "+strTable+" where is_valid='1'";
		
		CachedRowSet crs = dbTool.querySql(ConstantSymbol.dbSource, sql);
		if(crs != null){
			try{
			while(crs.next()){
				String rule = crs.getString("rule");
				String money = crs.getString("money");
				String limit = crs.getString("limit_one_issue");
				str = str + getRoleName(rule) + ":" + money + "元,限倍：" + limit + "<br>";
			}
			}catch(Exception e){
				logger.error("查询限号错误：" + e.toString());
			}
		}
		
		return str;
	}
	public String getTable(String gameType){
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
	public String getRoleName(String rule){
		String str = "";
		 if(rule.equals("wuxingzhixuan")) { str = "五星直选";}
		 if(rule.equals("wuxingtx")) { str = "五星通选一等奖";}
		 if(rule.equals("wuxingqian3")) { str = "五星二等奖";}
		 if(rule.equals("wuxinghou3")) { str = "五星后3";}
		 if(rule.equals("wuxingqian2")) { str = "五星三等奖";}
		 if(rule.equals("wuxinghou2")) { str = "五星后2";}
		 if(rule.equals("wuxingzu120")) { str = "五星组选一百二十";}
		 if(rule.equals("wuxingzu60")) { str = "五星组选六十";}
		 if(rule.equals("wuxingzu30")) { str = "五星组选三十";}
		 if(rule.equals("wuxingzu20")) { str = "五星组选二十";}
		 if(rule.equals("wuxingzu10")) { str = "五星组选十";}
		 if(rule.equals("wuxingzu5")) { str = "五星组选五";}
		 if(rule.equals("wuxingzuchong2")) { str = "五星重号全包";}
		 if(rule.equals("wuxingzuchong3")) { str = "五星三重号全包";}
		 if(rule.equals("wuxingzuchong4")) { str = "五星四重号全包";}
		 
         if(rule.equals("sixingzhixuan")) { str = "四星直选";}
         if(rule.equals("sixingzu24")) { str = "四星组选二十四";}
         if(rule.equals("sixingzu12")) { str = "四星组选十二";}
         if(rule.equals("sixingzu6")) { str = "四星组选六";}
         if(rule.equals("sixingzu4")) { str = "四星组选四";}
         
         
         if(rule.equals("sanxingzhixuan")) { str = "三星直选";}
         if(rule.equals("sanxingzu3")) { str = "三星组3";}
         if(rule.equals("sanxingzu6")) { str = "三星组6";}
         if(rule.equals("erxingzhixuan")) { str = "二星直选";}
         if(rule.equals("erxingzuxuan")) { str = "二星组选";}
         if(rule.equals("yixingzhixuan")) { str = "一星直选";}
         if(rule.equals("dxdszhixuan")) { str = "大小单双";}
         
         //12x5
         if(rule.equals("renxuan1")) { str = "任选一(1中1)";}
         if(rule.equals("renxuan2")) { str = "任选二(2中2)";}
         if(rule.equals("renxuan2zhiqian2")) { str = "任选二(直选前二)";}
         if(rule.equals("zhiqian2")) { str = "直选前二";}
         if(rule.equals("renxuan2zuqian2")) { str = "任选二(组选前二)";}
         if(rule.equals("zuqian2")) { str = "组选前二";}
         if(rule.equals("renxuan2zuqian2dantuo")) { str = "任选二(组选前二胆拖)";}
         if(rule.equals("zuqian2dantuo")) { str = "组选前二胆拖";}
         if(rule.equals("renxuan3")) { str = "任选三(3中3)";}
         if(rule.equals("renxuan3zuqian3")) { str = "任选三(组选前三)";}
         if(rule.equals("zuqian3")) { str = "组选前三";}
         if(rule.equals("renxuan3zuqian3dantuo")) { str = "任选三(组选前三胆拖)";}
         if(rule.equals("zuqian3dantuo")) { str = "组选前三胆拖";}
         if(rule.equals("renxuan3zhiqian3")) { str = "任选三(直选前三)";}
         if(rule.equals("zhiqian3")) { str = "直选前三";}
         if(rule.equals("renxuan4")) { str = "任选四(4中4)";}

         if(rule.equals("renxuan5")) { str = "任选五(5中5)";}
         if(rule.equals("renxuan6")) { str = "任选六(6中5)";}
         if(rule.equals("renxuan7")) { str = "任选七(7中5)";}
         if(rule.equals("renxuan8")) { str = "任选八(8中5)";}
         /*****修改玩法名称***去除这三中玩法*
         if(rule.equals("renxuan5zhong2")) { str = "任选二(5中2)";}
         if(rule.equals("renxuan5zhong3")) { str = "任选三(5中3)";}
         if(rule.equals("renxuan5zhong4")) { str = "任选四(5中4)";}
         */
         //***快乐十分 20选8 begin**********************//
         if(rule.equals("xuan1shutou")){str = "选一数投";}
         if(rule.equals("xuan1hongtou")){str = "选一红投";}
         if(rule.equals("xuan2renxuan")){str = "选二任选";}
         if(rule.equals("xuan2lianzu")){str = "选二连组";}
         if(rule.equals("xuan2lianzhi")){str = "选二连直";}
         if(rule.equals("xuan3renxuan")){str = "选三任选";}
         if(rule.equals("xuan3qianzu")){str = "选三前组";}
         if(rule.equals("xuan3qianzhi")){str = "选三前直";}
         if(rule.equals("xuan4renxuan")){str = "选四投注";}
         if(rule.equals("xuan5renxuan")){str = "选五投注";}
         //***快乐十分 20选8 end**********************//
         
         return str;
	}
}
