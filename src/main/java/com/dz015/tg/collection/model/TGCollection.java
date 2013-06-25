package com.dz015.tg.collection.model;

public class TGCollection {

    private String name;
    private long id;

    public TGCollection(long id) {
        this.id = id;
        name="Collection "+id;
    }

    public String getName() {
        return name;
    }
}
