package org.bardes.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bardes.db.DBCreation;

/**
 * Servlet implementation class Rebuild
 */
@WebServlet("/Rebuild")
public class Rebuild extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		rebuild();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		rebuild();
	}

	private void rebuild() 
	{
		DBCreation dbc = new DBCreation();
		dbc.t1();
	}
}
