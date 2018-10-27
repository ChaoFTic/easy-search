package com.chaoftic.easysearch.index.inverted;

import com.chaoftic.easysearch.index.document.DocItem;

import java.util.*;

/**
 * 倒排索引中的一行
 */
public class IndexTerm {

    /**
     * 对应词项的文档列表
     */
    private Set<DocItem> docList = new HashSet<>();

    // constructor
    public IndexTerm ( DocItem docItem) {
        this.docList.add(docItem);
    }

    // common function

    /**
     * @return 词项在几个文档中出现
     */
    public Integer getDf() {
        return docList.size();
    }

    public void append(DocItem docItem) {
        this.docList.add(docItem);
    }

    // getter & setter

    public Set<DocItem> getDocList() {
        return docList;
    }

    public void setDocList(Set<DocItem> docList) {
        this.docList = docList;
    }


}
