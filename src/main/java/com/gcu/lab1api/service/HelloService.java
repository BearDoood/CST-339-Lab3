package com.gcu.lab1api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gcu.lab1api.dto.HelloDTO;

@Service
public class HelloService {

	private static final Logger log = LoggerFactory.getLogger(HelloService.class);

	public HelloDTO getHello() {
		log.info("Building hello response");
		return new HelloDTO("Hello Spring Boot");
	}
}
