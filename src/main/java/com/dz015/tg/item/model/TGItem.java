package com.dz015.tg.item.model;

import java.util.Set;

public class TGItem {

    private final long id;
    private final Set<TGMetaData> metaData;
    private final boolean published;
    private final String type;
    private final String server;
    private final String path;

    private TGItem(ItemBuilder builder) {
        id = builder.id;
        metaData = builder.metaData;
        published = builder.published;
        type = builder.type;
        server = builder.server;
        path = builder.path;
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

    public String getServer() {
        return server;
    }

    public String getType() {
        return type;
    }

    public String getPath() {
        return path;
    }

    public static class ItemBuilder {

        private Set<TGMetaData> metaData;
        private long id;
        private boolean published;
        private String type;
        private String server;
        private String path;

        public ItemBuilder(long id) {
            this.id = id;
        }

        public ItemBuilder withMetaData(Set<TGMetaData> value) {
            metaData = value;
            return this;
        }

        public ItemBuilder isPublished(boolean value) {
            published = value;
            return this;
        }

        public TGItem build() {
            return new TGItem(this);
        }

        public ItemBuilder fromServer(String value) {
            server = value;
            return this;
        }

        public ItemBuilder at(String value) {
            path = value;
            return this;
        }

        public ItemBuilder ofType(String value) {
            type = value;
            return this;
        }
    }
}
