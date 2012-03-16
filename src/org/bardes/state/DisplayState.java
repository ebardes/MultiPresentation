package org.bardes.state;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

import org.java_websocket.WebSocket;

public abstract class DisplayState implements Runnable
{
	protected BlockingQueue<String> messages = new SynchronousQueue<String>();
	
	public WebSocket sock;
	
	public void message(String msg)
	{
		messages.add(msg);
	}
	
	@Override
	public void run()
	{
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
}
