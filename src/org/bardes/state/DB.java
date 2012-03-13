package org.bardes.state;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.bardes.entities.Cue;

public class DB
{
	public static final String PERSISTENCE_NAME = "MultiPresentation";
	private EntityManagerFactory entityManagerFactory;
	
	public DB()
	{
		Map<String,Object> env = new HashMap<String, Object>();
		env.put("eclipselink.ddl-generation", "create-tables");
		entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_NAME, env);
	}
	
	public List<Cue> getCues()
	{
		List<Cue> list = null;
		EntityManager em = entityManagerFactory.createEntityManager();
		try
		{
			TypedQuery<Cue> q = em.createQuery("select c from Cue c order by c.cue", Cue.class);
			list = q.getResultList();
		}
		finally
		{
			em.close();
		}
		return list;
	}
}
