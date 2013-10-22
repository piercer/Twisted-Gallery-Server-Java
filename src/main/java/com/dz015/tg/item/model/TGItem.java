package com.dz015.tg.item.model;

import java.util.HashSet;
import java.util.Set;

public class TGItem {

    private final long id;
    private final String path;
    private final Set<TGMetaData> metaData;

    private TGItem(ItemBuilder builder) {
        this.id = builder.id;
        this.path = builder.path;
        metaData = builder.metaData;
    }

    public long getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public Set<TGMetaData> getMetaData() {
        return metaData;
    }

    public static class ItemBuilder {

        private final Set<TGMetaData> metaData;
        private final long id;
        private String path;

        public ItemBuilder(long id) {
            this.id = id;
            metaData = new HashSet<>();
        }

        public ItemBuilder path(String value) {
            path = value;
            return this;
        }

        public ItemBuilder metaData(String name, String value) {
            metaData.add(new TGMetaData.MetaDataBuilder(name, value).build());
            return this;
        }

        public TGItem build() {
            return new TGItem(this);
        }

    }
}
