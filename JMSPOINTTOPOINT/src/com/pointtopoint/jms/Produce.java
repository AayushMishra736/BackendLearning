package com.pointtopoint.jms;

import javax.jms.DeliveryMode;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

public class Produce {

	public static void main(String[] args) throws Exception{
	
	InitialContext initialContext = new InitialContext();
	
	Queue queue = (Queue) initialContext.lookup("queue/queue0");
	
	QueueConnectionFactory qcf = (QueueConnectionFactory) initialContext.lookup("queue/connectionFactory");
	
	QueueConnection queueConnection = qcf.createQueueConnection();
	
	QueueSession queueSession = queueConnection.createQueueSession(false, Session.DUPS_OK_ACKNOWLEDGE);
	
	QueueSender queueSender = queueSession.createSender(queue);
	queueSender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
	
	TextMessage message = queueSession.createTextMessage("Hello World");
	
	queueSender.send(message);
	
	System.out.println("Message Sent By Producer.");
	
	queueConnection.close();

	}

}
