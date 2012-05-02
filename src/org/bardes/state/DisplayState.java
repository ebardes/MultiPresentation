package org.bardes.state;

import java.net.InetSocketAddress;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.bardes.entities.Cue;
import org.java_websocket.WebSocket;

public abstract class DisplayState implements Runnable, Comparable<DisplayState>
{
	protected BlockingQueue<String> messages = new LinkedBlockingQueue<String>();
	
	public final WebSocket sock;

	private long ping;

	private final InetSocketAddress sockAddr;
	
	public DisplayState(WebSocket sock) 
	{
		this.sock = sock;
		this.sockAddr = sock.getRemoteSocketAddress();
	}
	
	@Override
	public String toString() 
	{
		return getClass().getName() + "/" + sockAddr;
	}
	
	public void message(String msg)
	{
		this.ping = System.currentTimeMillis();
		messages.add(msg);
	}
	
	@Override
	public int compareTo(DisplayState o) 
	{
		int p1 = sockAddr.getPort();
		int p2 = o.sockAddr.getPort();
		return p1 - p2;
	}
	
	@Override
	public boolean equals(Object obj) 
	{
		return compareTo((DisplayState) obj) == 0;
	}
	
	@Override
	public int hashCode() 
	{
		return sock.getLocalSocketAddress().getPort();
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
	}

	public abstract void updateCue();

	protected Double getCurrentCue()
	{
		return DisplayPool.getCurrentCue();
	}
}
