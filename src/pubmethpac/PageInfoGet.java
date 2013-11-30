//include required classes
package pubmethpac;

import javax.servlet.http.HttpServletRequest;

import beanpac.PageShow;
import dbconnpac.DataBaseConn;

public class PageInfoGet{
	private PageShow beanPageShow;
	private DataBaseConn userPageConn;
	
	public PageInfoGet(){
		beanPageShow = new PageShow();
		userPageConn= new DataBaseConn();
	}
    public void generInfo(HttpServletRequest request, String strTableName, String strSqlChild){
		 if(request.getParameter("PageNo")==null){ //如果为空，则表示第1页
			 beanPageShow.setCurrentPage(1);
		 }else{ 
			 beanPageShow.setCurrentPage(Integer.parseInt(request.getParameter("PageNo")));//获得用户提交的页数
		 }
         //获得开始显示的记录编号如：（当前页2-1）*（每页的页数为10）=10（为（当前页2）（开始的行号为10））
		 beanPageShow.setStartRow((beanPageShow.getCurrentPage() - 1) * beanPageShow.getPageRecordNum());
	     userPageConn.execQuery("select count(*) from "+strTableName+" where id in ("+strSqlChild+")");
	     userPageConn.rsNext();
	     beanPageShow.setRecordTotal(userPageConn.rsGetInt(1));//总记录数
	     //获取总页数
	     if(beanPageShow.getRecordTotal()==0){//如果总记录数为0，当前为1页
	    	 beanPageShow.setMaxPage(1);
	     }else if(beanPageShow.getRecordTotal() % beanPageShow.getPageRecordNum() == 0){//如果(总记录30%页数10=余数0),那么最大页为(总记录30/页数10=3)
	    	 beanPageShow.setMaxPage(beanPageShow.getRecordTotal()/beanPageShow.getPageRecordNum());
		 }else{//如果(总记录32%页数10=余数2不等于0),那么最大页为(总记录30/页数10+1=4)
			 beanPageShow.setMaxPage(beanPageShow.getRecordTotal()/beanPageShow.getPageRecordNum()+1);
		 }
	     beanPageShow.setPrevPage(beanPageShow.getCurrentPage()-1);
	     beanPageShow.setNextPage(beanPageShow.getCurrentPage()+1);
	     
	     userPageConn.execQuery("SELECT * FROM "+strTableName+" WHERE id IN ("+strSqlChild+") ORDER BY id DESC LIMIT "+beanPageShow.getStartRow()+", "+beanPageShow.getPageRecordNum());

    }
	public PageShow getBeanPageShow() {
		return beanPageShow;
	}
	public DataBaseConn getUserPageConn() {
		return userPageConn;
	}

}
