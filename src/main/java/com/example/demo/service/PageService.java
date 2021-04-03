package com.example.demo.service;

import com.example.demo.models.Page;
import com.example.demo.models.requests.CreatePage;
import com.example.demo.models.user.POST;
import com.example.demo.models.user.User;

import java.util.List;

public interface PageService {
    Page createPage(User user, CreatePage createPage);

    List<Page> findPages(String pagename);
}
