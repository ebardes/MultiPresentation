package org.bardes.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.bardes.state.DisplayPool;

@WebServlet(loadOnStartup=1, urlPatterns="/dummy")
public class DummyInit extends HttpServlet
{
	private static final long serialVersionUID = -2292665147586760593L;

	@Override
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		
		DisplayPool.startup();
	}
	
	@Override
	public void destroy()
	{
		DisplayPool.shutdown();
	}
}
