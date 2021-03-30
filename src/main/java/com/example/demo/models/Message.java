package com.example.demo.models;

import com.example.demo.models.father.Father;
import com.example.demo.models.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="message")
public class Message extends Father {

    private String message;
    @JsonIgnore
    private String messageid;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private User sender;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private User reciver;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageid() {
        return messageid;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReciver() {
        return reciver;
    }

    public void setReciver(User reciver) {
        this.reciver = reciver;
    }
}
