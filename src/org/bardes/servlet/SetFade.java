package org.bardes.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bardes.state.DisplayPool;

@WebServlet("/setfade/*")
public class SetFade extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String p = req.getRequestURI();
		
		int n = p.lastIndexOf("/");
		p = p.substring(n+1);

		p = p.trim();
		String[] split = p.split("\\s*,\\s*");
		
		Double cueNum = Double.valueOf(split[0]);
		Double fadeTime = Double.valueOf(split[1]);
		
		DisplayPool.setFadeTime(cueNum, fadeTime);
		
	}		
}
