package org.bardes.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bardes.state.ProjectorPool;
import org.bardes.state.ProjectorState;

/**
 * Servlet implementation class AJAX1
 */
@WebServlet("/AJAX1")
public class AJAX1 extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private ProjectorPool projectorPool;

	@Override
	public void init(ServletConfig config) throws ServletException 
	{
		super.init(config);
		projectorPool = new ProjectorPool();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/json");
		
		PrintWriter out = response.getWriter();
		
		String projectorId = request.getParameter("projectorId");
		ProjectorState state = projectorPool.get(projectorId);
		
		out.println("{ \"slide\": \"" + state.getCurrentSlide() + "\"}");
	}
}
