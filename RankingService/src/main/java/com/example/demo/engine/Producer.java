package com.example.demo.engine;

import com.example.demo.model.PayLoad;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC = "loads";

    @Autowired
    private KafkaTemplate<String, PayLoad> kafkaTemplate;

    public void sendMessage(PayLoad payLoad) {
        logger.info(String.format("#### -> Producing message -> %s", payLoad));
        this.kafkaTemplate.send(TOPIC, payLoad);
    }
}
