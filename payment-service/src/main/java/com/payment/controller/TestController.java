package com.payment.controller;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableDiscoveryClient
public class TestController {

	@GetMapping("/hi")
	public String hi(){
		return "hi";
	}
}
