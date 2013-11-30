//import required classes
package servletpack;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import pubmethpac.WinPrizeGet;


//class login
public class WinNumPost extends HttpServlet
{	 
 public void doPost(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
     HttpSession session=req.getSession(false);
     
     WinPrizeGet wpg=new WinPrizeGet();
     wpg.getPrizeWinInfo();
     session.setAttribute("spadevalue", wpg.getStrSpade());
     session.setAttribute("heartvalue", wpg.getStrHeart());
     session.setAttribute("clubvalue", wpg.getStrClub());
     session.setAttribute("diamondvalue", wpg.getStrDiamond());
     session.setAttribute("winmaxissnum", wpg.getMaxIssNum());
     
     session.setAttribute("spadepiclink", wpg.getPicLinkSpade());
     session.setAttribute("heartpiclink", wpg.getPicLinkHeart());
     session.setAttribute("clubpiclink", wpg.getPicLinkClub());
     session.setAttribute("diamondpiclink", wpg.getPicLinkDiamond());
     session.setAttribute("isgetwinvalue", "false");
     
     ServletContext sc = getServletContext();
	 RequestDispatcher rd = sc.getRequestDispatcher("/lottpok/winmovie.jsp");
     rd.forward(req, res);
 }
 

 public void doGet(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
	 doPost(req,res);
 }
 
}
