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
		String baseURL = show.getBaseURL();
		if (!baseURL.endsWith("/"))
			baseURL += "/";
		URI url = URI.create(baseURL);
		
		switch (slide.getContentType())
		{
		case IMAGE:
			URI href = url.resolve(slide.getContentFile());
			sb.append("<img src=\""+href+"\" width=100% height=100% />");
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
	
	public String thumbnail(Slide slide, Show show)
	{
		String baseURL = show.getBaseURL();
		if (!baseURL.endsWith("/"))
			baseURL += "/";
		URI url = URI.create(baseURL);
		
		switch (slide.getContentType())
		{
		case IMAGE:
			URI href = url.resolve(slide.getContentFile());
			return "<img src=\"" + href + "\" width=100% height=100% />";
			
		case BLANK:
			return "Blank";
			
		case TRACKED:
			return "&dArr;";
		}
		return "";
	}
}
