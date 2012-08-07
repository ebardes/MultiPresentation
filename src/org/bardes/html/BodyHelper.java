package org.bardes.html;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.bardes.entities.Show;
import org.bardes.entities.Slide;
import org.bardes.state.DisplayPool;

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
		Show show = DisplayPool.getShow();

		File uploadBaseDirectory = new File(show.getUploadDir());
		
		switch (slide.getContentType())
		{
		case IMAGE:
			sb.append("<img src=\""+generateSourceURL(url, uploadBaseDirectory, slide)+"\" width=100% height=100% />");
			break;
			
		case MOVIE:
			sb.append("<video id=\"v"+slide.getId()+"\" width=\"100%\" height=\"100%\">");
			sb.append("<source src=\""+generateSourceURL(url, uploadBaseDirectory, slide)+"\" />");
			sb.append("</video>");
			break;
			
		case BLANK:
		case TRACKED:
			// do nothing
		}
		return sb;
	}

	protected URI generateSourceURL(URI baseURL, File uploadBaseDirectory, Slide slide) throws UnsupportedEncodingException
	{
		File z = new File(uploadBaseDirectory, slide.getContentFile());

		baseURL = baseURL.resolve("slides/");
		String contentFile = slide.getContentFile();
		contentFile = URLEncoder.encode(contentFile, "UTF8");
		if (z.exists())
		{
			contentFile += "?timestamp=" + z.lastModified();
		}
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
	
	public String webOperatorSocketURL() throws IOException
	{
		WSS wss = WSS.getInstance();
		int port = wss.getPort();
		
		String url = "ws://" +
				req.getServerName() +
				":" + port +
				"/operator";
		
		return url;
	}
	
	public String thumbnail(Slide slide) throws UnsupportedEncodingException
	{
		Show show = DisplayPool.getShow();
		String baseURL = req.getRequestURI();
		URI url = URI.create(baseURL);
		File uploadBaseDirectory = new File(show.getUploadDir());
		
		switch (slide.getContentType())
		{
		case IMAGE:
			return "<img src=\"" + generateSourceURL(url, uploadBaseDirectory, slide) + "\" width=100% height=100% />";
			
		case MOVIE:
			return "Clip";
			
		case BLANK:
			return "Blank";
			
		case TRACKED:
			return "&dArr;";
		}
		return "";
	}
}
