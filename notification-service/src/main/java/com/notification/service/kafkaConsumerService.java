package com.notification.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.notification.model.Notification;

@Service
public class kafkaConsumerService {
	@Autowired
    private JavaMailSender sender;
	
	@Value("${KAFKA_TOPIC:#{null}}")
	private String kafkaTopic;
	
	@KafkaListener(topics = "${KAFKA_TOPIC}", groupId = "group_id")
	public void consume(Notification notificationData) {
		MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(notificationData.getUserEmail());
            helper.setSubject("Eats Notification");
            helper.setText("Greetings from Eats your order status changed to : '"+ notificationData.getStatus()+"'");
            
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        sender.send(message);
	}
}
