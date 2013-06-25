package com.dz015.tg.category.model;

public class TGCategory {

    private String name;
    private long id;

    public TGCategory(long id) {
        this.id = id;
        name="Category+"+id;
    }

    public String getName() {
        return name;
    }
}
