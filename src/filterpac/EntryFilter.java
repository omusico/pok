package filterpac;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import beanpac.RemindInfo;
import beanpac.RegRemindInfo;
import pubmethpac.WinPrizeGet;

/** Simple filter that prints a report on the standard output
* each time an associated servlet or JSP page is accessed.
*/

public class EntryFilter implements Filter {
   public void doFilter(ServletRequest request,
                        ServletResponse response,
                        FilterChain chain)
       throws ServletException, IOException {
     HttpServletRequest req = (HttpServletRequest)request;
     //HttpServletResponse res = (HttpServletResponse) response;
     HttpSession session=req.getSession();
     
     RemindInfo objRemindInfo=new RemindInfo();
     session.setAttribute("loginmes", objRemindInfo);
     
     RegRemindInfo objRegRemInfo=new RegRemindInfo();
     session.setAttribute("reginforemind", objRegRemInfo);
     
     WinPrizeGet wpg=new WinPrizeGet();
     wpg.getPrizeWinInfo();
     session.setAttribute("spadevalue", wpg.getStrSpade());
     session.setAttribute("heartvalue", wpg.getStrHeart());
     session.setAttribute("clubvalue", wpg.getStrClub());
     session.setAttribute("diamondvalue", wpg.getStrDiamond());
     session.setAttribute("isswinprizresult", wpg.getMaxIssNum());
     
     session.setAttribute("spadepiclink", wpg.getPicLinkSpade());
     session.setAttribute("heartpiclink", wpg.getPicLinkHeart());
     session.setAttribute("clubpiclink", wpg.getPicLinkClub());
     session.setAttribute("diamondpiclink", wpg.getPicLinkDiamond());
     session.setAttribute("isgetwinvalue", "true");
     chain.doFilter(request,response);
   }

   public void init(FilterConfig config)throws ServletException {}
   
   public void destroy() {}
}
