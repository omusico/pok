//import required classes
package servletpack;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.*; 
//class login
public class Test extends HttpServlet
{	 
	private Logger log = Logger.getLogger("huacaizx");
 public void doPost(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
     HttpSession session=req.getSession(false);
     DocumentBuilderFactory factory=null;
     DocumentBuilder builder=null;
     Document doc=null; 
     try{
    	 factory = DocumentBuilderFactory.newInstance();
    	 builder=factory.newDocumentBuilder();
    	 // ServletContext sc = getServletContext();
    	 doc=builder.parse("111.xml");
    	 doc.normalize();
    	 }catch(Exception e){
    		 System.out.println(e.toString());
    	 } 
    	 ServletContext sc = getServletContext();
    	 RequestDispatcher rd = sc.getRequestDispatcher("/servlet/loginmaninit?play=pok");
         rd.forward(req, res);
 }
 
 public void doGet(HttpServletRequest req,HttpServletResponse res)
 throws IOException,ServletException
 {
	 doPost(req,res);
 }
 
}
