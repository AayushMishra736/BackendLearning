package com.publishsubscribemessage.producer;

import javax.jms.DeliveryMode;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.InitialContext;

public class Producer {
	
	public static void main(String[] args) throws Exception {
		
	InitialContext initalContext = new InitialContext();
	
	Topic topic = (Topic) initalContext.lookup("topic/topic0");
	
	TopicConnectionFactory tcf = (TopicConnectionFactory) initalContext.lookup("topic/connectionFactory");
	
	TopicConnection topConn = tcf.createTopicConnection();
	
	TopicSession topSession = topConn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
	
	TopicPublisher topPublisher = topSession.createPublisher(topic);
	
	topPublisher.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
	
	TextMessage message = topSession.createTextMessage();
	message.setText("Hello Java!!");
	
	topPublisher.publish(message);
	
	System.out.print("Message Published " + message.getText());
	
	topConn.close();
		
	}

}
