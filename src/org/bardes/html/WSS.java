package org.bardes.html;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.bardes.state.DisplayPool;
import org.bardes.state.DisplayState;
import org.bardes.state.OperatorState;
import org.bardes.state.ProjectorState;
import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketServer;
import org.java_websocket.handshake.ClientHandshake;

public class WSS extends WebSocketServer
{
	private static final int SOCKETSERVERPORT = 5451;
	private static WSS wss = new WSS();
	private static boolean started = false;
	
	Map<InetSocketAddress, DisplayState> addresses = new ConcurrentHashMap<InetSocketAddress, DisplayState>();
//	Map<String, WebSocket> sockets = new ConcurrentHashMap<String, WebSocket>();
//	Map<String, Collection<DisplayState>> displays = new ConcurrentHashMap<String, Collection<DisplayState>>();

	public WSS()
	{
		super(new InetSocketAddress(SOCKETSERVERPORT));
	}
	
	private DisplayState getDisplayState(WebSocket sock)
	{
		InetSocketAddress sockAddr = sock.getRemoteSocketAddress();
		DisplayState state = addresses.get(sockAddr);
		return state;
	}

	@Override
	public void onClose(WebSocket sock, int arg1, String arg2, boolean arg3) 
	{
		InetSocketAddress sockAddr = sock.getRemoteSocketAddress();
		DisplayState state = getDisplayState(sock);
		if (state != null)
		{
			state.close();
			addresses.remove(sockAddr);
			
			DisplayPool.removeDisplay(state);
		}
	}

	@Override
	public void onError(WebSocket sock, Exception ex)
	{
		if (sock == null)
		{
			ex.printStackTrace();
			return;
		}
		
		DisplayState state = getDisplayState(sock);
		if (state != null)
		{
			state.error();
		}
	}

	@Override
	public void onMessage(WebSocket sock, String msg) 
	{
		DisplayState state = getDisplayState(sock);
		if (state != null)
		{
			state.message(msg);
		}
	}

	@Override
	public void onOpen(WebSocket sock, ClientHandshake client) 
	{
		String value = client.getResourceDescriptor();
		InetSocketAddress sockAddr = sock.getRemoteSocketAddress();
		
		DisplayState state = null;
		if (value.startsWith("/operator"))
		{
			state = new OperatorState(sock);
		}
		else if (value.startsWith("/display"))
		{
			String num = value.replaceAll(".*/", "");
			int projectorId = Integer.valueOf(num);
			state = new ProjectorState(sock, projectorId);
		}
		
		if (state != null)
		{
			addresses.put(sockAddr, state);
			state.updateCue();
			DisplayPool.addDisplay(state);
		}
	}

	public static WSS getInstance()
	{
		if (!started)
		{
			started = true;
			wss.start();
		}
		return wss;
	}
	
	public void closeAll()
	{
		for (WebSocket sock : connections())
		{
			sock.close(1);
		}
		
		try
		{
			stop();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		wss = null;
	}

	public void sendAll(String string) 
	{
		for (WebSocket sock : connections())
		{
			try
			{
				sock.send("refresh");
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}
}
