package org.bardes.mq;

import java.io.IOException;

public interface MQ 
{
	void init() throws IOException;

	void close() throws IOException;
}
