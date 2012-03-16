package org.bardes.entities;

import java.math.BigDecimal;
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
public class Cue
{
	@Id
	@Column(name = "cue", precision=8, scale=2)
	BigDecimal cue;

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
	
	public BigDecimal getCue()
	{
		return cue;
	}

	public void setCue(BigDecimal cue)
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
		setCue(new BigDecimal(n));
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
}
