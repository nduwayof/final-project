package com.payment.controller;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.payment.model.Payment;
import com.payment.service.PaymentService;

@RestController
@EnableDiscoveryClient
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("/payment")
    public ResponseEntity<String> processPayment(@RequestBody Payment orderPayment) {
        if (orderPayment != null) {
            boolean paid = paymentService.savePayment(orderPayment);
            if (paid) {
                return ResponseEntity.ok("Order Paid Successfully");
            }
        }
        return ResponseEntity.ok("Order not paid check you credit card validition");
    }

    @GetMapping("/getAllPayments")
    public ResponseEntity<List<Payment>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @PostMapping("/getPaymentById")
    public ResponseEntity<Payment> getPaymentById(@RequestBody ObjectId paymentId) {
        Optional<Payment> payment = paymentService.getPaymentById(paymentId);
        return ResponseEntity.ok(payment.get());
    }

    @PostMapping("/getPaymentByOrderId")
    public ResponseEntity<Payment> getPaymentByOrderId(@RequestBody String orderId) {
        return ResponseEntity.ok(paymentService.getPaymentByOrderId(orderId));
    }

}