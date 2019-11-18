package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.example.demo.engine.Producer;
import com.example.demo.model.PayLoad;
import com.example.demo.model.OrderStatus;
import com.example.demo.model.State;
import com.example.demo.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderStatusController
{
	private final Producer producer;



	@Autowired
	OrderStatusRepository orderStatusRepository;

	@Autowired
	OrderStatusController(Producer producer) {
		this.producer = producer;
	}



	@PostMapping("/addState")
	public OrderStatus addVote(@RequestBody PayLoad payLoad) throws ParseException {
		OrderStatus orderStatus = new OrderStatus(payLoad.getOrder_id(),new ArrayList<>());
		State state = new State();
		Date date1=new SimpleDateFormat("MM/dd/yyyy").parse(payLoad.getDate());
		state.setDate(date1);
		state.setDescription(payLoad.getDescription());
		orderStatus.getStates().add(state);

		return orderStatus;
	}






}
