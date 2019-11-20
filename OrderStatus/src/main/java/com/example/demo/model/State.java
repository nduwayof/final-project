package com.example.demo.model;

import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.util.Date;
@UserDefinedType

public class State {
    private String description;
    private Date date;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
