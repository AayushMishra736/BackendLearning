package com.publishsubscribemessage.consumer;

import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.InitialContext;

public class Consumer {
	
	public static void main(String[] args) throws Exception{
		
	InitialContext intialContext = new InitialContext();
	
	Topic topic = (Topic) intialContext.lookup("topic/topic0");
		
	TopicConnectionFactory tcf = (TopicConnectionFactory) intialContext.lookup("topic/connectionFactory");
	
	TopicConnection topicConnection = tcf.createTopicConnection();
	
	TopicSession topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
	
	TopicSubscriber topicSubscriber = topicSession.createSubscriber(topic);
	
	topicConnection.start();
	
	TextMessage message = (TextMessage) topicSubscriber.receive();	
	
	System.out.print("Message Received " + message.getText());
	
	topicConnection.close();
	
	}

}
