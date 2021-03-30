package com.example.demo.models;

import com.example.demo.models.father.Father;
import com.example.demo.models.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="notification")
public class Notification extends Father {
    @NotNull
    private String title;
    @NotNull
    private String message;
    @NotNull
    private LocalDateTime time;
    private boolean seen;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    protected User user;


    public Notification() {
    }

    public Notification(String title, String message, LocalDateTime time, boolean seen) {
        this.title = title;
        this.message = message;
        this.time = time;
        this.seen = seen;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }


}
