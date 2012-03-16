package org.bardes.html;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.bardes.state.DisplayState;
import org.bardes.state.ProjectorState;
import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketServer;
import org.java_websocket.handshake.ClientHandshake;

public class WSS extends WebSocketServer
{
	private static final int SOCKETSERVERPORT = 5451;
	private static WSS wss = new WSS();
	private static boolean started = false;
	
	Map<InetSocketAddress, String> addresses = new ConcurrentHashMap<InetSocketAddress, String>();
	Map<String, WebSocket> sockets = new ConcurrentHashMap<String, WebSocket>();
	Map<String, DisplayState> displays = new ConcurrentHashMap<String, DisplayState>();

	public WSS()
	{
		super(new InetSocketAddress(SOCKETSERVERPORT));
	}
	
	public void registerDisplayStateCallback(String uri, ProjectorState state)
	{
		displays.put(uri, state);
	}
	
	private DisplayState getDisplayState(WebSocket sock)
	{
		InetSocketAddress sockAddr = sock.getRemoteSocketAddress();
		String v = addresses.get(sockAddr);
		if (v == null)
			return null;
		
		return displays.get(v);
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
		
		addresses.put(sockAddr, value);
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
	
	public void sendDisplay(int projectorId, String message) throws InterruptedException
	{
		String key = "/display/"+projectorId;
		if (wss.sockets.containsKey(key))
		{
			WebSocket webSocket = wss.sockets.get(key);
			webSocket.send(message);
		}
	}
}
