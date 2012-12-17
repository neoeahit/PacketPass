
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class editgroups
 */
public class editgroups extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editgroups() {
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
		
		String groupname=request.getParameter("groupname");
		
		System.out.println("Editing permissions for group: " + groupname);
		
		Group gr = new Group();
		
		gr.name = groupname;
		
		File file = new File("/Users/abhas/Documents/workspace/PacketPass/src/groupsettings.txt");
    	Scanner scanner = new Scanner(file);
    	
    	while ( scanner.hasNextLine() )
        {
        	String line = scanner.nextLine();
            if( line.contains(gr.name) )
            {
            	//found group, now read permissions
            	line = scanner.nextLine();
            	
            	//scan permissions
            	StringTokenizer stk = new StringTokenizer(line, ";");
            	
            	while(stk.hasMoreTokens())
            	{
            		String token = stk.nextToken().trim();
            		boolean allowed = false;
            		
            		if( token.contains(":Y") || token.contains(":y") )
            		{
            			allowed = true;
            		}
            		
            		System.out.println("Token found:"+token);
            		
            		if( token.contains("TCP") || token.contains("tcp") )
            				{
            					gr.tcp = ( token.contains(":Y") || token.contains(":y") ) ?'Y':'N';
            				}
            		else if( token.contains("SSH") || token.contains("ssh") )
    				{
    					gr.ssh = ( token.contains(":Y") || token.contains(":y") ) ?'Y':'N';
    				}
            		else if( token.contains("SMTP") || token.contains("smtp") )
    				{
    					gr.smtp = ( token.contains(":Y") || token.contains(":y") ) ?'Y':'N';
    				}
            		else if( ( token.contains("HTTP") || token.contains("http") ) && !( token.contains("HTTPS") || token.contains("https") ))
    				{
    					gr.http = ( token.contains(":Y") || token.contains(":y") ) ?'Y':'N';
    				}
            		else if( token.contains("HTTPS") || token.contains("https") )
    				{
    					gr.https = ( token.contains(":Y") || token.contains(":y") ) ?'Y':'N';
    				}
            	}
            	
            	System.out.println("Group Permissions:" + gr.tcp + "\t" + gr.ssh + "\t" + gr.smtp + "\t" + gr.http + "\t" + gr.https);
            }
        }
    	
    	//Display current permissions
    	
    	PrintWriter out = response.getWriter();
    	
    	out.println("<!doctype html>" + 
				"\n<html lang=\"en\">" + 
				"\n<head>" +
			    "\n<meta charset=\"utf-8\" />" +
			    "\n<title>Packetpass Firewall Manager</title>" +
			    "\n<link rel=\"stylesheet\" href=\"http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css\" />" +
				
			    "<link href=\"{{ STATIC_URL }}bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\" media=\"screen\">" +
				"<link href=\"{{ STATIC_URL }}bootstrap/css/bootstrap-responsive.min.css\" rel=\"stylesheet\">" +
			    
			    "<!-- Le styles -->" +
			    "<link href=\"twitter.github.com/bootstrap/assets/css/bootstrap.css\" rel=\"stylesheet\">" +
			    "<link href=\"twitter.github.com/bootstrap/assets/css/bootstrap-responsive.css\" rel=\"stylesheet\">" +
			    "<link href=\"twitter.github.com/bootstrap/assets/css/docs.css\" rel=\"stylesheet\">" +
			    "<link href=\"twitter.github.com/bootstrap/assets/js/google-code-prettify/prettify.css\" rel=\"stylesheet\">" +
				
			    "\n<script src=\"http://code.jquery.com/jquery-1.8.2.js\"></script>" +
			    "\n<script src=\"http://code.jquery.com/ui/1.9.1/jquery-ui.js\"></script>" +
			    "\n<link rel=\"stylesheet\" href=\"/resources/demos/style.css\" />" +
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
    			"<li class=\"\">" +
    			"<a href=\"./addgroup\">Add New Group</a>" +
    			"</li>" +
    			"<li class=\"\">" +
    			"<a href=\"./addvm\">Add new VM</a>" +
    			"</ul></div></div></div></div>" );

    	out.println(	
    	"<h1>Edit Permissions for " + gr.name + "</h1>");
    	
    out.println(
    		"<form action=\"GroupQServlet\" method=\"get\">");
    	if(gr.tcp=='N')
    	{
    		out.println("<br><br><input type=\"checkbox\" name=\"tcp\" value=\"Y\">tcp<br>");
    	}
    	else
    	{
    		out.println("<input type=\"checkbox\" name=\"tcp\" value=\"Y\" checked>tcp<br>");
    	}
    	if(gr.ssh=='N')
    	{
    		out.println("<input type=\"checkbox\" name=\"ssh\" value=\"Y\">ssh<br>");
    	}
    	else
    	{
    		out.println("<input type=\"checkbox\" name=\"ssh\" value=\"Y\" checked>ssh<br>");
    	}
    	if(gr.smtp=='N')
    	{
    		out.println("<input type=\"checkbox\" name=\"smtp\" value=\"Y\">smtp<br>");
    	}
    	else
    	{
    		out.println("<input type=\"checkbox\" name=\"smtp\" value=\"Y\" checked>smtp<br>");
    	}
    	if(gr.http=='N')
    	{
    		out.println("<input type=\"checkbox\" name=\"http\" value=\"Y\">http<br>");
    	}
    	else
    	{
    		out.println("<input type=\"checkbox\" name=\"http\" value=\"Y\" checked>http<br>");
    	}
    	if(gr.https=='N')
    	{
    		out.println("<input type=\"checkbox\" name=\"https\" value=\"Y\">https<br>");
    	}
    	else
    	{
    		out.println("<input type=\"checkbox\" name=\"https\" value=\"Y\" checked>https<br>");
    	}
    	    out.println(	
    			"<input type=\"hidden\" name=\"groupaction\" value=\"modifygroup\">" +
    			"<input type=\"hidden\" name=\"groupname\" value=\"" + gr.name + "\">" +
    	    	"<br><input type=\"submit\" value=\"Submit\">" +
    	    	"</form>");
    	    
    	    out.println(
    	    		"<form action=\"GroupQServlet\" method=\"get\">" +
    	    			"<input type=\"hidden\" name=\"groupaction\" value=\"deletegroup\">" +
    	        		"<input type=\"hidden\" name=\"groupname\" value=\"" + gr.name + "\">" +
    	    			"<br><input type=\"submit\" value=\"Delete Group\"><br><br>");
    	    
    	    out.println(
    	    	"</body>" +
    	    	"</html>");
	}
}
