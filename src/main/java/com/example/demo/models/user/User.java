package com.example.demo.models.user;

import com.example.demo.models.Message;
import com.example.demo.models.Notification;
import com.example.demo.models.father.Father;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="users")
public class User extends Father implements UserDetails {
    @Column(name="name")
    @NotNull
    private String name;
    @Column(name="lastname")
    @NotNull
    private String lastname;
    @Column(name="username")
    @NotNull
    private String username;
    @Column(name="email",unique = true)
    @Email
    private String email;
    @Column(name="password")
    @JsonIgnore
    private String password;
    @Column(name="created_at")
    @JsonIgnore
    private Date created_at;
    @Column(name = "ROLE")
    @NotNull
    @JsonIgnore
    private String role;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    protected List<Notification> notifications;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    protected List<POST> Posts;
    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    private Useraddress address;

    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    private UserInfo userInfo;

    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    private UserSecurity userSecurity;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Message> sender;

 @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
 @JsonIgnore
 private List<Message> reciver;

 @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
 @JsonIgnore
 private List<User> friends;

    public List<POST> getPosts() {
        return Posts;
    }

    public void setPosts(List<POST> posts) {
        Posts = posts;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public List<Message> getReciver() {
        return reciver;
    }

    public void setReciver(List<Message> reciver) {
        this.reciver = reciver;
    }

    public List<Message> getSender() {
        return sender;
    }

    public void setSender(List<Message> sender) {
        this.sender = sender;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public UserSecurity getUserSecurity() {
        return userSecurity;
    }

    public void setUserSecurity(UserSecurity userSecurity) {
        this.userSecurity = userSecurity;
    }

    public Useraddress getAddress() {
        return address;
    }

    public void setAddress(Useraddress address) {
        this.address = address;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void getRealUsername(String username){
        this.username=username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        String ROLE_PREFIX="ROLE_";
        list.add(new SimpleGrantedAuthority(ROLE_PREFIX+role));
        return list;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return email;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }


}
