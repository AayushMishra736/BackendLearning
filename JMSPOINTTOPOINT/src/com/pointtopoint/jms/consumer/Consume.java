package com.pointtopoint.jms.consumer;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

public class Consume {

	public static void main(String[] args) throws Exception{
		
	InitialContext initialContext = new InitialContext();
	
	Queue queue = (Queue) initialContext.lookup("queue/queue0");
	
	QueueConnectionFactory qcf = (QueueConnectionFactory) initialContext.lookup("queue/connectionFactory");
	
	QueueConnection queueConnection = qcf.createQueueConnection();
	
	QueueSession queSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
	
	QueueReceiver queReceiver = queSession.createReceiver(queue);
	
	queueConnection.start();
	
	TextMessage message = (TextMessage) queReceiver.receive();
		
	System.out.println("Message Received: " + message.getText());

	queueConnection.close();
		
	}

}
