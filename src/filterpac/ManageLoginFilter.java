package filterpac;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import beanpac.UserInfo;

public class ManageLoginFilter implements Filter {
   public void doFilter(ServletRequest request,
                        ServletResponse response,
                        FilterChain chain)
       throws ServletException, IOException {
     HttpServletRequest req = (HttpServletRequest)request;
     HttpServletResponse res = (HttpServletResponse) response;
     RequestDispatcher rd;
     HttpSession session=req.getSession();
     String passMes = (String)session.getAttribute("pass");
     if (passMes==null){
    	 rd=req.getRequestDispatcher("/loginmanage.jsp");
    	 rd.forward(req, res);
     }else if(!passMes.equals("success")){
    	 rd=req.getRequestDispatcher("/loginmanage.jsp");
    	 rd.forward(req, res);
     }else{
    	 chain.doFilter(request,response);
     }
   }

   public void init(FilterConfig config)throws ServletException {}
   
   public void destroy() {}
}
