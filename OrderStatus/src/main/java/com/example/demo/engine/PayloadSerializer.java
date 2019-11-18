package com.example.demo.engine;

import com.example.demo.model.PayLoad;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class PayloadSerializer  implements org.apache.kafka.common.serialization.Serializer<PayLoad> {


    @Override
    public byte[] serialize(String arg0, PayLoad developer) {
        byte[] serializedBytes = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            serializedBytes = objectMapper.writeValueAsString(developer).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serializedBytes;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

    @Override
    public void configure(Map<String, ?> arg0, boolean arg1) {
        // TODO Auto-generated method stub
    }
}
