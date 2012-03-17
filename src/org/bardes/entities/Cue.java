package org.bardes.entities;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cues")
public class Cue implements Comparable<Cue>
{
	@Id
	@Column(name = "cue", precision=8, scale=2)
	Double cue;

	@Column(length = 200)
	String description;
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="cue")
	@MapKeyColumn(name="projector")
	Map<Integer, Slide> slides;

	@Override
	public String toString()
	{
		return "("+cue.toString() + slides+")";
	}
	
	public Double getCue()
	{
		return cue;
	}

	public void setCue(Double cue)
	{
		this.cue = cue;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setCue(String n)
	{
		setCue(Double.valueOf(n));
	}

	public void setSlide(int projector, Slide slide)
	{
		if (slides == null)
			slides = new HashMap<Integer, Slide>();
		
		slides.put(projector, slide);
	}
	
	public Slide getSlide(int projector)
	{
		if (slides == null)
			return null;
		
		return slides.get(projector);
	}

	@Override
	public int compareTo(Cue o)
	{
		return cue.compareTo(o.cue);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		return cue.equals(((Cue)obj).cue);
	}
	
	@Override
	public int hashCode()
	{
		return cue.hashCode();
	}
}
