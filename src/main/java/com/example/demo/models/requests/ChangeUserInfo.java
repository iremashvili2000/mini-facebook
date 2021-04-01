package com.example.demo.models.requests;

import java.util.Date;

public class ChangeUserInfo {

    private String password;

    private String email;

    private String gender;

    private String birthday;

    public ChangeUserInfo(String password, String email, String gender, String birthday) {
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.birthday = birthday;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
