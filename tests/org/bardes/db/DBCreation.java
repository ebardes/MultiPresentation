package org.bardes.db;

import java.util.List;

import org.bardes.entities.Cue;
import org.bardes.entities.Show;
import org.bardes.entities.Slide;
import org.bardes.entities.Slide.Type;
import org.bardes.state.DB;
import org.junit.Test;

public class DBCreation
{
	@Test
	public void t1()
	{
		Cue c;
		
		DB db = DB.getInstance();
		
		Show show = new Show();
		show.setMaxProjectors(3);
		show.setUploadDir("/slides");
		
		db.save(show);
		
		
		c = new Cue();
		c.setCue("1");
		
		for (int p = 1; p <= 3; p++)
		{
			Slide s = new Slide();
			s.setContentType(Type.BLANK);
			
			c.setSlide(p, s);
		}
		
		db.save(c);
		db.close();
	}
	
	@Test
	public void t2()
	{
		DB db = DB.getInstance();
		
		List<Cue> cues = db.getCues();
		db.close();
		
		System.out.println(cues);
	}
}
