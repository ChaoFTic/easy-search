package com.chaoftic.easysearch.db.services.impl;

import com.chaoftic.easysearch.db.dao.BloggerDao;
import com.chaoftic.easysearch.db.models.Blogger;
import com.chaoftic.easysearch.db.services.BloggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BloggerServiceImpl implements BloggerService {
    private final BloggerDao bloggerDao;

    @Autowired
    public BloggerServiceImpl(BloggerDao bloggerDao) {
        this.bloggerDao = bloggerDao;
    }

    @Override
    public Blogger save(Blogger blogger) {
        return bloggerDao.save(blogger);
    }

    @Override
    public Page<Blogger> findAll(Pageable pageable) {
        return bloggerDao.findAll(pageable);
    }

    @Override
    public Optional<Blogger> findById(Integer id) {
        return bloggerDao.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        bloggerDao.deleteById(id);
    }

    @Override
    public void delete(Blogger blogger) {
        bloggerDao.delete(blogger);
    }
}
