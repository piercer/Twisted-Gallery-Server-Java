package com.dz015.tg.category.model;

import com.dz015.tg.collection.model.TGCollection;

import java.util.Collection;

public class TGCategory {

    private final String name;
    private final String description;
    private final long id;
    private final Collection<TGCategory> parents;
    private final Collection<TGCategory> subCategories;
    private final Collection<TGCollection> collections;
    private final int nCollections;
    private final int nItems;
    private final int previewId;


    private TGCategory(CategoryBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.parents = builder.parents;
        this.subCategories = builder.subCategories;
        this.collections = builder.collections;
        this.nCollections = builder.nCollections;
        this.nItems = builder.nItems;
        this.previewId = builder.previewId;
    }

    public String getName() {
        return name;
    }

    public Collection<TGCategory> getParents() {
        return parents;
    }

    public Collection<TGCategory> getSubCategories() {
        return subCategories;
    }

    public Collection<TGCollection> getCollections() {
        return collections;
    }

    public long getId() {
        return id;
    }

    public int getnCollections() {
        return nCollections;
    }

    public int getnItems() {
        return nItems;
    }

    public String getDescription() {
        return description;
    }

    public static class CategoryBuilder {

        private final long id;
        private final String name;
        private String description;
        private int nCollections;
        private int nItems;
        private int previewId;
        private Collection<TGCategory> parents;
        private Collection<TGCategory> subCategories;
        private Collection<TGCollection> collections;

        public CategoryBuilder(long id, String name) {
            this.id = id;
            this.name = name;
        }

        public CategoryBuilder parents(Collection<TGCategory> val) {
            parents = val;
            return this;
        }

        public CategoryBuilder subCategories(Collection<TGCategory> val) {
            subCategories = val;
            return this;
        }

        public CategoryBuilder collections(Collection<TGCollection> val) {
            collections = val;
            return this;
        }

        public CategoryBuilder description(String val) {
            description = val;
            return this;
        }

        public CategoryBuilder nCollections(int val) {
            nCollections = val;
            return this;
        }

        public CategoryBuilder nItems(int val) {
            nItems = val;
            return this;
        }

        public CategoryBuilder previewId(int val) {
            previewId = val;
            return this;
        }

        public TGCategory build() {
            return new TGCategory(this);
        }
    }

}
