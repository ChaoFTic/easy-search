package com.chaoftic.easysearch.db.dao;

import com.chaoftic.easysearch.db.models.Blogger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@SuppressWarnings("unchecked")
@Repository
public interface BloggerDao extends JpaRepository<Blogger, Integer> {
    Blogger save(Blogger blogger);

    Page<Blogger> findAll(Pageable pageable);

    Optional<Blogger> findById(Integer integer);

    @Override
    void delete(Blogger blogger);
}
