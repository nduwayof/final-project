package com.example.demo.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;


public class Payment {
	@Id
	private String id;
	private String orderId;
	private CreditCardInfo creditCardInfo;
	private LocalDate paymentDate = LocalDate.now();
	
	public Payment(String orderId, CreditCardInfo creditCardInfo, LocalDate paymentDate) {
		super();
		this.orderId = orderId;
		this.creditCardInfo = creditCardInfo;
		this.paymentDate = paymentDate;
	}
	
	public String getId() {
		return id;
	}
	public String getOrderId() {
		return orderId;
	}
	public CreditCardInfo getCreditCardInfo() {
		return creditCardInfo;
	}
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public void setCreditCardInfo(CreditCardInfo creditCardInfo) {
		this.creditCardInfo = creditCardInfo;
	}
	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	
}
