package com.bookstore.work;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bookstore.work.dao")
public class SpdApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpdApplication.class, args);
	}
	
}
