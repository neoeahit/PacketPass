

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GroupQServlet
 */
public class GroupQServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupQServlet() {
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
		
		//Get parameters from calling servlet
		String groupname=request.getParameter("groupname");
		String groupaction=request.getParameter("groupaction");
		char ftp=(request.getParameter("ftp")!=null)?'Y':'N';
		char ssh=(request.getParameter("ssh")!=null)?'Y':'N';
		char smtp=(request.getParameter("smtp")!=null)?'Y':'N';
		char http=(request.getParameter("http")!=null)?'Y':'N';
		char https=(request.getParameter("https")!=null)?'Y':'N';
		
		try {
			
			//groupaction can be creategroup or modifygroup
			String content = null;
			
			if(groupaction.equals("modifygroup"))
			{
				content = new String("{"+"\"task\":\"modifygroup\",\"group\":\""+groupname.trim()+"\",\"permissions\":\""+ftp+ssh+smtp+http+https+"\""+"}");
			}
			else if(groupaction.equals("deletegroup"))
			{
				content = new String("{"+"\"task\":\"deletegroup\",\"group\":\""+groupname.trim()+"\",\"permissions\":\""+ftp+ssh+smtp+http+https+"\""+"}");
			}
			else
			{
				content = new String("{"+"\"task\":\"creategroup\",\"group\":\"group"+groupname.trim()+"\",\"permissions\":\""+ftp+ssh+smtp+http+https+"\""+"}");
			}
			
			System.out.println("Content being pushed:\n"+content);
			
			File file = new File("/Users/abhas/Documents/workspace/PacketPass/src/nodejsfile.txt");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
			
			shellclass.execShellCmd();
			
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Put a wait till the operations are complete (Do not use Thread.sleep, find alternate way)
		//Thread.sleep(10000);
		
		//Call the homepage servlet again
		response.sendRedirect("http://localhost:8080/PacketPass/homepage");
	}

}
