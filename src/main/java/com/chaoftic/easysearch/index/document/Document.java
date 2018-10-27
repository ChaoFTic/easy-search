package com.chaoftic.easysearch.index.document;

import java.util.HashMap;
import java.util.Map;

public class Document {
    /**
     * 文档id
     */
    private Integer id;
    /**
     * 文档的<词项，词项信息>集合
     */
    private Map<String, DocItem> termsMap = new HashMap<>();
    /**
     * 文档向量的长度
     * 在新建文档的时候建立
     */
    private Double vectorLength;

    // constructor


    // common function

    /**
     * @return 文档的《词项,词频》向量
     */
    public Map<String, Integer> getVector() {
        Map<String, Integer> result = new HashMap<>();
        for (Map.Entry<String, DocItem> termEntry : this.termsMap.entrySet()) {
            result.put(termEntry.getKey(), termEntry.getValue().getTf());
        }
        return result;
    }

    public void add(String word, DocItem docItem) {
        if (this.termsMap.containsKey(word)) {
            this.termsMap.get(word).merge(docItem);
        } else {
            this.termsMap.put(word, docItem);
        }
        updateVectorLength();
    }

    private void updateVectorLength() {
        double length = 0.0;
        Map<String, Integer> vector = getVector();
        for (Map.Entry<String, Integer> vectorEntry : vector.entrySet()) {
            length += Math.pow(vectorEntry.getValue(), 2);
        }
        this.vectorLength = Math.sqrt(length);
    }

    // getter & setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<String, DocItem> getTermsMap() {
        return termsMap;
    }

    public void setTermsMap(Map<String, DocItem> termsMap) {
        this.termsMap = termsMap;
    }

    public Double getVectorLenth() {
        return vectorLength;
    }

}
