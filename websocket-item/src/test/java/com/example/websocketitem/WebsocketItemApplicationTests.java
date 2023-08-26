package com.example.websocketitem;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
@Slf4j
class WebsocketItemApplicationTests {

	@Test
	void contextLoads() {
		LocalDateTime now = LocalDateTime.now();
		log.info("Current Date Time: {}", now);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		String formattedDateTime = now.format(formatter);
		log.info("Formatted Date Time: {}", formattedDateTime);
	}
	@Test
	void test() {
		String dateTimeString = "2023-08-26 15:30:00";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		LocalDateTime parsedDateTime = LocalDateTime.parse(dateTimeString, formatter);
		log.info("Parsed Date Time: {}", parsedDateTime);
	}

}
