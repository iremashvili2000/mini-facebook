package com.example.demo.repository;

import com.example.demo.models.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PageRepository extends JpaRepository<Page,Long> {

    Page findByPagename(String name);


    List<Page> findAllByPagenameIsLike(String name);
}
