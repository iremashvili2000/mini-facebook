package com.example.demo.service;

import com.example.demo.exception.BadDataException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.models.Message;
import com.example.demo.models.Notification;
import com.example.demo.models.requests.SendMessage;
import com.example.demo.models.requests.UpdateAddress;
import com.example.demo.models.requests.WritePost;
import com.example.demo.models.response.SendRequest;
import com.example.demo.models.user.*;
import com.example.demo.repository.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final FriendRequestRepository friendRequestRepository;
    private final NotificationRepository notificationRepository;
    private final UserAddressRepository userAddressRepository;
    private final PostRepository postRepository;



    public UserServiceImpl(UserRepository userRepository, MessageRepository messageRepository, FriendRequestRepository friendRequestRepository, NotificationRepository notificationRepository, UserAddressRepository userAddressRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.friendRequestRepository = friendRequestRepository;
        this.notificationRepository = notificationRepository;
        this.userAddressRepository = userAddressRepository;
        this.postRepository = postRepository;
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
        message.setMessage(Base64.getEncoder().encodeToString(sendMessage.getMessage().getBytes(StandardCharsets.UTF_8)));
        message.setCreated_at(new Date());
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

    @Override
    public List<Message> sentMessages(User user) {
      List<Message>messageList=user.getSender();
      if(messageList.isEmpty()){
          throw new NotFoundException("messages is empty");

      }
      List<Message>messages=new ArrayList<Message>();
      for(int i=0;i<messageList.size();i++){
          byte[] decodedBytes=Base64.getDecoder().decode(messageList.get(i).getMessage());
          String decodedString=new String(decodedBytes);
          messages.add(messageList.get(i));
          messages.get(i).setMessage(decodedString);
      }
      return messages;
    }

    @Override
    public List<Message> recivesMessages(User user) {
        List<Message>messageList=user.getReciver();
        List<Message>messages=new ArrayList<Message>();
        for(int i=0;i<messageList.size();i++){
            byte[] decoderbytes=Base64.getDecoder().decode(messageList.get(i).getMessage());
            String decoderstring=new String(decoderbytes);
            messages.add(messageList.get(i));
            messages.get(i).setMessage(decoderstring);
        }

        if(messageList.isEmpty()){
            throw new NotFoundException("messages is empty");
        }
        return messages;
    }

    @Override
    public List<Message> deletereciveMessages(User user) {
        List<Message> messageList=new ArrayList<Message>();
        user.setReciver(messageList);
        userRepository.save(user);
        return user.getReciver();
    }

    @Override
    public List<Message> deleteSentMessages(User user) {
        List<Message>messageList=new ArrayList<Message>();
        user.setSender(messageList);
        userRepository.save(user);
        return user.getReciver();
    }

    @Override
    public void deletesentMessage(Long number, User user) {
        if(user.getSender().isEmpty()){
            throw new NotFoundException("messages dont found");
        }
        List<Message>messageList=new ArrayList<Message>();
        for(int i=0;i<user.getSender().size();i++){
            if(!user.getSender().get(i).getId().equals(number)){
                messageList.add(user.getSender().get(i));
            }
        }
        user.setSender(messageList);
        userRepository.save(user);
    }

    @Override
    public void deletereciveMessage(Long number, User user) {
        System.out.println("muraba");
    }

    @Override
    public Notification sendRequest(User user, SendRequest sendRequest) {
        FriendRequests friendRequests=new FriendRequests();
        friendRequests.setSender(user);
        User user1=(User)userRepository.findByEmail(sendRequest.getUsername());
        friendRequests.setReciver(user1);
        friendRequests.setRequest(true);
        friendRequests.setCreated_at(new Date());
        friendRequests.setReciv(false);
        friendRequestRepository.save(friendRequests);
        Notification notification=new Notification();
        notification.setTitle("send request");
        LocalDateTime localDateTime=LocalDateTime.now(Clock.tickMillis(ZoneId.systemDefault()));

        notification.setTime(localDateTime);
        notification.setUser(user);
        notification.setMessage("you send friend request "+user1.getName()+" "+user1.getLastname());
        notificationRepository.save(notification);
        System.out.println("");
        Notification notification1=new Notification();
        LocalDateTime localDateTime1=LocalDateTime.now(Clock.tickMillis(ZoneId.systemDefault()));

        notification1.setTime(localDateTime1);
        notification1.setUser(user1);
        notification1.setMessage("you recive friend request "+user.getName()+" "+user.getLastname());
        notificationRepository.save(notification1);
        return notification;

    }

    @Override
    public List<FriendRequests> friendRequest(User user) {
       List<FriendRequests>friendRequestslist=friendRequestRepository.findAllByReciver(user);
       if(friendRequestslist.isEmpty()){
           throw new NotFoundException("friend Request is not found");
       }
       List<FriendRequests>friendRequests=new ArrayList<FriendRequests>();
       for(int i=0;i<friendRequestslist.size();i++){
           if(!friendRequestslist.get(i).isReciv()){
               friendRequests.add(friendRequestslist.get(i));
           }
       }
       if(friendRequests.isEmpty()){
           throw new NotFoundException("friendRequests is empty");
       }
       return friendRequests;
    }

    @Override
    public User reciveFriendRequest(User user, Long num) {
        List<FriendRequests>friendRequestslist=friendRequestRepository.findAllByReciver(user);
        if(friendRequestslist.isEmpty()){
            throw new NotFoundException("friend Request is not found");
        }
        List<FriendRequests>friendRequests=new ArrayList<FriendRequests>();
        for(int i=0;i<friendRequestslist.size();i++){
            if(!friendRequestslist.get(i).isReciv()){
                friendRequests.add(friendRequestslist.get(i));
            }
        }

        for(int j=0;j<friendRequests.size();j++){
            if(friendRequests.get(j).getId().equals(num)){
                friendRequests.get(j).setReciv(true);
                friendRequestRepository.save(friendRequests.get(j));
             List<User>userList=user.getFriends();
             if(userList.isEmpty()){
                 userList=new ArrayList<User>();
             }
             if(!userList.contains(friendRequests.get(j).getSender())){
                    userList.add(friendRequests.get(j).getSender());
             }
             userList.add(friendRequests.get(j).getSender());
             user.setFriends(userList);
                userRepository.save(user);
                userList=null;
             User user1=friendRequests.get(j).getSender();
             userList=user1.getFriends();
             if(userList.isEmpty()){
                    userList=new ArrayList<User>();
             }
             if(!userList.contains(user)){
                 userList.add(user);
             }
             user1.setFriends(userList);
             userRepository.save(user1);
             return user1;
            }
        }
        throw new NotFoundException("friendrequest not found");
    }

    @Override
    public void deleteFriend(User user, String email) {
            List<User> userList=user.getFriends();
            if(userList.isEmpty()){
                throw new NotFoundException("you have not friend");
            }
            User user1=(User)userRepository.findByEmail(email);

            List<User>users=new ArrayList<User>();
            for(int i=0;i<userList.size();i++){
                if(!userList.get(i).equals(user1)){
                    users.add(userList.get(i));
                }
            }
            if(users.size()==userList.size()){
                throw new NotFoundException("username dont found");
            }
            user.setFriends(users);
            userRepository.save(user);
    }

    @Override
    public UserInfo seeInfo(User user) {
        UserInfo userInfo= user.getUserInfo();
        if(userInfo==null){
            throw new NotFoundException("user info is empty");
        }
        return userInfo;
    }

    @Override
    public Useraddress updateAddress(User user, UpdateAddress updateAddress) {
        if(updateAddress.getCity()==null || updateAddress.getCountry()==null || updateAddress.getStreet()==null|| updateAddress.getHomecity()==null || updateAddress.getZipcode()==null){
            throw new BadDataException("you cant line was empty");
        }
        Useraddress useraddress=user.getAddress();
        if(useraddress==null){
            useraddress=new Useraddress();
        }

        useraddress.setUser(user);
        useraddress.setCity(updateAddress.getCity());
        useraddress.setCountry(updateAddress.getCountry());
        useraddress.setHomecity(updateAddress.getHomecity());
        useraddress.setZipcode(updateAddress.getZipcode());
        useraddress.setStreet(updateAddress.getStreet());
        userAddressRepository.save(useraddress);
        return useraddress;
    }

    @Override
    public List<User> getFriendlist(User user) {
        List<User>friends=user.getFriends();
        if(friends.isEmpty()){
            throw new NotFoundException("you have not friends");
        }
        return friends;
    }

    @Override
    public POST writePost(User user, WritePost writePost) {
        POST post=new POST();
        post.setPost(writePost.getPost());
        post.setPriv(writePost.isInfrends());
        post.setUser(user);
        postRepository.save(post);
        return post;
    }



}
