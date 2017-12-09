package com.lshaci.springboot.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ConsumerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ConsumerApplication.class, args);
		// 注册spring容器优雅关机
		context.registerShutdownHook();
		System.out.println("============= Start SpringBoot Server Success =============");
		synchronized (ConsumerApplication.class) {
			try {
				ConsumerApplication.class.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
