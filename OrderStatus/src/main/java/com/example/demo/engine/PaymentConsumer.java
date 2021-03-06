package com.example.demo.engine;

import com.example.demo.model.*;
import com.example.demo.repository.OrderStatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.payment.model.Payment;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

@Service
public class PaymentConsumer {
    private final Logger logger = LoggerFactory.getLogger(OrderConsumer.class);
    private Producer producer;
    @Autowired
    OrderStatusRepository orderStatusRepository;

    PaymentConsumer() {

    }

    @Autowired
    PaymentConsumer(Producer producer) {
        this.producer = producer;
    }

    @KafkaListener(topics = "payment", groupId = "group_id", containerFactory = "kafkaListenerContainerFactory")
    public void consume(Payment message) {
        producer = new Producer();
        if (message != null) {
            OrderStatus orderStatus = orderStatusRepository.findById(message.getOrderId()).get();
            State state = new State();
            state.setDate(new Date());
            state.setDescription("Payment completed Order Done");
            orderStatus.getStates().add(state);
            orderStatusRepository.save(orderStatus);
            Notification notification = new Notification();
            notification.setStatus(state.getDescription());
            notification.setUserId(orderStatus.getUserid());
            System.out.println(notification.getStatus() + notification.getUserId());
            System.out.println(producer == null);
            producer.sendMessage(notification);

        }

        logger.info(String.format("#### -> Consumed message Payment Consumer-> %s", message));
    }

}
