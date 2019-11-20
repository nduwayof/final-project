package com.example.demo.engine;

import com.example.demo.model.PayLoad;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;


public class Sender {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);



    @Autowired
    private KafkaTemplate<String, PayLoad> kafkaTemplate;

    public void send(PayLoad payLoad) {
        LOGGER.info("sending payload='{}'", payLoad.toString());
        kafkaTemplate.send("rank", payLoad);
    }
}
