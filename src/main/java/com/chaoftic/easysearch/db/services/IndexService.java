package com.chaoftic.easysearch.db.services;

import com.chaoftic.easysearch.db.models.BaseEntity;
import com.chaoftic.easysearch.db.models.Post;
import com.chaoftic.easysearch.index.document.Similarity;
import com.chaoftic.easysearch.index.inverted.InvertedIndex;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public interface IndexService {

    InvertedIndex getInvertedIndex(Collection<BaseEntity> entities);

    List<BaseEntity> vectorSearch(String query, Integer k);
}
