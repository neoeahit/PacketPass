

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class bootstraptrial
 */
public class bootstraptrial extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bootstraptrial() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		try
		{
			processRequest(request, response);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		try
		{
			processRequest(request, response);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		// TODO Auto-generated method stub
		
		//Display form
    	
    	PrintWriter out = response.getWriter();
    	
    	out.println(
    	"<!DOCTYPE html>" +
    	"<html>");
    	
    	out.println("<!doctype html>" + 
				"\n<html lang=\"en\">" + 
				"\n<head>" +
			    "\n<meta charset=\"utf-8\" />" +
			    "\n<title>Packetpass Firewall Manager</title>" +
			    
				"<script type=\"text/javascript\" src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js\"></script>" +
			    
			    "<!-- Le styles -->" +
			    "<link href=\"http://twitter.github.com/bootstrap/assets/css/bootstrap.css\" rel=\"stylesheet\">" +
			    "\n</head>");
    	
    	out.println(
    	"<body>");
    	
    	out.println("\n<div class=\"navbar navbar-inverse navbar-fixed-top\">" +
    			"<div class=\"navbar-inner\">" +
    			"<div class=\"container\">" +
    			"<button type=\"button\" class=\"btn btn-navbar\" data-toggle=\"collapse\" data-target=\".nav-collapse\">" +
    			"<span class=\"icon-bar\"></span>" +
    			"<span class=\"icon-bar\"></span>" +
    			"<span class=\"icon-bar\"></span>" +
    			"</button>" +
    			"<a class=\"brand\" href=\"./homepage\">PacketPass</a>" +
    			"<div class=\"nav-collapse collapse\">" +
    			"<ul class=\"nav\">" +
    			"<li class=\"\">" +
    			"<a href=\"./homepage\">Home</a>" +
    			"</li>" +
    			"</ul></div></div></div></div>" +
    			"<script type=\"text/javascript\" src=\"http://twitter.github.com/bootstrap/js/bootstrap.js\"></script>");
    	
    	out.println(

    	    "</body>" +
    	    "</html>");
	}
}
