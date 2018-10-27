package com.chaoftic.easysearch.db.models;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {
    private int id;

    public int getId() {
        return this.id;
    }

}
