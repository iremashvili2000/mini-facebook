package com.example.demo.models.user;

import com.example.demo.models.father.Father;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="userSecurity")
public class UserSecurity extends Father {
    private Date pincode_created;
    private String pincode;

    private Date emailCode_created;
    private String emailcode;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    protected User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getPincode_created() {
        return pincode_created;
    }

    public void setPincode_created(Date pincode_created) {
        this.pincode_created = pincode_created;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public Date getEmailCode_created() {
        return emailCode_created;
    }

    public void setEmailCode_created(Date emailCode_created) {
        this.emailCode_created = emailCode_created;
    }

    public String getEmailcode() {
        return emailcode;
    }

    public void setEmailcode(String emailcode) {
        this.emailcode = emailcode;
    }
}
