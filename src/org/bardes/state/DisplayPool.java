package org.bardes.state;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.bardes.entities.Show;
import org.bardes.html.WSS;

public class DisplayPool 
{
	private static Map<String, DisplayState> projectors = new HashMap<String, DisplayState>();
	private static ExecutorService threadPool = Executors.newCachedThreadPool();

	public static DisplayState get(String projectorId)
	{
		return projectors.get(projectorId);
	}

	public static void startup()
	{
		WSS wss = WSS.getInstance();
		DB db = new DB();
		Show show = db.getShow();
		
		for (int i = 1; i < show.getMaxProjectors(); i++)
		{
			String uri = "/display/"+i;
			
			ProjectorState projectorState = new ProjectorState();
			projectors.put(uri, projectorState);
			threadPool.submit(projectorState);
			
			wss.registerDisplayStateCallback(uri, projectorState);
		}
	}
}
