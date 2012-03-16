package org.bardes.state;

import java.util.HashMap;
import java.util.Map;

public class ProjectorPool 
{
	private static Map<String, ProjectorState> projectors = new HashMap<String, ProjectorState>();

	public void declare(String projectorId, ProjectorState state)
	{
		projectors.put(projectorId, state);
	}
	
	public ProjectorState get(String projectorId)
	{
		return projectors.get(projectorId);
	}
}
