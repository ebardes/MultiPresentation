package org.bardes.config;

import java.util.Set;
import java.util.TreeSet;

import org.bardes.entities.Cue;
import org.bardes.entities.Slide;
import org.mozilla.javascript.NativeObject;


public class DefCue
{
	public Set<Cue> cues = new TreeSet<Cue>(); 
	
	public Set<Cue> define(String cueName, NativeObject obj)
	{
		Object[] allIds = obj.getAllIds();
		
		Cue cue = new Cue();
		cue.setCue(cueName);
		cues.add(cue);
		
		System.out.println("Define cue " + cue);
		
		for (Object id : allIds)
		{
			Slide slide = new Slide();
					
			Object value = obj.get(id);
			if (value instanceof SlideType)
			{
				SlideType type = (SlideType) value;
				slide.setContentType(type.getType());
			}
			System.out.println("  " + id + ": " + slide);
			
			cue.setSlide(Integer.parseInt(id.toString()), slide);
		}
		
		return cues;
	}
	
	@Override
	public String toString()
	{
		return cues.toString();
	}
}
