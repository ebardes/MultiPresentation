package org.bardes.state;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.bardes.entities.Cue;
import org.java_websocket.WebSocket;

public abstract class DisplayState implements Runnable
{
	protected BlockingQueue<String> messages = new LinkedBlockingQueue<String>();
	
	public WebSocket sock;

	private long ping;
	
	public void message(String msg)
	{
		this.ping = System.currentTimeMillis();
		messages.add(msg);
	}
	
	@Override
	public void run()
	{
		Thread.currentThread().setDaemon(true);
		for (;;)
		{
			try
			{
				String message = messages.take();
				onMessage(message);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}

	public abstract void onMessage(String message);

	public void close()
	{
		this.ping = 0;
	}

	public void error()
	{
		this.ping = 0;
	}
	
	public boolean isOkay()
	{
		return (System.currentTimeMillis() - ping) > 60000;
	}

	public abstract void goCue(Cue cue);

	public void shutdown()
	{
		// TODO Auto-generated method stub
		
	}
}
