package com.notification.service;

import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.notification.model.Notification;

@Service
public class kafkaConsumerService {
	private static final Logger logger = LogManager.getLogger(kafkaConsumerService.class);
	
	@Autowired
	private JavaMailSender sender;

	@Value("${KAFKA_TOPIC:#{null}}")
	private String kafkaTopic;

	@Value("${USER_SERVICE:#{null}}")
	private String userService;

	@Value("${API:#{null}}")
	private String api;

	@Autowired
	RestTemplate restTemplate;

	@KafkaListener(topics = "${KAFKA_TOPIC}", groupId = "group_id")
	public void consume(Notification notificationData) {
		logger.info("Notification Service : Start consumer method");
		logger.info("Notification Service : userService: {} , API : {}", userService, api);
		try {
			logger.info("Notification Service : calling users api to get email by Id");
			final String uri = String.format("http://%s/" + api + "/" + notificationData.getUserId(), userService);
			String user = restTemplate.getForObject(uri, String.class);
			JsonParser springParser = JsonParserFactory.getJsonParser();
			Map<String, Object> userMap = springParser.parseMap(user);
			String email = (String) userMap.get("email");
			String userName = (String) userMap.get("name");
			
			logger.info("Notification Service : user is : {}", user);
			if (userMap.get("email") != null && !email.isEmpty()) {
				MimeMessage message  = sender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message);

				helper.setTo(email);
				helper.setSubject("Eats Notification");
				helper.setText("Hello "+userName+",\n Greetings from Eats your order status changed to : '" + notificationData.getStatus() + "'");
				logger.info("Notification Service : Sending notification email to : email");
				sender.send(message);
				logger.info("Notification Service : Email sent successfully !");
				logger.info("Notification Service : End of consumer method");
			} else {
				logger.error("Notification Service : calling users api to get email by Id");
			}
		} catch (MessagingException e) {
			logger.error("Notification Service : Error happened {}", e);
			e.printStackTrace();
		}
	}
}
