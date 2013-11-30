package org.bardes.entities;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Show
{
	int id = 1;
	
	private	int maxProjectors;

	private String uploadDir;
	
	public Show()
	{
	}
	
	public String getUploadDir()
	{
		return uploadDir;
	}

	public void setUploadDir(String uploadDir)
	{
		this.uploadDir = uploadDir;
	}

	public int getMaxProjectors()
	{
		return maxProjectors;
	}

	public void setMaxProjectors(int maxProjectors)
	{
		this.maxProjectors = maxProjectors;
	}
}
