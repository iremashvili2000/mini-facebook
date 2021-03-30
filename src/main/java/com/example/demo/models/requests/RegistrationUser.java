package com.example.demo.models.requests;

import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.Email;

public class RegistrationUser {
    @NotNull
    private String name;
    @NotNull
    private String lastname;
    @NotNull
    private String username;
    @Email
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String repassword;

    public RegistrationUser(String name, String lastname, String username, String email, String password, String repassword) {
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.repassword = repassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }
}
