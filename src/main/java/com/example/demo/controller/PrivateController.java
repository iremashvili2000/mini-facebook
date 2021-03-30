package com.example.demo.controller;

import com.example.demo.models.Message;
import com.example.demo.models.Notification;
import com.example.demo.models.requests.SendMessage;
import com.example.demo.models.user.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/private")
public class PrivateController {
    private final UserRepository userRepository;
    private final UserService userService;

    public PrivateController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }


    @RequestMapping(value = "/chat",method = RequestMethod.POST)
    public Message sendMessage(@AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody SendMessage sendMessage){
        User user=(User)userRepository.findByEmail(userDetails.getUsername());
       return userService.sendMessage(user,sendMessage);

    }

    @RequestMapping(value = "/me",method = RequestMethod.POST)
    public User getUser(@AuthenticationPrincipal UserDetails userDetails){
        User user=(User)userRepository.findByEmail(userDetails.getUsername());
        return user;
    }

    @RequestMapping(value = "/me/notification",method = RequestMethod.POST)
    public List<Notification> notificationList(@AuthenticationPrincipal UserDetails userDetails){
        User user=(User)userRepository.findByEmail(userDetails.getUsername());
      return  userService.getNotificationList(user);
    }







}
