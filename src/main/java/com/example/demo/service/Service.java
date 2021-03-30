package com.example.demo.service;

import com.example.demo.models.requests.RegistrationUser;
import com.example.demo.models.user.User;

public interface Service {
    User registration(RegistrationUser registrationUser);
}
