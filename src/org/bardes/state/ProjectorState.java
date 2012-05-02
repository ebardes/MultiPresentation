package org.bardes.state;

import org.bardes.entities.Cue;
import org.bardes.entities.Slide;
import org.bardes.entities.Slide.Type;
import org.java_websocket.WebSocket;

public class ProjectorState extends DisplayState 
{
	private final int projectorId;
	
	public ProjectorState(WebSocket sock, int projectorId)
	{
		super(sock);
		this.projectorId = projectorId;
	}
	
	@Override
	public String toString()
	{
		return super.toString() + ":Display#"+projectorId;
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
			{
				sock.send("changeslide:q"+cue.getCue());
				
				if (slide.getContentType() == Type.MOVIE)
				{
					sock.send("startclip:v" + slide.getId());
				}
			}
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

	@Override
	public void updateCue()
	{
		if (sock != null)
		{
			try
			{
				sock.send("changeslide:q"+getCurrentCue());
			}
			catch (Exception ignore)
			{
			}
		}
	}
}
