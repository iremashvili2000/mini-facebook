package com.example.demo.service;

import com.example.demo.exception.BadDataException;
import com.example.demo.models.Notification;
import com.example.demo.models.requests.ChangePassword;
import com.example.demo.models.requests.ChangeUserInfo;
import com.example.demo.models.requests.RegistrationUser;
import com.example.demo.models.user.User;
import com.example.demo.models.user.UserInfo;
import com.example.demo.repository.NotificationRepository;
import com.example.demo.repository.UserInfoRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
@org.springframework.stereotype.Service
public class ServiceImpl implements Service{
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;
    private final UserInfoRepository userInfoRepository;

    public ServiceImpl(BCryptPasswordEncoder passwordEncoder, UserRepository userRepository, NotificationRepository notificationRepository, UserInfoRepository userInfoRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    public User registration(RegistrationUser regist) {
        if(!regist.getPassword().equals(regist.getRepassword())){
            throw new BadDataException("passwords must be equal");
        }
        if(userRepository.findByEmail(regist.getEmail())!=null){
            throw new BadDataException("this email alredy used");
        }
        if(regist.getPassword().length()<6){
            throw new BadDataException("password is small");
        }
        if(regist.getPassword().isEmpty() || regist.getEmail().isEmpty() || regist.getName().isEmpty() || regist.getLastname().isEmpty() || regist.getUsername().isEmpty()){
            throw new BadDataException("please write all line ");
        }
        User user=new User();
        user.setEmail(regist.getEmail());
        user.setLastname(regist.getLastname());
        user.setName(regist.getName());
        user.setUsername(regist.getUsername());
        user.setCreated_at(new Date());
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(regist.getPassword()));
        userRepository.save(user);

        Notification notification=new Notification();
        notification.setTitle("hello");
        notification.setMessage("welcome to our site we proud of you");
        LocalDateTime localDateTime=LocalDateTime.now(Clock.tickMillis(ZoneId.systemDefault()));
        notification.setTime(localDateTime);
        notification.setSeen(false);
        notification.setUser(user);
        notificationRepository.save(notification);


        return user;
    }

    @Override
    public void changePassword(User user, ChangePassword changePassword) {
        System.out.println(changePassword.getPass()+" "+changePassword.getPassword());
        if(!passwordEncoder.matches(changePassword.getPassword(), user.getPassword())){
            throw new BadDataException("password is not correct");
        }
        if(!changePassword.getPass().equals(changePassword.getRepass())){
            throw new BadDataException("passwords is not equals");
        }

        user.setPassword(passwordEncoder.encode(changePassword.getPass()));
        userRepository.save(user);
        System.out.println("morchaa");
    }

    @Override
    public ChangeUserInfo changeUserInfo(User user, ChangeUserInfo changeUserInfo) {
        if(changeUserInfo.getBirthday()==null || changeUserInfo.getGender()==null || changeUserInfo.getEmail()==null || changeUserInfo.getPassword()==null){
            throw new BadDataException("line or lines are empty");
        }
        if(!passwordEncoder.matches(changeUserInfo.getPassword(), user.getPassword())){
            throw new BadDataException("password is not correct");
        }
        user.setEmail(changeUserInfo.getEmail());
        userRepository.save(user);
        UserInfo userInfo=user.getUserInfo();
        if(userInfo==null){
            userInfo=new UserInfo();
        }
        userInfo.setUser(user);
        userInfo.setGender(changeUserInfo.getGender());
        Date date=new Date();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = format.parse(changeUserInfo.getBirthday());
        }catch (Exception e){
            throw new BadDataException("please write date good");
        }
        userInfo.setBirthday(date);
        userRepository.save(user);
        userInfoRepository.save(userInfo);
        return changeUserInfo;

    }
}
