package com.chaoftic.easysearch.db.services;

import com.chaoftic.easysearch.db.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    Page<Post> findAll(Pageable pageable);

    List<Post> findAll();
}
