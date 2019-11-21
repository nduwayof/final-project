package com.example.demo.engine;

import com.example.demo.model.*;
import com.example.demo.repository.OrderStatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class OrderConsumer {
    @Autowired
    OrderStatusRepository orderStatusRepository;
    private Producer producer;

    OrderConsumer() {

    }

    @Autowired
    OrderConsumer(Producer producer) {
        this.producer = producer;
    }

    private final Logger logger = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(topics = "order", groupId = "group_id", containerFactory = "kafkaListenerContainerFactory2")
    public void consume(Order message) throws IOException, ParseException {

        OrderStatus orderStatus = new OrderStatus(message.get_id(), new ArrayList<>());
        State state = new State();
        Date date1 = new Date();
        state.setDate(date1);
        state.setDescription("The order placed successfully");
        orderStatus.getStates().add(state);
        orderStatus.setUserid(message.getUser());
        orderStatusRepository.save(orderStatus);
        Notification notification = new Notification();
        notification.setStatus(state.getDescription());
        notification.setUserId(message.getUser());
        producer.sendMessage(notification);

        logger.info(String.format("#### -> Consumed message order Consumer -> %s", message));
    }

}
