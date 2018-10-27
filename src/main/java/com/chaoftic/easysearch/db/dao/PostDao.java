package com.chaoftic.easysearch.db.dao;

import com.chaoftic.easysearch.db.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unchecked")
@Repository
public interface PostDao extends JpaRepository<Post, Integer> {
    Post save(Post post);

    Optional<Post> findById(Integer id);

    List<Post> findAll();

    Page<Post> findAll(Pageable pageable);

    void delete(Post post);
}
