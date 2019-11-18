package com.example.demo.model;

import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.util.Date;


@UserDefinedType
public class Vote {
    private int userid;
private Date data;

    public Vote(int userid, int vote,Date data ) {
        this.userid = userid;
        this.data = data;
        this.vote = vote;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }



    private int vote;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }




}
