package com.spring.jms;

import javax.jms.MessageFormatException;
import javax.jms.Queue;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import java.util.Iterator;
import java.util.Map;

import javax.jms.ConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class DSAdminEventSender {
	private Queue ds2AdminBatchQueue;
	private JmsTemplate jmsTemplate;
	
	public boolean sendMessage(final Map<String, String> theMessage) {
        jmsTemplate.send(ds2AdminBatchQueue, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return createMessageForMap(theMessage,session);
            }
        });
        return true;
    }

	protected MapMessage createMessageForMap(Map map, Session session) throws JMSException {
	    MapMessage message = session.createMapMessage();
	    for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
	        Map.Entry entry = (Map.Entry) it.next();
	        if (!(entry.getKey() instanceof String)) {
	            throw new MessageFormatException("Cannot convert non-String key of type [" + entry.getKey()+ "] to JMS MapMessage entry");
	        }
	        message.setObject((String) entry.getKey(), entry.getValue());
	    }
	    return message;
	}
	
	 
	
	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.jmsTemplate = new JmsTemplate(connectionFactory);
	}

	public Queue getDs2AdminBatchQueue() {
		return ds2AdminBatchQueue;
	}

	public void setDs2AdminBatchQueue(Queue ds2AdminBatchQueue) {
		this.ds2AdminBatchQueue = ds2AdminBatchQueue;
	}
	
	
	
}
