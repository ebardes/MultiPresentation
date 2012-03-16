package org.bardes.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="show")
public class Show
{
	@Id int id = 1;
	
	@Column(name="max_projectors")
	private	int maxProjectors;

	@Column(name="base_url", length=250)
	private String baseURL;
	
	public int getMaxProjectors()
	{
		return maxProjectors;
	}

	public void setMaxProjectors(int maxProjectors)
	{
		this.maxProjectors = maxProjectors;
	}

	public String getBaseURL()
	{
		return baseURL;
	}

	public void setBaseURL(String baseURL)
	{
		this.baseURL = baseURL;
	}
}
