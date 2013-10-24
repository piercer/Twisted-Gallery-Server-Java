package com.dz015.tg.item.model;

import java.util.HashSet;
import java.util.Set;

public class TGItem {

    private final long id;
    private final Set<TGMetaData> metaData;
    private final boolean published;

    private TGItem(ItemBuilder builder) {
        id = builder.id;
        metaData = builder.metaData;
        published = builder.published;
    }

    public long getId() {
        return id;
    }

    public Set<TGMetaData> getMetaData() {
        return metaData;
    }

    public boolean isPublished() {
        return published;
    }

    public static class ItemBuilder {

        private final Set<TGMetaData> metaData;
        private final long id;
        private boolean published;

        public ItemBuilder(long id) {
            this.id = id;
            metaData = new HashSet<>();
        }

        public ItemBuilder metaData(String name, String value) {
            metaData.add(new TGMetaData.MetaDataBuilder(name, value).build());
            return this;
        }

        public ItemBuilder published(boolean value) {
            published = value;
            return this;
        }

        public TGItem build() {
            return new TGItem(this);
        }

    }
}
