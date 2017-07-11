package com.lshaci.springboot.activemq.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQConsumer {

	@JmsListener(destination = "${activemq.queue.name.test}")
	public void receiveAndReplyQueue(String msg) {
		System.out.println("ActiveMQProducer接收的消息: " + msg);
	}

}
