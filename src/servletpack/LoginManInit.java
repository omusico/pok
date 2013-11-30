//import required classes
package servletpack;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


public class LoginManInit extends HttpServlet
{
 public void doPost(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException{
	 Logger log = Logger.getLogger("huacai");

	 HttpSession session=req.getSession(false);
	 ServletContext sc = getServletContext();
	 RequestDispatcher rd = sc.getRequestDispatcher("/e");
	 String strFromlogman=(String)session.getAttribute("fromlogman");
	 if(strFromlogman==null){
		 rd = sc.getRequestDispatcher("/e");
	 }else if(strFromlogman.equals("true")){
		 String strPlay=req.getParameter("play");
		 if(strPlay.equals("manplat")){
			 rd = sc.getRequestDispatcher("/WEB-INF/usermanage/manplat.jsp");
		 }
		 if(strPlay.equals("lau")){
			 rd = sc.getRequestDispatcher("/WEB-INF/usermanage/lau/lauplat.jsp");
		 }
		 if(strPlay.equals("hap")){
			 rd = sc.getRequestDispatcher("/WEB-INF/usermanage/hap/happlat.jsp");

		 }
		 if(strPlay.equals("pth")){
			 rd = sc.getRequestDispatcher("/WEB-INF/usermanage/pth/pthplat.jsp");

		 }
		 if(strPlay.equals("pok")){
			 rd = sc.getRequestDispatcher("/WEB-INF/usermanage/pok/pokplat.jsp");
		 }
		 if(strPlay.equals("bul")){
			 rd = sc.getRequestDispatcher("/WEB-INF/usermanage/bul.jsp");
		 }
	 }
	 rd.forward(req, res);
 }
 
 public void doGet(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException{
	 doPost(req,res);
 }
}
