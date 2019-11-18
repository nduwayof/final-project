package com.notification.model;

public class Notification {
	private String userEmail;
	private String status;
	
	public Notification() {
		// TODO Auto-generated constructor stub
	}
	
	public Notification(String userEmail, String status) {
		super();
		this.userEmail = userEmail;
		this.status = status;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
