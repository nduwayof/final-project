package com.example.demo.model;

public class Notification {
    private String userId;
    private String status;

    public Notification() {
        // TODO Auto-generated constructor stub
    }

    public Notification(String userId, String status) {
        super();
        this.userId = userId;
        this.status = status;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

}
