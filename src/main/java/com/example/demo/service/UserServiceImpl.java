package com.example.demo.service;

import com.example.demo.exception.BadDataException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.models.Message;
import com.example.demo.models.Notification;
import com.example.demo.models.requests.SendMessage;
import com.example.demo.models.user.User;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;


    public UserServiceImpl(UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user= (User) userRepository.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("Dont Found "+email);
        }


        return (UserDetails) user;
    }


    @Override
    public Message sendMessage(User user, SendMessage sendMessage) {
        User user1=(User)userRepository.findByEmail(sendMessage.getReciver());
        if(user==null){
            throw new BadDataException("dont found");
        }
        Message message=new Message();
        message.setMessage(sendMessage.getMessage());
        //
        message.setMessageid("daxkx");

        messageRepository.save(message);

        System.out.println(message.getMessageid());
        Message message1=messageRepository.findByMessageid("daxkx");
        message1.setMessageid("bla");


        message1.setSender(user);
        message1.setReciver(user1);

   List<Message>messageList=user.getSender();
   if(messageList==null){
       messageList=new ArrayList<Message>();
   }
   messageList.add(message1);
    user.setSender(messageList);
    userRepository.save(user);

    List<Message> messageList1=user1.getReciver();
    if(messageList1==null){
        messageList1=new ArrayList<Message>();
    }
    messageList1.add(message1);
    user1.setReciver(messageList1);
    userRepository.save(user1);

     //


     //
        System.out.println("davitiii");
        messageRepository.save(message1);







        //

     //

     return message1;

    }

    @Override
    public List<Notification> getNotificationList(User user) {
        List<Notification> notificationList=user.getNotifications();
        if(notificationList.isEmpty()){
            throw new NotFoundException("notification dont found");
        }
        return notificationList;
    }

}
