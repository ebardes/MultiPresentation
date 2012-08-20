package org.bardes.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bardes.state.DB;
import org.bardes.state.DisplayPool;

@WebServlet("/refresh/*")
public class RefreshDisplay extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		DB db = DB.getInstance();
		DisplayPool.refresh(db);
		db.close();
	}
}
