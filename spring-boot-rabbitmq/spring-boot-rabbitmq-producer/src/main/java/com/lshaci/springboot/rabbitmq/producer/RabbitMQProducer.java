package com.lshaci.springboot.rabbitmq.producer;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RabbitMQProducer {

	@Value("${jsa.rabbitmq.exchange}")
	public String exchange;

	@Value("${jsa.rabbitmq.routingkey}")
	public String routingkey;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void sendMessage(String message, RabbitTemplate.ConfirmCallback callback) {
		rabbitTemplate.setConfirmCallback(callback);

		CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());

		rabbitTemplate.convertAndSend(exchange, routingkey, message, correlationId);
	}

	public void produceMsg(String msg) {
		rabbitTemplate.convertAndSend(exchange, routingkey, msg);
		log.info("Send msg = " + msg);
	}

}