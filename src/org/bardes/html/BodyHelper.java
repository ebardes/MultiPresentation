package org.bardes.html;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

public class BodyHelper
{
	private final HttpServletRequest req;

	public BodyHelper(HttpServletRequest req)
	{
		this.req = req;
	}
	
	public CharSequence body()
	{
		return new java.util.Date().toString();
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
