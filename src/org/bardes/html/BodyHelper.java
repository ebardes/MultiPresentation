package org.bardes.html;

import javax.servlet.http.HttpServletRequest;

public class BodyHelper
{
	public BodyHelper(HttpServletRequest req)
	{
	}
	
	public CharSequence body()
	{
		return new java.util.Date().toString();
	}
}
