package com.lshaci.springboot.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RabbitMQConsumer {

	@RabbitListener(queues = "${jsa.rabbitmq.queue}")
	public void recievedMessage(String message) {
		log.info("Recieve the message is : " + message);
	}

}