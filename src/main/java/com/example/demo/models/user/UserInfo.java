package com.example.demo.models.user;

import com.example.demo.models.father.Father;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;
@Entity
@Table(name="userinfo")
public class UserInfo extends Father {
    private Date birthday;
    private String gender;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    protected User user;



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
