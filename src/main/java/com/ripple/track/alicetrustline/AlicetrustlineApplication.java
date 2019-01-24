package com.ripple.track.alicetrustline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.ripple.track")
public class AlicetrustlineApplication {
	
	static Logger logger = LoggerFactory.getLogger(AlicetrustlineApplication.class);

	public static void main(String[] args) {
		logger.info("Welcome to the Trustline");
		logger.info("Trustline balance is: 0");

		SpringApplication.run(AlicetrustlineApplication.class, args);
	}

}

