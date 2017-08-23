package com.lshaci.springboot.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DubboConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DubboConsumerApplication.class, args);
		// 注册spring容器优雅关机
//		context.registerShutdownHook();
		System.out.println("============= Start Spring Boot Dubbo Provider Server Success =============");
		synchronized (DubboConsumerApplication.class) {
			try {
				DubboConsumerApplication.class.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
