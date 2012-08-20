package org.bardes.state;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.bardes.entities.Cue;
import org.bardes.entities.Show;
import org.bardes.entities.Slide;
import org.bardes.entities.Slide.Type;

public class DBXML extends DB
{
	private File cuesDir;

	public DBXML()
	{
		cuesDir = new File(baseDirectory);
		cuesDir = new File(cuesDir, "cues");
		if (!cuesDir.exists())
			cuesDir.mkdirs();
	}

	@Override
	public void close()
	{
	}

	@Override
	public List<Cue> getCues()
	{
		
		File[] files = cuesDir.listFiles(new FilenameFilter()
		{
			@Override
			public boolean accept(File arg0, String arg1)
			{
				return arg1.contains(".xml");
			}
		});
		
		SortedSet<Cue> list = new TreeSet<Cue>();
		for (File f : files)
		{
			try
			{
				FileInputStream is = new FileInputStream(f);
				try
				{
					XMLDecoder xmlDecoder = new XMLDecoder(is);
					Cue cue = (Cue) xmlDecoder.readObject();
					list.add(cue);
					xmlDecoder.close();
				}
				finally
				{
					is.close();
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return new ArrayList<Cue>(list);
	}

	@Override
	public void save(Cue c)
	{
		File f = fileForCue(c.getCue());
		try
		{
			FileOutputStream os = new FileOutputStream(f);
			XMLEncoder encoder = new XMLEncoder(os);
			encoder.writeObject(c);
			encoder.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private File fileForCue(Double cue)
	{
		return new File(cuesDir, cue+".xml");
	}

	@Override
	public void save(Show show)
	{
		File f = new File(baseDirectory, "show.xml");
		try
		{
			FileOutputStream os = new FileOutputStream(f);
			XMLEncoder encoder = new XMLEncoder(os);
			encoder.writeObject(show);
			encoder.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void save(Collection<Cue> cues)
	{
		for (Cue c : cues)
		{
			save(c);
		}
	}

	@Override
	public Show getShow()
	{
		File f = new File(baseDirectory, "show.xml");
		try
		{
			FileInputStream is = new FileInputStream(f);
			XMLDecoder decoder = new XMLDecoder(is);
			Show show = (Show) decoder.readObject();
			decoder.close();
			is.close();
			
			return show;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Cue getCue(Double cueNum)
	{
		File f = fileForCue(cueNum);
		try
		{
			FileInputStream is = new FileInputStream(f);
			XMLDecoder decoder = new XMLDecoder(is);
			Cue cue = (Cue) decoder.readObject();
			decoder.close();
			is.close();
			
			return cue;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void saveImage(Double cueNum, int displayNum, String fileName, Type type, Double fadeTime)
	{
		Cue cue = new Cue();
		cue.setCue(cueNum);
		cue.setFadeTime(fadeTime);
		
		Slide slide = new Slide();
		slide.setContentFile(fileName);
		slide.setContentType(type);
		cue.setSlide(displayNum, slide);
		
		save(cue);
	}

	@Override
	public void blankSlide(Double cueNum, int projector)
	{
		Cue cue = new Cue();
		cue.setCue(cueNum);
		Slide slide = new Slide();
		slide.setContentFile(null);
		slide.setContentType(Type.BLANK);
		cue.setSlide(projector, slide);
		
		save(cue);
	}

	@Override
	public void trackSlide(Double cueNum, int projector)
	{
		Cue cue = new Cue();
		cue.setCue(cueNum);
		Slide slide = new Slide();
		slide.setContentFile(null);
		slide.setContentType(Type.TRACKED);
		cue.setSlide(projector, slide);
		
		save(cue);
	}

	@Override
	public void deleteCue(Double cueNum)
	{
		File f = fileForCue(cueNum);
		f.delete();
	}

}
