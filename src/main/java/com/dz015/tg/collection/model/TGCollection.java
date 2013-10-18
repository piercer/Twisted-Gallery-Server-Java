package com.dz015.tg.collection.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="collection")
public class TGCollection {

    private final String name;
    private final String description;
    private final long id;
    private final int nItems;
    private final int previewId;


    private TGCollection(CollectionBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.nItems = builder.nItems;
        this.previewId = builder.previewId;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public int getnItems() {
        return nItems;
    }

    public String getDescription() {
        return description;
    }

    public static class CollectionBuilder {

        private final long id;
        private final String name;
        private String description;
        private int nItems;
        private int previewId;

        public CollectionBuilder(long id, String name) {
            this.id = id;
            this.name = name;
        }

        public CollectionBuilder description(String val) {
            description = val;
            return this;
        }

        public CollectionBuilder nItems(int val) {
            nItems = val;
            return this;
        }

        public CollectionBuilder previewId(int val) {
            previewId = val;
            return this;
        }

        public TGCollection build() {
            return new TGCollection(this);
        }
    }
}
