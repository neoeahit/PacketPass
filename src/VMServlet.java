

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VMServlet
 */
public class VMServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VMServlet() {
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
		
		// TODO Auto-generated method stub
		
				//Get parameters from calling servlet
				String groupname=request.getParameter("groupname");
				String cloudprovider = request.getParameter("cloudprovider");
				String vmaction = request.getParameter("vmaction");
				String vmsize = request.getParameter("vmsize");
				
				try {
					
					String content = new String("{"+"\"task\":\""+vmaction.trim()+"\",\"size\":\""+vmsize.trim()+"\",\"group\":\""+groupname.trim()+"\"}");
					
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
