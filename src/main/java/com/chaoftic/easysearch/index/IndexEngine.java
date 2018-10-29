package com.chaoftic.easysearch.index;

import com.chaoftic.easysearch.index.document.Document;
import com.chaoftic.easysearch.index.document.QueryDocument;
import com.chaoftic.easysearch.index.document.Similarity;
import com.chaoftic.easysearch.index.inverted.InvertedIndex;

import java.io.IOException;
import java.util.*;

public class IndexEngine {
    /**
     * 该索引对应的文档集合
     */
    private Set<Document> documents;

    /**
     * 倒排索引
     */
    private InvertedIndex invertedIndex;

    /**
     * 分词工具
     */
    private Segment segment = new Segment();


    // common functions

    /**
     * 调用invertedIndex类中的create方法更新indexEngine类中的invertedIndex属性
     *
     * @return 倒排索引
     */
    public InvertedIndex createInvertedIndex() {
        this.invertedIndex = new InvertedIndex();
        this.invertedIndex.create(documents);
        return this.invertedIndex;
    }

    // getter & setter
    public Set<Document> getDocuments() {
        return documents;
    }

    public IndexEngine setDocuments(Collection<Document> documents) {
        this.documents = new HashSet<>(documents);
        return this;
    }

    public InvertedIndex getInvertedIndex() {
        return invertedIndex;
    }

    public Set<Integer> vectorSearch(String query, Integer k) throws IOException {
        QueryDocument queryDocument = segment.enCut(query);
        List<Similarity> result = new LinkedList<>();
        for (Document document : documents) {
            Double similarity = queryDocument.getSimilarity(document);
            Similarity sim = new Similarity(similarity, document.getId());
            result.add(sim);
        }
        queryDocument.setSimilarityList(result);
        return queryDocument.selectTopK(k);
    }

    private List<Similarity> getSimilarities(String queryString) {
        List<Similarity> similarityList = new LinkedList<>();
        QueryDocument queryDoc;
        try {
            Segment segment = new Segment();
            queryDoc = segment.enCut(queryString);
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
        for (Document document : documents) {
            Double similarity = queryDoc.getSimilarity(document);
            Similarity sim = new Similarity(similarity, document.getId());
            similarityList.add(sim);
        }
        return similarityList;
    }
}
