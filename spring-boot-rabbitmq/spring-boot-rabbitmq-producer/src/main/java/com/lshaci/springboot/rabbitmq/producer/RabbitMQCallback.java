package com.lshaci.springboot.rabbitmq.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQCallback implements RabbitTemplate.ConfirmCallback {

	/**
	 * 回调
	 */
	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		System.out.println(" 回调id:" + correlationData);
		if (ack) {
			System.out.println("消息成功消费");
		} else {
			System.out.println("消息消费失败:" + cause);
		}
	}

}