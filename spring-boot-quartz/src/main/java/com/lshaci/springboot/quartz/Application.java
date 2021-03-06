package com.lshaci.springboot.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		// 注册spring容器优雅关机, SpringApplication.run内已设置
//		context.registerShutdownHook();
		System.out.println("============= Start SpringBoot Server Success =============");
		synchronized (Application.class) {
			try {
				Application.class.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
