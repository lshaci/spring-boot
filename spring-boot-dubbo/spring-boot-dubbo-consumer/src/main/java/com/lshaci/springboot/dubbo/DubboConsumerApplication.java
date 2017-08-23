package com.lshaci.springboot.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DubboConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DubboConsumerApplication.class, args);
		System.out.println("============= Start Spring Boot Dubbo Consumer Server Success =============");
	}
}
