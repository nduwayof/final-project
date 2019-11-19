package com.payment.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.payment.model.Payment;

public interface PaymentRepository extends MongoRepository<Payment, String> {
	public Payment findByOrderId(String orderId);
}

