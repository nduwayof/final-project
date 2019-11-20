package com.example.demo.model;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;


@Table
public class OrderStatus {
    @PrimaryKey
    private String order_id;
    private String  userid;

    public String getOrder_id() {
        return order_id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public OrderStatus(String order_id, List<State> states) {
        this.order_id = order_id;
        this.states = states;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    @Override
    public String toString() {
        return "OrderStatus{" +
                "order_id=" + order_id +
                ", states=" + states +
                '}';
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    private List<State> states;




}
