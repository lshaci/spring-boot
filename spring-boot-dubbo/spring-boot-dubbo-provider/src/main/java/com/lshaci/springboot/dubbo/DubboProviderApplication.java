package com.lshaci.springboot.dubbo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lshaci.springboot.dubbo.mapper")
public class DubboProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(DubboProviderApplication.class, args);
		// 注册spring容器优雅关机
//		context.registerShutdownHook();
		System.out.println("============= Start Spring Boot Dubbo Provider Server Success =============");
//		synchronized (DubboProviderApplication.class) {
//			try {
//				DubboProviderApplication.class.wait();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}
}
