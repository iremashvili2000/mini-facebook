package com.example.demo.repository;

import com.example.demo.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {
 Message findByMessageid(String id);
}
