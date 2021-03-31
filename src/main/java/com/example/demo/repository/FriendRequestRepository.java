package com.example.demo.repository;

import com.example.demo.models.user.FriendRequests;
import com.example.demo.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRequestRepository extends JpaRepository<FriendRequests,Long> {
        List<FriendRequests> findAllByReciver(User user);
}
