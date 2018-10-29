package com.chaoftic.easysearch.index.heap;

import java.util.*;

public class MaxHeap<T extends Comparable> {
    private List<T> data;
    private Integer ordered;

    public MaxHeap(Collection<T> objects) {
        data = new LinkedList<>(objects);
        this.ordered = 0;
    }

    public Set<T> topK(Integer k) {
        sort(k);
        return new HashSet<>(data.subList(data.size() - k, data.size()));
    }

    private void sort(Integer k) {
        if (ordered >= k) {
            return;
        }
        while (ordered < k) {
            heapify(0);
            swap(0, data.size()-1 - ordered);
            ordered++;
        }
    }

    @SuppressWarnings("unchecked")
    private void heapify(Integer current) {
        if (current >= data.size()) {
            return;
        }
        Integer left = left(current);
        Integer right = right(current);
        if (left >= data.size() || right >= data.size()) {
            return;
        }
        Integer max = max(left, right);

        if (data.get(max).compareTo(data.get(current)) > 0) {
            swap(max, current);
            while (max(current, parent(current)).equals(current) && current > 0) {
                swap(current, parent(current));
                current = parent(current);
            }
        }
        heapify(left);
        heapify(right);
    }

    private void swap(int v1, int v2) {
        T curr = data.get(v2);
        data.set(v2, data.get(v1));
        data.set(v1, curr);
    }

    private Integer left(Integer i) {
        return 2 * i + 1;
    }

    private Integer right(Integer i) {
        return 2 * i + 2;
    }

    private Integer parent(Integer i) {
        return (i - 1) / 2;
    }


    @SuppressWarnings("unchecked")
    private Integer max(Integer t1, Integer t2) {
        if (data.get(t2).compareTo(data.get(t1)) >= 0) {
            return t2;
        } else {
            return t1;
        }
    }
}
