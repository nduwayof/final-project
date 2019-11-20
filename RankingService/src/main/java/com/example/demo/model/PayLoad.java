package com.example.demo.model;


public class PayLoad {
    private String rest_id;
    private String userid;
    private int vote;

    public String getRest_id() {
        return rest_id;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public void setRest_id(String rest_id) {
        this.rest_id = rest_id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }


}
