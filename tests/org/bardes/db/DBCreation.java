package org.bardes.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.derby.jdbc.ClientDataSource;
import org.bardes.entities.Cue;
import org.bardes.entities.Show;
import org.bardes.entities.Slide;
import org.bardes.entities.Slide.Type;
import org.bardes.state.DB;
import org.junit.Test;

public class DBCreation
{
	DB db = new DB(true);
	
//	@Test
	public void jndiCreate() throws NamingException, SQLException
	{
		InitialContext ic = new InitialContext();
		
		ClientDataSource ds = new ClientDataSource();
		ds.setDatabaseName("mp");
		ds.setUser("app");
		ds.setPassword("app");
		ds.setServerName("bardes.org");
		ds.setConnectionAttributes(";create=true");
		
		Connection conn = ds.getConnection();
		conn.close();
		
		ic.bind("jdbc/mp", ds);
	}
	
	@Test
	public void t1()
	{
		Show show = new Show();
		show.setMaxProjectors(3);
		show.setBaseURL("http://cmt.bardes.org/slidedata/");
		
		db.save(show);
		
		Cue c = new Cue();
		c.setCue("1.5");
		c.setDescription("This is a test cue");
		
		Slide slide = new Slide();
		slide.setContentType(Type.BLANK);
		c.setSlide(1, slide);

		slide = new Slide();
		slide.setContentType(Type.IMAGE);
		slide.setContentFile("image.001.png");
		c.setSlide(2, slide);
		
		db.save(c);
		
		c = new Cue();
		c.setCue("1");
		
		for (int p = 1; p <= 3; p++)
		{
			Slide s = new Slide();
			s.setContentType(Type.BLANK);
			
			c.setSlide(p, s);
		}
		
		db.save(c);
	}
	
	@Test
	public void t2()
	{
		List<Cue> cues = db.getCues();
		
		System.out.println(cues);
	}
}
