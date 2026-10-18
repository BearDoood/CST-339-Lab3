package com.gcu.lab1api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcu.lab1api.dto.HelloDTO;
import com.gcu.lab1api.service.HelloService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Hello", description = "Simple test endpoint")
public class HelloController {

	private static final Logger log = LoggerFactory.getLogger(HelloController.class);

	private final HelloService helloService;

	public HelloController(HelloService helloService) {
		this.helloService = helloService;
	}

	@GetMapping("/hello")
	@Operation(summary = "Returns a hello message")
	public HelloDTO hello() {
		log.info("GET /hello called");
		return helloService.getHello();
	}
}
