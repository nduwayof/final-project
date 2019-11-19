package com.payment.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.payment.model.Payment;
import com.payment.repo.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	private KafkaTemplate<String, Payment> kafkaTemplate;
	
	@Value("${KAFKA_TOPIC:#{null}}")
	private String kafkaTopic;

	public boolean savePayment(Payment payment) {
		YearMonth creditCardDate = null;
		if (payment.getCreditCardInfo() != null) {
		creditCardDate = YearMonth.of(payment.getCreditCardInfo().getYear(),
				payment.getCreditCardInfo().getMonth());
		}
		YearMonth nowDate = YearMonth.of(LocalDate.now().getYear(), LocalDate.now().getMonth());
		if (creditCardDate != null && creditCardDate.isAfter(nowDate)) {
			 paymentRepository.save(payment);
			 sendMessage(payment);
			 return true;
		}
		
		return false;
	}
	
	public Optional<Payment> getPaymentById(ObjectId id) {
		return paymentRepository.findById(id.toString());
	}
	
	public List<Payment> getAllPayments() {
		return paymentRepository.findAll();
	}

	public Payment getPaymentByOrderId(String orderId) {
		return paymentRepository.findByOrderId(orderId);
	}
	 
	public void sendMessage(Payment paymentModel) {
	    kafkaTemplate.send(kafkaTopic, paymentModel);
	}
}
