package com.example.demo.model;


public class PayLoad {
    private String rest_id;
    private int userid;
    private String date;


    public String getData() {
        return date;
    }

    public void setData(String data) {
        this.date = data;
    }

    public String getRest_id() {
        return rest_id;
    }

    public void setRest_id(String rest_id) {
        this.rest_id = rest_id;
    }

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

    private int vote;

}
