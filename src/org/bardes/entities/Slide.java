package org.bardes.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "slides")
public class Slide
{
	public static enum Type
	{
		TRACKED, BLANK, IMAGE, MOVIE 
	}
	
	@Id
	@GeneratedValue
	int id;

	@Column
	@Enumerated(EnumType.ORDINAL)
	Type contentType;

	@Column(length = 255)
	String contentFile;
	
	@Override
	public String toString()
	{
		return "("+contentType+","+contentFile+")";
	}
	
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
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
}
