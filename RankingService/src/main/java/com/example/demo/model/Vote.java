package com.example.demo.model;

import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.util.Date;


@UserDefinedType
public class Vote {
    private String userid;
private Date data;

    int vote;

    public Vote(String userid, Date data, int vote) {
        this.userid = userid;
        this.data = data;
        this.vote = vote;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }
}
