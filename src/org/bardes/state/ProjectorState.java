package org.bardes.state;

import org.bardes.entities.Cue;
import org.bardes.entities.Slide;
import org.bardes.entities.Slide.Type;

public class ProjectorState extends DisplayState 
{
	private final int projectorId;
	
	public ProjectorState(int projectorId)
	{
		this.projectorId = projectorId;
	}

	@Override
	public void goCue(Cue cue) 
	{
		try
		{
			Slide slide = cue.getSlide(projectorId);
			if (slide == null || slide.getContentType() == Type.TRACKED)
				return;
			
			if (sock != null)
				sock.send("changeslide:q"+cue.getCue());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void onMessage(String message)
	{
		if (message == null)
			return;
		
		if (message.startsWith("ping"))
		{
		}
	}
}
