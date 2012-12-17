import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class homepage
 */
public class homepage extends HttpServlet
{
	String sourcepath = "/Users/abhas/Documents/workspace/PacketPass/src/";
    
	public class SecGroup
	{
		String name;
		ArrayList<VM> vmlist = new ArrayList<VM>();
	}
	
	public class VM
	{
		String name;
		String ip;
	}
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public homepage()
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @throws Exception 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception 
    {
		// TODO Auto-generated method stub
    	response.setContentType("text/html;charset=UTF-8");
    	
    	ArrayList<SecGroup> grouplist = new ArrayList<SecGroup>();
    	
    	File file = new File("/Users/abhas/Documents/workspace/PacketPass/src/config.txt");
    	Scanner scanner = new Scanner(file);
        
    	int numvms = 0;
    	int numgroups = 0;
    	
    	while ( scanner.hasNextLine() )
        {
        	String line = scanner.nextLine();
            if( line.toLowerCase().contains("group") )
            {
            	//add new group to list
            	SecGroup g = new SecGroup();
            	g.name = line.trim();
            	
            	line = scanner.nextLine();
            	
            	//add vm's to last group
            	StringTokenizer stk = new StringTokenizer(line, ";");
            	while(stk.hasMoreTokens())
            	{
            		String token = stk.nextToken().trim();
            		VM tempvm = new VM();
            		tempvm.ip = token;
            		tempvm.name = token;
            		g.vmlist.add(tempvm);
            		numvms++;
            	}
            	
            	grouplist.add(g);
            	numgroups++;
            }
        }
    	
    	PrintWriter out = response.getWriter();
    	
    	/*Output HTML/Javascript page*/
    	out.println("<!doctype html>" + 
    				"\n<html lang=\"en\">" + 
    				"\n<head>" +
				    "\n<meta charset=\"utf-8\" />" +
				    "\n<title>Packetpass Firewall Manager</title>" +
				    "\n<link rel=\"stylesheet\" href=\"http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css\" />" +
					
				    "<link href=\"{{ STATIC_URL }}bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\" media=\"screen\">" +
					"<link href=\"{{ STATIC_URL }}bootstrap/css/bootstrap-responsive.min.css\" rel=\"stylesheet\">" +
				    
				    "<!-- Le styles -->" +
				    "<link href=\"assets/css/bootstrap.css\" rel=\"stylesheet\">" +
				    "<link href=\"assets/css/bootstrap-responsive.css\" rel=\"stylesheet\">" +
				    "<link href=\"assets/css/docs.css\" rel=\"stylesheet\">" +
				    "<link href=\"assets/js/google-code-prettify/prettify.css\" rel=\"stylesheet\">" +
					
				    "\n<script src=\"http://code.jquery.com/jquery-1.8.2.js\"></script>" +
				    "\n<script src=\"http://code.jquery.com/ui/1.9.1/jquery-ui.js\"></script>" +
				    "\n<link rel=\"stylesheet\" href=\"/resources/demos/style.css\" />" +
				    "\n<style>");
    	
    	for(int i=1;i<=numgroups;i++)
    	{
    		out.println("\n#droppable" +i+" { width: 200px; height: 100px; padding: 0.5em; float: left; margin: 20px; }");
    	}
    	for(int i=1;i<=numvms;i++)
    	{
    		out.println("\n#draggable"+i+" { width: 30px; height: 50px; padding: 0.5em; float: left; margin: 20px 20px 20px 0; }");
    	}
    	out.println("\n</style>");
    	out.println("\n<script>");
    	
    	out.println("\nvar hidden = null;");
    	
    	out.println("\nfunction placement(zone)" +
    			"\n{" +
    			"\nalert(hidden + \" moved to \" + zone);" +
    			"\nloadSQS(hidden, zone);" + 
    			"\n}" +
    			"\nfunction setHidden(hiddenvalue)" +
    			"\n{" +
    				"\nhidden = hiddenvalue;" +
    				"\nreturn true;" +
    			"\n}");
    	
    	out.println("function loadSQS(vm, zone)" + 
    				"{" +  
    				"document.location.href = \"Qservlet?vmname=\"+vm+\"&zone=\"+zone;" +  
    				"}");
    	
    	out.println("\n$(function() {");
    	
    	for(int i=1;i<=numvms;i++)
    	{
    		out.println("\n$( \"#draggable"+i+"\" ).draggable();");
    	}
    	int i = 1;
    	for(SecGroup group:grouplist)
    	{
    		out.println("\n$( \"#droppable"+i+"\" ).droppable({" + 
    					"\ndrop: function( event, ui ) {" + 
    					"\nplacement(\""+group.name+"\");" + 
    					"\n}" + 
    					"\n});");
    		i++;
    	}
    	
    	out.println("\n});");
    	
    	out.println("\n</script>" + 
    				"\n</head>" + 
    				"\n<body>");
    	
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
    			"</ul></div></div></div></div>" );
			
    	
    	i =1;
    	int j=1;
    	for(SecGroup group:grouplist)
    	{
    		//display group
    		
    		out.println("\n<div id=\"droppable"+j+"\" class=\"ui-widget-header\">" + 
    					"\n<p><a href=\"./editgroups?groupname="+group.name+"\">" + group.name + "</p>" + 
    					"\n</div>");
    		
    		for(VM vmac:group.vmlist)
    		{
    			//display VM
    			out.println("\n<div id=\"draggable"+i+"\" class=\"ui-widget-content\" onMouseDown=\"setHidden('"+vmac.name+"');\">" + 
    						"\n<p>"+vmac.name+"</p>" + 
    						"\n</div>");
    			i++;
    		}
    		
    		out.println("\n<br><br><br><br><br><br><br><br><br>");
    		j++;
    	}
    	
    	out.println("\n</body></html>");
    }
    
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

}