package org.bardes.state;

import java.io.Closeable;
import java.io.File;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.bardes.entities.Cue;
import org.bardes.entities.Show;
import org.bardes.entities.Slide.Type;

public abstract class DB implements Closeable
{
	public final String baseDirectory;
	
	/**
	 * Normal initialization.
	 */
	public DB()
	{
		baseDirectory = System.getProperty("user.home") + File.separatorChar + "mp";
	}
	
	public static DB getInstance()
	{
		try
		{
			return new DBXML();
		}
		catch (JAXBException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public abstract void close();
	
	public abstract List<Cue> getCues();

	public abstract void save(Cue c);
	
	public abstract void save(Show show);
	
	public abstract void save(Collection<Cue> cues);
	
	public abstract Show getShow();

	public abstract Cue getCue(Double cueNum);

	public abstract void saveImage(Double cueNum, int displayNum, String fileName, Type type, Double fadeTime);

	public abstract void blankSlide(Double cueNum, int projector);
	
	public abstract void trackSlide(Double cueNum, int projector);

	public abstract void deleteCue(Double cueNum);
}
