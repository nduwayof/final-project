package com.payment.model;

import org.springframework.data.annotation.Id;

public class CreditCardInfo {

	@Id
	private String creditCardInfoId;

	private String cvc;
	private int month;
	private int year;

	public CreditCardInfo() {
		// TODO Auto-generated constructor stub
	}

	public CreditCardInfo(String cvc, int month, int year) {
		super();
		this.cvc = cvc;
		this.month = month;
		this.year = year;
	}

	public String getCreditCardInfoId() {
		return creditCardInfoId;
	}

	public String getCvc() {
		return cvc;
	}

	public void setCreditCardInfoId(String creditCardInfoId) {
		this.creditCardInfoId = creditCardInfoId;
	}

	public void setCvc(String cvc) {
		this.cvc = cvc;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
