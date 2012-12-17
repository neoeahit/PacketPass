

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
    	"<html>" +
    	"<body>" +
    		"<h2>Edit Permissions for new group:" + "</h2><br>" +
    		"<form action=\"GroupQServlet\" method=\"get\">" +
    		
			"Group Name:	<input type=\"text\" name=\"groupname\"><br><br>"
    		);
    	
    	out.println("<input type=\"checkbox\" name=\"tcp\" value=\"Y\" checked>tcp<br>");
    	
    	out.println("<input type=\"checkbox\" name=\"ssh\" value=\"Y\" checked>ssh<br>");
    	
    	out.println("<input type=\"checkbox\" name=\"smtp\" value=\"Y\" checked>smtp<br>");
    	
    	out.println("<input type=\"checkbox\" name=\"http\" value=\"Y\" checked>http<br>");
    	
    	out.println("<input type=\"checkbox\" name=\"https\" value=\"Y\" checked>https<br>");
    	
    	out.println(	
    			"<input type=\"hidden\" name=\"groupaction\" value=\"creategroup\">" +

    	    	"<input type=\"submit\" value=\"Submit\">" +
    	    "</form>" +
    	    "</body>" +
    	    "</html>");
	}
}
