package com.lshaci.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.lshaci.test.mapper")
public class Application {

	public static void main(String[] args)  {
		SpringApplication.run(Application.class, args);
	}

}
