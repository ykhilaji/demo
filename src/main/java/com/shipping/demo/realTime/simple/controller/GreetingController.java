package com.shipping.demo.realTime.simple.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shipping.demo.realTime.simple.service.GreetingService;


@RestController
public class GreetingController {
	private final GreetingService greetingService;
	
	public GreetingController(GreetingService greetingService){
		 this.greetingService = greetingService;
	}

	@GetMapping("/api/v1/hello")
	public CompletableFuture<String> hello() {
		return greetingService.hello();
	}
}
