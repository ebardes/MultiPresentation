package org.bardes.html;

import java.io.IOException;
import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import org.bardes.entities.Show;
import org.bardes.entities.Slide;

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
	
	public CharSequence slideContent(Slide slide, Show show)
	{
		StringBuilder sb = new StringBuilder();
		URI url = URI.create(show.getBaseURL());
		
		switch (slide.getContentType())
		{
		case IMAGE:
			URI href = url.resolve(slide.getContentFile());
			sb.append("<img href=\""+href+"\" />");
		}
		return sb;
	}
	
	public String webDisplaySocketURL() throws IOException
	{
		WSS wss = WSS.getInstance();
		int port = wss.getPort();
		
		String url = "ws://" +
				req.getServerName() +
				":" + port +
				"/display/" + req.getParameter("projectorId");
		
		return url;
	}
}
