package com.lshaci.springboot.rabbitmq.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lshaci.springboot.rabbitmq.producer.RabbitMQProducer;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ProducerController {

	@Autowired
	private RabbitMQProducer rabbitMQProducer;
	
	@RequestMapping("/send")
	public String sendMsg(@RequestParam("msg")String msg){
		log.debug("This message is : " + msg);
		
		rabbitMQProducer.produceMsg(msg);
		return "Done";
	}
}
