package com.example.demo.repository;

import com.example.demo.models.user.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSecurityRepository extends JpaRepository<UserSecurity,Long> {


}
