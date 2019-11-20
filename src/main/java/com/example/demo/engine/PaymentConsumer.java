package com.example.demo.engine;

import com.example.demo.model.*;
import com.example.demo.repository.OrderStatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public class PaymentConsumer {
    private final Logger logger = LoggerFactory.getLogger(OrderConsumer.class);
    private  Producer producer;
    @Autowired
    OrderStatusRepository orderStatusRepository;


    @KafkaListener(topics = "payment", groupId = "group_id",containerFactory = "kafkaListenerContainerFactory")
    public void consume(Payment message) throws IOException, ParseException {
        producer = new Producer();
        if(message != null)
        {
            OrderStatus orderStatus = orderStatusRepository.findById(message.getOrderId()).get();
            State state = new State();
            state.setDate(new Date());
            state.setDescription("Payment completed Order Done");
            orderStatus.getStates().add(state);

            Notification notification = new Notification();
            notification.setStatus(state.getDescription());
            notification.setUserEmail(orderStatus.getUserid());
            producer = new Producer();
            producer.sendMessage(notification);

        }


        logger.info(String.format("#### -> Consumed message Payment Consumer-> %s", message));
    }

}
