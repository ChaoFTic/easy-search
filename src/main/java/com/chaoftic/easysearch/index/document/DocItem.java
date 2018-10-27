package com.chaoftic.easysearch.index.document;

import java.util.HashSet;
import java.util.Set;

public class DocItem {
    private Set<Integer> startOffsets = new HashSet<>();

    // constructor
    public DocItem(Integer start) {
        this.startOffsets.add(start);
    }

    /**
     * @return 在文档中的出现次数
     */
    public Integer getTf() {
        return startOffsets.size();
    }

    public void merge(DocItem docItem) {
        this.startOffsets.addAll(docItem.startOffsets);
    }

    // getter & setter

    public Set<Integer> getStartOffsets() {
        return startOffsets;
    }

    public void setStartOffsets(Set<Integer> startOffsets) {
        this.startOffsets = startOffsets;
    }

}
