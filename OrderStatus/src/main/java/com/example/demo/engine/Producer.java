package com.example.demo.engine;

import com.example.demo.model.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC = "notification";

    @Autowired
    private KafkaTemplate<String, Notification> kafkaTemplate;

    public void sendMessage(Notification payLoad) {
        logger.info(String.format("#### -> Producing message -> %s", payLoad));
        this.kafkaTemplate.send(TOPIC, payLoad);
    }
}
