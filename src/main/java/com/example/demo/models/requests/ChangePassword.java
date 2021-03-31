package com.example.demo.models.requests;

import javax.validation.constraints.NotNull;

public class ChangePassword {
  private String password;

  private String  pass;

  private String  repass;

    public ChangePassword(String password, String pass, String repass) {
        this.password = password;
        this.pass = pass;
        this.repass = repass;
    }

    public ChangePassword() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRepass() {
        return repass;
    }

    public void setRepass(String repass) {
        this.repass = repass;
    }
}
