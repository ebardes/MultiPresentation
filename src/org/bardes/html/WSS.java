package org.bardes.html;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketServer;
import org.java_websocket.handshake.ClientHandshake;

public class WSS extends WebSocketServer
{
	private static final int SOCKETSERVERPORT = 5451;
	private static WSS wss = new WSS();
	private static boolean started = false;
	
	Map<String, WebSocket> sockets = new ConcurrentHashMap<String, WebSocket>();

	public WSS()
	{
		super(new InetSocketAddress(SOCKETSERVERPORT));
	}

	@Override
	public void onClose(WebSocket sock, int arg1, String arg2, boolean arg3) 
	{
	}

	@Override
	public void onError(WebSocket sock, Exception ex)
	{
	}

	@Override
	public void onMessage(WebSocket sock, String arg1) 
	{
		try 
		{
			sock.send("Registered");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void onOpen(WebSocket sock, ClientHandshake client) 
	{
		String value = client.getResourceDescriptor();
		while (value.startsWith("/"))
			value = value.substring(1);
		
		sockets.put(value, sock);
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
	
	public  void send(String projectorId, String message) throws InterruptedException
	{
		if (wss.sockets.containsKey(projectorId))
		{
			WebSocket webSocket = wss.sockets.get(projectorId);
			webSocket.send(message);
		}
	}
}
