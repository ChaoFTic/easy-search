package com.chaoftic.easysearch.index;

import com.chaoftic.easysearch.db.models.BaseEntity;
import com.chaoftic.easysearch.index.document.Document;
import com.chaoftic.easysearch.index.inverted.InvertedIndex;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class IndexEngine {
    /**
     * 该索引对应的文档集合
     */
    private Set<Document> documents;

    /**
     * 倒排索引
     */
    private InvertedIndex invertedIndex;
    // common functions

    /**
     * 调用invertedIndex类中的create方法更新indexEngine类中的invertedIndex属性
     * @return 倒排索引
     */
    public InvertedIndex createInvertedIndex() {
        this.invertedIndex.create(documents);
        return this.invertedIndex;
    }


    // getter & setter
    public Set<Document> getDocuments() {
        return documents;
    }

    IndexEngine setDocuments(Collection<Document> documents) {
        this.documents = new HashSet<>(documents);
        return this;
    }

    public InvertedIndex getInvertedIndex() {
        return invertedIndex;
    }

}
