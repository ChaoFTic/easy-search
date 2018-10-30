package com.chaoftic.easysearch.db.services.impl;

import com.chaoftic.easysearch.db.dao.PostDao;
import com.chaoftic.easysearch.db.models.BaseEntity;
import com.chaoftic.easysearch.db.models.Post;
import com.chaoftic.easysearch.db.services.IndexService;
import com.chaoftic.easysearch.index.IndexEngine;
import com.chaoftic.easysearch.index.Segment;
import com.chaoftic.easysearch.index.document.Document;
import com.chaoftic.easysearch.index.document.QueryDocument;
import com.chaoftic.easysearch.index.inverted.InvertedIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class IndexServiceImpl implements IndexService {
    private final PostDao postDao;
    private IndexEngine indexEngine = new IndexEngine();
    private Segment segment = new Segment();
    private QueryDocument queryDocument;

    @Autowired
    public IndexServiceImpl(PostDao postDao) {
        this.postDao = postDao;
    }


    @Override
    public List<BaseEntity> vectorSearch(String query, Integer k) {
        Set<Integer> docList;
        if (indexEngine.getDocuments() == null || indexEngine.getDocuments().size() <= 0) {
            LinkedList<BaseEntity> entities = new LinkedList<>(postDao.findAll());
            Set<Document> documents = segment.enCut(entities);
            indexEngine.setDocuments(documents);
        }
        try {
            docList = indexEngine.vectorSearch(query, k);
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
        return new LinkedList<>(postDao.findAllById(docList));
    }

    @Override
    public InvertedIndex getInvertedIndex(Collection<BaseEntity> entities) {

        Set<Document> documents = segment.enCut(entities);
        return indexEngine.setDocuments(documents).createInvertedIndex();
    }
}
