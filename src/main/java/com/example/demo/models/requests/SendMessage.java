package com.example.demo.models.requests;

public class SendMessage {

    private String sender;

    private String reciver;
    private String message;

    public SendMessage(String reciver, String message) {
        this.reciver = reciver;
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReciver() {
        return reciver;
    }

    public void setReciver(String reciver) {
        this.reciver = reciver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
