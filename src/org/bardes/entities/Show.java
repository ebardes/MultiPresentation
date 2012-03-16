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
	private
	int maxProjectors;

	public int getMaxProjectors()
	{
		return maxProjectors;
	}

	public void setMaxProjectors(int maxProjectors)
	{
		this.maxProjectors = maxProjectors;
	}
}
