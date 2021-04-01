package com.example.demo.service;

import com.example.demo.models.requests.ChangePassword;
import com.example.demo.models.requests.ChangeUserInfo;
import com.example.demo.models.requests.RegistrationUser;
import com.example.demo.models.user.User;

public interface Service {
    User registration(RegistrationUser registrationUser);

    void changePassword(User user, ChangePassword changePassword);

    ChangeUserInfo changeUserInfo(User user, ChangeUserInfo changeUserInfo);
}
