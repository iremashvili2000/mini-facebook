package com.example.demo.models.requests;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.validation.constraints.Email;

public class Login {
    @Email
    private String email;
    @NotNull
    private String password;

    public Login(@Email String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
