package com.lshaci.springboot.rabbitmq;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lshaci.springboot.rabbitmq.ProducerApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProducerApplication.class)
public abstract class SpringTest<T> {

	@Autowired
	protected T bean;
}
