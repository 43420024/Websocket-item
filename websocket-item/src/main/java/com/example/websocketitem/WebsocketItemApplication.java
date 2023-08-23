package com.example.websocketitem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.websocketitem.mapper")
public class WebsocketItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsocketItemApplication.class, args);
	}

}
