package com.payment.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.payment.model.CreditCardInfo;
import com.payment.model.Payment;
import com.payment.repo.PaymentRepository;
import com.payment.service.PaymentService;

public class PaymentServiceTest {

	@Spy
	@InjectMocks
	PaymentService paymentService;

	@Mock
	PaymentRepository paymentRepository;

	@Mock
	Payment payment;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void savePaymentFalse() {
		when(paymentRepository.save(payment)).thenReturn(payment);
		doNothing().when(paymentService).sendMessage(payment);
		assertFalse(paymentService.savePayment(payment));
		System.out.println();
	}

	@Test
	public void savePaymentTrue() {
		Payment payment = new Payment("1", new CreditCardInfo("123", 12, 2022), new Date());
		when(paymentRepository.save(payment)).thenReturn(payment);
		doNothing().when(paymentService).sendMessage(payment);
		assertTrue(paymentService.savePayment(payment));
		System.out.println();
	}
}
