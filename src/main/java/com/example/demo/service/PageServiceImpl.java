package com.example.demo.service;

import com.example.demo.exception.BadDataException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.models.Page;
import com.example.demo.models.requests.CreatePage;
import com.example.demo.models.user.POST;
import com.example.demo.models.user.User;
import com.example.demo.repository.PageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageServiceImpl implements PageService {
    private PageRepository pageRepository;

    public PageServiceImpl(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    @Override
    public Page createPage(User user, CreatePage createPage) {

        if(createPage.getDescription()==null || createPage.getTitle()==null){
            throw new BadDataException("description/title line is empty");
        }
        Page page=new Page();
        page.setPagename(createPage.getTitle());
        page.setDescription(createPage.getDescription());
        page.setAdmin(user);
        pageRepository.save(page);
        return page;
    }


    @Override
    public List<Page> findPages(String pagename) {
     List<Page>pageList=pageRepository.findAllByPagenameIsLike(pagename);
     if(pageList.isEmpty()){
         throw new NotFoundException("page or pages dont found");
     }
     return pageList;
    }


}
