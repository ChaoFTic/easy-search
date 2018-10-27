package com.chaoftic.easysearch.db.services;

import com.chaoftic.easysearch.db.models.Blogger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface BloggerService {
    Blogger save(Blogger blogger);

    Page<Blogger> findAll(Pageable pageable);

    Optional<Blogger> findById(Integer integer);

    void deleteById(Integer integer);

    void delete(Blogger blogger);
}
