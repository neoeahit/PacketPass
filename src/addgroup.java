

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class addgroup
 */
public class addgroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addgroup() {
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
			    "\n<link rel=\"stylesheet\" href=\"http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css\" />" +
				
			    "<link href=\"{{ STATIC_URL }}bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\" media=\"screen\">" +
				"<link href=\"{{ STATIC_URL }}bootstrap/css/bootstrap-responsive.min.css\" rel=\"stylesheet\">" +
			    
			    "<!-- Le styles -->" +
			    "<link href=\"http://twitter.github.com/bootstrap/assets/css/bootstrap.css\" rel=\"stylesheet\">" +
//			    "<link href=\"twitter.github.com/bootstrap/assets/css/bootstrap-responsive.css\" rel=\"stylesheet\">" +
//			    "<link href=\"twitter.github.com/bootstrap/assets/css/docs.css\" rel=\"stylesheet\">" +
//			    "<link href=\"twitter.github.com/bootstrap/assets/js/google-code-prettify/prettify.css\" rel=\"stylesheet\">" +
				
			    "\n<script src=\"http://code.jquery.com/jquery-1.8.2.js\"></script>" +
			    "\n<script src=\"http://code.jquery.com/ui/1.9.1/jquery-ui.js\"></script>" +
			    "\n<link rel=\"stylesheet\" href=\"/resources/demos/style.css\" />" +
    			"\n</head>");
    	
    	out.println(
    	"<body><br><br><br>");
    	
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
    			"</ul></div></div></div></div>" );
    	
    	out.println(
    		"<tab align=center><h2>New Group" + "</h2><br>" +
    		"<form action=\"GroupQServlet\" method=\"get\">" +
    		
			"Group Name:	<input type=\"text\" name=\"groupname\" placeholder=\"example: AppServers\"><br><br>"
    		);
    	
    	out.println("<input type=\"checkbox\" name=\"ftp\" value=\"Y\" checked>ftp<br>");
    	
    	out.println("<input type=\"checkbox\" name=\"ssh\" value=\"Y\" checked>ssh<br>");
    	
    	out.println("<input type=\"checkbox\" name=\"smtp\" value=\"Y\" checked>smtp<br>");
    	
    	out.println("<input type=\"checkbox\" name=\"http\" value=\"Y\" checked>http<br>");
    	
    	out.println("<input type=\"checkbox\" name=\"https\" value=\"Y\" checked>https<br>");
    	
    	out.println(	
    			"<input type=\"hidden\" name=\"groupaction\" value=\"creategroup\">" +

    	    	"<br><button type=\"submit\" class=\"btn btn-success\">Submit</button>" +
    	    "</form>" +
    	    "</body>" +
    	    "</html>");
	}
}
