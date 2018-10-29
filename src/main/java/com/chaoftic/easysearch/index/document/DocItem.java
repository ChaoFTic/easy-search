package com.chaoftic.easysearch.index.document;

import java.util.HashSet;
import java.util.Set;

public class DocItem {
    private Integer docId;
    private Set<Integer> startOffsets = new HashSet<>();

    // constructor
    public DocItem(Integer docId, Integer start) {
        this.docId = docId;
        this.startOffsets.add(start);
    }

    /**
     * @return 在文档中的出现次数
     */
    public Integer getTf() {
        return startOffsets.size();
    }

    public void merge(DocItem docItem) {
        assert this.docId.equals(docItem.docId);
        this.startOffsets.addAll(docItem.startOffsets);
    }

    // getter & setter

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    public Set<Integer> getStartOffsets() {
        return startOffsets;
    }

    public void setStartOffsets(Set<Integer> startOffsets) {
        this.startOffsets = startOffsets;
    }

}
