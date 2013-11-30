//import required classes
package servletpack;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import pubmethpac.WinPrizeGet;


//class login
public class StartWinMovie extends HttpServlet
{
 public void doPost(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
	 HttpSession session=req.getSession(false);
	 
	 WinPrizeGet wpg=new WinPrizeGet();
     wpg.getPrizeWinInfo();
     session.setAttribute("hidspadevalue", wpg.getStrSpade());
     session.setAttribute("hidheartvalue", wpg.getStrHeart());
     session.setAttribute("hidclubvalue", wpg.getStrClub());
     session.setAttribute("hiddiamondvalue", wpg.getStrDiamond());
     session.setAttribute("hidwinmaxissnum", wpg.getMaxIssNum());
     
     session.setAttribute("hidspadepiclink", wpg.getPicLinkSpade());
     session.setAttribute("hidheartpiclink", wpg.getPicLinkHeart());
     session.setAttribute("hidclubpiclink", wpg.getPicLinkClub());
     session.setAttribute("hiddiamondpiclink", wpg.getPicLinkDiamond());
     session.setAttribute("isgetwinvalue", "true");

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
