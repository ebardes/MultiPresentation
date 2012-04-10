package org.bardes.html;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

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
	
	public CharSequence slideContent(Slide slide) throws UnsupportedEncodingException
	{
		StringBuilder sb = new StringBuilder();
		String baseURL = req.getRequestURI();
		URI url = URI.create(baseURL);
		
		switch (slide.getContentType())
		{
		case IMAGE:
			url = url.resolve("slides/");
			String contentFile = slide.getContentFile();
			contentFile = URLEncoder.encode(contentFile, "UTF8");
			URI href = url.resolve(contentFile);
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
	
	public String thumbnail(Slide slide) throws UnsupportedEncodingException
	{
		String baseURL = req.getRequestURI();
		URI url = URI.create(baseURL);
		
		switch (slide.getContentType())
		{
		case IMAGE:
			url = url.resolve("slides/");
			String contentFile = slide.getContentFile();
			contentFile = URLEncoder.encode(contentFile, "UTF8");
			URI href = url.resolve(contentFile);
			return "<img src=\"" + href + "\" width=100% height=100% />";
			
		case BLANK:
			return "Blank";
			
		case TRACKED:
			return "&dArr;";
		}
		return "";
	}
}
