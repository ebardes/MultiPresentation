package org.bardes.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bardes.state.DisplayPool;

@WebServlet("/track/*")
public class Track  extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String p = req.getRequestURI();
		int n = p.lastIndexOf("/");
		p = p.substring(n+1);
		
		DisplayPool.track(p);
	}
}
