package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.example.demo.engine.OrderConsumer;
import com.example.demo.engine.PaymentConsumer;
import com.example.demo.engine.Producer;
import com.example.demo.model.OrderStatus;
import com.example.demo.model.State;
import com.example.demo.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderStatusController
{

	private  Producer producer;

	@Autowired
	private OrderConsumer order_consumer;
	@Autowired
	private PaymentConsumer payment_consumer;



	@Autowired
	OrderStatusRepository orderStatusRepository;


	@Autowired
	OrderStatusController(Producer producer) {
		this.producer = producer;
	}





}
