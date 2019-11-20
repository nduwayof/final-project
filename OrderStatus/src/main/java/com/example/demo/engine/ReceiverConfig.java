package com.example.demo.engine;

import com.example.demo.model.Order;
import com.example.demo.model.Payment;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;


@Configuration
@EnableKafka
@PropertySource("classpath:application.properties")

public class ReceiverConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaServer;


    @Bean
    public ConsumerFactory<String, Order> consumerFactory() {
        HashMap<String, Object> config = new HashMap<String, Object>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group-id");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<String, Order>(config, new StringDeserializer(), new JsonDeserializer<Order>(Order.class));
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Order> kafkaListenerContainerFactory2(){
        ConcurrentKafkaListenerContainerFactory<String, Order> consumerListener = new ConcurrentKafkaListenerContainerFactory<String, Order>();
        consumerListener.setConsumerFactory(consumerFactory());
        return consumerListener;
    }


    @Bean
    public PaymentConsumer payment_consumer() {
        return new PaymentConsumer();
    }



    @Bean
    public ConsumerFactory<String, Payment> consumerFactory2() {
        HashMap<String, Object> config = new HashMap<String, Object>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group-id");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<String, Payment>(config, new StringDeserializer(), new JsonDeserializer<Payment>(Payment.class));
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Payment> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, Payment> consumerListener = new ConcurrentKafkaListenerContainerFactory<String, Payment>();
        consumerListener.setConsumerFactory(consumerFactory2());
        return consumerListener;
    }


    @Bean
    public OrderConsumer order_consumer() {
        return new OrderConsumer();
    }





}