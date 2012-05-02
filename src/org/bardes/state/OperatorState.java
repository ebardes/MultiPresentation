package org.bardes.state;

import org.bardes.entities.Cue;
import org.java_websocket.WebSocket;

public class OperatorState extends DisplayState 
{

	public OperatorState(WebSocket sock) 
	{
		super(sock);
	}

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
				sock.send("curcue:"+getCurrentCue());
			}
			catch (Exception ignore)
			{
			}
		}
	}
}
