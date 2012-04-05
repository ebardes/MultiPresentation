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
	@Test
	public void jndiCreate() throws NamingException, SQLException
	{
		InitialContext ic = new InitialContext();
		
		ClientDataSource ds = new ClientDataSource();
		ds.setDatabaseName("/home/eric/mp");
		ds.setUser("app");
		ds.setPassword("app");
		ds.setServerName("congo.bardes.org");
		ds.setConnectionAttributes(";create=true");
		
		Connection conn = ds.getConnection();
		conn.close();
		
		ic.bind("jdbc/mp", ds);
	}
	
	@Test
	public void t1()
	{
		Cue c;
		
		DB db = new DB(true);
		
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
	}
	
	@Test
	public void t2()
	{
		DB db = new DB();
		
		List<Cue> cues = db.getCues();
		
		System.out.println(cues);
	}
}
