package com.example.demo.models.response;


import javax.validation.constraints.NotNull;

public class SendRequest {
    @NotNull
    private String username;

    public SendRequest() {
    }

    public SendRequest(@NotNull String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
