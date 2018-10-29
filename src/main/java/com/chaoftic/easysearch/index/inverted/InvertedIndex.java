package com.chaoftic.easysearch.index.inverted;

import com.chaoftic.easysearch.index.document.DocItem;
import com.chaoftic.easysearch.index.document.Document;

import java.util.*;

public class InvertedIndex {
    private Map<String, IndexTerm> index;


    // common function

    /**
     * 主要功能：把原来按照文档分开的 DocItem 按照 word 重新组合 生成InvertedIndex
     * @param documents Set<Document>
     */
    public void create(Set<Document> documents) {
        Map<String, IndexTerm> newIndex = new HashMap<>();

        for(Document document: documents) {

            Map<String, DocItem> termsMap = document.getTermsMap();
            for (Map.Entry<String, DocItem> itemEntry: termsMap.entrySet()) {

                if(newIndex.containsKey(itemEntry.getKey())) {
                    newIndex.get(itemEntry.getKey()).append(itemEntry.getValue());
                } else {
                    newIndex.put(itemEntry.getKey(), new IndexTerm(itemEntry.getValue()));
                }
            }
        }
        this.index = newIndex;
    }

    // getter & setter
    public Map<String, IndexTerm> getIndex() {
        return index;
    }

    // overwrite toString expect to beautify json format
    // debug
//    @Override
//    public String toString() {
//        return index.toString();
//    }
}
