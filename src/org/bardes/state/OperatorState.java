package org.bardes.state;

import org.bardes.entities.Cue;

public class OperatorState extends DisplayState 
{

	@Override
	public void onMessage(String message)
	{
	}

	@Override
	public void goCue(Cue cue) 
	{
		try
		{
			if (sock != null)
			{
				sock.send("curcue:"+cue.getCue());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void updateCue()
	{
		if (sock != null)
		{
			try
			{
				sock.send("curcue:"+currentCue);
			}
			catch (Exception ignore)
			{
			}
		}
	}
}
