package org.bardes.config;

import org.bardes.entities.Slide.Type;

public class Image implements SlideType
{

	@Override
	public Type getType()
	{
		return Type.IMAGE;
	}

}
