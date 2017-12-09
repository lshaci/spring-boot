package com.lshaci.springboot.rabbitmq.producer;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lshaci.springboot.rabbitmq.SpringTest;

public class RabbitMQProducerTest extends SpringTest<RabbitMQProducer> {

	@Autowired
	private RabbitMQCallback rabbitMQCallback;
	
	@Test
	public void testSendMessage() throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			bean.produceMsg("Test for RabbitMQ --> " + i);
		}
		
		synchronized (RabbitMQProducerTest.class) {
			RabbitMQProducerTest.class.wait();
		}
	}

}
