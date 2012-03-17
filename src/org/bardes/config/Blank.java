package org.bardes.config;

import org.bardes.entities.Slide.Type;

public class Blank implements SlideType
{

	@Override
	public Type getType()
	{
		return Type.BLANK;
	}

}
