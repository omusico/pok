package com.onmyway.common.displaytag;

import java.text.DecimalFormat;

import org.apache.commons.lang.time.FastDateFormat;
import org.displaytag.decorator.TableDecorator;

import com.onmyway.exb.manage.model.ExbZjInfo;
import com.onmyway.exb.manage.model.ExbZjNumConfig;
import com.onmyway.exb.play.model.ExbTzInfo;
import com.onmyway.ssc.manage.model.SscZjInfo;
import com.onmyway.ssc.manage.model.SscZjNumConfig;
import com.onmyway.ssc.play.model.SscTzInfo;
import com.onmyway.sxw.manage.model.SxwZjInfo;
import com.onmyway.sxw.manage.model.SxwZjNumConfig;
import com.onmyway.sxw.play.model.SxwTzInfo;

/**
 * @Title:displayTag 分页标签的包装类
 * @Description: 
 * @Create on: Jan 5, 2011 9:38:30 PM
 * @Author:LJY
 * @Version:1.0
 */
public class DisplayTagWrapper extends TableDecorator{
	 
	  private FastDateFormat dateFormat;
	  private DecimalFormat moneyFormat;

	  public DisplayTagWrapper()
	  {
	    this.dateFormat = FastDateFormat.getInstance("MM/dd/yy");
	    this.moneyFormat = new DecimalFormat("$ #,###,###.00");
	  }

	  public String getNullValue()
	  {
	    return null;
	  }

//	  public String getDate()
//	  {
//	    return this.dateFormat.format(((ListObject)getCurrentRowObject()).getDate());
//	  }
//
//	  public String getMoney()
//	  {
//	    return this.moneyFormat.format(((ListObject)getCurrentRowObject()).getMoney());
//	  }
	  /**
	   * 12x5删除中奖号码
	   * @return
	   */
	  public String getSxwZhongjianghaomaHref()
	  {
		SxwZjNumConfig object = (SxwZjNumConfig)getCurrentRowObject();
	    int index = getListIndex();

	    return "<a href=\"#\" onclick=\"javascript:to_del('" + object.getIssueNum() + "')\">删除</a>";
	  }
	
	 /**
	  * 12x5查看指定用户投注信息时，删除投注号码
	  * @return
	  */
	  public String getSxwUserTouzhuHref()
	  {
		  SxwTzInfo object = (SxwTzInfo)getCurrentRowObject();
	    int index = getListIndex();

//	    return "<a href=\"#\" onclick=\"javascript:to_del('" + object.getId() + "')\">删除</a>";
	    return "<input type='button' class='anniu' value='删除' onclick=\"javascript:to_del('" + object.getId() + "')\">";
	  }
	/**
	 * 12x5查看指定用户中奖信息时，删除中奖号码
	 * @return
	 */
	  public String getSxwUserZhongjiangHref()
	  {
		  SxwZjInfo object = (SxwZjInfo)getCurrentRowObject();
	    int index = getListIndex();
	    
	    //得到对应的投注信息的主键ID
// 	    return "<a href=\"#\" onclick=\"javascript:to_del('" + object.getId() + "')\">删除</a>";
	    return "<input type='button' class='anniu' value='删除' onclick=\"javascript:to_del('" + object.getTzId() + "')\">";

	  }
	  /**
	   * 时时彩删除中奖号码
	   * @return
	   */
	  public String getSscZhongjianghaomaHref()
	  {
		  SscZjNumConfig object = (SscZjNumConfig)getCurrentRowObject();
	    int index = getListIndex();

	    return "<a href=\"#\" onclick=\"javascript:to_del('" + object.getIssueNum() + "')\">删除</a>";
	  }
	  /**
	   * 时时彩查看指定用户投注信息时，删除投注号码
	   * @return
	   */
	  public String getSscUserTouzhuHref()
	  {
		  SscTzInfo object = (SscTzInfo)getCurrentRowObject();
	    int index = getListIndex();

// 	    return "<a href=\"#\" onclick=\"javascript:to_del('" + object.getId() + "')\">删除</a>";
	    return "<input type='button' class='anniu' value='删除' onclick=\"javascript:to_del('" + object.getId() + "')\">";

	  }
	/**
	 * 时时彩查看指定用户中奖信息时，删除中奖号码
	 * @return
	 */
	  public String getSscUserZhongjiangHref()
	  {
		  SscZjInfo object = (SscZjInfo)getCurrentRowObject();
	    int index = getListIndex();
	    //用href时，造成页面跳转
// 	    return "<a href=\"#\" onclick=\"javascript:to_del('" + object.getId() + "')\">删除</a>";
	    return "<input type='button' class='anniu' value='删除' onclick=\"javascript:to_del('" + object.getTzId() + "')\">";

	  }

	  /**
	   * 快乐十分删除中奖号码
	   * @return
	   */
	  public String getExbZhongjianghaomaHref()
	  {
		ExbZjNumConfig object = (ExbZjNumConfig)getCurrentRowObject();
	    int index = getListIndex();

	    return "<a href=\"#\" onclick=\"javascript:to_del('" + object.getIssueNum() + "')\">删除</a>";
	  }
	
	 /**
	  * 快乐十分查看指定用户投注信息时，删除投注号码
	  * @return
	  */
	  public String getExbUserTouzhuHref()
	  {
		  ExbTzInfo object = (ExbTzInfo)getCurrentRowObject();
	    int index = getListIndex();

//	    return "<a href=\"#\" onclick=\"javascript:to_del('" + object.getId() + "')\">删除</a>";
	    return "<input type='button' class='anniu' value='删除' onclick=\"javascript:to_del('" + object.getId() + "')\">";
	  }
	/**
	 * 快乐十分查看指定用户中奖信息时，删除中奖号码
	 * @return
	 */
	  public String getExbUserZhongjiangHref()
	  {
		  ExbZjInfo object = (ExbZjInfo)getCurrentRowObject();
	    int index = getListIndex();
	    
	    //得到对应的投注信息的主键ID
// 	    return "<a href=\"#\" onclick=\"javascript:to_del('" + object.getId() + "')\">删除</a>";
	    return "<input type='button' class='anniu' value='删除' onclick=\"javascript:to_del('" + object.getTzId() + "')\">";

	  }
}
