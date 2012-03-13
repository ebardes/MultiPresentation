package org.bardes.mq;

import java.io.IOException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQ implements MQ 
{
	private Connection conn;
	private String hostName;

	@Override
	public void init() throws IOException 
	{
		ConnectionFactory factory = new ConnectionFactory();
//		factory.setUsername(userName);
//		factory.setPassword(password);
//		factory.setVirtualHost(virtualHost);
		factory.setHost(hostName);
//		factory.setPort(portNumber);
		conn = factory.newConnection();
	}
	
	@Override
	public void close() throws IOException
	{
		conn.close();
	}
}
