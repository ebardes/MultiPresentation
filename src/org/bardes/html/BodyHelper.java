package org.bardes.html;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

public class BodyHelper
{
	private final HttpServletRequest req;
	private String projector;
	private String slide;

	public BodyHelper(HttpServletRequest req)
	{
		this.req = req;
		projector = req.getParameter("projector");
		slide = req.getParameter("slide");
	}
	
	public CharSequence body()
	{
		return new java.util.Date().toString();
	}
	
	public CharSequence getContentLink()
	{
		return "<img src=\"pages/Untitled."+slide+".png\" />";
	}
	
	public CharSequence getTransitionHeader()
	{
		return "body { -webkit-transition: opacity 3s linear; }";
	}
	
	public String webSocketURL() throws IOException
	{
		WSS wss = WSS.getInstance();
		int port = wss.getPort();
		
		String url = "ws://" +
				req.getServerName() +
				":" + port +
				"/" + req.getParameter("projectorId");
		
		return url;
	}
}
