package com.example.demo.models.user;

import com.example.demo.models.father.Father;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "friendrequests")
public class FriendRequests extends Father {
    private boolean request;

    private Date created_at;

    private boolean reciv;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private User sender;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private User reciver;

    public boolean isRequest() {
        return request;
    }

    public boolean isReciv() {
        return reciv;
    }

    public void setReciv(boolean reciv) {
        this.reciv = reciv;
    }

    public void setRequest(boolean request) {
        this.request = request;
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

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
