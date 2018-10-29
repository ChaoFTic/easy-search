package com.chaoftic.easysearch.index.document;

import com.chaoftic.easysearch.index.Segment;
import com.chaoftic.easysearch.index.heap.MaxHeap;

import java.io.IOException;
import java.util.*;


public class QueryDocument extends Document {
    private List<Similarity> similarityList = new LinkedList<>();

    // constructor

    public Double getSimilarity(Document document) {
        Map<String, Integer> v1 = this.getVector();
        Map<String, Integer> v2 = document.getVector();
        Set<String> common = v1.keySet();
        Set<String> k2 = v2.keySet();
        common.retainAll(k2);

        Double result = 0.0;
        for (String word : common) {
            result += v1.get(word) * v2.get(word);
        }
        return result / document.getVectorLength();
    }

    /**
     * 在similarityList 用堆排序的方法找出最相近的k个结果
     *
     * @param k Integer
     * @return Set
     */
    public Set<Integer> selectTopK(Integer k) {
        Set<Integer> result = new HashSet<>();
        Set<Similarity> topK = new MaxHeap<>(similarityList).topK(k);
        topK.forEach(similarity -> result.add(similarity.getDocId()));
        return result;
    }

    public Set<Integer> getDocs() {
        Set<Integer> docs = new HashSet<>();
        for (Similarity similarity : similarityList) {
            docs.add(similarity.getDocId());
        }
        return docs;
    }

    public List<Similarity> getSimilarityList() {
        return similarityList;
    }

    public void setSimilarityList(List<Similarity> similarityList) {
        this.similarityList = similarityList;
    }
}
