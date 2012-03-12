package org.bardes.html;

import javax.servlet.http.HttpServletRequest;

public class BodyHelper
{
	private String projector;
	private String slide;

	public BodyHelper(HttpServletRequest req)
	{
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
}
