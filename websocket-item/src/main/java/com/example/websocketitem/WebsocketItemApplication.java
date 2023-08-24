package com.example.websocketitem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.example.websocketitem.mapper")
public class WebsocketItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsocketItemApplication.class, args);
	}

}
