package org.bardes.state;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.bardes.entities.Cue;
import org.bardes.html.WSS;


public class DisplayPool
{
	private static List<DisplayState> displays = new ArrayList<DisplayState>();
	private static ExecutorService threadPool = Executors.newCachedThreadPool();
	
	private static List<Cue> cues;
	
	private static Cue currentCue = null;
	private static Thread t;

	public static void startup()
	{
		t = new Thread(new Runnable() {

			public void run()
			{
				try
				{
					Thread.sleep(10000);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
					return;
				}
				
				DB db = new DB();
				cues = db.getCues();
				Collections.sort(cues);
				
				if (currentCue == null && cues.size() > 0)
					currentCue = cues.get(0);
				
				t = null;
			}
		});
		
		t.start();
	}
	
	public static void goCue(Cue cue)
	{
		currentCue = cue;
		if (displays == null)
			return;
		
		for (DisplayState d : displays)
		{
			d.goCue(cue);
		}
	}
	
	public static void goCue(String cue)
	{
		int n;
		if (cue.equalsIgnoreCase("next")) 
		{
			n = cues.indexOf(currentCue);
			n++;
		}
		else if (cue.equalsIgnoreCase("prev")) 
		{
			n = cues.indexOf(currentCue);
			n--;
		}
		else
		{
			Cue x = new Cue();
			x.setCue(cue);
			
			n = cues.indexOf(x);
		}
		if (n >= 0 && n < cues.size())
		{
			Cue c = cues.get(n);
			goCue(c);
		}
	}
	
	public static Cue go()
	{
		if (cues == null || cues.size() == 0)
			return null;
		
		if (currentCue == null)
		{
			goCue(cues.get(0));
		}
		else
		{
			int n = cues.indexOf(currentCue);
			if (n+1 < cues.size())
			{
				goCue(cues.get(n+1));
			}
		}
		return currentCue;
	}
	
	public static void join() throws InterruptedException
	{
		t.join();
	}

	public static void shutdown()
	{
		WSS wss = WSS.getInstance();
		wss.closeAll();
		
		for (DisplayState s : displays)
		{
			s.shutdown();
		}
		threadPool.shutdownNow();
	}

	public static void refresh()
	{
		DB db = new DB();
		cues = db.getCues();
		Collections.sort(cues);
		
		WSS wss = WSS.getInstance();
		wss.sendAll("refresh");
	}

	public static void blank(String p) 
	{
		String[] split = p.split(",");
		
		Double cueNum = Double.valueOf(split[0]);
		int projector = Integer.valueOf(split[1]);
		
		DB db = new DB();
		
		db.blankSlide(cueNum, projector);
		
		cues = db.getCues();
		refresh();
	}

	public static void track(String p) 
	{
		String[] split = p.split(",");
		
		Double cueNum = Double.valueOf(split[0]);
		int projector = Integer.valueOf(split[1]);
		
		DB db = new DB();
		
		db.trackSlide(cueNum, projector);
		
		cues = db.getCues();
		refresh();
	}
	
	public static Collection<Cue> getCues()
	{
		return cues;
	}
	
	public static void addDisplay(DisplayState display)
	{
		displays.add(display);
		System.out.println("Add display: "+display + " " + displays);
	}
	
	public static void removeDisplay(DisplayState display)
	{
		displays.remove(display);
		System.out.println("Remove display: "+display + " " + displays);
	}

	public static Double getCurrentCue() 
	{
		return currentCue.getCue();
	}
}
