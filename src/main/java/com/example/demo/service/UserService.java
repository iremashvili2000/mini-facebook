package com.example.demo.service;

import com.example.demo.models.Message;
import com.example.demo.models.Notification;
import com.example.demo.models.requests.SendMessage;
import com.example.demo.models.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService extends UserDetailsService {


    Message sendMessage(User user, SendMessage sendMessage);

    List<Notification> getNotificationList(User user);

    List<Message> sentMessages(User user);

    List<Message> recivesMessages(User user);

    List<Message> deletereciveMessages(User user);

    List<Message> deleteSentMessages(User user);
}
