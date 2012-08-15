package org.bardes.entities;

public class Slide
{
	public static enum Type
	{
		TRACKED, BLANK, IMAGE, MOVIE 
	}
	
	Type contentType;

	String contentFile;
	
	@Override
	public String toString()
	{
		return "("+contentType+","+contentFile+")";
	}
	
	public Type getContentType()
	{
		return contentType;
	}

	public void setContentType(Type contentType)
	{
		this.contentType = contentType;
	}

	public String getContentFile()
	{
		return contentFile;
	}

	public void setContentFile(String contentFile)
	{
		this.contentFile = contentFile;
	}

	public int getId()
	{
		return 0;
	}
}
