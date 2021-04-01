package com.example.demo.repository;

import com.example.demo.models.user.POST;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<POST,Long>{

}
