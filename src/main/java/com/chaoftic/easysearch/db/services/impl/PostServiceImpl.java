package com.chaoftic.easysearch.db.services.impl;

import com.chaoftic.easysearch.db.dao.PostDao;
import com.chaoftic.easysearch.db.models.BaseEntity;
import com.chaoftic.easysearch.db.models.Post;
import com.chaoftic.easysearch.db.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostDao postDao;

    @Autowired
    public PostServiceImpl(PostDao postDao) {
        this.postDao = postDao;
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postDao.findAll(pageable);
    }

    @Override
    public List<Post> findAll() {
        return postDao.findAll();
    }
}
