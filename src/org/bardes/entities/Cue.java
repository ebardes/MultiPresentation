package org.bardes.entities;

import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Cue implements Comparable<Cue>
{
	Double cue;

	private Double fadeTime = 0.5;

	private String description = "";

	private Map<Integer,Slide> slides = new TreeMap<Integer, Slide>();

	@Override
	public String toString()
	{
		return "(" + cue.toString() + slides + ")";
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
		slides.put(projector, slide);
	}

	public Slide getSlide(int projector)
	{
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
		return cue.equals(((Cue) obj).cue);
	}

	@Override
	public int hashCode()
	{
		return cue.hashCode();
	}

	public Double getFadeTime()
	{
		return fadeTime;
	}

	public void setFadeTime(Double fadeTime)
	{
		this.fadeTime = fadeTime;
	}

	public Map<Integer, Slide> getSlides()
	{
		return slides;
	}

	public void setSlides(Map<Integer, Slide> slides)
	{
		this.slides = slides;
	}

}
