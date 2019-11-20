package com.notification.config;

import java.util.HashMap;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.notification.model.Notification;
import com.notification.service.kafkaConsumerService;





@EnableKafka
@Configuration
public class KafkaConfiguration {
	private static final Logger logger = LogManager.getLogger(KafkaConfiguration.class);
	
	
	@Value("${KAFKA_SERVER}")
	private String kafkaServer;
	
	
	
	@Bean
	public ConsumerFactory<String, Notification> consumerFactory() {		
		HashMap<String, Object> config = new HashMap<String, Object>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "group-id");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		return new DefaultKafkaConsumerFactory<String, Notification>(config, new StringDeserializer(), new JsonDeserializer<Notification>(Notification.class));
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Notification> kafkaListenerContainerFactory(){
		 ConcurrentKafkaListenerContainerFactory<String, Notification> consumerListener = new ConcurrentKafkaListenerContainerFactory<String, Notification>();
		 consumerListener.setConsumerFactory(consumerFactory());
		 return consumerListener;
	}

}
