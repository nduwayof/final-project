package com.example.demo;

import com.example.demo.engine.OrderConsumer;
import com.example.demo.engine.PaymentConsumer;
import com.example.demo.engine.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
	@Autowired
	private Producer producer;
	@Autowired
	private OrderConsumer order_consumer;
	@Autowired
	private PaymentConsumer payment_consumer;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

