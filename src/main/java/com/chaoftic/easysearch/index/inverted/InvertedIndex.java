package com.chaoftic.easysearch.index.inverted;

import com.chaoftic.easysearch.index.document.DocItem;
import com.chaoftic.easysearch.index.document.Document;

import java.util.*;

public class InvertedIndex {
    private Map<String, IndexTerm> index;


    // common function

    /**
     * 主要功能：把原来按照文档分开的 DocItem 按照 word 重新组合 生成InvertedIndex
     *
     * @param documents Set<Document>
     */
    public void create(Set<Document> documents) {
        Map<String, IndexTerm> newIndex = new HashMap<>();

        for (Document document : documents) {

            Map<String, DocItem> termsMap = document.getTermsMap();
            for (Map.Entry<String, DocItem> itemEntry : termsMap.entrySet()) {

                if (newIndex.containsKey(itemEntry.getKey())) {
                    newIndex.get(itemEntry.getKey()).append(itemEntry.getValue());
                } else {
                    newIndex.put(itemEntry.getKey(), new IndexTerm(itemEntry.getValue()));
                }
            }
        }
        this.index = newIndex;
    }

    // getter & setter
    public List<Map<String, Object>> getIndex() {
        List<Map<String, Object>> result = new LinkedList<>();
        for (Map.Entry<String, IndexTerm> entry : index.entrySet()) {
            Map<String, Object> tmp = new HashMap<>();
            tmp.put("word", entry.getKey());
            tmp.put("docList", entry.getValue().getDocList());
            result.add(tmp);
        }
        return result;
    }

    // overwrite toString expect to beautify json format
    // debug
//    @Override
//    public String toString() {
//        return index.toString();
//    }
}
