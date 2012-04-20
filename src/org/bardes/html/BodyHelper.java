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
			sb.append("<img src=\""+generateSourceURL(url, slide)+"\" width=100% height=100% />");
			break;
			
		case MOVIE:
			sb.append("<video width=\"100%\" height=\"100%\">");
			sb.append("<source src=\""+generateSourceURL(url, slide)+"\" />");
			sb.append("</video>");
			break;
		}
		return sb;
	}

	protected URI generateSourceURL(URI baseURL, Slide slide) throws UnsupportedEncodingException
	{
		baseURL = baseURL.resolve("slides/");
		String contentFile = slide.getContentFile();
		contentFile = URLEncoder.encode(contentFile, "UTF8");
		return baseURL.resolve(contentFile);
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
			return "<img src=\"" + generateSourceURL(url, slide) + "\" width=100% height=100% />";
			
		case BLANK:
			return "Blank";
			
		case TRACKED:
			return "&dArr;";
		}
		return "";
	}
}
