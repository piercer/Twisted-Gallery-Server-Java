package com.dz015.tg.item.model;

public class TGMetaData {

    private final String name;
    private final String value;

    private TGMetaData(MetaDataBuilder builder) {
        this.name = builder.name;
        this.value = builder.value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public static class MetaDataBuilder {

        private final String name;
        private final String value;

        public MetaDataBuilder(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public TGMetaData build() {
            return new TGMetaData(this);
        }
    }
}
