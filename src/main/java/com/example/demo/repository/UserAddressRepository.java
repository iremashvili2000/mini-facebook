package com.example.demo.repository;

import com.example.demo.models.user.Useraddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressRepository extends JpaRepository<Useraddress,Long> {
}
