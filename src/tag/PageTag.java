//import required classes
package tag;

import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class PageTag extends TagSupport {
	private int curpage=0;
	private int maxpage=0;
	private int prevpage=0;
	private int nextpage=0;
	private String servhref="";


    public int doStartTag() throws JspException {
      try {
    	  JspWriter out = pageContext.getOut();
    	  out.print("<table width=100% border=1 bordercolorlight=#FFFF00><tr>");
    	  out.print("<td>总共"+maxpage+"页--当前页为"+curpage+"</td>");
    	  out.print("<td>");
    	  if(curpage==1){
    		  out.print("上一页:");
    	  }else{
    		  out.print("<a href=/"+servhref+"PageNo="+prevpage+">上一页</a>:");
    	  }
    	  if(curpage==maxpage){
    		  out.print("下一页:");
    	  }else{
    		  out.print("<a href=/"+servhref+"PageNo="+nextpage+">下一页</a>:");
    	  }
    	  if(curpage==1){
    		  out.print("第一页:");
    	  }else{
    		  out.print("<a href=/"+servhref+"PageNo=1>第一页</a>:");
    	  }
    	  if(curpage==maxpage){
    		  out.print("最后一页");
    	  }else{
    		  out.print("<a href=/"+servhref+"PageNo="+maxpage+">最后一页</a>");
    	  }
    	  out.print("</td>");
    	  out.print("</tr></table>");
      } catch (IOException ioe) {
         throw new JspException("Error:IOException while writing to client" + ioe.getMessage());
      }
      return SKIP_BODY;
    }

    public int doEndTag() throws JspException {
      return EVAL_PAGE;
    }

	public void setCurpage(int curpage) {
		this.curpage = curpage;
	}

	public void setMaxpage(int maxpage) {
		this.maxpage = maxpage;
	}

	public void setNextpage(int nextpage) {
		this.nextpage = nextpage;
	}

	public void setPrevpage(int prevpage) {
		this.prevpage = prevpage;
	}

	public void setServhref(String servhref) {
		this.servhref = servhref;
	}


}
