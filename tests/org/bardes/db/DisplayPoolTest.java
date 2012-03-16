package org.bardes.db;

import org.bardes.entities.Cue;
import org.bardes.state.DisplayPool;
import org.junit.Test;

public class DisplayPoolTest
{
	@Test
	public void test1() throws InterruptedException
	{
		DisplayPool.startup();
		DisplayPool.join();
		
		Cue go = DisplayPool.go();
		
		System.out.println(go);
	}
}
