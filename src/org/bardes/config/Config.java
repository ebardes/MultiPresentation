package org.bardes.config;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collection;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.derby.jdbc.ClientDataSource;
import org.bardes.entities.Cue;
import org.bardes.state.DB;
import org.junit.Test;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.NativeJavaObject;
import org.mozilla.javascript.Scriptable;


public class Config
{
	@Test
	public void a() throws NamingException
	{
		InitialContext ic = new InitialContext();
		
		ClientDataSource ds = new ClientDataSource();
		ds.setServerName("localhost");
		ds.setDatabaseName("mp");
		ds.setUser("app");
		ds.setPassword("app");
		ds.setConnectionAttributes(";create=true");
		
		ic.bind("jdbc/__default", ds);
	}
	
	@Test
	public void f() throws IOException
	{
		Context cx = Context.enter();
		try
		{
			// Initialize the standard objects (Object, Function, etc.)
			// This must be done before scripts can be executed. Returns
			// a scope object that we use in later calls.
			Scriptable scope = cx.initStandardObjects();

			Reader r = new FileReader("init.js");
			cx.evaluateReader(scope, r, "init.js", 1, null);
			r.close();
			
			r = new FileReader("config.js");
			// Now evaluate the string we've colected.
			NativeJavaObject result =  (NativeJavaObject) cx.evaluateReader(scope, r, "<cmd>", 1, null);
			r.close();

			// Convert the result to a string and print it.
			System.out.println(Context.toString(result));

			DefCue defCue = (DefCue) result.unwrap();
			
			Collection<Cue> cues = defCue.cues;
			
			DB db = new DB();
			db.save(cues);
		}
		catch (RuntimeException re)
		{
			re.printStackTrace();
			throw re;
		}
		finally
		{
			// Exit from the context.
			Context.exit();
		}
		
	}
}
