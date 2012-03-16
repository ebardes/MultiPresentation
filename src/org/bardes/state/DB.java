package org.bardes.state;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.bardes.entities.Cue;
import org.bardes.entities.Show;

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

	public void save(Cue c)
	{
		EntityManager em = entityManagerFactory.createEntityManager();
		try
		{
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			
			Cue x = em.find(Cue.class, c.getCue());
			if (x != null)
			{
				em.remove(x);
				em.flush();
			}
			em.merge(c);
			
			tx.commit();
		}
		finally
		{
			em.close();
		}
	}
	
	public void save(Show show)
	{
		EntityManager em = entityManagerFactory.createEntityManager();
		try
		{
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			
			em.merge(show);
			
			tx.commit();
		}
		finally
		{
			em.close();
		}
	}
}
