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

	@Column(name="upload_dir", length=250)
	private String uploadDir;
	
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
